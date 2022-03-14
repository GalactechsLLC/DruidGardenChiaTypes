package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt64;

import java.io.IOException;

public class NativeUInt64Adapter extends TypeAdapter<NativeUInt64> {

	@Override
	public NativeUInt64 read(JsonReader reader) throws IOException {
		return new NativeUInt64().fromString(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt64 integer) throws IOException {
		writer.value(integer.toBigInteger());
	}
}