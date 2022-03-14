package garden.druid.chia.types.blockchain;

public class MempoolInclusionStatus {
	final static int SUCCESS = 1; // Transaction added to mempool
	final static int PENDING = 2; // Transaction not yet added to mempool
	final static int FAILED = 3; // Transaction was invalid and dropped
}
