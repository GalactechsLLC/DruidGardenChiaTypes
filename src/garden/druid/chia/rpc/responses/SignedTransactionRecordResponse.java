package garden.druid.chia.rpc.responses;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.blockchain.TransactionRecord;

public class SignedTransactionRecordResponse {
	private boolean success;
	@SerializedName(value = "signed_tx", alternate = "signedTx")
	private TransactionRecord signedTx;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public TransactionRecord getSigned_tx() {
		return signedTx;
	}

	public void setSigned_tx(TransactionRecord signed_tx) {
		this.signedTx = signed_tx;
	}
}
