package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.farmer.FarmerPoolState;

import java.util.ArrayList;

public class PoolStateResponse {

	private boolean success;
	@SerializedName(value = "pool_state", alternate = "poolState")
	private ArrayList<FarmerPoolState> poolState;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ArrayList<FarmerPoolState> getPool_state() {
		return poolState;
	}
	public void setPool_state(ArrayList<FarmerPoolState> pool_state) {
		this.poolState = pool_state;
	}
	
}
