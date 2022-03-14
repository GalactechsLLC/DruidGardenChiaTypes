package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import garden.druid.chia.types.bytes.Bytes32;

public class NPC {
	@SerializedName(value = "coin_name", alternate = "coinName")
	private Bytes32 coinName; // bytes32
	@SerializedName(value = "puzzle_hash", alternate = "puzzleHash")
	private Bytes32 puzzleHash;
	private LinkedTreeMap<?, ?>[] conditions; // List[]

	public Bytes32 getCoinName() {
		return coinName;
	}

	public void setCoinName(Bytes32 coinName) {
		this.coinName = coinName;
	}

	public Bytes32 getPuzzleHash() {
		return puzzleHash;
	}

	public void setPuzzleHash(Bytes32 puzzleHash) {
		this.puzzleHash = puzzleHash;
	}

	public LinkedTreeMap<?, ?>[] getConditions() {
		return conditions;
	}

	public void setConditions(LinkedTreeMap<?, ?>[] conditions) {
		this.conditions = conditions;
	}
}
