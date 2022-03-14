package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.CoinRecord;

public class CoinRecordArrayResponse {
	@SerializedName(value = "coin_records", alternate = "coinRecords")
	private CoinRecord[] coinRecords;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public CoinRecord[] getCoinRecords() {
		return coinRecords;
	}

	public void setCoinRecords(CoinRecord[] coin_records) {
		this.coinRecords = coin_records;
	}
}
