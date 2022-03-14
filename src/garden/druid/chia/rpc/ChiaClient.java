package garden.druid.chia.rpc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import garden.druid.base.logging.Logger;
import garden.druid.chia.rpc.responses.ClientResponse;
import garden.druid.chia.types.Tuple;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

public class ChiaClient {

	private String host;
	private int port;
	protected static Gson gson = new GsonBuilder().serializeNulls().create();
	protected boolean debug = false;
	protected static HostnameVerifier hostVerifier;
	protected static ClientConfig config = null;
	@SerializedName(value = "ssl_certs", alternate = "sslCerts")
	protected static ConcurrentHashMap<String, SSLContext> sslCerts = new ConcurrentHashMap<String, SSLContext>();

	static {
		loadConfig();
		initSSL();
	}
	
	public ChiaClient() {
		this(null, -1);
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public ChiaClient(String host, int port) {
		if (host != null && ChiaClient.config.getTrustedHosts().contains(host)) {
			this.host = host;
		} else {
			this.host = ChiaClient.config.getHost();
		}
		if (port > 0) {
			this.port = port;
		} else {
			this.port = ChiaClient.config.getPort();
		}
	}

	private static void loadConfig() {
		try {
			String ROOT_PATH;
			if (System.getProperty("os.name").toLowerCase().contains("win")) {
				ROOT_PATH = "C:\\tomcat\\";
			} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				ROOT_PATH = "/to/be/determined";
			} else {
				ROOT_PATH = "/home/tomcat/";
			}
			String json = new String(Files.readAllBytes(Paths.get(ROOT_PATH + "/chia_client_config.json")));
			ChiaClient.config = gson.fromJson(json, ClientConfig.class);
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in ChiaClient.loadConfig", e);
			ChiaClient.config = new ClientConfig();
		}
	}

	private static void initSSL() {
		try {
			String hostPath = null;
			if (ChiaClient.config.getTrustedHosts().size() > 1) {
				for(String s : ChiaClient.config.getTrustedHosts()) {
					hostPath = "/" + s.replace(".", "_") + "/";
					try {
						loadSSLContext(hostPath, s);
					} catch (Exception e) {
						Logger.getInstance().log(Level.INFO, "Error in ChiaClient.initSSL: Failed to load cert for host: " + s, e);
					}
				}
			} else {
				hostPath = "/";
				loadSSLContext("/", "localhost");
			}
			hostVerifier = new HostnameVerifier() {
				private final HashSet<String> trustedHosts = new HashSet<String>(ChiaClient.config.getTrustedHosts());
				@Override
				public boolean verify(String hostname, SSLSession sslSession) {
					return trustedHosts.contains(hostname);
				}
			};
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in ChiaClient.initSSL", e);
			throw new RuntimeException(e);
		}
	}
	
	private static void loadSSLContext(String hostPath, String host) throws Exception {
		// CA Cert = Certificate Authority
		X509Certificate ca_crt = getCertificate(ChiaClient.config.getROOT_PATH() + hostPath + ChiaClient.config.getCa_crt_path());

		// This is the Public CRT for the PrivateKey
		X509Certificate crt = getCertificate(ChiaClient.config.getROOT_PATH() + hostPath + ChiaClient.config.getCrt_path());

		// Private key used to encrypt communications
		PrivateKey key = getPrivateKey(ChiaClient.config.getROOT_PATH() + hostPath + ChiaClient.config.getKey_path());

		// Set up the RSA Keystore
		KeyStore keyKS = KeyStore.getInstance(KeyStore.getDefaultType());
		keyKS.load(null, null);
		keyKS.setKeyEntry("main", key, "".toCharArray(), new Certificate[] { crt, ca_crt });

		// Set up the Certificate Chain Keystore

		KeyStore trustKS = KeyStore.getInstance(KeyStore.getDefaultType());
		trustKS.load(null, null);
		trustKS.setCertificateEntry(Integer.toString(1), ca_crt);
		trustKS.setCertificateEntry(Integer.toString(2), ca_crt);

		// Java Factory stuff
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmf.init(keyKS, "".toCharArray());
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		tmf.init(trustKS);
		// Create the SSL Context
		SSLContext sslContext = SSLContext.getInstance("TLS");
		// Add the custom KeyFiles and Certificates
		sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		//Since we are using private IP ranges for a chia domain we need to specify that these hosts are allowed for the given SSL Certificates
		sslCerts.put(host, sslContext);
	}

	private static X509Certificate getCertificate(String filename) throws Exception {
		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
		ByteArrayInputStream bis = new ByteArrayInputStream(keyBytes);
		return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(bis);
	}

