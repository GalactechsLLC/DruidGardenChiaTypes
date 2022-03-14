package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.bytes.UnsizedBytes;

import java.io.IOException;

public class UnsizedBytesAdapter extends TypeAdapter<UnsizedBytes> {

	@Override
	public UnsizedBytes read(JsonReader reader) throws IOException {
		return new UnsizedBytes(reader.nextString());
	}

	@Override
	public void write(JsonWriter writer, UnsizedBytes bytes) throws IOException {
		writer.value(bytes.toString());
	}
}
