package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.TXStatus;

public class TXResponse {
	private TXStatus status;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public TXStatus getTXStatus() {
		return status;
	}

	public void setTXStatus(TXStatus status) {
		this.status = status;
	}
}
