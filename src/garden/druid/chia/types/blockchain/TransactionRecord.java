package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt64;

import java.util.ArrayList;

public class TransactionRecord {
	@SerializedName(value = "confirmed_at_height", alternate = "confirmedAtHeight")
	private NativeUInt32 confirmedAtHeight;
	private NativeUInt32 sent;
	@SerializedName(value = "wallet_id", alternate = "walletId")
	private NativeUInt32 walletId;
	private NativeUInt32 type;
	@SerializedName(value = "created_at_time", alternate = "createdAtTime")
	private NativeUInt64 createdAtTime;
	private NativeUInt64 amount;
	@SerializedName(value = "fee_amount", alternate = "feeAmount")
	private NativeUInt64 feeAmount;
	@SerializedName(value = "to_puzzle_hash", alternate = "toPuzzleHash")
	private Bytes32 toPuzzleHash;
	@SerializedName(value = "trade_id", alternate = "tradeId")
	private Bytes32 tradeId;
	private Bytes32 name;
	private boolean confirmed;
	@SerializedName(value = "spend_bundle", alternate = "spendBundle")
	private SpendBundle spendBundle;
	private ArrayList<Coin> additions, removals;
	@SerializedName(value = "sent_to", alternate = "sentTo")
	private ArrayList<TransactionPeer> sentTo;

    public boolean isInMempool(){
        for (TransactionPeer peer : this.sentTo) {
            if (peer.getStatus() == MempoolInclusionStatus.SUCCESS) {
                return true;
            }
        }
        return false;
    }
	
	public NativeUInt32 getConfirmedAtHeight() {
		return confirmedAtHeight;
	}

	public void setConfirmedAtHeight(NativeUInt32 confirmedAtHeight) {
		this.confirmedAtHeight = confirmedAtHeight;
	}

	public NativeUInt64 getCreatedAtTime() {
		return createdAtTime;
	}

	public void setCreatedAtTime(NativeUInt64 createdAtTime) {
		this.createdAtTime = createdAtTime;
	}

	public Bytes32 getToPuzzleHash() {
		return toPuzzleHash;
	}

	public void setToPuzzleHash(Bytes32 toPuzzleHash) {
		this.toPuzzleHash = toPuzzleHash;
	}

	public NativeUInt64 getAmount() {
		return amount;
	}

	public void setAmount(NativeUInt64 amount) {
		this.amount = amount;
	}

	public NativeUInt64 getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(NativeUInt64 feeAmount) {
		this.feeAmount = feeAmount;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public NativeUInt32 getSent() {
		return sent;
	}

	public void setSent(NativeUInt32 sent) {
		this.sent = sent;
	}

	public SpendBundle getSpendBundle() {
		return spendBundle;
	}

	public void setSpendBundle(SpendBundle spendBundle) {
		this.spendBundle = spendBundle;
	}

	public ArrayList<Coin> getAdditions() {
		return additions;
	}

	public void setAdditions(ArrayList<Coin> additions) {
		this.additions = additions;
	}

	public ArrayList<Coin> getRemovals() {
		return removals;
	}

	public void setRemovals(ArrayList<Coin> removals) {
		this.removals = removals;
	}

	public NativeUInt32 getWalletId() {
		return walletId;
	}

	public void setWalletId(NativeUInt32 walletId) {
		this.walletId = walletId;
	}

	public ArrayList<TransactionPeer> getSentTo() {
		return sentTo;
	}

	public void setSentTo(ArrayList<TransactionPeer> sentTo) {
		this.sentTo = sentTo;
	}

	public Bytes32 getTradeId() {
		return tradeId;
	}

	public void setTradeId(Bytes32 tradeId) {
		this.tradeId = tradeId;
	}

	public NativeUInt32 getType() {
		return type;
	}

	public void setType(NativeUInt32 type) {
		this.type = type;
	}

	public Bytes32 getName() {
		return name;
	}

	public void setName(Bytes32 name) {
		this.name = name;
	}
}
