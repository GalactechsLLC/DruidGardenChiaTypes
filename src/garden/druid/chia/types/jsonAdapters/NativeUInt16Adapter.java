package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt16;

import java.io.IOException;

public class NativeUInt16Adapter extends TypeAdapter<NativeUInt16> {

	@Override
	public NativeUInt16 read(JsonReader reader) throws IOException {
		return new NativeUInt16(reader.nextInt());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt16 integer) throws IOException {
		writer.value(integer.toString());
	}

}