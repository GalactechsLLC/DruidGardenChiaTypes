package garden.druid.chia.crypt.BIP39;

import garden.druid.chia.crypt.sha.SHA;
import garden.druid.chia.types.bytes.Bytes;

import java.util.Arrays;

public class MnemonicGenerator {

    public String createMnemonic(final String entropyHex) {
        final int length = entropyHex.length();
        if (length % 2 != 0)
            throw new RuntimeException("Length of hex chars must be divisible by 2");
        final byte[] entropy = new byte[length / 2];
        try {
            for (int i = 0, j = 0; i < length; i += 2, j++) {
                entropy[j] = (byte) (Bytes.hexToBin(entropyHex.charAt(i)) << 4 | Bytes.hexToBin(entropyHex.charAt(i + 1)));
            }
            return createMnemonic(entropy);
        } finally {
            Arrays.fill(entropy, (byte) 0);
        }
    }
	
    public static String createMnemonic(final byte[] entropy) {
        final int[] wordIndexes = wordIndexes(entropy);
        try {
            return createMnemonic(wordIndexes);
        } finally {
            Arrays.fill(wordIndexes, 0);
        }
    }

    private static String createMnemonic(final int[] indexes) {
    	StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indexes.length; i++) {
            if (i > 0) builder.append(' ');
            builder.append(WordList.getWord(indexes[i]));
        }
        return builder.toString();
    }
    
    private static int[] wordIndexes(byte[] entropy) {
        final int ent = entropy.length * 8;
        entropyLengthPreChecks(ent);
        final byte[] entropyWithChecksum = Arrays.copyOf(entropy, entropy.length + 1);
        entropyWithChecksum[entropy.length] = firstByteOfSha256(entropy);
        final int cs = ent / 32;
        final int ms = (ent + cs) / 11;
        final int[] wordIndexes = new int[ms];
        for (int i = 0, wi = 0; wi < ms; i += 11, wi++) {
            wordIndexes[wi] = next11Bits(entropyWithChecksum, i);
        }
        return wordIndexes;
    }
    
    static int next11Bits(byte[] bytes, int offset) {
        final int skip = offset / 8;
        final int lowerBitsToRemove = (3 * 8 - 11) - (offset % 8);
        return (((int) bytes[skip] & 0xff) << 16 | ((int) bytes[skip + 1] & 0xff) << 8 | (lowerBitsToRemove < 8 ? ((int) bytes[skip + 2] & 0xff) : 0)) >> lowerBitsToRemove & (1 << 11) - 1;
    }
	
    private static byte firstByteOfSha256(final byte[] entropy) {
        final byte[] hash = SHA.hash256(entropy);
        final byte firstByte = hash[0];
        Arrays.fill(hash, (byte) 0);
        return firstByte;
    }

    private static void entropyLengthPreChecks(final int ent) {
        if (ent < 128)
            throw new RuntimeException("Entropy too low, 128-256 bits allowed");
        if (ent > 256)
            throw new RuntimeException("Entropy too high, 128-256 bits allowed");
        if (ent % 32 > 0)
            throw new RuntimeException("Number of entropy bits must be divisible by 32");
    }

}
