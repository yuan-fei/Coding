import java.util.Arrays;
import java.util.Comparator;

import tree.range.PersistentSegmentTree;

/**
 * https://codeforces.com/blog/entry/15890
 */
public class OnlineKthNumber {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 5, 2, 6, 3, 7, 4 };
		int[][] q = new int[][] { new int[] { 1, 4, 3 }, new int[] { 3, 3, 1 }, new int[] { 0, 6, 3 } };
		OnlineKthNumber okn = new OnlineKthNumber();
		okn.build(a);
		for (int i = 0; i < q.length; i++) {
			System.out.println(okn.kthNumber(q[i][0], q[i][1], q[i][2]));
		}
	}

	PersistentSegmentTree pst = new PersistentSegmentTree();
	int[][] aWithIndex;
	int[] rank;
	int[] root;
	int[] a;

	public void build(int[] arr) {
		a = arr;
		aWithIndex = new int[a.length][2];
		for (int i = 0; i < a.length; i++) {
			aWithIndex[i][0] = a[i];
			aWithIndex[i][1] = i;
		}
		Arrays.sort(aWithIndex, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[0], o2[0]);
			}
		});
		rank = new int[a.length];
		for (int i = 0; i < rank.length; i++) {
			rank[aWithIndex[i][1]] = i;
		}

		root = new int[a.length + 1];
		root[0] = pst.build(a.length);
		for (int i = 1; i <= rank.length; i++) {
			root[i] = pst.increase(root[i - 1], rank[i - 1], 1);
		}
	}

	public int kthNumber(int start, int end, int k) {
		return kthNumber(0, a.length - 1, root[start], root[end + 1], k);
	}

	public int kthNumber(int l, int r, int rStart, int rEnd, int k) {
		if (l < r) {
			int mid = l + (r - l) / 2;
			int rk = pst.value[pst.lChild[rEnd]];
			rk -= pst.value[pst.lChild[rStart]];
			if (rk >= k) {
				return kthNumber(l, mid, pst.lChild[rStart], pst.lChild[rEnd], k);
			} else {
				return kthNumber(mid + 1, r, pst.rChild[rStart], pst.rChild[rEnd], k - rk);
			}
		}
		return aWithIndex[l][0];
	}

}
