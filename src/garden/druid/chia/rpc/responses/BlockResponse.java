package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.FullBlock;

public class BlockResponse {
	private boolean success;
	private FullBlock block;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public FullBlock getBlock() {
		return block;
	}

	public void setBlock(FullBlock block) {
		this.block = block;
	}
}
