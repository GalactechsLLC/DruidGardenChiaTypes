package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.ints.NativeUInt64;

public class InitialFreezePeriodResponse {
	private boolean success;
	private NativeUInt64 INITIAL_FREEZE_END_TIMESTAMP;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public NativeUInt64 getInitialFreezePeriod() {
		return INITIAL_FREEZE_END_TIMESTAMP;
	}

	public void setInitialFreezePeriod(NativeUInt64 INITIAL_FREEZE_END_TIMESTAMP) {
		this.INITIAL_FREEZE_END_TIMESTAMP = INITIAL_FREEZE_END_TIMESTAMP;
	}
}
