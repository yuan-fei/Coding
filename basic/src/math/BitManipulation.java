package math;

public class BitManipulation {

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(getBitMask(0)));
		System.out.println(Integer.toBinaryString(getBitMask(1)));
		System.out.println(Integer.toBinaryString(getBitMask(31)));
		System.out.println(Integer.toBinaryString(getBitMask(32)));
		System.out.println(Integer.toBinaryString(getBitMaskOfLength(0)));
		System.out.println(Integer.toBinaryString(getBitMaskOfLength(1)));
		System.out.println(Integer.toBinaryString(getBitMaskOfLength(31)));
		System.out.println(Integer.toBinaryString(getBitMaskOfLength(32)));
		System.out.println(Integer.toBinaryString(getBitMask(0, 1)));
		System.out.println(Integer.toBinaryString(getBitMask(1, 31)));
		System.out.println(Integer.toBinaryString(getBitMask(31, 31)));
		System.out.println(Integer.toBinaryString(getBitMask(0, 31)));
		System.out.println(Integer.toBinaryString(1001));
		System.out.println(Integer.toBinaryString(getBit(1001, 0)));
		System.out.println(Integer.toBinaryString(setBit(1001, 2)));
		System.out.println(Integer.toBinaryString(clearBit(1001, 0)));
		System.out.println(Integer.toBinaryString(toggleBit(1001, 0)));
		System.out.println(Integer.toBinaryString(clearLowestOne(6)));
		System.out.println(Integer.toBinaryString(getLowestOneMask(6)));
		System.out.println(countOnes(6));
		allSubset(13);
	}

	public static int getBitMask(int b) {
		return 1 << b;
	}

	/** return a mask whose lowest len bits are 1s: ...001111.. */
	public static int getBitMaskOfLength(int len) {
		// i=0, j=31
		int mask = -1;
		if (len < 32) {
			mask = (1 << len) - 1;
		}
		return mask;
	}

	/** return a mask whose bits from low to high are 1s: ...001111.. */
	public static int getBitMask(int low, int high) {
		return getBitMaskOfLength(high - low + 1) << low;
	}

	public static int getBit(int n, int b) {
		return (n >> b) & 1;
	}

	public static int setBit(int n, int b) {
		return n | getBitMask(b);
	}

	public static int clearBit(int n, int b) {
		return n & (~getBitMask(b));
	}

	public static int toggleBit(int n, int b) {
		return n ^ getBitMask(b);
	}

	public static int clearLowestOne(int n) {
		return n & (n - 1);
	}

	public static int getLowestOneMask(int n) {
		return n & (-n);
	}

	public static int countOnes(int n) {
		int count = 0;
		while (n != 0) {
			count++;
			n = n & (n - 1);
		}
		return count;
	}

	public static void allSubset(int mask) {
		int x = mask;
		while (x != 0) {
			System.out.println(Integer.toBinaryString(x));
			x = (x - 1) & mask;
		}
	}
}
