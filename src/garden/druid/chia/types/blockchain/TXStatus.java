package garden.druid.chia.types.blockchain;

public enum TXStatus {
	SUCCESS(1), // Transaction added to mempool
	PENDING(2), // Transaction not yet added to mempool
	FAILED(3); // Transaction was invalid and dropped

	private int value = 3;

	TXStatus(int value) {
		this.setValue(value);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
