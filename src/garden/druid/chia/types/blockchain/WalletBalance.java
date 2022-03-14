package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt64;

public class WalletBalance {
	@SerializedName(value = "wallet_id", alternate = "walletId")
	private NativeUInt32 walletId;
	@SerializedName(value = "pending_coin_removal_count", alternate = "pendingCoinRemovalCount")
	private NativeUInt32 pendingCoinRemovalCount;
	@SerializedName(value = "unspent_coin_count", alternate = "unspentCoinCount")
	private NativeUInt32 unspentCoinCount;
	@SerializedName(value = "confirmed_wallet_balance", alternate = "confirmedWalletBalance")
	private NativeUInt64 confirmedWalletBalance;
	@SerializedName(value = "max_send_amount", alternate = "maxSendAmount")
	private NativeUInt64 maxSendAmount;
	@SerializedName(value = "pending_change", alternate = "pendingChange")
	private NativeUInt64 pendingChange;
	@SerializedName(value = "spendable_balance", alternate = "spendableBalance")
	private NativeUInt64 spendableBalance;
	@SerializedName(value = "unconfirmed_wallet_balance", alternate = "unconfirmedWalletBalance")
	private NativeUInt64 unconfirmedWalletBalance;

	public NativeUInt32 getWalletId() {
		return walletId;
	}

	public void setWalletId(NativeUInt32 walletId) {
		this.walletId = walletId;
	}

	public NativeUInt32 getPendingCoinRemovalCount() {
		return pendingCoinRemovalCount;
	}

	public void setPendingCoinRemovalCount(NativeUInt32 pendingCoinRemovalCount) {
		this.pendingCoinRemovalCount = pendingCoinRemovalCount;
	}

	public NativeUInt32 getUnspentCoinCount() {
		return unspentCoinCount;
	}

	public void setUnspentCoinCount(NativeUInt32 unspentCoinCount) {
		this.unspentCoinCount = unspentCoinCount;
	}

	public NativeUInt64 getConfirmedWalletBalance() {
		return confirmedWalletBalance;
	}

	public void setConfirmedWalletBalance(NativeUInt64 confirmedWalletBalance) {
		this.confirmedWalletBalance = confirmedWalletBalance;
	}

	public NativeUInt64 getMaxSendAmount() {
		return maxSendAmount;
	}

	public void setMaxSendAmount(NativeUInt64 maxSendAmount) {
		this.maxSendAmount = maxSendAmount;
	}

	public NativeUInt64 getPendingChange() {
		return pendingChange;
	}

	public void setPendingChange(NativeUInt64 pendingChange) {
		this.pendingChange = pendingChange;
	}

	public NativeUInt64 getSpendableBalance() {
		return spendableBalance;
	}

	public void setSpendableBalance(NativeUInt64 spendableBalance) {
		this.spendableBalance = spendableBalance;
	}

	public NativeUInt64 getUnconfirmedWalletBalance() {
		return unconfirmedWalletBalance;
	}

	public void setUnconfirmedWalletBalance(NativeUInt64 unconfirmedWalletBalance) {
		this.unconfirmedWalletBalance = unconfirmedWalletBalance;
	}

}
