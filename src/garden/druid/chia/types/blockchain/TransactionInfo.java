package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes96;
import garden.druid.chia.types.ints.NativeUInt64;

import java.util.ArrayList;

public class TransactionInfo {

	@SerializedName(value = "aggregated_signature", alternate = "aggregatedSignature")
	private Bytes96 aggregatedSignature;
	@SerializedName(value = "generator_refs_root", alternate = "generatorRefsFoot")
	private Bytes32 generatorRefsFoot;
	@SerializedName(value = "generator_root", alternate = "generatorRoot")
	private Bytes32 generatorRoot;
	private NativeUInt64 cost, fees;
	@SerializedName(value = "reward_claims_incorporated", alternate = "rewardClaimsIncorporated")
	private ArrayList<Coin> rewardClaimsIncorporated;

	public Bytes96 getAggregatedSignature() {
		return aggregatedSignature;
	}

	public void setAggregatedSignature(Bytes96 aggregatedSignature) {
		this.aggregatedSignature = aggregatedSignature;
	}

	public Bytes32 getGeneratorRefsRoot() {
		return generatorRefsFoot;
	}

	public void setGeneratorRefsRoot(Bytes32 generatorRefsFoot) {
		this.generatorRefsFoot = generatorRefsFoot;
	}

	public Bytes32 getGeneratorRoot() {
		return generatorRoot;
	}

	public void setGeneratorRoot(Bytes32 generatorRoot) {
		this.generatorRoot = generatorRoot;
	}

	public NativeUInt64 getCost() {
		return cost;
	}

	public void setCost(NativeUInt64 cost) {
		this.cost = cost;
	}

	public NativeUInt64 getFees() {
		return fees;
	}

	public void setFees(NativeUInt64 fees) {
		this.fees = fees;
	}

	public ArrayList<Coin> getRewardClaimsIncorporated() {
		return rewardClaimsIncorporated;
	}

	public void setRewardClaimsIncorporated(ArrayList<Coin> rewardClaimsIncorporated) {
		this.rewardClaimsIncorporated = rewardClaimsIncorporated;
	}
}
