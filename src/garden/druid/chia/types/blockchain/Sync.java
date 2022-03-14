package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.ints.NativeUInt32;

public class Sync {
	@SerializedName(value = "sync_mode", alternate = "syncMode")
	private boolean syncMode;
	private boolean synced;
	@SerializedName(value = "sync_tip_height", alternate = "syncTipHeight")
	private NativeUInt32 syncTipHeight;
	@SerializedName(value = "sync_progress_height", alternate = "syncProgressHeight")
	private NativeUInt32 syncProgressHeight;

	public boolean isSyncMode() {
		return syncMode;
	}

	public void setSyncMode(boolean syncMode) {
		this.syncMode = syncMode;
	}

	public boolean isSynced() {
		return synced;
	}

	public void setSynced(boolean synced) {
		this.synced = synced;
	}

	public NativeUInt32 getSyncTipHeight() {
		return syncTipHeight;
	}

	public void setSyncTipHeight(NativeUInt32 syncTipHeight) {
		this.syncTipHeight = syncTipHeight;
	}

	public NativeUInt32 getSyncProgressHeight() {
		return syncProgressHeight;
	}

	public void setSyncProgressHeight(NativeUInt32 syncProgressHeight) {
		this.syncProgressHeight = syncProgressHeight;
	}
}
