package tree.range;

/**
 * An index data structure, which update and sum-in-range query for an array can
 * be done in O(logM) (M is the size of array)
 */
public class BinaryIndexedTree {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 9, 5, 7, 3 };
		BinaryIndexedTree t = new BinaryIndexedTree(a.length);
		for (int i = 0; i < a.length; i++) {
			t.update(i, a[i]);
		}

		System.out.println(t.sumRange(4, 4));
		System.out.println(t.sumRange(2, 4));
		System.out.println(t.sumRange(3, 3));
		t.update(4, 5);
		t.update(1, 7);
		t.update(0, 8);
		System.out.println(t.sumRange(1, 2));
		t.update(1, 9);
		System.out.println(t.sumRange(4, 4));
		t.update(3, 4);

	}

	int size;

	int[] nums;
	// range from 1 to nums.length
	int[] bit;

	public BinaryIndexedTree(int size) {
		nums = new int[size];
		bit = new int[size + 1];
	}

	public void update(int i, int val) {
		int delta = val - nums[i];
		nums[i] = val;
		inc(i, delta);
	}

	public void inc(int i, int delta) {
		increment(i + 1, delta);
	}

	public int sumRange(int i, int j) {
		return prefixSum(j + 1) - prefixSum(i);
	}

	private void increment(int i, int delta) {
		while (i < bit.length) {
			bit[i] += delta;
			i += (i & (-i));
		}
	}

	private int prefixSum(int i) {
		int sum = 0;
		while (i >= 1) {
			sum += bit[i];
			i -= (i & (-i));
		}
		return sum;
	}
}
