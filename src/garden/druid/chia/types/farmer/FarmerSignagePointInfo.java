package garden.druid.chia.types.farmer;

import garden.druid.chia.types.blockchain.ProofOfSpace;

import java.util.ArrayList;

public class FarmerSignagePointInfo {

	private FarmerSignagePoint signage_point;
	private ArrayList<ProofOfSpace> proofs;
	
	public FarmerSignagePoint getSignage_point() {
		return signage_point;
	}
	public void setSignage_point(FarmerSignagePoint signage_point) {
		this.signage_point = signage_point;
	}
	public ArrayList<ProofOfSpace> getProofs() {
		return proofs;
	}
	public void setProofs(ArrayList<ProofOfSpace> proofs) {
		this.proofs = proofs;
	}
}
