package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.MempoolItem;

import java.util.HashMap;

public class MempoolItemsResponse {
	@SerializedName(value = "mempool_items", alternate = "mempoolItems")
	private HashMap<String, MempoolItem> mempoolItems;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HashMap<String, MempoolItem> getMempoolItems() {
		return mempoolItems;
	}

	public void setMempoolItems(HashMap<String, MempoolItem> mempool_items) {
		this.mempoolItems = mempool_items;
	}
}
