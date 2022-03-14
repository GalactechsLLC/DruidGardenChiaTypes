package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.farmer.FarmerRewardTarget;

public class FarmerRewardTargetResponse extends FarmerRewardTarget {
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
