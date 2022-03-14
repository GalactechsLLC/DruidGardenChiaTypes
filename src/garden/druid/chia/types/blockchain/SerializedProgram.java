package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.JsonAdapter;
import garden.druid.chia.types.jsonAdapters.SerializedProgramAdapter;

@JsonAdapter(SerializedProgramAdapter.class)
public class SerializedProgram {
	private byte[] _buf;

	public byte[] getBuf() {
		return _buf;
	}

	public void setBuf(byte[] _buf) {
		this._buf = _buf;
	}
}
