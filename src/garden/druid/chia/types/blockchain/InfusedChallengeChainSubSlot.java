package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

public class InfusedChallengeChainSubSlot {
	@SerializedName(value = "infused_challenge_chain_end_of_slot_vdf", alternate = "infusedChallengeChainEndOfSlotVdf")
	private VdfInfo infusedChallengeChainEndOfSlotVdf;

	public VdfInfo getInfusedChallengeChainEndOfSlotVdf() {
		return infusedChallengeChainEndOfSlotVdf;
	}

	public void setInfusedChallengeChainEndOfSlotVdf(VdfInfo infusedChallengeChainEndOfSlotVdf) {
		this.infusedChallengeChainEndOfSlotVdf = infusedChallengeChainEndOfSlotVdf;
	}
}
