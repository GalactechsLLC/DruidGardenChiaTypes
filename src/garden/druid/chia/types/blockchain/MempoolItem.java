package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;

public class MempoolItem {
	@SerializedName(value = "spend_bundle", alternate = "spendBundle")
	private SpendBundle spendBundle;
	private NativeUInt64 fee, cost;
	@SerializedName(value = "npc_result", alternate = "npcResult")
	private NPCResult npcResult;
	@SerializedName(value = "spend_bundle_name", alternate = "spendBundleName")
	private Bytes32 spendBundleName;
	private SerializedProgram program;
	private Coin[] additions;
	private Coin[] removals;

	public SpendBundle getSpendBundle() {
		return spendBundle;
	}

	public void setSpendBundle(SpendBundle spendBundle) {
		this.spendBundle = spendBundle;
	}

	public NativeUInt64 getFee() {
		return fee;
	}

	public void setFee(NativeUInt64 fee) {
		this.fee = fee;
	}

	public NativeUInt64 getCost() {
		return cost;
	}

	public void setCost(NativeUInt64 cost) {
		this.cost = cost;
	}

	public NPCResult getNpcResult() {
		return npcResult;
	}

	public void setNpcResult(NPCResult npcResult) {
		this.npcResult = npcResult;
	}

	public Bytes32 getSpendBundleName() {
		return spendBundleName;
	}

	public void setSpendBundleName(Bytes32 spendBundleName) {
		this.spendBundleName = spendBundleName;
	}

	public SerializedProgram getProgram() {
		return program;
	}

	public void setProgram(SerializedProgram program) {
		this.program = program;
	}

	public Coin[] getAdditions() {
		return additions;
	}

	public void setAdditions(Coin[] additions) {
		this.additions = additions;
	}

	public Coin[] getRemovals() {
		return removals;
	}

	public void setRemovals(Coin[] removals) {
		this.removals = removals;
	}
}
