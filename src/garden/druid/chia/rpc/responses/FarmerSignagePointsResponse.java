package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.farmer.FarmerSignagePointInfo;

import java.util.ArrayList;

public class FarmerSignagePointsResponse {
	private boolean success;
	@SerializedName(value = "signage_points", alternate = "signagePoints")
	private ArrayList<FarmerSignagePointInfo> signagePoints;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<FarmerSignagePointInfo> getSignagePoints() {
		return signagePoints;
	}

	public void setSignagePoints(ArrayList<FarmerSignagePointInfo> signage_points) {
		this.signagePoints = signage_points;
	}
}
