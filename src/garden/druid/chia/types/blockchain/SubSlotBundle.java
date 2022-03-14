package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class SubSlotBundle {
	
	@SerializedName(value = "challenge_chain", alternate = "challengeChain")
	private ChallengeChainSubSlot challengeChain;
	@SerializedName(value = "infused_challenge_chain", alternate = "infusedChallengeChain")
	private InfusedChallengeChainSubSlot infusedChallengeChain;
	@SerializedName(value = "reward_chain", alternate = "rewardChain")
	private RewardChainSubSlot rewardChain;
	private SubSlotProofs proofs;

	public ChallengeChainSubSlot getChallengeChain() {
		return challengeChain;
	}

	public void setChallengeChain(ChallengeChainSubSlot challengeChain) {
		this.challengeChain = challengeChain;
	}

	public InfusedChallengeChainSubSlot getInfusedChallengeChain() {
		return infusedChallengeChain;
	}

	public void setInfusedChallengeChain(InfusedChallengeChainSubSlot infusedChallengeChain) {
		this.infusedChallengeChain = infusedChallengeChain;
	}

	public RewardChainSubSlot getRewardChain() {
		return rewardChain;
	}

	public void setRewardChain(RewardChainSubSlot rewardChain) {
		this.rewardChain = rewardChain;
	}

	public SubSlotProofs getProofs() {
		return proofs;
	}

	public void setProofs(SubSlotProofs proofs) {
		this.proofs = proofs;
	}
}