	private static PrivateKey getPrivateKey(String filename) throws Exception {
		String sKey = new String(Files.readAllBytes(Paths.get(filename)));
		sKey = sKey.replace("-----BEGIN RSA PRIVATE KEY-----", "");
		sKey = sKey.replace("-----END RSA PRIVATE KEY-----", "");
		sKey = sKey.replace("\n", "");
		sKey = sKey.trim();
		Security.addProvider(new BouncyCastleProvider());
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(sKey));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public <K> ClientResponse<K> get(String url, byte[] requestBody, Class<K> cls) {
		ClientResponse<K> resp = new ClientResponse<K>();
		byte[] byteData = null;
		try {
			Tuple<Integer, byte[]> result = connect("GET", url, requestBody);
			int status = result.getFirst();
			byteData = result.getSecond();
			if (byteData != null && status == HttpURLConnection.HTTP_OK) {
				try {
					K dataObj = gson.fromJson(new String(byteData), cls);
					resp.setData(dataObj);
				} catch (Exception e) {
					resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
					resp.setMessage(e.getMessage() + new String(byteData));
				}
			} else {
				resp.setStatus(status);
				resp.setMessage(new String(byteData));
			}
		} catch (Exception e) {
			resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	public <K> ClientResponse<K> post(String url, byte[] requestBody, Class<K> cls) {
		ClientResponse<K> resp = new ClientResponse<K>();
		byte[] byteData = null;
		try {
			Tuple<Integer, byte[]> result = connect("POST", url, requestBody);
			int status = result.getFirst();
			byteData = result.getSecond();
			if (byteData != null && status == HttpURLConnection.HTTP_OK) {
				try {
					String jsonStr = new String(byteData);
					K dataObj = gson.fromJson(jsonStr, cls);
					resp.setData(dataObj);
				} catch (Exception e) {
					e.printStackTrace();
					resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
					resp.setMessage(e.getMessage() + new String(byteData));
				}
			} else {
				resp.setStatus(status);
				resp.setMessage(new String(byteData));
			}
		} catch (Exception e) {
			resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	public <K> ClientResponse<K> put(String url, byte[] requestBody, Class<K> cls) {
		ClientResponse<K> resp = new ClientResponse<K>();
		byte[] byteData = null;
		try {
			Tuple<Integer, byte[]> result = connect("PUT", url, requestBody);
			int status = result.getFirst();
			byteData = result.getSecond();
			if (byteData != null && status == HttpURLConnection.HTTP_OK) {
				try {
					K dataObj = gson.fromJson(new String(byteData), cls);
					resp.setData(dataObj);
				} catch (Exception e) {
					resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
					resp.setMessage(e.getMessage() + new String(byteData));
				}
			} else {
				resp.setStatus(status);
				resp.setMessage(new String(byteData));
			}
		} catch (Exception e) {
			resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	public <K> ClientResponse<K> delete(String url, byte[] requestBody, Class<K> cls) {
		ClientResponse<K> resp = new ClientResponse<K>();
		byte[] byteData = null;
		try {
			Tuple<Integer, byte[]> result = connect("DELETE", url, requestBody);
			int status = result.getFirst();
			byteData = result.getSecond();
			if (byteData != null && status == HttpURLConnection.HTTP_OK) {
				try {
					K dataObj = gson.fromJson(new String(byteData), cls);
					resp.setData(dataObj);
				} catch (Exception e) {
					resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
					resp.setMessage(e.getMessage() + new String(byteData));
				}
			} else {
				resp.setStatus(status);
				resp.setMessage(new String(byteData));
			}
		} catch (Exception e) {
			resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	private Tuple<Integer, byte[]> connect(String method, String url, byte[] requestBody) {
		URI uri;
		HttpsURLConnection connection = null;
		byte[] rtn = new byte[0];
		int status = -1;
		try {
			uri = new URI(("https://" + this.host + ":" + this.port + url));
			connection = (HttpsURLConnection) uri.toURL().openConnection();
			connection.setSSLSocketFactory(sslCerts.get(this.host).getSocketFactory());
			connection.setHostnameVerifier(hostVerifier);
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			if (connection.getRequestProperty("Content-Type") == null) {
				connection.setDoOutput(false);
				connection.connect();
			} else if (requestBody != null && requestBody.length > 0) {
				connection.setDoOutput(true);
				try (OutputStream os = connection.getOutputStream()) {
					os.write(requestBody, 0, requestBody.length);
					os.flush();
				}
			} else {
				connection.connect();
			}
			// Get response
			status = connection.getResponseCode();
			InputStream rd = null;
			try {
				rd = connection.getInputStream();
			} catch (Exception e) {
				rd = connection.getErrorStream();
			}
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int read = 0;
			while ((read = rd.read(buffer)) >= 0) {
				byteOutput.write(buffer, 0, read);
			}
			rtn = byteOutput.toByteArray();
			if (debug) {
				if(rtn.length <= 2048) {
					Logger.getInstance().log(Level.FINE, new String(rtn));
				} else {
					Logger.getInstance().log(Level.FINE, "Response too Long, Truncating: " + new String(Arrays.copyOfRange(rtn, 0, 2048)));
				}
			}
			byteOutput.close();
			rd.close();
		} catch (Exception e) {
			status = HttpURLConnection.HTTP_INTERNAL_ERROR;
			Logger.getInstance().log(Level.SEVERE, "Error in ChiaClient.connect", e);
		} finally {
			try {
				if (connection != null) connection.disconnect();
			} catch (Exception e) {
			}
		}
		return new Tuple<Integer, byte[]>(status, rtn);
	}
}
