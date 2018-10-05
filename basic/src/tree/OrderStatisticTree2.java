package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderStatisticTree2 {

	public static void main(String[] args) {
		System.out.println(countOfSmallerNumberII(new int[] { 100, 78, 100, 33, 67, 90 }));

	}

	/**
	 * @param A:
	 *            an integer array
	 * @return: A list of integers includes the index of the first number and the
	 *          index of the last number
	 */
	public static List<Integer> countOfSmallerNumberII(int[] A) {
		OrderStatisticTree2 os = new OrderStatisticTree2();
		List<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i < A.length; i++) {
			res.add(os.insertAndGetRank(A[i]));
		}
		os.print();
		return res;
	}

	class TreeNode {
		TreeNode l, r;
		int v;
		int c;

		public TreeNode(int value) {
			v = value;
		}

		public TreeNode(int value, int cnt) {
			v = value;
			c = cnt;
		}

		@Override
		public String toString() {
			return "[" + v + ", " + c + "]";
		}
	}

	TreeNode root = new TreeNode(Integer.MAX_VALUE, 1);

	public int insertAndGetRank(int v) {
		TreeNode tn = new TreeNode(v, 1);
		insert(tn);
		return getRank(tn);
	}

	public void insert(TreeNode n) {
		root = insert(root, n);
	}

	public int getRank(TreeNode n) {
		return getRank(root, n);
	}

	public void print() {
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				TreeNode n = q.poll();
				s += (n + ",");
				if (n != null) {
					q.offer(n.l);
					q.offer(n.r);
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}

	private int getCnt(TreeNode r) {
		return (r == null) ? 0 : r.c;
	}

	private TreeNode insert(TreeNode r, TreeNode n) {
		if (r == null) {
			return n;
		} else if (r.v < n.v) {
			r.r = insert(r.r, n);
		} else {
			r.l = insert(r.l, n);
		}
		r.c = getCnt(r.l) + getCnt(r.r) + 1;
		return r;
	}

	private int getRank(TreeNode r, TreeNode n) {
		if (r == null || r == n) {
			return 0;
		} else if (r.v < n.v) {
			return getCnt(r.l) + 1 + getRank(r.r, n);
		} else {
			return getRank(r.l, n);
		}
	}
}
