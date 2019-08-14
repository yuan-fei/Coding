package range;

/**
 * An index data structure, which update and sum-in-range query for an array can
 * be done in O(logM) (M is the size of array).
 * 
 * Note BIT is a 1-based data structure
 */
public class BinaryIndexedTree {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 9, 5, 7, 3 };
		BinaryIndexedTree t = new BinaryIndexedTree(a.length);
		for (int i = 0; i < a.length; i++) {
			t.update(i + 1, a[i]);
		}

		System.out.println(t.sumRange(4, 4));
		System.out.println(t.sumRange(2, 5));
		System.out.println(t.sumRange(3, 3));
		t.update(4, 5);
		t.update(1, 7);
		t.update(1, 8);
		System.out.println(t.sumRange(1, 2));
		t.update(1, 9);
		System.out.println(t.sumRange(4, 5));
		System.out.println(t.prefixSum(5));
	}

	int N;

	long[] nums;
	// range from 1 to N
	int[] bit;

	public BinaryIndexedTree(int size) {
		N = size;
		nums = new long[N + 1];
		bit = new int[N + 1];
	}

	public void update(int i, long val) {
		long delta = val - nums[i];
		nums[i] = val;
		inc(i, delta);
	}

	public long sumRange(int i, int j) {
		return prefixSum(j) - prefixSum(i - 1);
	}

	public void inc(int i, long delta) {
		while (i <= N) {
			bit[i] += delta;
			i += (i & (-i));
		}
	}

	public long prefixSum(int i) {
		long sum = 0;
		while (i > 0) {
			sum += bit[i];
			i -= (i & (-i));
		}
		return sum;
	}
}
