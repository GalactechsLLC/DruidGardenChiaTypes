package garden.druid.chia.types.blockchain;

import com.google.gson.annotations.SerializedName;
import garden.druid.chia.crypt.bls_blst_bindings.BLS;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes96;
import garden.druid.chia.types.ints.NativeUInt8;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class SpendBundle {

	@SerializedName(value = "coin_spends", alternate = "coinSpends")
	private List<CoinSpend> coinSpends;
	@SerializedName(value = "aggregated_signature", alternate = "aggregatedSignature")
	private Bytes96 aggregatedSignature;

	public SpendBundle(List<CoinSpend> coinSpends) {
		this.coinSpends = coinSpends;
		this.aggregatedSignature = new Bytes96("0xc00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
	}

	public SpendBundle(List<CoinSpend> coinSpends, Bytes96 aggregatedSignature) {
		this.coinSpends = coinSpends;
		this.aggregatedSignature = aggregatedSignature;
	}

	public Bytes96 getAggregatedSignature() {
		return aggregatedSignature;
	}

	public void setAggregatedSignature(Bytes96 aggregatedSignature) {
		this.aggregatedSignature = aggregatedSignature;
	}

	public List<CoinSpend> getCoinSpends() {
		return coinSpends;
	}

	public void setCoinSpends(List<CoinSpend> coinSpends) {
		this.coinSpends = coinSpends;
	}
	
	public static SpendBundle aggregate(SpendBundle... SpendBundles) {
		List<CoinSpend> coinSpends = new ArrayList<CoinSpend>();
		List<Bytes96> sigs = new ArrayList<Bytes96>();
        for(SpendBundle bundle : SpendBundles) {
        	coinSpends.addAll(bundle.getCoinSpends());
            sigs.add(bundle.getAggregatedSignature());
        }
        Bytes96 aggregatedSignature = BLS.aggregate(sigs);
        return new SpendBundle(coinSpends, aggregatedSignature);
	}
	
	public Bytes32 name() {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			ByteBuffer byteBuf = ByteBuffer.allocate(8 + this.coinSpends.size() * (32+32+9+1204+1024) + 96);
			byteBuf.put(new NativeUInt8(this.coinSpends.size()).toByteArray());
			for(CoinSpend spend : coinSpends) {
				byteBuf.put(spend.getCoin().getParentCoinInfo().getBytes());
				byteBuf.put(spend.getCoin().getPuzzleHash().getBytes());
				byteBuf.put(spend.getCoin().getAmount().chiaIntToBytes());
				byteBuf.put(spend.getPuzzleReveal().getBytes());
				byteBuf.put(spend.getSolution().getBytes());
			}
			byteBuf.put(this.aggregatedSignature.getBytes());
			byteBuf.flip();
			md = MessageDigest.getInstance("SHA-256");
			byte[] objAry = new byte[byteBuf.limit()];
			byteBuf.get(objAry, 0, byteBuf.limit());
			return new Bytes32(md.digest(objAry));
		} catch (NoSuchAlgorithmException e) { // Shouldnt Ever Happen
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean equals(Object other) {
		if( other == null || !(other instanceof SpendBundle)) {
			return false;
		}
		SpendBundle sbOther = (SpendBundle) other;
		return this.name().equals(sbOther.name());
	}

}
