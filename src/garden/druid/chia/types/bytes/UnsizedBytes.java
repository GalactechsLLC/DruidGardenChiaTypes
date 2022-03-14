package garden.druid.chia.types.bytes;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.UnsizedBytesAdapter;

import java.nio.charset.StandardCharsets;

@JsonAdapter(UnsizedBytesAdapter.class)
public class UnsizedBytes extends Bytes implements Comparable<UnsizedBytes> {

	private final String asString;

	public UnsizedBytes(String hexString) {
		this.bytes = parseHexBinary(hexString);
		this.asString = saveString();
	}

	public UnsizedBytes(byte[] bytes) {
		this.bytes = bytes;
		this.asString = saveString();
	}

	private String saveString() {
		if(this.bytes.length > 1 && this.bytes.length%2 == 0 ) {
			return "0x" + printHexBinary(this.bytes).toLowerCase();
		} else if(this.bytes.length == 1) {
			return String.format("%02X", this.bytes[0]);
		} else if(this.bytes.length > 0) {
			return new String(this.bytes, StandardCharsets.UTF_8);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return this.asString;
	}

	public byte[] getBytes() {
		return this.bytes;
	}
	
	public int size() {
		return this.bytes.length;
	}

	@Override
	public int compareTo(UnsizedBytes o) {
		return this.toString().compareTo(o.toString());
	}

	@Override
	public boolean equals(Object o) {
		return this.toString().compareTo(o.toString()) == 0 && o instanceof UnsizedBytes;
	}
}
