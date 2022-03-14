package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class SubSlotProofs {

	@SerializedName(value = "challenge_chain_slot_proof", alternate = "challengeChainSlotProof")
	private VdfProof challengeChainSlotProof;
	@SerializedName(value = "infused_challenge_chain_slot_proof", alternate = "infusedChallengeChainSlotProof")
	private VdfProof infusedChallengeChainSlotProof;
	@SerializedName(value = "reward_chain_slot_proof", alternate = "rewardChainSlotProof")
	private VdfProof rewardChainSlotProof;

	public VdfProof getChallengeChainSlotProof() {
		return challengeChainSlotProof;
	}

	public void setChallengeChainSlotProof(VdfProof challengeChainSlotProof) {
		this.challengeChainSlotProof = challengeChainSlotProof;
	}

	public VdfProof getInfusedChallengeChainSlotProof() {
		return infusedChallengeChainSlotProof;
	}

	public void setInfusedChallengeChainSlotProof(VdfProof infusedChallengeChainSlotProof) {
		this.infusedChallengeChainSlotProof = infusedChallengeChainSlotProof;
	}

	public VdfProof getRewardChainSlotProof() {
		return rewardChainSlotProof;
	}

	public void setRewardChainSlotProof(VdfProof rewardChainSlotProof) {
		this.rewardChainSlotProof = rewardChainSlotProof;
	}
}
