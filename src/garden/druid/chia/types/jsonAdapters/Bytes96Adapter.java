package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.bytes.Bytes96;

import java.io.IOException;

public class Bytes96Adapter extends TypeAdapter<Bytes96> {

	@Override
	public Bytes96 read(JsonReader reader) throws IOException {
		return new Bytes96(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, Bytes96 bytes96) throws IOException {
		writer.value(bytes96.toString());
	}

}
