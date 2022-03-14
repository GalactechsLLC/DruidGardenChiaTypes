package garden.druid.chia.types.bytes;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.Bytes96Adapter;

@JsonAdapter(Bytes96Adapter.class)
public class Bytes96 extends Bytes implements Comparable<Bytes96> {

	private final String asString;

	public Bytes96(String hexString) {
		byte[] asBytes = parseHexBinary(hexString);
		if (asBytes.length == 96) {
			this.bytes = asBytes;
			this.asString = saveString();
		} else {
			throw new IllegalArgumentException("Invalid byte Length for Bytes96: " + asBytes.length);
		}
	}

	public Bytes96(byte[] bytes) {
		if (bytes == null || bytes.length != 96) {
			throw new IllegalArgumentException("Invalid byte Length for Bytes96: " + (bytes == null ? 0 : bytes.length));
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
	public int compareTo(Bytes96 o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public boolean equals(Object o) {
		return this.toString().compareTo(o.toString()) == 0 && o instanceof Bytes96;
	}
}
