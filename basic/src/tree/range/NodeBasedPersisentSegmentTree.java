package tree.range;

import java.util.LinkedList;
import java.util.Queue;

public class NodeBasedPersisentSegmentTree {
	class SegmentTreeNode implements Cloneable {
		public int start, end;
		public SegmentTreeNode left, right;
		public long value;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.left = this.right = null;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + value;
		}

		@Override
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	int size;

	public SegmentTreeNode build(int n) {
		size = n;
		return build(0, size - 1);
	}

	private SegmentTreeNode build(int start, int end) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		if (start < end) {
			r.left = build(start, start + (end - start) / 2);
			r.right = build(start + (end - start) / 2 + 1, end);
		}
		return r;
	}

	public long sum(SegmentTreeNode r, int start, int end) {
		if (r == null) {
			return 0;
		} else if (start > r.end || end < r.start) {
			return 0;
		} else if (start <= r.start && end >= r.end) {
			return r.value;
		} else {
			long leftSum = sum(r.left, start, end);
			long rightSum = sum(r.right, start, end);
			return leftSum + rightSum;
		}
	}

	public SegmentTreeNode increase(SegmentTreeNode r, int index, int value) {
		if (r != null && index >= r.start && index <= r.end) {
			SegmentTreeNode newRoot = (SegmentTreeNode) r.clone();
			newRoot.value += value;
			if (r.left != null) {
				newRoot.left = increase(r.left, index, value);
			}
			if (r.right != null) {
				newRoot.right = increase(r.right, index, value);
			}
			return newRoot;
		}
		return r;
	}

	public static void main(String[] args) {
		int[] a = new int[] { 0, 4, 3, 5, 2, 6, 3 };
		NodeBasedPersisentSegmentTree s = new NodeBasedPersisentSegmentTree();
		SegmentTreeNode base = s.build(a.length);
		SegmentTreeNode[] root = new SegmentTreeNode[a.length];
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				root[i] = s.increase(base, a[i], 1);
			} else {
				root[i] = s.increase(root[i - 1], a[i], 1);
			}
			s.print(root[i]);
		}

		System.out.println(s.sum(root[5], 0, 3));
		System.out.println(s.sum(root[2], 0, 3));
		System.out.println(s.sum(root[5], 0, 4));
		System.out.println(s.sum(root[2], 0, 4));
		System.out.println(s.sum(root[5], 0, 5));
		System.out.println(s.sum(root[2], 0, 5));
		System.out.println(s.sum(root[5], 0, 6));
		System.out.println(s.sum(root[2], 0, 6));

	}

	public void print(SegmentTreeNode root) {
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
