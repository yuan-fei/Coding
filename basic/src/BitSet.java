
public class BitSet {

	public static void main(String[] args) {
		BitSet bs = new BitSet(63);
		bs.setBit(13);
		System.out.println(bs.getBit(13));
	}

	int length;
	int[] bitArray;

	public BitSet(int length) {
		this.length = length;
		this.bitArray = new int[length / 8 + 1];
	}

	public void setBit(int i) {
		int iByte = i / 8;
		int iBit = i % 8;
		int mask = 1 << iBit;
		bitArray[iByte] |= mask;
	}

	public boolean getBit(int i) {
		int iByte = i / 8;
		int iBit = i % 8;
		int mask = 1 << iBit;
		return (bitArray[iByte] & mask) != 0;
	}

}
