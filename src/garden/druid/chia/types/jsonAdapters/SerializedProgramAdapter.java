package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.blockchain.SerializedProgram;
import garden.druid.chia.types.bytes.Bytes;

import java.io.IOException;

public class SerializedProgramAdapter extends TypeAdapter<SerializedProgram> {

	@Override
	public SerializedProgram read(JsonReader reader) throws IOException {
		String program = null;
		try{
			program = reader.nextString();
		} catch(Exception e) {
			reader.nextNull();
		}
		SerializedProgram rtn = new SerializedProgram();
		rtn.setBuf(Bytes.parseHexBinary(program));
		return rtn;
	}

	@Override
	public void write(JsonWriter writer, SerializedProgram program) throws IOException {
		writer.beginArray();
		writer.value(Bytes.printHexBinary(program.getBuf()));
		writer.endArray();
	}
}

