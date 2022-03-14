package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt256;

import java.io.IOException;

public class NativeUInt256Adapter extends TypeAdapter<NativeUInt256> {

	@Override
	public NativeUInt256 read(JsonReader reader) throws IOException {
		return new NativeUInt256().fromString(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt256 integer) throws IOException {
		writer.value(integer.toBigInteger());
	}
}