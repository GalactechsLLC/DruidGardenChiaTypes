package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;

public class FoliageTransactionBlock {
	@SerializedName(value = "additions_root", alternate = "additionsRoot")
	private Bytes32 additionsRoot;
	@SerializedName(value = "filter_hash", alternate = "filterHash")
	private Bytes32 filterHash;
	@SerializedName(value = "prev_transaction_block_hash", alternate = "prevTransactionBlockHash")
	private Bytes32 prevTransactionBlockHash;
	@SerializedName(value = "removals_root", alternate = "removalsRoot")
	private Bytes32 removalsRoot;
	@SerializedName(value = "transactions_info_hash", alternate = "transactionsInfoHash")
	private Bytes32 transactionsInfoHash;
	private NativeUInt64 timestamp;

	public Bytes32 getAdditionsRoot() {
		return additionsRoot;
	}

	public void setAdditionsRoot(Bytes32 additionsRoot) {
		this.additionsRoot = additionsRoot;
	}

	public Bytes32 getFilterHash() {
		return filterHash;
	}

	public void setFilterHash(Bytes32 filterHash) {
		this.filterHash = filterHash;
	}

	public Bytes32 getPrevTransactionBlockHash() {
		return prevTransactionBlockHash;
	}

	public void setPrevTransactionBlockHash(Bytes32 prevTransactionBlockHash) {
		this.prevTransactionBlockHash = prevTransactionBlockHash;
	}

	public Bytes32 getRemovalsRoot() {
		return removalsRoot;
	}

	public void setRemovalsRoot(Bytes32 removalsRoot) {
		this.removalsRoot = removalsRoot;
	}

	public Bytes32 getTransactionsInfoHash() {
		return transactionsInfoHash;
	}

	public void setTransactionsInfoHash(Bytes32 transactionsInfoHash) {
		this.transactionsInfoHash = transactionsInfoHash;
	}

	public NativeUInt64 getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(NativeUInt64 timestamp) {
		this.timestamp = timestamp;
	}
}
