package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.WalletBalance;

public class WalletBalanceResponse {
	private boolean success;
	@SerializedName(value = "wallet_balance", alternate = "walletBalance")
	private WalletBalance walletBalance;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public WalletBalance getWallet_balance() {
		return walletBalance;
	}

	public void setWallet_balance(WalletBalance wallet_balance) {
		this.walletBalance = wallet_balance;
	}

}
