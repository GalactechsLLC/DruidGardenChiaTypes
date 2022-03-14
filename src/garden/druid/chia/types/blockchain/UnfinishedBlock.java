package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UnfinishedBlock {

	@SerializedName(value = "challenge_chain_sp_proof", alternate = "challengeChainSpProof")
	private VdfProof challengeChainSpProof;
	@SerializedName(value = "reward_chain_sp_proof", alternate = "rewardChainSpProof")
	private VdfProof rewardChainSpProof;
	private Foliage foliage;
	@SerializedName(value = "foliage_transaction_block", alternate = "foliageTransactionBlock")
	private FoliageTransactionBlock foliageTransactionBlock;
	@SerializedName(value = "transactions_filter", alternate = "transactionsFilter")
	private byte[] transactionsFilter;
	@SerializedName(value = "finished_sub_slots", alternate = "finishedSubSlots")
	private ArrayList<SubSlotBundle> finishedSubSlots;
	@SerializedName(value = "reward_chain_block", alternate = "rewardChainBlock")
	private RewardChainBlock rewardChainBlock;

	public VdfProof getChallengeChainSpProof() {
		return challengeChainSpProof;
	}

	public void setChallengeChainSpProof(VdfProof challengeChainSpProof) {
		this.challengeChainSpProof = challengeChainSpProof;
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

	public byte[] getTransactionsFilter() {
		return transactionsFilter;
	}

	public void setTransactionsFilter(byte[] transactionsFilter) {
		this.transactionsFilter = transactionsFilter;
	}

	public ArrayList<SubSlotBundle> getFinishedSubSlots() {
		return finishedSubSlots;
	}

	public void setFinishedSubSlots(ArrayList<SubSlotBundle> finishedSubSlots) {
		this.finishedSubSlots = finishedSubSlots;
	}

	public RewardChainBlock getRewardChainBlock() {
		return rewardChainBlock;
	}

	public void setRewardChainBlock(RewardChainBlock rewardChainBlock) {
		this.rewardChainBlock = rewardChainBlock;
	}

}
