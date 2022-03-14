package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes96;
import garden.druid.chia.types.ints.NativeUInt128;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt8;

public class RewardChainBlock {

	@SerializedName(value = "pos_ss_cc_challenge_hash", alternate = "posSsCcChallengeHash")
	private Bytes32 posSsCcChallengeHash;
	@SerializedName(value = "challenge_chain_sp_signature", alternate = "challengeChainSpSignature")
	private Bytes96 challengeChainSpSignature;
	@SerializedName(value = "reward_chain_sp_signature", alternate = "rewardChainSpSignature")
	private Bytes96 rewardChainSpSignature;
	@SerializedName(value = "challenge_chain_sp_vdf", alternate = "challengeChainSpVdf")
	private VdfInfo challengeChainSpVdf;
	@SerializedName(value = "infused_challenge_chain_ip_vdf", alternate = "infusedChallengeChainIpVdf")
	private VdfInfo infusedChallengeChainIpVdf;
	@SerializedName(value = "challenge_chain_ip_vdf", alternate = "challengeChainIpVdf")
	private VdfInfo challengeChainIpVdf; 
	@SerializedName(value = "reward_chain_ip_vdf", alternate = "rewardChainIpVdf")
	private VdfInfo rewardChainIpVdf;
	@SerializedName(value = "reward_chain_sp_vdf", alternate = "rewardChainSpVdf")
	private VdfInfo rewardChainSpVdf;
	private NativeUInt32 height;
	@SerializedName(value = "signage_point_index", alternate = "signagePointIndex")
	private NativeUInt8 signagePointIndex;
	@SerializedName(value = "total_iters", alternate = "totalIters")
	private NativeUInt128 totalIters;
	private NativeUInt128 weight;
	@SerializedName(value = "is_transaction_block", alternate = "isTransactionBlock")
	private boolean isTransactionBlock;
	@SerializedName(value = "proof_of_space", alternate = "proofOfSpace")
	private ProofOfSpace proofOfSpace;

	public Bytes96 getChallengeChainSpSignature() {
		return challengeChainSpSignature;
	}

	public void setChallengeChainSpSignature(Bytes96 challengeChainSpSignature) {
		this.challengeChainSpSignature = challengeChainSpSignature;
	}

	public Bytes32 getPosSsCcChallengeHash() {
		return posSsCcChallengeHash;
	}

	public void setPosSsCcChallengeHash(Bytes32 posSsCcChallengeHash) {
		this.posSsCcChallengeHash = posSsCcChallengeHash;
	}

	public Bytes96 getRewardChainSpSignature() {
		return rewardChainSpSignature;
	}

	public void setRewardChainSpSignature(Bytes96 rewardChainSpSignature) {
		this.rewardChainSpSignature = rewardChainSpSignature;
	}

	public VdfInfo getChallengeChainSpVdf() {
		return challengeChainSpVdf;
	}

	public void setChallengeChainSpVdf(VdfInfo challengeChainSpVdf) {
		this.challengeChainSpVdf = challengeChainSpVdf;
	}

	public VdfInfo getInfusedChallengeChainIpVdf() {
		return infusedChallengeChainIpVdf;
	}

	public void setInfusedChallengeChainIpVdf(VdfInfo infusedChallengeChainIpVdf) {
		this.infusedChallengeChainIpVdf = infusedChallengeChainIpVdf;
	}

	public VdfInfo getChallengeChainIpVdf() {
		return challengeChainIpVdf;
	}

	public void setChallengeChainIpVdf(VdfInfo challengeChainIpVdf) {
		this.challengeChainIpVdf = challengeChainIpVdf;
	}

	public VdfInfo getRewardChainIpVdf() {
		return rewardChainIpVdf;
	}

	public void setRewardChainIpVdf(VdfInfo rewardChainIpVdf) {
		this.rewardChainIpVdf = rewardChainIpVdf;
	}

	public VdfInfo getRewardChainSpVdf() {
		return rewardChainSpVdf;
	}

	public void setRewardChainSpVdf(VdfInfo rewardChainSpVdf) {
		this.rewardChainSpVdf = rewardChainSpVdf;
	}

	public NativeUInt32 getHeight() {
		return height;
	}

	public void setHeight(NativeUInt32 height) {
		this.height = height;
	}

	public NativeUInt8 getSignagePointIndex() {
		return signagePointIndex;
	}

	public void setSignagePointIndex(NativeUInt8 signagePointIndex) {
		this.signagePointIndex = signagePointIndex;
	}

	public NativeUInt128 getTotalIters() {
		return totalIters;
	}

	public void setTotalIters(NativeUInt128 totalIters) {
		this.totalIters = totalIters;
	}

	public NativeUInt128 getWeight() {
		return weight;
	}

	public void setWeight(NativeUInt128 weight) {
		this.weight = weight;
	}

	public boolean isIsTransactionBlock() {
		return isTransactionBlock;
	}

	public void setIsTransactionBlock(boolean isTransactionBlock) {
		this.isTransactionBlock = isTransactionBlock;
	}

	public ProofOfSpace getProofOfSpace() {
		return proofOfSpace;
	}

	public void setProofOfSpace(ProofOfSpace proofOfSpace) {
		this.proofOfSpace = proofOfSpace;
	}
}
