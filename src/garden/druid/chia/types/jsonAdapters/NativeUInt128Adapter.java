package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt128;

import java.io.IOException;

public class NativeUInt128Adapter extends TypeAdapter<NativeUInt128> {

	@Override
	public NativeUInt128 read(JsonReader reader) throws IOException {
		return new NativeUInt128().fromString(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt128 integer) throws IOException {
		writer.value(integer.toBigInteger());
	}
}