package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;

public class VdfInfo {

	private Bytes32 challenge;
	private VdfOutput output;
	@SerializedName(value = "number_of_iterations", alternate = "numberOfIterations")
	private NativeUInt64 numberOfIterations;

	public Bytes32 getChallenge() {
		return challenge;
	}

	public void setChallenge(Bytes32 challenge) {
		this.challenge = challenge;
	}

	public VdfOutput getOutput() {
		return output;
	}

	public void setOutput(VdfOutput output) {
		this.output = output;
	}

	public NativeUInt64 getNumberOfIterations() {
		return numberOfIterations;
	}

	public void setNumberOfIterations(NativeUInt64 numberOfIterations) {
		this.numberOfIterations = numberOfIterations;
	}
}
