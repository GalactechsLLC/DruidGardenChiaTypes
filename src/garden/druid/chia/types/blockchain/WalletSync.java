package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class WalletSync {
	@SerializedName(value = "genesis_initialized", alternate = "genesisInitialized")
	private boolean genesisInitialized;
	private boolean success, synced, syncing;

	public boolean isGenesisInitialized() {
		return genesisInitialized;
	}

	public void setGenesisInitialized(boolean genesisInitialized) {
		this.genesisInitialized = genesisInitialized;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSynced() {
		return synced;
	}

	public void setSynced(boolean synced) {
		this.synced = synced;
	}

	public boolean isSyncing() {
		return syncing;
	}

	public void setSyncing(boolean syncing) {
		this.syncing = syncing;
	}
}
