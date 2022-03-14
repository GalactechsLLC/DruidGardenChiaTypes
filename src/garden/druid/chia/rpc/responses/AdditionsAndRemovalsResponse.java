package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.CoinRecord;

import java.util.ArrayList;

public class AdditionsAndRemovalsResponse {

	private boolean success;
	private ArrayList<CoinRecord> additions, removals;

	public ArrayList<CoinRecord> getAdditions() {
		return additions;
	}

	public void setAdditions(ArrayList<CoinRecord> additions) {
		this.additions = additions;
	}

	public ArrayList<CoinRecord> getRemovals() {
		return removals;
	}

	public void setRemovals(ArrayList<CoinRecord> removals) {
		this.removals = removals;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
