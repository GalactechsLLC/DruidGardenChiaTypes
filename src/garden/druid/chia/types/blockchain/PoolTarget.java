package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt32;

public class PoolTarget {

	@SerializedName(value = "max_height", alternate = "maxHeight")
	private NativeUInt32 maxHeight;
	@SerializedName(value = "puzzle_hash", alternate = "puzzleHash")
	private Bytes32 puzzleHash;

	public NativeUInt32 getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(NativeUInt32 maxHeight) {
		this.maxHeight = maxHeight;
	}

	public Bytes32 getPuzzleHash() {
		return puzzleHash;
	}

	public void setPuzzleHash(Bytes32 puzzleHash) {
		this.puzzleHash = puzzleHash;
	}
}
