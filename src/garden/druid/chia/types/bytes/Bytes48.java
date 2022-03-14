package garden.druid.chia.types.bytes;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.Bytes48Adapter;

@JsonAdapter(Bytes48Adapter.class)
public class Bytes48 extends Bytes implements Comparable<Bytes48> {

	private final String asString;

	public Bytes48(String hexString) {
		byte[] asBytes = parseHexBinary(hexString);
		if (asBytes.length == 48) {
			this.bytes = asBytes;
			this.asString = saveString();
		} else {
			throw new IllegalArgumentException("Invalid byte Length for Bytes48: " + asBytes.length);
		}
	}

	public Bytes48(byte[] bytes) {
		if (bytes == null || bytes.length != 48) {
			throw new IllegalArgumentException("Invalid byte Length for Bytes48: " + (bytes == null ? 0 : bytes.length));
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
	public int compareTo(Bytes48 o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public boolean equals(Object o) {
		return this.toString().compareTo(o.toString()) == 0 && o instanceof Bytes48;
	}

}
