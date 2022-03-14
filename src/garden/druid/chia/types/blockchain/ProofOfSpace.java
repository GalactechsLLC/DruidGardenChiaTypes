package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.base.logging.Logger;
import garden.druid.chia.Constants;
import garden.druid.chia.clvmwrapper.Puzzles;
import garden.druid.chia.crypt.sha.SHA;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes48;
import garden.druid.chia.types.bytes.UnsizedBytes;
import garden.druid.chia.types.interfaces.Streamable;
import garden.druid.chia.types.ints.NativeUInt8;

import java.nio.ByteBuffer;
import java.util.logging.Level;

public class ProofOfSpace implements Streamable {
	public static final transient int NUMBER_ZERO_BITS_PLOT_FILTER = 9;
	private Bytes32 challenge;
	@SerializedName(value = "pool_contract_puzzle_hash", alternate = "poolContractPuzzleHash")
	private Bytes32 poolContractPuzzleHash;
	@SerializedName(value = "plot_public_key", alternate = "plotPublicKey")
	private Bytes48 plotPublicKey;
	@SerializedName(value = "pool_public_key", alternate = "poolPublicKey")
	private Bytes48 poolPublicKey;
	private UnsizedBytes proof;
	private int size;
	
	private Bytes32 getPlotId() {
		if (this.poolPublicKey == null || this.poolContractPuzzleHash == null) {
			if (this.poolPublicKey == null) {
				return calculatePlotIdPh(this.poolContractPuzzleHash, this.plotPublicKey);
			}
			return calculatePlotIdPk(this.poolPublicKey, this.plotPublicKey);
		} else {
			return null;
		}
	}

	public Bytes32 verifyAndGetQualityString(Bytes32 originalChallengeHash, Bytes32 signagePoint) {
		return verifyAndGetQualityString(originalChallengeHash, signagePoint, false);
	}

	public Bytes32 verifyAndGetQualityString(Bytes32 originalChallengeHash, Bytes32 signagePoint, boolean testnet) {
		if (this.poolPublicKey == null && this.poolContractPuzzleHash == null) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: null value for pool_public_key and pool_contract_puzzle_hash");
			return null;
		}
		if (this.poolPublicKey != null && this.poolContractPuzzleHash != null) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: Non Null value for both for pool_public_key and pool_contract_puzzle_hash");
			return null;
		}
		if (this.size < (testnet ? Constants.TESTNET_MIN_PLOT_SIZE : Constants.MIN_PLOT_SIZE)) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: Plot failed MIN_PLOT_SIZE");
			return null;
		}
		if (this.size > Constants.MAX_PLOT_SIZE) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: Plot failed MAX_PLOT_SIZE");
			return null;
		}
		Bytes32 plotId = getPlotId();
		if (plotId == null) {
			return null;
		}
		if (!challenge.equals(calculatePosChallenge(plotId, originalChallengeHash, signagePoint))) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: New challenge is not challenge");
			return null;
		}
		if (!passesPlotFilter(plotId, originalChallengeHash, signagePoint)) {
			Logger.getInstance().log(Level.WARNING, "Failed to Verify ProofOfSpace: Plot Failed to Pass Filter");
			return null;
		}
		return getQualityString(plotId);
	}

	private Bytes32 getQualityString(Bytes32 plotId) {
		return Puzzles.validateProof(plotId, this.size, this.challenge, this.proof);
	}

	public Bytes32 calculatePlotIdPk(Bytes48 poolPublicKey, Bytes48 plotPublicKey) {
		ByteBuffer buf = ByteBuffer.allocate(96);
		buf.put(poolPublicKey.getBytes());
		buf.put(plotPublicKey.getBytes());
		buf.flip();
		return new Bytes32(SHA.hash256(buf.array()));
	}

	public Bytes32 calculatePlotIdPh(Bytes32 poolContractPuzzleHash, Bytes48 plotPublicKey) {
		ByteBuffer buf = ByteBuffer.allocate(80);
		buf.put(poolContractPuzzleHash.getBytes());
		buf.put(plotPublicKey.getBytes());
		buf.flip();
		return new Bytes32(SHA.hash256(buf.array()));
	}

	public boolean passesPlotFilter(Bytes32 plotId, Bytes32 challengeHash, Bytes32 signagePoint) {
		if (plotId == null || challengeHash == null || signagePoint == null) {
			return false;
		}
		boolean[] filter = new boolean[256];
		int index = 0;
		for(byte b : calculatePlotFilterInput(plotId, challengeHash, signagePoint).getBytes()) {
			for(int i = 7; i >=0; i-- ) {
				filter[index++] = (b >> i & 1) == 1;
			}
		}
		for (int i = 0; i < NUMBER_ZERO_BITS_PLOT_FILTER; i++) {
			if (filter[i]) {
				return false;
			}
		}
		return true;
	}

	public Bytes32 calculatePlotFilterInput(Bytes32 plotId, Bytes32 challengeHash, Bytes32 signagePoint) {
		if (plotId == null || challengeHash == null || signagePoint == null) {
			return null;
		}
		ByteBuffer buf = ByteBuffer.allocate(96);
		buf.put(plotId.getBytes());
		buf.put(challengeHash.getBytes());
		buf.put(signagePoint.getBytes());
		buf.flip();
		return new Bytes32(SHA.hash256(buf.array()));
	}

	public Bytes32 calculatePosChallenge(Bytes32 plotId, Bytes32 challengeHash, Bytes32 signagePoint) {
		return new Bytes32(SHA.hash256((calculatePlotFilterInput(plotId, challengeHash, signagePoint).getBytes())));
	}

	@Override
	public Bytes32 hash() {
		ByteBuffer byteBuf = ByteBuffer.allocate(1024);
		byteBuf.put(this.getChallenge().getBytes());
		if (this.getPoolPublicKey() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getPoolPublicKey().getBytes());
		} else {
			byteBuf.put((byte) 0);
		}
		if (this.getPoolContractPuzzleHash() != null) {
			byteBuf.put((byte) 1);
			byteBuf.put(this.getPoolContractPuzzleHash().getBytes());
		} else {
			byteBuf.put((byte) 0);
		}
		byteBuf.put(this.getPlotPublicKey().getBytes());
		byteBuf.put(new NativeUInt8(this.getSize()).toByteArray());
		byteBuf.put(this.getProof().getBytes());
		byteBuf.flip();
		byte[] objAry = new byte[byteBuf.limit()];
		byteBuf.get(objAry, 0, byteBuf.limit());
		return new Bytes32(SHA.hash256(objAry));
	}
	
	public Bytes32 getChallenge() {
		return challenge;
	}

	public void setChallenge(Bytes32 challenge) {
		this.challenge = challenge;
	}

	public Bytes48 getPlotPublicKey() {
		return plotPublicKey;
	}

	public void setPlotPublicKey(Bytes48 plotPublicKey) {
		this.plotPublicKey = plotPublicKey;
	}

	public Bytes32 getPoolContractPuzzleHash() {
		return poolContractPuzzleHash;
	}

	public void setPoolContractPuzzleHash(Bytes32 poolContractPuzzleHash) {
		this.poolContractPuzzleHash = poolContractPuzzleHash;
	}

	public Bytes48 getPoolPublicKey() {
		return poolPublicKey;
	}

	public void setPoolPublicKey(Bytes48 poolPublicKey) {
		this.poolPublicKey = poolPublicKey;
	}

	public UnsizedBytes getProof() {
		return proof;
	}

	public void setProof(UnsizedBytes proof) {
		this.proof = proof;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
