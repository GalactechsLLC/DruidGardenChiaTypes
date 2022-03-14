package garden.druid.chia.crypt.BIP39;

import java.util.Comparator;

public class IndexedWord {

	private int index;
	private String word;
	
	public IndexedWord(int i, String word) {
		this.index = i;
		this.word = WordList.normalize(word);
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public static final Comparator<IndexedWord> IndexedWordComparator = (o1, o2) -> {
		final int length1 = o1.word.length();
		final int length2 = o2.word.length();
		final int length = Math.min(length1, length2);
		for (int i = 0; i < length; i++) {
			final int compare = Character.compare(o1.word.charAt(i), o2.word.charAt(i));
			if (compare != 0) return compare;
		}
		return Integer.compare(length1, length2);
	};

}
