package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.UnsizedBytes;

import java.util.ArrayList;

public class FullBlock {
	@SerializedName(value = "challenge_chain_ip_proof", alternate = "challengeChainIpProof")
	private VdfProof challengeChainIpProof;
	@SerializedName(value = "challenge_chain_sp_proof", alternate = "challengeChainSpProof")
	private VdfProof challengeChainSpProof;
	@SerializedName(value = "infused_challenge_chain_ip_proof", alternate = "infusedChallengeChainIpProof")
	private VdfProof infusedChallengeChainIpProof;
	@SerializedName(value = "reward_chain_ip_proof", alternate = "rewardChainIpProof")
	private VdfProof rewardChainIpProof;
	@SerializedName(value = "reward_chain_sp_proof", alternate = "rewardChainSpProof")
	private VdfProof rewardChainSpProof;
	private Foliage foliage;
	@SerializedName(value = "foliage_transaction_block", alternate = "foliageTransactionBlock")
	private FoliageTransactionBlock foliageTransactionBlock;
	@SerializedName(value = "transactions_generator", alternate = "transactionsGenerator")
	private UnsizedBytes transactionsGenerator;
	@SerializedName(value = "transactions_generator_ref_list", alternate = "transactionsGeneratorRefList")
	private ArrayList<Integer> transactionsGeneratorRefList;
	@SerializedName(value = "finished_sub_slots", alternate = "finishedSubSlots")
	private ArrayList<SubSlotBundle> finishedSubSlots;
	@SerializedName(value = "reward_chain_block", alternate = "rewardChainBlock")
	private RewardChainBlock rewardChainBlock;
	@SerializedName(value = "transactions_info", alternate = "transactionsInfo")
	private TransactionInfo transactionsInfo;

	public VdfProof getChallengeChainIpProof() {
		return challengeChainIpProof;
	}

	public void setChallengeChainIpProof(VdfProof challengeChainIpProof) {
		this.challengeChainIpProof = challengeChainIpProof;
	}

	public VdfProof getChallengeChainSpProof() {
		return challengeChainSpProof;
	}

	public void setChallengeChainSpProof(VdfProof challengeChainSpProof) {
		this.challengeChainSpProof = challengeChainSpProof;
	}

	public VdfProof getInfusedChallengeChainIpProof() {
		return infusedChallengeChainIpProof;
	}

	public void setInfusedChallengeChainIpProof(VdfProof infusedChallengeChainIpProof) {
		this.infusedChallengeChainIpProof = infusedChallengeChainIpProof;
	}

	public VdfProof getRewardChainIpProof() {
		return rewardChainIpProof;
	}

	public void setRewardChainIpProof(VdfProof rewardChainIpProof) {
		this.rewardChainIpProof = rewardChainIpProof;
	}

	public VdfProof getRewardChainSpProof() {
		return rewardChainSpProof;
	}

	public void setRewardChainSpProof(VdfProof rewardChainSpProof) {
		this.rewardChainSpProof = rewardChainSpProof;
	}

	public Foliage getFoliage() {
		return foliage;
	}

	public void setFoliage(Foliage foliage) {
		this.foliage = foliage;
	}

	public FoliageTransactionBlock getFoliageTransactionBlock() {
		return foliageTransactionBlock;
	}

	public void setFoliageTransactionBlock(FoliageTransactionBlock foliageTransactionBlock) {
		this.foliageTransactionBlock = foliageTransactionBlock;
	}

	public UnsizedBytes getTransactionsGenerator() {
		return transactionsGenerator;
	}

	public void setTransactionsGenerator(UnsizedBytes transactionsGenerator) {
		this.transactionsGenerator = transactionsGenerator;
	}

	public ArrayList<Integer> getTransactionsGeneratorRefList() {
		return transactionsGeneratorRefList;
	}

	public void setTransactionsGeneratorRefList(ArrayList<Integer> transactionsGeneratorRefList) {
		this.transactionsGeneratorRefList = transactionsGeneratorRefList;
	}

	public RewardChainBlock getRewardChainBlock() {
		return rewardChainBlock;
	}

	public void setRewardChainBlock(RewardChainBlock rewardChainBlock) {
		this.rewardChainBlock = rewardChainBlock;
	}

	public TransactionInfo getTransactionsInfo() {
		return transactionsInfo;
	}

	public void setTransactionsInfo(TransactionInfo transactionsInfo) {
		this.transactionsInfo = transactionsInfo;
	}

	public ArrayList<SubSlotBundle> getFinishedSubSlots() {
		return finishedSubSlots;
	}

	public void setFinishedSubSlots(ArrayList<SubSlotBundle> finishedSubSlots) {
		this.finishedSubSlots = finishedSubSlots;
	}
}
