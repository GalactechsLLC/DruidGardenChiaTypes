package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MempoolTXResponse {
	@SerializedName(value = "tx_ids", alternate = "txIds")
	private ArrayList<String> txIds;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<String> getTx_ids() {
		return txIds;
	}

	public void setTx_ids(ArrayList<String> tx_ids) {
		this.txIds = tx_ids;
	}
}
