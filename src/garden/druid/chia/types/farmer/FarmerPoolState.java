package garden.druid.chia.types.farmer;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;
import garden.druid.chia.types.ints.NativeUInt8;

public class FarmerPoolState {
	
	private Bytes32 p2_singleton_puzzle_hash;
	private NativeUInt8 authentication_token_timeout;
	private NativeUInt64 default_difficulty, current_points, next_farmer_update, next_pool_info_update, points_acknowledged_since_start, points_found_since_start;
	private FarmerPoolConfig pool_config;
	
	
	public Bytes32 getP2_singleton_puzzle_hash() {
		return p2_singleton_puzzle_hash;
	}
	public void setP2_singleton_puzzle_hash(Bytes32 p2_singleton_puzzle_hash) {
		this.p2_singleton_puzzle_hash = p2_singleton_puzzle_hash;
	}
	public NativeUInt8 getAuthentication_token_timeout() {
		return authentication_token_timeout;
	}
	public void setAuthentication_token_timeout(NativeUInt8 authentication_token_timeout) {
		this.authentication_token_timeout = authentication_token_timeout;
	}
	public NativeUInt64 getDefault_difficulty() {
		return default_difficulty;
	}
	public void setDefault_difficulty(NativeUInt64 default_difficulty) {
		this.default_difficulty = default_difficulty;
	}
	public NativeUInt64 getCurrent_points() {
		return current_points;
	}
	public void setCurrent_points(NativeUInt64 current_points) {
		this.current_points = current_points;
	}
	public NativeUInt64 getNext_farmer_update() {
		return next_farmer_update;
	}
	public void setNext_farmer_update(NativeUInt64 next_farmer_update) {
		this.next_farmer_update = next_farmer_update;
	}
	public NativeUInt64 getNext_pool_info_update() {
		return next_pool_info_update;
	}
	public void setNext_pool_info_update(NativeUInt64 next_pool_info_update) {
		this.next_pool_info_update = next_pool_info_update;
	}
	public NativeUInt64 getPoints_acknowledged_since_start() {
		return points_acknowledged_since_start;
	}
	public void setPoints_acknowledged_since_start(NativeUInt64 points_acknowledged_since_start) {
		this.points_acknowledged_since_start = points_acknowledged_since_start;
	}
	public NativeUInt64 getPoints_found_since_start() {
		return points_found_since_start;
	}
	public void setPoints_found_since_start(NativeUInt64 points_found_since_start) {
		this.points_found_since_start = points_found_since_start;
	}
	public FarmerPoolConfig getPool_config() {
		return pool_config;
	}
	public void setPool_config(FarmerPoolConfig pool_config) {
		this.pool_config = pool_config;
	}
}
