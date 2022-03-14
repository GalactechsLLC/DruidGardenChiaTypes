package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.ints.NativeUInt16;
import garden.druid.chia.types.ints.NativeUInt64;

public class NPCResult {
	private NativeUInt16 error; // Optional[uint16];
	@SerializedName(value = "npc_list", alternate = "npcList")
	private Object[] npcList;
	@SerializedName(value = "clvm_cost", alternate = "clvmCost")
	private NativeUInt64 clvmCost;// uint64 # CLVM cost only, cost of conditions and tx size is not included

	public NativeUInt16 getError() {
		return error;
	}

	public void setError(NativeUInt16 error) {
		this.error = error;
	}

	public Object[] getNpcList() {
		return npcList;
	}

	public void setNpcList(Object[] npcList) {
		this.npcList = npcList;
	}

	public NativeUInt64 getClvmCost() {
		return clvmCost;
	}

	public void setClvmCost(NativeUInt64 clvmCost) {
		this.clvmCost = clvmCost;
	}
}
