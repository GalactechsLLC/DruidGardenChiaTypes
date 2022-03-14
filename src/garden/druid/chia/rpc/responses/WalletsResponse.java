package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.WalletInfo;

import java.util.ArrayList;

public class WalletsResponse {
	private boolean success;
	private ArrayList<WalletInfo> wallets;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<WalletInfo> getWallets() {
		return wallets;
	}

	public void setWallets(ArrayList<WalletInfo> wallets) {
		this.wallets = wallets;
	}
}
