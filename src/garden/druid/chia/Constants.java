package garden.druid.chia;

import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.ints.NativeUInt64;

public class Constants {

	public static final Bytes32 GENESIS_CHALLENGE = new Bytes32("ccd5bb71183532bff220ba46c268991a3ff07eb358e8255a65c30a2dce0e5fbb");//MAINNET
	public static final Bytes32 TESTNET_GENESIS_CHALLENGE = new Bytes32("ae83525ba8d1dd3f09b277de18ca3e43fc0af20d20c4b3e92ef2a48bd291ccb2");// TESTNET
	public static final int TESTNET_MIN_PLOT_SIZE = 25;
	public static final int MIN_PLOT_SIZE = 32;
	public static final int MAX_PLOT_SIZE = 50;
	public static final NativeUInt64 MAX_TRANSACTION_AMOUNT = new NativeUInt64(446250000000000l);
}
