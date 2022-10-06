package range;

/**
 * An index data structure, which update and sum-in-range query for an array can
 * be done in O(logM) (M is the size of array).
 * 
 * Note BIT internally use 1-based arrays for indexing
 */
public class BinaryIndexedTree {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 9, 5, 7, 3 };
		BinaryIndexedTree t = new BinaryIndexedTree(a.length);
		for (int i = 0; i < a.length; i++) {
			t.inc(i, a[i]);
		}

		System.out.println(t.sumRange(3, 3));
		System.out.println(t.sumRange(1, 4));
		System.out.println(t.sumRange(2, 2));
		t.inc(3, -2);
		t.inc(0, 7);
		t.inc(0, 1);
		System.out.println(t.sumRange(0, 2));
		t.inc(0, 1);
		System.out.println(t.sumRange(3, 4));
		System.out.println(t.prefixSum(4));
	}

	int N;

	// range from 1 to N
	int[] bit;

	public BinaryIndexedTree(int size) {
		N = size;
		bit = new int[N + 1];
	}

	public long sumRange(int i, int j) {
		return prefixSum(j) - prefixSum(i - 1);
	}

	public void inc(int i, long delta) {
		incInternal(i + 1, delta);
	}

	public long prefixSum(int i) {
		return prefixSumInternal(i + 1);
	}

	private void incInternal(int i, long delta) {
		while (i <= N) {
			bit[i] += delta;
			i += (i & (-i));
		}
	}

	private long prefixSumInternal(int i) {
		long sum = 0;
		while (i > 0) {
			sum += bit[i];
			i -= (i & (-i));
		}
		return sum;
	}
}
