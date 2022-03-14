package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.BlockRecord;

public class BlockRecordResponse {
	@SerializedName(value = "block_record", alternate = "blockRecord")
	private BlockRecord blockRecord;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public BlockRecord getBlockRecord() {
		return blockRecord;
	}

	public void setBlockRecord(BlockRecord block_record) {
		this.blockRecord = block_record;
	}
}
