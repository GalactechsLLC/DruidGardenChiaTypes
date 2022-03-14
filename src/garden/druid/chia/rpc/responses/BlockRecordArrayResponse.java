package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.BlockRecord;

public class BlockRecordArrayResponse {
	@SerializedName(value = "block_records", alternate = "blockRecords")
	private BlockRecord[] blockRecords;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public BlockRecord[] getBlockRecords() {
		return blockRecords;
	}

	public void setBlockRecords(BlockRecord[] block_records) {
		this.blockRecords = block_records;
	}
}
