package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.State;

public class StateResponse {
	private boolean success;
	@SerializedName(value = "blockchain_state", alternate = "blockchainState")
	private State blockchainState;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public State getBlockshain_state() {
		return blockchainState;
	}

	public void setBlockshain_state(State blockchain_state) {
		this.blockchainState = blockchain_state;
	}
}
