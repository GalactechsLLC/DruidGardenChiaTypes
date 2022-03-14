package garden.druid.chia.types.bytes;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.Bytes100Adapter;

@JsonAdapter(Bytes100Adapter.class)
public class Bytes100 extends Bytes implements Comparable<Bytes100> {

	private final String asString;

	public Bytes100(String hexString) {
		byte[] asBytes = parseHexBinary(hexString);
		if (asBytes.length == 100) {
			this.bytes = asBytes;
			this.asString = saveString();
		} else {
			throw new IllegalArgumentException("Invalid byte Length for Bytes100: " + asBytes.length);
		}
	}

	public Bytes100(byte[] bytes) {
		if (bytes == null || bytes.length != 100) {
			throw new IllegalArgumentException("Invalid byte Length for Bytes100: " + (bytes == null ? 0 : bytes.length));
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
	public int compareTo(Bytes100 o) {
		return this.toString().compareTo(o.toString());
	}

	public boolean equals(Bytes100 o) {
		return this.toString().compareTo(o.toString()) == 0;
	}

}
