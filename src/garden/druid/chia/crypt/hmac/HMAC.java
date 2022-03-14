package garden.druid.chia.crypt.hmac;

import garden.druid.base.logging.Logger;
import garden.druid.chia.types.bytes.Bytes;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.util.logging.Level;

public class HMAC {

	private final static int BLOCK_SIZE = 32;

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String HMAC_SHA512 = "HmacSHA512";
	
	public static byte[] extract(byte[] salt, byte[] ikm) {
		return hmac256(salt, ikm);
	}
	
	public static byte[] hmac256(byte[] salt, byte[] ikm) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(salt, HMAC_SHA256);
	    Mac mac;
		try {
			mac = Mac.getInstance(HMAC_SHA256);
		    mac.init(secretKeySpec);
		    return mac.doFinal(ikm);
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in HMAC.hmac256", e);
		} 
		return null;
	}
	
	public static byte[] hmac512(byte[] salt, byte[] ikm) {
		SecretKeySpec secretKeySpec = new SecretKeySpec(salt, HMAC_SHA512);
	    Mac mac;
		try {
			mac = Mac.getInstance(HMAC_SHA512);
		    mac.init(secretKeySpec);
		    return mac.doFinal(ikm);
		} catch (Exception e) {
			Logger.getInstance().log(Level.SEVERE, "Error in HMAC.hmac512", e);
		} 
		return null;
	}
	
	public static byte[] pbkdf2_hmac(byte[] salt, byte[] password, int iters) {
		PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA512Digest());
		gen.init(password, salt, iters);
		byte[] dk = ((KeyParameter) gen.generateDerivedParameters(512)).getKey();
		return dk;
	}
	
	public static byte[] expand(int L, byte[] prk, byte[] info) {
	    int N = (int) Math.ceil(L / BLOCK_SIZE);
	    int bytes_written = 0;
	    ByteBuffer buffer = ByteBuffer.allocate(L);
	    byte[] tmp = null;
	    for(int i = 1; i <= N+1; i++) {
	        if(i == 1) {
	        	tmp = hmac256(prk, Bytes.add(info, new byte[]{1}));
	        } else {
	        	tmp = hmac256(prk, Bytes.add(Bytes.add(tmp,info),new byte[]{(byte)i}));
	        }
	        
	        int to_write = L - bytes_written;
	        if(to_write > BLOCK_SIZE) {
	            to_write = BLOCK_SIZE;
	        }
	        buffer.put(tmp, 0, to_write);
	        bytes_written += to_write;
	    }
	    buffer.flip();
	    assert buffer.limit() == L;
	    return buffer.array();
	}
	
	public static byte[] extract_expand(int L, byte[] key, byte[] salt, byte[] info) {
	    byte[] prk = extract(salt, key);
	    return expand(L, prk, info);
	}
}
