package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.ints.NativeUInt8;

import java.io.IOException;

public class NativeUInt8Adapter extends TypeAdapter<NativeUInt8> {

	@Override
	public NativeUInt8 read(JsonReader reader) throws IOException {
		return new NativeUInt8(reader.nextInt());
	}

	@Override
	public void write(JsonWriter writer, NativeUInt8 integer) throws IOException {
		writer.value(integer.toBigInteger());
	}

}
