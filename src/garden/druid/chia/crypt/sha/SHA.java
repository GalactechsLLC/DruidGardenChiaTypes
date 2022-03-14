package garden.druid.chia.crypt.sha;

import garden.druid.base.logging.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;

public class SHA {
	
	public static byte[] hash256(String s) {
	    return hash256(s.getBytes(StandardCharsets.UTF_8));
	}	
		
	public static byte[] hash256(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			Logger.getInstance().log(Level.SEVERE, "Error in SHA.hash256", e);
		}
	    return md.digest(bytes);
	}
	
	public static byte[] hash512(String s) {
	    return hash256(s.getBytes(StandardCharsets.UTF_8));
	}	
		
	public static byte[] hash512(byte[] bytes) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			Logger.getInstance().log(Level.SEVERE, "Error in SHA.hash512", e);
		}
	    return md.digest(bytes);
	}
}
