package tree.range;

/**
 * Support range update and query in O(logn)
 */
public class RangeUpdateBinaryIndexedTree {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 9, 5, 7, 3 };
		RangeUpdateBinaryIndexedTree t = new RangeUpdateBinaryIndexedTree(a.length);
		for (int i = 0; i < a.length; i++) {
			t.increase(i, i, a[i]);
		}

		System.out.println(t.sumRange(4, 4));
		System.out.println(t.sumRange(2, 4));
		System.out.println(t.sumRange(3, 3));
		t.increase(0, 1, 1);
		t.increase(3, 4, 2);
		System.out.println(t.sumRange(4, 4));
		System.out.println(t.sumRange(2, 4));
		System.out.println(t.sumRange(3, 3));
		System.out.println(t.sumRange(1, 2));
	}

	int size;

	int[] nums;
	// range from 1 to nums.length
	int[] bitBase;
	int[] bitInc;

	public RangeUpdateBinaryIndexedTree(int size) {
		nums = new int[size];
		bitBase = new int[size + 1];
		bitInc = new int[size + 1];
	}

	public void increase(int start, int end, int val) {
		start += 1;
		end += 1;
		increment(bitBase, start, -val * (start - 1));
		increment(bitBase, end, val * end);
		increment(bitInc, start, val);
		increment(bitInc, end, -val);
	}

	public int sumRange(int i, int j) {
		return prefixSum(j) - prefixSum(i - 1);
	}

	public int prefixSum(int i) {
		i += 1;
		return prefixSum(bitBase, i) + prefixSum(bitInc, i) * i;
	}

	private void increment(int[] bit, int i, int delta) {
		while (i < bit.length) {
			bit[i] += delta;
			i += (i & (-i));
		}
	}

	private int prefixSum(int[] bit, int i) {
		int sum = 0;
		while (i >= 1) {
			sum += bit[i];
			i -= (i & (-i));
		}
		return sum;
	}
}
