package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.CoinSpend;

public class CoinSpendResponse {
	@SerializedName(value = "coin_solution", alternate = "coinSolution")
	private CoinSpend coinSolution;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public CoinSpend getCoinSpend() {
		return coinSolution;
	}

	public void setCoinSpend(CoinSpend coin_solution) {
		this.coinSolution = coin_solution;
	}
}
