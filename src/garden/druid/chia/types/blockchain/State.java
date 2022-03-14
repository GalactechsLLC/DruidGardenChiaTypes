package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.ints.NativeUInt128;
import garden.druid.chia.types.ints.NativeUInt64;

public class State {

	private NativeUInt64 difficulty;
	@SerializedName(value = "sub_slot_iters", alternate = "subSlotIters")
	private NativeUInt64 subSlotIters;
	@SerializedName(value = "mempool_size", alternate = "mempoolSize")
	private int mempoolSize;
	@SerializedName(value = "genesis_challenge_initialized", alternate = "genesisChallengeInitialized")
	private boolean genesisChallengeInitialized;
	private NativeUInt128 space;
	private BlockRecord peak;
	private Sync sync;

	public NativeUInt64 getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(NativeUInt64 difficulty) {
		this.difficulty = difficulty;
	}

	public int getMempoolSize() {
		return mempoolSize;
	}

	public void setMempoolSize(int mempoolSize) {
		this.mempoolSize = mempoolSize;
	}

	public boolean isGenesisChallengeInitialized() {
		return genesisChallengeInitialized;
	}

	public void setGenesisChallengeInitialized(boolean genesisChallengeInitialized) {
		this.genesisChallengeInitialized = genesisChallengeInitialized;
	}

	public NativeUInt128 getSpace() {
		return space;
	}

	public void setSpace(NativeUInt128 space) {
		this.space = space;
	}

	public NativeUInt64 getSubSlotIters() {
		return subSlotIters;
	}

	public void setSubSlotIters(NativeUInt64 subSlotIters) {
		this.subSlotIters = subSlotIters;
	}

	public BlockRecord getPeak() {
		return peak;
	}

	public void setPeak(BlockRecord peak) {
		this.peak = peak;
	}

	public Sync getSync() {
		return sync;
	}

	public void setSync(Sync sync) {
		this.sync = sync;
	}
}
