package garden.druid.chia.rpc;

import garden.druid.chia.rpc.responses.*;
import garden.druid.chia.types.blockchain.*;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.exceptions.InvalidArgument;
import garden.druid.chia.types.ints.NativeUInt32;
import garden.druid.chia.types.ints.NativeUInt64;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class FullNodeRpcClient extends ChiaClient {

	public FullNodeRpcClient() {
		super();
	}

	public FullNodeRpcClient(String host, int port) {
		super(host, port);
	}

	/*
	 * BLOCKCHAIN FUNCTIONS
	 */

	public State get_blockchain_state() {
		try {
			ClientResponse<StateResponse> resp = post("/get_blockchain_state", "{}".getBytes(StandardCharsets.UTF_8), StateResponse.class);
			return resp.getData().getBlockshain_state();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FullBlock get_block(Bytes32 header_hash) {
		try {
			if(header_hash == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_block: header_hash is null");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("header_hash", header_hash);
			ClientResponse<BlockResponse> resp = post("/get_block", gson.toJson(params).getBytes(StandardCharsets.UTF_8), BlockResponse.class);
			return resp.getData().getBlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FullBlock[] get_blocks(NativeUInt32 start, NativeUInt32 end, boolean exclude_header_hash) {
		try {
			if(start == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_blocks: start is null");
			}
			if(end == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_blocks: end is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("start", start.toString());
			params.put("end", end.toString());
			params.put("exclude_header_hash", exclude_header_hash ? "True" : "False");
			ClientResponse<BlockArrayResponse> resp = post("/get_blocks", gson.toJson(params).getBytes(StandardCharsets.UTF_8), BlockArrayResponse.class);
			return resp.getData().getBlocks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public FullBlock[] get_all_blocks(NativeUInt32 start, NativeUInt32 end) {
		try {
			if(start == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_all_blocks: start is null");
			}
			if(end == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_all_blocks: end is null");
			}
			return get_blocks(start, end, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BlockRecord get_block_record_by_height(NativeUInt32 height) {
		try {
			if(height == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_block_record_by_height: height is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("height", height.toString());
			ClientResponse<BlockRecordResponse> resp = post("/get_block_record_by_height", gson.toJson(params).getBytes(StandardCharsets.UTF_8), BlockRecordResponse.class);
			return resp.getData().getBlockRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BlockRecord get_block_record(Bytes32 header_hash) {
		try {
			if(header_hash == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_block_record: header_hash is null");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("header_hash", header_hash);
			ClientResponse<BlockRecordResponse> resp = post("/get_block_record", gson.toJson(params).getBytes(StandardCharsets.UTF_8), BlockRecordResponse.class);
			return resp.getData().getBlockRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BlockRecord[] get_block_records(NativeUInt32 start, NativeUInt32 end) {
		try {
			if(start == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_block_records: start is null");
			}
			if(end == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_block_records: end is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("start", start.toString());
			params.put("end", end.toString());
			ClientResponse<BlockRecordArrayResponse> resp = post("/get_block_records", gson.toJson(params).getBytes(StandardCharsets.UTF_8), BlockRecordArrayResponse.class);
			return resp.getData().getBlockRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public UnfinishedBlock[] get_unfinished_block_headers() {
		try {
			ClientResponse<UnfinishedBlockArrayResponse> resp = post("/get_unfinished_block_headers", "{}".getBytes(StandardCharsets.UTF_8), UnfinishedBlockArrayResponse.class);
			return resp.getData().getUnfinishedBlocks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NativeUInt64 get_network_space(NativeUInt32 older_block_height, NativeUInt32 newer_block_height) {
		try {
			if(older_block_height == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: older_block_height is null");
			}
			if(newer_block_height == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: newer_block_height is null");
			}
			if(older_block_height.equals(newer_block_height)) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: New and old must not be the same");
			}
			BlockRecord newer_block = get_block_record_by_height(newer_block_height);
			BlockRecord older_block = get_block_record_by_height(older_block_height);
			return get_network_space(older_block.getHeaderHash(), newer_block.getHeaderHash());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NativeUInt64 get_network_space(Bytes32 older_block_header_hash, Bytes32 newer_block_header_hash) {
		try {
			if(older_block_header_hash == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: older_block_header_hash is null");
			}
			if(newer_block_header_hash == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: newer_block_header_hash is null");
			}
			if(older_block_header_hash.equals(newer_block_header_hash)) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_network_space: New and old must not be the same");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("older_block_header_hash", older_block_header_hash);
			params.put("newer_block_header_hash", newer_block_header_hash);
			ClientResponse<NetSpaceResponse> resp = post("/get_network_space", gson.toJson(params).getBytes(StandardCharsets.UTF_8), NetSpaceResponse.class);
			return resp.getData().getNetspace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public AdditionsAndRemovals get_additions_and_removals(Bytes32 header_hash) {
		try {
			if(header_hash == null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_additions_and_removals: header_hash is null");
			}
			HashMap<String, Bytes32> params = new HashMap<String, Bytes32>();
			params.put("header_hash", header_hash);
			ClientResponse<AdditionsAndRemovalsResponse> resp = post("/get_additions_and_removals", gson.toJson(params).getBytes(StandardCharsets.UTF_8), AdditionsAndRemovalsResponse.class);
			AdditionsAndRemovals toRtn = new AdditionsAndRemovals();
			toRtn.setAdditions(resp.getData().getAdditions());
			toRtn.setRemovals(resp.getData().getRemovals());
			return toRtn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NativeUInt64 get_initial_freeze_period() {
		try {
			ClientResponse<InitialFreezePeriodResponse> resp = post("/get_initial_freeze_period", "{}".getBytes(StandardCharsets.UTF_8), InitialFreezePeriodResponse.class);
			return resp.getData().getInitialFreezePeriod();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public NetworkInfo get_network_info() {
		try {
			ClientResponse<NetworkInfoResponse> resp = post("/get_network_info", "{}".getBytes(StandardCharsets.UTF_8), NetworkInfoResponse.class);
			NetworkInfo networkInfo = new NetworkInfo();
			networkInfo.setNetworkName(resp.getData().getNetwork_name());
			networkInfo.setNetworkPrefix(resp.getData().getNetwork_prefix());
			return networkInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SignagePointOrEOS get_recent_signage_point_or_eos(Bytes32 sp_hash, Bytes32 challenge_hash) {
		try {
			if(sp_hash != null && challenge_hash != null) {
				throw new InvalidArgument("InvalidArgument FullNodeRpcClient.get_recent_signage_point_or_eos: One of sp_hash or challenge_hash must be null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			if (sp_hash != null) {
				params.put("sp_hash", sp_hash.toString());
			} else if (challenge_hash != null) {
				params.put("challenge_hash", challenge_hash.toString());
			} else {
				return null;
			}
			ClientResponse<SignagePointOrEOSResponse> resp = post("/get_recent_signage_point_or_eos", gson.toJson(params).getBytes(StandardCharsets.UTF_8), SignagePointOrEOSResponse.class);
			SignagePointOrEOS soe = new SignagePointOrEOS();
			soe.setSignagePoint(resp.getData().getSignage_point());
			soe.setEos(resp.getData().getEos());
			soe.setTimeReceived(resp.getData().getTime_received());
			soe.setReverted(resp.getData().isReverted());
			return soe;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * COIN FUNCTIONS
	 */

	public CoinRecord[] get_coin_records_by_puzzle_hash(Bytes32 puzzle_hash, boolean include_spent_coins, NativeUInt32 start_height, NativeUInt32 end_height) {
		try {
			if(puzzle_hash == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_coin_records_by_puzzle_hash: puzzle_hash is null");
			}
			// We are sending a JSON Dict with the params instead of using URL encoding
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("puzzle_hash", puzzle_hash.toString());
			params.put("include_spent_coins", include_spent_coins);
			if (start_height != null) {
				params.put("start_height", start_height.toString());
			}
			if (end_height != null) {
				params.put("end_height", end_height.toString());
			}
			ClientResponse<CoinRecordArrayResponse> resp = post("/get_coin_records_by_puzzle_hash", gson.toJson(params).getBytes(StandardCharsets.UTF_8), CoinRecordArrayResponse.class);
			return resp.getData().getCoinRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoinRecord[] get_coin_records_by_puzzle_hashes(ArrayList<Bytes32> puzzle_hashes, boolean include_spent_coins, NativeUInt32 start_height, NativeUInt32 end_height) {
		try {
			if(puzzle_hashes == null || puzzle_hashes.size() == 0) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_coin_records_by_puzzle_hashes: puzzle_hashes is empty or null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("puzzle_hashes", puzzle_hashes);
			params.put("include_spent_coins", include_spent_coins);
			if (start_height != null) {
				params.put("start_height", start_height);
			}
			if (end_height != null) {
				params.put("end_height", end_height);
			}
			ClientResponse<CoinRecordArrayResponse> resp = post("/get_coin_records_by_puzzle_hashes", gson.toJson(params).getBytes(StandardCharsets.UTF_8), CoinRecordArrayResponse.class);
			return resp.getData().getCoinRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoinRecord get_coin_record_by_name(String name) {
		try {
			if(name == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_coin_record_by_name: name is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("name", name);
			ClientResponse<CoinRecordResponse> resp = post("/get_coin_record_by_name", gson.toJson(params).getBytes(StandardCharsets.UTF_8), CoinRecordResponse.class);
			return resp.getData().getCoinRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoinRecord[] get_coin_records_by_parent_ids(ArrayList<Bytes32> parent_ids, boolean include_spent_coins, NativeUInt32 start_height, NativeUInt32 end_height) {
		try {
			if(parent_ids == null || parent_ids.size() == 0) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_coin_records_by_parent_ids: parent_ids is empty or null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("parent_ids", parent_ids);
			params.put("include_spent_coins", include_spent_coins);
			if (start_height != null) {
				params.put("start_height", start_height);
			}
			if (end_height != null) {
				params.put("end_height", end_height);
			}
			ClientResponse<CoinRecordArrayResponse> resp = post("/get_coin_records_by_parent_ids", gson.toJson(params).getBytes(StandardCharsets.UTF_8), CoinRecordArrayResponse.class);
			return resp.getData().getCoinRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TXStatus push_tx(SpendBundle spend_bundle) {
		try {
			if(spend_bundle == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.push_tx: spend_bundle is null");
			}
			HashMap<String, SpendBundle> params = new HashMap<String, SpendBundle>();
			params.put("spend_bundle", spend_bundle);
			ClientResponse<TXResponse> resp = post("/push_tx", gson.toJson(params).getBytes(StandardCharsets.UTF_8), TXResponse.class);
			return resp.getData().getTXStatus();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoinSpend get_puzzle_and_solution(String coin_id, NativeUInt32 height) {
		try {
			if(coin_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_puzzle_and_solution: coin_id is null");
			}
			if(height == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_puzzle_and_solution: height is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("coin_id", coin_id);
			params.put("height", height.toBigInteger());
			ClientResponse<CoinSpendResponse> resp = post("/get_puzzle_and_solution", gson.toJson(params).getBytes(StandardCharsets.UTF_8), CoinSpendResponse.class);
			return resp.getData().getCoinSpend();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public CoinSpend get_coin_spend(CoinRecord coin_record) {
		try {
			if(coin_record == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_coin_spend: coin_record is null");
			}
			if (coin_record.isSpent() == false) {
				return null;
			}
			return get_puzzle_and_solution(coin_record.getCoin().name(), coin_record.getSpentBlockIndex());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * MEMPOOL FUNCTIONS
	 */

	public ArrayList<String> get_all_mempool_tx_ids() {
		try {
			ClientResponse<MempoolTXResponse> resp = post("/get_all_mempool_tx_ids", "{}".getBytes(StandardCharsets.UTF_8), MempoolTXResponse.class);
			return resp.getData().getTx_ids();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, MempoolItem> get_all_mempool_items() {
		try {
			ClientResponse<MempoolItemsResponse> resp = post("/get_all_mempool_items", "{}".getBytes(StandardCharsets.UTF_8), MempoolItemsResponse.class);
			return resp.getData().getMempoolItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public MempoolItem get_mempool_item_by_tx_id(String tx_id) {
		try {
			if(tx_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_mempool_item_by_tx_id: tx_id is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("tx_id", tx_id);
			ClientResponse<MempoolItemResponse> resp = post("/get_mempool_item_by_tx_id", gson.toJson(params).getBytes(StandardCharsets.UTF_8), MempoolItemResponse.class);
			return resp.getData().getMempoolItem();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
