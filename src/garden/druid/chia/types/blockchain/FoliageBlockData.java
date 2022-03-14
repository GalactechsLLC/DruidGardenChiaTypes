package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes96;

public class FoliageBlockData {

	@SerializedName(value = "extension_data", alternate = "extensionData")
	private Bytes32 extensionData;
	@SerializedName(value = "farmer_reward_puzzle_hash", alternate = "farmerRewardPuzzleHash")
	private Bytes32 farmerRewardPuzzleHash;
	@SerializedName(value = "unfinished_reward_block_hash", alternate = "unfinishedRewardBlockHash")
	private Bytes32 unfinishedRewardBlockHash;
	@SerializedName(value = "pool_signature", alternate = "poolSignature")
	private Bytes96 poolSignature;
	@SerializedName(value = "pool_target", alternate = "poolTarget")
	private PoolTarget poolTarget;

	public Bytes32 getExtensionData() {
		return extensionData;
	}

	public void setExtensionData(Bytes32 extensionData) {
		this.extensionData = extensionData;
	}

	public Bytes32 getFarmerRewardPuzzleHash() {
		return farmerRewardPuzzleHash;
	}

	public void setFarmerRewardPuzzleHash(Bytes32 farmerRewardPuzzleHash) {
		this.farmerRewardPuzzleHash = farmerRewardPuzzleHash;
	}

	public Bytes96 getPoolSignature() {
		return poolSignature;
	}

	public void setPoolSignature(Bytes96 poolSignature) {
		this.poolSignature = poolSignature;
	}

	public Bytes32 getUnfinishedRewardBlockHash() {
		return unfinishedRewardBlockHash;
	}

	public void setUnfinishedRewardBlockHash(Bytes32 unfinishedRewardBlockHash) {
		this.unfinishedRewardBlockHash = unfinishedRewardBlockHash;
	}

	public PoolTarget getPoolTarget() {
		return poolTarget;
	}

	public void setPoolTarget(PoolTarget poolTarget) {
		this.poolTarget = poolTarget;
	}
}
