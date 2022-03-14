package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.bytes.Bytes48;

import java.io.IOException;

public class Bytes48Adapter extends TypeAdapter<Bytes48> {

	@Override
	public Bytes48 read(JsonReader reader) throws IOException {
		return new Bytes48(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, Bytes48 bytes48) throws IOException {
		writer.value(bytes48.toString());
	}

}
