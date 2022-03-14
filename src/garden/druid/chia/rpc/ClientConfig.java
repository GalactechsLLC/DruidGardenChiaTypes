package garden.druid.chia.rpc;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;

public class ClientConfig {

	
	private String ROOT_PATH = "/home/chia/.chia/mainnet/config/ssl/"; //maybe adjust to be windows friendly
	@SerializedName(value = "ca_crt_path", alternate = "caCrtPath")
	private String caCrtPath = "ca/private_ca.crt";
	@SerializedName(value = "crt_path", alternate = "crtPath")
	private String crtPath = "daemon/private_daemon.crt";
	@SerializedName(value = "key_path", alternate = "keyPath")
	private String keyPath = "daemon/private_daemon.key";
	private ArrayList<String> trustedHosts = new ArrayList<String>(Arrays.asList(new String[] { "localhost" }));
	private String host = "localhost";
	private int port = 8555;

	public String getROOT_PATH() {
		return this.ROOT_PATH;
	}

	public String getCa_crt_path() {
		return this.caCrtPath;
	}

	public String getCrt_path() {
		return this.crtPath;
	}

	public String getKey_path() {
		return this.keyPath;
	}

	public ArrayList<String> getTrustedHosts() {
		return this.trustedHosts;
	}

	public String getHost() {
		return this.host;
	}

	public int getPort() {
		return this.port;
	}

}
