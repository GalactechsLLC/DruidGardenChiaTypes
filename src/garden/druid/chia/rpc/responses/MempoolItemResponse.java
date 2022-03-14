package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.MempoolItem;

public class MempoolItemResponse {

	@SerializedName(value = "mempool_item", alternate = "mempoolItem")
	private MempoolItem mempoolItem;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public MempoolItem getMempoolItem() {
		return mempoolItem;
	}

	public void setMempoolItem(MempoolItem mempool_item) {
		this.mempoolItem = mempool_item;
	}
}
