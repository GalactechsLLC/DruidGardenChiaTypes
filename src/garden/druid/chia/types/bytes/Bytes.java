package garden.druid.chia.types.bytes;

import java.math.BigInteger;

public abstract class Bytes {

	protected byte[] bytes = new byte[0];

	public static byte[] parseHexBinary(String input) {
		if (input == null) {
			return new byte[0];
		}
		if (input.startsWith("0x")) {
			input = input.substring(2);
		}
		final int len = input.length();
		if (len % 2 != 0) {
			throw new IllegalArgumentException("hexBinary needs to be even-length: " + input);
		}
		byte[] out = new byte[len / 2];

		for (int i = 0; i < len; i += 2) {
			int h = hexToBin(input.charAt(i));
			int l = hexToBin(input.charAt(i + 1));
			if (h == -1 || l == -1) {
				throw new IllegalArgumentException("contains illegal character for hexBinary: " + input);
			}
			out[i / 2] = (byte) (h * 16 + l);
		}
		return out;
	}
	
    @Override
    public int hashCode() {
        return new BigInteger(1, bytes).intValue();
    }

	public static int hexToBin(char ch) {
		if ('0' <= ch && ch <= '9')
			return ch - '0';
		if ('A' <= ch && ch <= 'F')
			return ch - 'A' + 10;
		if ('a' <= ch && ch <= 'f')
			return ch - 'a' + 10;
		return -1;
	}

	private static final char[] hexCode = "0123456789ABCDEF".toCharArray();

	public static String printHexBinary(byte[] data) {
		StringBuilder r = new StringBuilder(data.length * 2);
		for (byte b : data) {
			r.append(hexCode[(b >> 4) & 0xF]);
			r.append(hexCode[(b & 0xF)]);
		}
		return r.toString();
	}
	
	public static byte[] add(final byte[] first, final byte[] second) {
		byte[] sum = new byte[first.length + second.length];
		System.arraycopy(first, 0, sum, 0, first.length);
		System.arraycopy(second, 0, sum, first.length, second.length);
		return sum;
	}
	
	public static byte[] multiply(final byte[] bytes, int times) {
		byte[] val = new byte[bytes.length * times];
		for(int i = 0; i < val.length; i++) {
			val[i] = bytes[i%bytes.length];
		}
		return val;
	}
}
