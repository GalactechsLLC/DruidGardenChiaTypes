package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.bytes.Bytes100;

import java.io.IOException;

public class Bytes100Adapter extends TypeAdapter<Bytes100> {

	@Override
	public Bytes100 read(JsonReader reader) throws IOException {
		return new Bytes100(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, Bytes100 bytes100) throws IOException {
		writer.value(bytes100.toString());
	}
}
