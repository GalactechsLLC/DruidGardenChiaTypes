package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.TransactionPeerAdapter;

@JsonAdapter(TransactionPeerAdapter.class)
public class TransactionPeer {

	private String Peer, error;
	private int status;

	public String getPeer() {
		return Peer;
	}

	public void setPeer(String peer) {
		Peer = peer;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
