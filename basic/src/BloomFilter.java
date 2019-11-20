import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BloomFilter {

	public static void main(String[] args) {
		BloomFilter bf = new BloomFilter(100, 5);
		bf.add("hash");
		bf.add("function");
		bf.add("bloom");
		bf.add("filter");
		System.out.println(bf.contains("bloom"));
		System.out.println(bf.contains("awesome"));
	}

	int nBit;
	BitSet bitMap;
	List<IHash> hashFunctions;

	public BloomFilter(int nBit, int nHash) {
		this.nBit = nBit;
		bitMap = new BitSet(nBit);
		hashFunctions = new ArrayList<IHash>();
		for (int i = 0; i < nHash; i++) {
			hashFunctions.add(new SimpleHash());
		}
	}

	void add(String str) {
		for (IHash fun : hashFunctions) {
			int bit = (int) (Math.abs(fun.hash(str)) % nBit);
			bitMap.setBit(bit);
		}
	}

	boolean contains(String str) {
		for (IHash fun : hashFunctions) {
			int bit = (int) (Math.abs(fun.hash(str)) % nBit);
			if (!bitMap.getBit(bit)) {
				return false;
			}
		}
		return true;
	}
}

interface IHash {
	long hash(String str);
}

class SimpleHash implements IHash {

	long base;

	public SimpleHash() {
		long x = new Random().nextInt();
		base = x * x + 1;
	}

	@Override
	public long hash(String str) {
		long hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash *= base;
			hash += str.charAt(i);
		}
		return hash;
	}

}
