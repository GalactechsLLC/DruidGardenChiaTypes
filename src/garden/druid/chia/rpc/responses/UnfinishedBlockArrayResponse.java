package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.UnfinishedBlock;

public class UnfinishedBlockArrayResponse {
	private UnfinishedBlock[] headers;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public UnfinishedBlock[] getUnfinishedBlocks() {
		return headers;
	}

	public void setUnfinishedBlocks(UnfinishedBlock[] headers) {
		this.headers = headers;
	}
}
