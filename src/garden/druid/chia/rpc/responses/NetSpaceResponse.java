package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.ints.NativeUInt64;

public class NetSpaceResponse {
	private NativeUInt64 space;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public NativeUInt64 getNetspace() {
		return space;
	}

	public void setNetspace(NativeUInt64 space) {
		this.space = space;
	}
}
