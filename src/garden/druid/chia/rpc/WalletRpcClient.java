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

public class WalletRpcClient extends ChiaClient {

	public WalletRpcClient() {
		super();
	}

	public WalletRpcClient(String host, int port) {
		super(host, port);
	}

	public String log_in(NativeUInt32 wallet_fingerprint) {
		try {
			if(wallet_fingerprint == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.log_in: wallet_fingerprint is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("host", "https://backup.chia.net");
			params.put("fingerprint", wallet_fingerprint.longValue());
			params.put("type", "start");
			ClientResponse<LoginResponse> resp = post("/log_in", gson.toJson(params).getBytes(StandardCharsets.UTF_8), LoginResponse.class);
			return resp.getData().getFingerprint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String log_in_and_skip(NativeUInt32 wallet_fingerprint) {
		try {
			if(wallet_fingerprint == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.log_in_and_skip: wallet_fingerprint is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("host", "https://backup.chia.net");
			params.put("fingerprint", wallet_fingerprint.longValue());
			params.put("type", "skip");
			ClientResponse<LoginResponse> resp = post("/log_in", gson.toJson(params).getBytes(StandardCharsets.UTF_8), LoginResponse.class);
			return resp.getData().getFingerprint();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<WalletInfo> get_wallets() {
		try {
			ClientResponse<WalletsResponse> resp = post("/get_wallets", "{}".getBytes(StandardCharsets.UTF_8), WalletsResponse.class);
			return resp.getData().getWallets();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public WalletBalance get_wallet_balance(NativeUInt32 wallet_id) {
		try {
			if(wallet_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_wallet_balance: wallet_id is null");
			}
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("wallet_id", wallet_id.toString());
			ClientResponse<WalletBalanceResponse> resp = post("/get_wallet_balance", gson.toJson(params).getBytes(StandardCharsets.UTF_8), WalletBalanceResponse.class);
			return resp.getData().getWallet_balance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public WalletSync get_sync_status() {
		try {
			ClientResponse<WalletSync> resp = post("/get_sync_status", "{}".getBytes(StandardCharsets.UTF_8), WalletSync.class);
			return resp.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransactionRecord send_transaction(NativeUInt32 wallet_id, NativeUInt64 amount, String address, NativeUInt64 fee) {
		try {
			if(wallet_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction: wallet_id is null");
			}
			if(amount == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction: amount is null");
			}
			if(address == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction: address is null");
			}
			if(fee == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction: fee is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("wallet_id", wallet_id.toString());
			params.put("amount", amount.toBigInteger());
			params.put("address", address);
			params.put("fee", fee.toBigInteger());
			ClientResponse<TransactionRecord> resp = post("/send_transaction", gson.toJson(params).getBytes(StandardCharsets.UTF_8), TransactionRecord.class);
			return resp.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransactionRecord send_transaction_multi(NativeUInt32 wallet_id, ArrayList<PendingPayment> additions, NativeUInt64 fee) {
		try {
			if(wallet_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction_multi: wallet_id is null");
			}
			if(additions == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction_multi: additions is null");
			}
			if(fee == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.send_transaction_multi: fee is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("wallet_id", wallet_id.toString());
			params.put("additions", additions);
			params.put("fee", fee.toString());
			ClientResponse<TransactionRecordResponse> resp = post("/send_transaction_multi", gson.toJson(params).getBytes(StandardCharsets.UTF_8), TransactionRecordResponse.class);
			return resp.getData().getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransactionRecord get_transaction(NativeUInt32 wallet_id, Bytes32 transaction_id) {
		try {
			if(wallet_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_transaction: wallet_id is null");
			}
			if(transaction_id == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.get_transaction: transaction_id is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("wallet_id", wallet_id.longValue());
			params.put("transaction_id", transaction_id.toString());
			ClientResponse<TransactionRecordResponse> resp = post("/get_transaction", gson.toJson(params).getBytes(StandardCharsets.UTF_8), TransactionRecordResponse.class);
			return resp.getData().getTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TransactionRecord create_signed_transaction(ArrayList<Coin> additions, ArrayList<Coin> coins, NativeUInt64 fee) {
		try {
			if(additions == null) {
				throw new InvalidArgument("InvalidArgumentin FullNodeRpcClient.create_signed_transaction: additions is null");
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("additions", additions);
			if(coins != null && coins.size() > 0) {
				params.put("coins", coins);
			}
			params.put("fee", fee == null ? NativeUInt64.ZERO : fee);
			this.debug = true;
			ClientResponse<SignedTransactionRecordResponse> resp = post("/create_signed_transaction", gson.toJson(params).getBytes(StandardCharsets.UTF_8), SignedTransactionRecordResponse.class);
			this.debug = false;
			return resp.getData().getSigned_tx();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
