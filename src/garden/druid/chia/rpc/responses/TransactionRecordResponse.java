package garden.druid.chia.rpc.responses;

import garden.druid.chia.types.blockchain.TransactionRecord;

public class TransactionRecordResponse {
	private boolean success;
	private TransactionRecord transaction;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public TransactionRecord getTransaction() {
		return transaction;
	}

	public void setTransaction(TransactionRecord transaction) {
		this.transaction = transaction;
	}
}
