package garden.druid.chia.types.farmer;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes48;

public class FarmerPoolConfig {

	private Bytes32 launcher_id, p2_singleton_puzzle_hash, payout_instructions, target_puzzle_hash;
	private Bytes48 authentication_public_key, owner_public_key;
	private String pool_url;
	
	public Bytes32 getLauncher_id() {
		return launcher_id;
	}
	public void setLauncher_id(Bytes32 launcher_id) {
		this.launcher_id = launcher_id;
	}
	public Bytes32 getP2_singleton_puzzle_hash() {
		return p2_singleton_puzzle_hash;
	}
	public void setP2_singleton_puzzle_hash(Bytes32 p2_singleton_puzzle_hash) {
		this.p2_singleton_puzzle_hash = p2_singleton_puzzle_hash;
	}
	public Bytes32 getPayout_instructions() {
		return payout_instructions;
	}
	public void setPayout_instructions(Bytes32 payout_instructions) {
		this.payout_instructions = payout_instructions;
	}
	public Bytes32 getTarget_puzzle_hash() {
		return target_puzzle_hash;
	}
	public void setTarget_puzzle_hash(Bytes32 target_puzzle_hash) {
		this.target_puzzle_hash = target_puzzle_hash;
	}
	public Bytes48 getAuthentication_public_key() {
		return authentication_public_key;
	}
	public void setAuthentication_public_key(Bytes48 authentication_public_key) {
		this.authentication_public_key = authentication_public_key;
	}
	public Bytes48 getOwner_public_key() {
		return owner_public_key;
	}
	public void setOwner_public_key(Bytes48 owner_public_key) {
		this.owner_public_key = owner_public_key;
	}
	public String getPool_url() {
		return pool_url;
	}
	public void setPool_url(String pool_url) {
		this.pool_url = pool_url;
	}
}
