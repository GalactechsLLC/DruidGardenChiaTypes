package garden.druid.chia.types.jsonAdapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import garden.druid.chia.types.blockchain.TransactionPeer;

import java.io.IOException;

public class TransactionPeerAdapter extends TypeAdapter<TransactionPeer> {

	@Override
	public TransactionPeer read(JsonReader reader) throws IOException {
		reader.beginArray();
		String peer = reader.nextString();
		int status = reader.nextInt();
		String error = null;
		try{
			error = reader.nextString();
		} catch(Exception e) {
			reader.nextNull();
		}
		reader.endArray();
		TransactionPeer rtn = new TransactionPeer();
		rtn.setPeer(peer);
		rtn.setStatus(status);
		rtn.setError(error);
		return rtn;
	}

	@Override
	public void write(JsonWriter writer, TransactionPeer peer) throws IOException {
		writer.beginArray();
		writer.value(peer.getPeer());
		writer.value(peer.getStatus());
		writer.value(peer.getError());
		writer.endArray();
	}
}
