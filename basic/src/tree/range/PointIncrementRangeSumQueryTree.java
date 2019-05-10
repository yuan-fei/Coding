package tree.range;

import java.util.LinkedList;
import java.util.Queue;

public class PointIncrementRangeSumQueryTree {
	class SegmentTreeNode {
		public int start, end;
		public SegmentTreeNode left, right;
		public long value;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + value;
		}
	}

	SegmentTreeNode root;
	int n;

	public PointIncrementRangeSumQueryTree(int n) {
		build(n);
	}

	public void build(int n) {
		this.n = n;
		root = build(0, n - 1);
	}

	public Long query(int start, int end) {
		return query(root, start, end);
	}

	public void increase(int index, int value) {
		increase(root, index, value);
	}

	private SegmentTreeNode build(int start, int end) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		if (start < end) {
			r.left = build(start, start + (end - start) / 2);
			r.right = build(start + (end - start) / 2 + 1, end);
			r.value = r.left.value + r.right.value;
		}
		return r;
	}

	private long query(SegmentTreeNode r, int start, int end) {
		if (r == null) {
			return 0;
		} else if (start > r.end || end < r.start) {
			return 0;
		} else if (start <= r.start && end >= r.end) {
			return r.value;
		} else {
			long leftSum = query(r.left, start, end);
			long rightSum = query(r.right, start, end);
			return leftSum + rightSum;
		}
	}

	private void increase(SegmentTreeNode r, int index, int value) {
		if (r != null && index >= r.start && index <= r.end) {
			if (r.start == index && r.end == index) {
				r.value += value;
			} else {
				increase(r.left, index, value);
				increase(r.right, index, value);
				r.value = r.left.value + r.right.value;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = new int[] { -1, 0, 1, 2, 3, 4, 5 };
		PointIncrementRangeSumQueryTree s = new PointIncrementRangeSumQueryTree(a.length);
		for (int i = 0; i < a.length; i++) {
			s.increase(i, a[i]);
		}
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
		s.increase(0, 1);
		s.print();
		System.out.println(s.query(0, 7));

	}

	public void print() {
		Queue<SegmentTreeNode> q = new LinkedList<SegmentTreeNode>();
		q.offer(root);
		while (!q.isEmpty()) {
			int cnt = q.size();
			String s = "";
			for (int i = 0; i < cnt; i++) {
				SegmentTreeNode n = q.poll();
				s += (n + ",");
				if (n != null) {
					q.offer(n.left);
					q.offer(n.right);
				}
			}
			System.out.println(s);
		}
		System.out.println();
	}
}
