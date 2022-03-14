package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt8;

public class RewardChainSubSlot {

	@SerializedName(value = "end_of_slot_vdf", alternate = "endOfSlotVdf")
	private VdfInfo endOfSlotVdf;
	@SerializedName(value = "challenge_chain_sub_slot_hash", alternate = "challengeChainSubSlotHash")
	private Bytes32 challengeChainSubSlotHash;
	@SerializedName(value = "infused_challenge_chain_sub_slot_hash", alternate = "infusedChallengeChainSubSlotHash")
	private Bytes32 infusedChallengeChainSubSlotHash;
	private NativeUInt8 deficit;

	public VdfInfo getEndOfSlotVdf() {
		return endOfSlotVdf;
	}

	public void setEndOfSlotVdf(VdfInfo endOfSlotVdf) {
		this.endOfSlotVdf = endOfSlotVdf;
	}

	public Bytes32 getChallengeChainSubSlotHash() {
		return challengeChainSubSlotHash;
	}

	public void setChallengeChainSubSlotHash(Bytes32 challengeChainSubSlotHash) {
		this.challengeChainSubSlotHash = challengeChainSubSlotHash;
	}

	public Bytes32 getInfusedChallengeChainSubSlotHash() {
		return infusedChallengeChainSubSlotHash;
	}

	public void setInfusedChallengeChainSubSlotHash(Bytes32 infusedChallengeChainSubSlotHash) {
		this.infusedChallengeChainSubSlotHash = infusedChallengeChainSubSlotHash;
	}

	public NativeUInt8 getDeficit() {
		return deficit;
	}

	public void setDeficit(NativeUInt8 deficit) {
		this.deficit = deficit;
	}
}
