package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;

public class DelayedPuzInfo {

	@SerializedName(value = "delay_time", alternate = "delayTime")
	private NativeUInt64 delayTime;
	
	@SerializedName(value = "delay_puzzle_hash", alternate = "delayPuzzleHash")
	private Bytes32 delayPuzzleHash;

	public NativeUInt64 getDelayTime() {
		return delayTime;
	}

	public void setDelay_time(NativeUInt64 delayTime) {
		this.delayTime = delayTime;
	}

	public Bytes32 getDelayPuzzleHash() {
		return delayPuzzleHash;
	}

	public void setDelayPuzzleHash(Bytes32 delayPuzzleHash) {
		this.delayPuzzleHash = delayPuzzleHash;
	}
}
