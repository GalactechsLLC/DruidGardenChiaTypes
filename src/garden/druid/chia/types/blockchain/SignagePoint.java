package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class SignagePoint {
	@SerializedName(value = "cc_vdf", alternate = "ccVdf")
	private VdfInfo ccVdf;
	@SerializedName(value = "cc_proof", alternate = "ccProof")
	private VdfProof ccProof;
	@SerializedName(value = "rc_vdf", alternate = "rcVdf")
	private VdfInfo rcVdf;
	@SerializedName(value = "rc_proof", alternate = "rcProof")
	private VdfProof rcProof;

	public VdfInfo getCcVdf() {
		return ccVdf;
	}

	public void setCcVdf(VdfInfo ccVdf) {
		this.ccVdf = ccVdf;
	}

	public VdfProof getCcProof() {
		return ccProof;
	}

	public void setCcProof(VdfProof ccProof) {
		this.ccProof = ccProof;
	}

	public VdfInfo getRcVdf() {
		return rcVdf;
	}

	public void setRcVdf(VdfInfo rcVdf) {
		this.rcVdf = rcVdf;
	}

	public VdfProof getRcProof() {
		return rcProof;
	}

	public void setRcProof(VdfProof rcProof) {
		this.rcProof = rcProof;
	}
}
