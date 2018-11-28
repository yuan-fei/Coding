package tree.range;

import java.util.Arrays;

/** Build in O(nlogn), each online query in O(logn) */
public class PersistentSegmentTree {

	public static void main(String[] args) {
		int[] a = new int[] { 0, 4, 3, 5, 2, 6, 3 };
		PersistentSegmentTree pst = new PersistentSegmentTree();
		int base = pst.build(a.length);
		int[] root = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				root[i] = pst.increase(base, a[i], 1);
			} else {
				root[i] = pst.increase(root[i - 1], a[i], 1);
			}
		}
		System.out.println(pst.sum(root[5], 0, 3));
		System.out.println(pst.sum(root[2], 0, 3));
		System.out.println(pst.sum(root[5], 0, 4));
		System.out.println(pst.sum(root[2], 0, 4));
		System.out.println(pst.sum(root[5], 0, 5));
		System.out.println(pst.sum(root[2], 0, 5));
		System.out.println(pst.sum(root[5], 0, 6));
		System.out.println(pst.sum(root[2], 0, 6));
	}

	private static void test() {
		int[] a = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		PersistentSegmentTree pst = new PersistentSegmentTree();
		int base = pst.build(a.length);
		int[] root = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				root[i] = pst.increase(base, i, a[i]);
			} else {
				root[i] = pst.increase(root[i - 1], i, a[i]);
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println(pst.sum(root[i], 0, a.length));
		}
	}

	int MAXN = 100005;
	int MAXM = MAXN * 40;
	int FREE_NODE_INDEX = 0;
	public int size = 0;
	public int[] value = new int[MAXN];
	public int[] lChild = new int[MAXM];
	public int[] rChild = new int[MAXM];

	public int build(int n) {
		size = n;
		Arrays.fill(lChild, -1);
		Arrays.fill(rChild, -1);
		return build(0, size - 1);
	}

	private int build(int l, int r) {
		int newRootId = FREE_NODE_INDEX;
		FREE_NODE_INDEX++;
		if (l < r) {
			lChild[newRootId] = build(l, (l + r) / 2);
			rChild[newRootId] = build((l + r) / 2 + 1, r);
		}
		return newRootId;
	}

	public int increase(int baseRootId, int i, int value) {
		return increase(baseRootId, 0, size - 1, i, value);
	}

	private int increase(int baseRootId, int l, int r, int i, int v) {
		int newRootId = FREE_NODE_INDEX;
		FREE_NODE_INDEX++;
		value[newRootId] = value[baseRootId];
		if (l < r) {
			int mid = (l + r) / 2;
			lChild[newRootId] = lChild[baseRootId];
			rChild[newRootId] = rChild[baseRootId];
			if (i <= mid) {
				lChild[newRootId] = increase(lChild[newRootId], l, mid, i, v);
			} else {
				rChild[newRootId] = increase(rChild[newRootId], mid + 1, r, i, v);
			}
		}
		value[newRootId] += v;
		return newRootId;
	}

	public int sum(int rootId, int start, int end) {
		return sum(rootId, 0, size - 1, start, end);
	}

	private int sum(int rootId, int l, int r, int start, int end) {
		if (rootId == -1 || start > r || end < l) {
			return 0;
		} else if (start <= l && r <= end) {
			return value[rootId];
		} else {
			return sum(lChild[rootId], l, (l + r) / 2, start, end)
					+ sum(rChild[rootId], (l + r) / 2 + 1, r, start, end);
		}
	}
}
