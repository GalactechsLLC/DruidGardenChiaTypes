package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.CoinRecord;

public class CoinRecordResponse {
	@SerializedName(value = "coin_record", alternate = "coinRecord")
	private CoinRecord coinRecord;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public CoinRecord getCoinRecord() {
		return coinRecord;
	}

	public void setCoinRecords(CoinRecord coin_record) {
		this.coinRecord = coin_record;
	}
}
