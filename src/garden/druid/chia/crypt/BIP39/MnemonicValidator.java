package garden.druid.chia.crypt.BIP39;

import garden.druid.base.logging.Logger;
import garden.druid.chia.crypt.sha.SHA;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;

public class MnemonicValidator {
	
	private static IndexedWord[] words;
	
	static {
		words = new IndexedWord[2048];
        for (int i = 0; i < 2048; i++) {
            words[i] = new IndexedWord(i, WordList.getWord(i));
        }
        Arrays.sort(words, IndexedWord.IndexedWordComparator);
    }
	
	public static boolean validate(final String mnemonic) {
		return validate(Arrays.asList(mnemonic.toString().split("\\s*")));
	}
	
	public static boolean validate(final Collection<String> mnemonic) {
		final int[] wordIndexes = findWordIndexes(mnemonic);
		try {
		    return validate(wordIndexes);
		} finally {
		    Arrays.fill(wordIndexes, 0);
		}
	}
	
	public static byte[] getEntropy(final String mnemonic) {
		return getEntropy(Arrays.asList(mnemonic.toString().split("\\s*")));
	}
	
	public static byte[] getEntropy(final Collection<String> mnemonic) {
		final int[] wordIndexes = findWordIndexes(mnemonic);
		try {
		    return getEntropy(wordIndexes);
		} finally {
		    Arrays.fill(wordIndexes, 0);
		}
	}
	
	public static byte[] getEntropy(final int[] wordIndexes) {
		final int ms = wordIndexes.length;
		final int entPlusCs = ms * 11;
		final int ent = (entPlusCs * 32) / 33;
		final int cs = ent / 32;
		if (entPlusCs != ent + cs) {
			Logger.getInstance().log(Level.SEVERE, "Invalid Word Count");
			return null;
		}
		final byte[] entropyWithChecksum = new byte[(entPlusCs + 7) / 8];
		wordIndexesToEntropyWithCheckSum(wordIndexes, entropyWithChecksum);
		Arrays.fill(wordIndexes, 0);
		return Arrays.copyOf(entropyWithChecksum, entropyWithChecksum.length - 1);
	}
	
    private static boolean validate(final int[] wordIndexes) {
		final int ms = wordIndexes.length;
		final int entPlusCs = ms * 11;
		final int ent = (entPlusCs * 32) / 33;
		final int cs = ent / 32;
		if (entPlusCs != ent + cs) {
			Logger.getInstance().log(Level.SEVERE, "Invalid Word Count");
			return false;
		}
		final byte[] entropyWithChecksum = new byte[(entPlusCs + 7) / 8];
		wordIndexesToEntropyWithCheckSum(wordIndexes, entropyWithChecksum);
		Arrays.fill(wordIndexes, 0);
		final byte[] entropy = Arrays.copyOf(entropyWithChecksum, entropyWithChecksum.length - 1);
		final byte lastByte = entropyWithChecksum[entropyWithChecksum.length - 1];
		Arrays.fill(entropyWithChecksum, (byte) 0);
		final byte sha = firstByteOfSha256(entropy);
		final byte mask = maskOfFirstNBits(cs);
		if (((sha ^ lastByte) & mask) != 0) {
			Logger.getInstance().log(Level.SEVERE, "Invalid Checksum Count");
			return false;
		}
		return true;
	}
    
    private static byte firstByteOfSha256(final byte[] entropy) {
        final byte[] hash = SHA.hash256(entropy);
        final byte firstByte = hash[0];
        Arrays.fill(hash, (byte) 0);
        return firstByte;
    }
	
	private static int[] findWordIndexes(final Collection<String> split) {
		final int ms = split.size();
		final int[] result = new int[ms];
		int i = 0;
		for (final String buffer : split) {
		    if (buffer.length() == 0) {
		    	Logger.getInstance().log(Level.SEVERE, "Unexpected White Space");
				return null;
		    }
		    result[i++] = findWordIndex(buffer);
		}
		return result;
	}
	
	private static int findWordIndex(String buffer) {
		final IndexedWord key = new IndexedWord(-1, buffer.toString());
		final int index = Arrays.binarySearch(words, key, IndexedWord.IndexedWordComparator);
		if (index < 0) {
		    final int insertionPoint = -index - 1;
		    int suggestion = insertionPoint == 0 ? insertionPoint : insertionPoint - 1;
		    if (suggestion + 1 == words.length) {
		    	suggestion--;
		    }
		    Logger.getInstance().log(Level.SEVERE, "Word not found: Buffer: `" + buffer + "`, Words: `(" + words[suggestion].getWord() + "), (" + words[suggestion + 1].getWord() + ")`");
		    return -1;
		}
		return words[index].getIndex();
	}
	
	private static void wordIndexesToEntropyWithCheckSum(final int[] wordIndexes, final byte[] entropyWithChecksum) {
		for (int i = 0, bi = 0; i < wordIndexes.length; i++, bi += 11) {
		    writeNext11(entropyWithChecksum, wordIndexes[i], bi);
		}
	}
	
	private static void writeNext11(byte[] bytes, int value, int offset) {
        int skip = offset / 8;
        int bitSkip = offset % 8;
        {//byte 0
		    byte firstValue = bytes[skip];
		    byte toWrite = (byte) (value >> (3 + bitSkip));
		    bytes[skip] = (byte) (firstValue | toWrite);
		}
		
		{//byte 1
		    byte valueInByte = bytes[skip + 1];
		    final int i = 5 - bitSkip;
		    byte toWrite = (byte) (i > 0 ? (value << i) : (value >> -i));
		    bytes[skip + 1] = (byte) (valueInByte | toWrite);
		}
		
		if (bitSkip >= 6) {//byte 2
	        byte valueInByte = bytes[skip + 2];
	        byte toWrite = (byte) (value << 13 - bitSkip);
	        bytes[skip + 2] = (byte) (valueInByte | toWrite);
	    }
	}
	
	private static byte maskOfFirstNBits(final int n) {
		return (byte) ~((1 << (8 - n)) - 1);
	}
}
