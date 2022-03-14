package garden.druid.chia.crypt.bls_blst_bindings;

import garden.druid.chia.crypt.BIP39.MnemonicGenerator;
import garden.druid.chia.crypt.BIP39.MnemonicValidator;
import garden.druid.chia.crypt.hmac.HMAC;
import garden.druid.chia.types.bytes.Bytes;
import garden.druid.chia.types.bytes.Bytes32;
import garden.druid.chia.types.bytes.Bytes48;
import garden.druid.chia.types.bytes.Bytes96;
import supranational.blst.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.List;

public class BLS {
	
	private final static BigInteger n = new BigInteger(Bytes.parseHexBinary("0x73EDA753299D7D483339D80809A1D80553BDA402FFFE5BFEFFFFFFFF00000001"));
	public static final String basic_scheme_dst = "BLS_SIG_BLS12381G2_XMD:SHA-256_SSWU_RO_NUL_";
	public static final String aug_scheme_dst = "BLS_SIG_BLS12381G2_XMD:SHA-256_SSWU_RO_AUG_";
	public static final String pop_scheme_dst = "BLS_SIG_BLS12381G2_XMD:SHA-256_SSWU_RO_POP_";
	public static final String pop_scheme_pop_dst = "BLS_POP_BLS12381G2_XMD:SHA-256_SSWU_RO_POP_";
	
	public static boolean verifySignature(Bytes48 publicKey, byte[] msg, Bytes96 signature) {
		P2_Affine sig = new P2_Affine(signature.getBytes());
		P1_Affine pk = new P1_Affine(publicKey.getBytes());
		if (!pk.in_group()) {
			return false;
		}
		Pairing ctx = new Pairing(true, aug_scheme_dst);
		ctx.aggregate(pk, sig, msg, publicKey.getBytes());
		ctx.commit();
		return ctx.finalverify();
	}
	
	public static boolean aggregateVerifySignature(List<Bytes48> publicKeys, List<byte[]> msgs, Bytes96 signature) {
		P1_Affine pk = null;
		Pairing ctx = new Pairing(true, aug_scheme_dst);
		for (int i = 0; i < publicKeys.size(); i++) {
			pk = new P1_Affine(publicKeys.get(i).getBytes());
			if (!pk.in_group()) {
				return false;
			}
			ctx.aggregate(pk, null, msgs.get(i), publicKeys.get(i).getBytes());
		}
		ctx.commit();
		return ctx.finalverify(new PT(new P2_Affine(signature.getBytes())));
	}
	
	public static Bytes96 sign(Bytes32 privateKey, byte[] payload) {		
		SecretKey SK = new SecretKey();
		SK.from_bendian(privateKey.getBytes());
		P2 sig = new P2().hash_to(payload, aug_scheme_dst, new P1(SK).compress()).sign_with(SK);
		return new Bytes96(sig.compress());
	}
	
	public static P1 getPublicKey(SecretKey sk) {
		return new P1(sk);
	}
	
	public static Bytes96 signAggregate(List<Bytes32> privateKeys, List<byte[]> payloads) {		
		P2 sig = new P2();
		SecretKey tmpPriv = null;
		for(int i = 0; i < privateKeys.size(); i++) {
			P2 tmp = new P2();
			tmpPriv = new SecretKey();
			tmpPriv.from_bendian(privateKeys.get(i).getBytes());
			tmp.hash_to(payloads.get(i), aug_scheme_dst, new P1(tmpPriv).compress()).sign_with(tmpPriv);
			sig = sig.add(tmp);
		}
		byte[] sig_for_wire = sig.compress();
		return new Bytes96(sig_for_wire);
	}
	
	public static Bytes96 aggregate(List<Bytes96> sigs) {		
		P2 sig = new P2();
		for(int i = 0; i < sigs.size(); i++) {
			sig = sig.add(new P2(sigs.get(i).getBytes()));
		}
		return new Bytes96(sig.compress());
	}
	
	
	public static SecretKey loadKey(BigInteger keyInt) {
		final byte[] key = keyInt.toByteArray();
		SecretKey SK = new SecretKey();
		SK.from_bendian(new Bytes32(key).getBytes());
		return SK;
	}

	public static SecretKey generateKey(byte[] seed) {
		SecretKey SK = new SecretKey();
		SK.keygen(seed);
		return SK;
	}
	
	public static SecretKey KeyFromMnemonic(List<String> words) {
		if(!MnemonicValidator.validate(words)) {
			return null;
		}
		return loadKey(new BigInteger(1,HMAC.extract_expand(48, Bytes.add(mnemonicToSeed(MnemonicGenerator.createMnemonic(MnemonicValidator.getEntropy(words)), ""), new byte[] {0}), "BLS-SIG-KEYGEN-SALT-".getBytes(), new byte[] {0, 48})).mod(n));
	}
	
	private static byte[] mnemonicToSeed(String mnemonic, String passphrase) {
		String saltString = "mnemonic" + passphrase;
		byte[] salt = Normalizer.normalize(saltString, Normalizer.Form.NFKD).getBytes(StandardCharsets.UTF_8);
	    byte[] mnemonic_normalized = Normalizer.normalize(mnemonic, Normalizer.Form.NFKD).getBytes(StandardCharsets.UTF_8);
	    byte[] seed = HMAC.pbkdf2_hmac(salt, mnemonic_normalized, 2048);
	    assert seed.length == 64;
	    return seed;
	}
}
