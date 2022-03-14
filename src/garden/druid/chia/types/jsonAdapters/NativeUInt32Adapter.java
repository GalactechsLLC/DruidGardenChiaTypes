package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt32;

import java.io.IOException;

public class NativeUInt32Adapter extends TypeAdapter<NativeUInt32> {

	@Override
	public NativeUInt32 read(JsonReader reader) throws IOException {
		return new NativeUInt32().fromString(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt32 integer) throws IOException {
		writer.value(integer.toString());
	}
}