package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.farmer.Harvester;

import java.util.ArrayList;

public class HarvesterResponse {

	private ArrayList<Harvester> harvesters;
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ArrayList<Harvester> getHarvesters() {
		return harvesters;
	}

	public void setHarvesters(ArrayList<Harvester> harvesters) {
		this.harvesters = harvesters;
	}
}
