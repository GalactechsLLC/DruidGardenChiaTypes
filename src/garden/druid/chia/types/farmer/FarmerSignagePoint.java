package garden.druid.chia.types.farmer;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;
import garden.druid.chia.types.ints.NativeUInt8;

public class FarmerSignagePoint {
	private Bytes32 challenge_hash, challenge_chain_sp, reward_chain_sp;
	private NativeUInt64 sub_slot_iters, difficulty;
	private NativeUInt8 signage_point_index;
	
	public Bytes32 getChallenge_hash() {
		return challenge_hash;
	}
	public void setChallenge_hash(Bytes32 challenge_hash) {
		this.challenge_hash = challenge_hash;
	}
	public Bytes32 getChallenge_chain_sp() {
		return challenge_chain_sp;
	}
	public void setChallenge_chain_sp(Bytes32 challenge_chain_sp) {
		this.challenge_chain_sp = challenge_chain_sp;
	}
	public Bytes32 getReward_chain_sp() {
		return reward_chain_sp;
	}
	public void setReward_chain_sp(Bytes32 reward_chain_sp) {
		this.reward_chain_sp = reward_chain_sp;
	}
	public NativeUInt64 getSub_slot_iters() {
		return sub_slot_iters;
	}
	public void setSub_slot_iters(NativeUInt64 sub_slot_iters) {
		this.sub_slot_iters = sub_slot_iters;
	}
	public NativeUInt64 getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(NativeUInt64 difficulty) {
		this.difficulty = difficulty;
	}
	public NativeUInt8 getSignage_point_index() {
		return signage_point_index;
	}
	public void setSignage_point_index(NativeUInt8 signage_point_index) {
		this.signage_point_index = signage_point_index;
	}
}
