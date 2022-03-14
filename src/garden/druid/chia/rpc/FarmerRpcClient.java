package garden.druid.chia.rpc;

import garden.druid.chia.rpc.responses.*;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.exceptions.InvalidArgument;
import garden.druid.chia.types.farmer.FarmerPoolState;
import garden.druid.chia.types.farmer.FarmerRewardTarget;
import garden.druid.chia.types.farmer.FarmerSignagePointInfo;
import garden.druid.chia.types.farmer.Harvester;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class FarmerRpcClient extends ChiaClient {

	public FarmerRpcClient() {
		super();
	}

	public FarmerRpcClient(String host, int port) {
		super(host, port);
	}
	
	public FarmerSignagePointInfo get_signage_point(Bytes32 sp_hash) {
		try {
			if(sp_hash == null) {
				throw new InvalidArgument("InvalidArgument FarmerRpcClient.get_signage_point: sp_hash is null");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("sp_hash", sp_hash);
			ClientResponse<FarmerSignagePointResponse> resp = post("/get_signage_point", gson.toJson(params).getBytes(StandardCharsets.UTF_8), FarmerSignagePointResponse.class);
			if(resp.getData().isSuccess()) {
				return (FarmerSignagePointInfo) resp.getData();
			} else {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FarmerSignagePointInfo> get_signage_points() {
		try {
			ClientResponse<FarmerSignagePointsResponse> resp = post("/get_signage_points", "{}".getBytes(StandardCharsets.UTF_8), FarmerSignagePointsResponse.class);
			return resp.getData().getSignagePoints();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public FarmerRewardTarget get_reward_targets(boolean search_for_private_key) {
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("search_for_private_key", search_for_private_key ? "True" : "False");
			ClientResponse<FarmerRewardTargetResponse> resp = post("/get_reward_targets", gson.toJson(params).getBytes(StandardCharsets.UTF_8), FarmerRewardTargetResponse.class);
			return (FarmerRewardTarget) resp.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<FarmerPoolState> get_pool_state() {
		try {
			ClientResponse<PoolStateResponse> resp = post("/get_pool_state", "{}".getBytes(StandardCharsets.UTF_8), PoolStateResponse.class);
			return resp.getData().getPool_state();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Harvester> get_harvesters() {
		try {
			ClientResponse<HarvesterResponse> resp = post("/get_harvesters", "{}".getBytes(StandardCharsets.UTF_8), HarvesterResponse.class);
			return resp.getData().getHarvesters();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String get_pool_login_link(Bytes32 launcher_id) {
		try {
			this.debug = true;
			if(launcher_id == null) {
				throw new InvalidArgument("InvalidArgument FarmerRpcClient.get_pool_login_link: launcher_id is null");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("launcher_id", launcher_id);
			ClientResponse<String> resp = post("/get_pool_login_link", gson.toJson(params).getBytes(StandardCharsets.UTF_8), String.class);
			return resp.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
