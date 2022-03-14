package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.UnsizedBytes;
import garden.druid.chia.types.ints.NativeUInt8;

public class VdfProof {
	@SerializedName(value = "normalized_to_identity", alternate = "normalizedToIdentity")
	private boolean normalizedToIdentity;
	private UnsizedBytes witness;
	@SerializedName(value = "witness_type", alternate = "witnessType")
	private NativeUInt8 witnessType;

	public boolean isNormalizedToIdentity() {
		return normalizedToIdentity;
	}

	public void setNormalizedToIdentity(boolean normalizedToIdentity) {
		this.normalizedToIdentity = normalizedToIdentity;
	}

	public UnsizedBytes getWitness() {
		return witness;
	}

	public void setWitness(UnsizedBytes witness) {
		this.witness = witness;
	}

	public NativeUInt8 getWitnessType() {
		return witnessType;
	}

	public void setWitnessType(NativeUInt8 witnessType) {
		this.witnessType = witnessType;
	}
}
