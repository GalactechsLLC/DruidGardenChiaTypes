package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.FullBlock;

public class BlockArrayResponse {
	private boolean success;
	private FullBlock[] blocks;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public FullBlock[] getBlocks() {
		return blocks;
	}

	public void setBlocks(FullBlock[] blocks) {
		this.blocks = blocks;
	}
}
