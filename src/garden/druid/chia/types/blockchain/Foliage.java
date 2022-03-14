package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes96;

public class Foliage {

	@SerializedName(value = "foliage_transaction_block_hash", alternate = "foliageTransactionBlockHash")
	private Bytes32 foliageTransactionBlockHash;
	@SerializedName(value = "prev_block_hash", alternate = "prevBlockHash")
	private Bytes32 prevBlockHash;
	@SerializedName(value = "reward_block_hash", alternate = "rewardBlockHash")
	private Bytes32 rewardBlockHash;
	@SerializedName(value = "foliage_block_data_signature", alternate = "foliageBlockDataSignature")
	private Bytes96 foliageBlockDataSignature;
	@SerializedName(value = "foliage_transaction_block_signature", alternate = "foliageTransactionBlockSignature")
	private Bytes96 foliageTransactionBlockSignature;
	@SerializedName(value = "foliage_block_data", alternate = "foliageBlockData")
	private FoliageBlockData foliageBlockData;

	public Bytes96 getFoliageBlockDataSignature() {
		return foliageBlockDataSignature;
	}

	public void setFoliageBlockDataSignature(Bytes96 foliageBlockDataSignature) {
		this.foliageBlockDataSignature = foliageBlockDataSignature;
	}

	public Bytes32 getFoliageTransactionBlockHash() {
		return foliageTransactionBlockHash;
	}

	public void setFoliageTransactionBlockHash(Bytes32 foliageTransactionBlockHash) {
		this.foliageTransactionBlockHash = foliageTransactionBlockHash;
	}

	public Bytes96 getFoliageTransactionBlockSignature() {
		return foliageTransactionBlockSignature;
	}

	public void setFoliageTransactionBlockSignature(Bytes96 foliageTransactionBlockSignature) {
		this.foliageTransactionBlockSignature = foliageTransactionBlockSignature;
	}

	public Bytes32 getPrevBlockHash() {
		return prevBlockHash;
	}

	public void setPrevBlockHash(Bytes32 prevBlockHash) {
		this.prevBlockHash = prevBlockHash;
	}

	public Bytes32 getRewardBlockHash() {
		return rewardBlockHash;
	}

	public void setRewardBlockHash(Bytes32 rewardBlockHash) {
		this.rewardBlockHash = rewardBlockHash;
	}

	public FoliageBlockData getFoliageBlockData() {
		return foliageBlockData;
	}

	public void setFoliageBlockData(FoliageBlockData foliageBlockData) {
		this.foliageBlockData = foliageBlockData;
	}
}
