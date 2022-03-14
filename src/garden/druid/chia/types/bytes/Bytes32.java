package garden.druid.chia.types.bytes;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.Bytes32Adapter;

@JsonAdapter(Bytes32Adapter.class)
public class Bytes32 extends Bytes implements Comparable<Bytes32> {

	private final String asString;

	public Bytes32(String hexString) {
		byte[] asBytes = parseHexBinary(hexString);
		if (asBytes.length == 32) {
			this.bytes = asBytes;
			this.asString = saveString();
		} else {
			throw new IllegalArgumentException("Invalid byte Length for Byte32: " + asBytes.length);
		}
	}

	public Bytes32(byte[] bytes) {
		if (bytes == null || bytes.length != 32) {
			throw new IllegalArgumentException("Invalid byte Length for Byte32: " + (bytes == null ? 0 : bytes.length));
		}
		this.bytes = bytes;
		this.asString = saveString();
	}

	private String saveString() {
		return "0x" +
				printHexBinary(this.bytes).toLowerCase();
	}

	public String toString() {
		return this.asString;
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	@Override
	public int compareTo(Bytes32 o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public boolean equals(Object o) {
		return this.toString().compareTo(o.toString()) == 0 && o instanceof Bytes32;
	}

}
