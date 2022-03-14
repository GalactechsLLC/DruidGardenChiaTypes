package garden.druid.chia.clvm.runtime;

import garden.druid.chia.types.bytes.Bytes;
import garden.druid.chia.types.bytes.UnsizedBytes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Atom{

	private byte[] bytes;

	public Atom(byte[] bytes) {
		super();
		this.bytes = bytes;
	}
	
	public Atom(UnsizedBytes bytes) {
		super();
		this.bytes = bytes.getBytes();
	}

	public Atom(BigInteger bigInt) {
		super();
		this.bytes = bigInt.toByteArray();
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public int length() {
		return this.bytes != null ? this.bytes.length : 0;
	}
	
	@Override
	public String toString() {
		if(bytes.length == 0) {
			return "0";
		} 
		if(bytes.length > 2 && bytes.length % 2 == 0 && bytes[0] == 48 && bytes[1] == 120) {
			return "0x"+Bytes.printHexBinary(Bytes.parseHexBinary(new String(this.getBytes(), StandardCharsets.UTF_8))).toLowerCase();
		}
		String rtn = null;
		try {
			rtn = asInteger().toString();
		} catch(Exception e) {
			rtn = null;
		}
		if(rtn != null) {
			return rtn;
		}
		return this.bytes.toString();
	}
	
	public BigInteger asInteger() {
		if(this.bytes.length == 0) return BigInteger.ZERO;
		return new BigInteger(this.bytes);
	}
	
	public boolean equals(Atom other) {
		if(other == null) return false;
		if(this.length() != other.length()) {
			return false;
		}
		return this.bytes.equals(other.getBytes());
	}
}
