package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.farmer.FarmerSignagePointInfo;

public class FarmerSignagePointResponse extends FarmerSignagePointInfo{
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
