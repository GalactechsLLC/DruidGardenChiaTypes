package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.bytes.Bytes32;

import java.io.IOException;

public class Bytes32Adapter extends TypeAdapter<Bytes32> {

	@Override
	public Bytes32 read(JsonReader reader) throws IOException {
		return new Bytes32(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, Bytes32 bytes32) throws IOException {
		writer.value(bytes32.toString());
	}

}
