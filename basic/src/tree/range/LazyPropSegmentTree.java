package tree.range;

import java.util.LinkedList;
import java.util.Queue;

/** Support range update and query in O(logn) */
public class LazyPropSegmentTree {

	class SegmentTreeNode {
		public int start, end;
		public SegmentTreeNode left, right;
		public long base;
		public long increment;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.left = this.right = null;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + base + ", " + increment;
		}
	}

	SegmentTreeNode root;

	public LazyPropSegmentTree(int[] nums) {
		build(nums);
	}

	public LazyPropSegmentTree(int n) {
		build(new int[n]);
	}

	private void build(int[] nums) {
		if (nums.length > 0) {
			root = build(0, nums.length - 1, nums);
		}
	}

	public Long query(int start, int end) {
		return query(root, start, end);
	}

	public void increase(int start, int end, int value) {
		increase(root, start, end, value);
	}

	private SegmentTreeNode build(int start, int end, int[] nums) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		if (start == end) {
			r.base = nums[start];
		} else {
			r.left = build(start, start + (end - start) / 2, nums);
			r.right = build(start + (end - start) / 2 + 1, end, nums);
			r.base = r.left.base + r.right.base;
		}
		return r;
	}

	private long query(SegmentTreeNode r, int start, int end) {
		if (r == null) {
			return 0;
		} else if (start > r.end || end < r.start) {
			return 0;
		} else if (start <= r.start && r.end <= end) {
			return r.base + (r.end - r.start + 1) * r.increment;
		} else {
			long leftSum = query(r.left, start, end);
			long rightSum = query(r.right, start, end);
			return leftSum + rightSum + (Math.min(end, r.end) - Math.max(start, r.start) + 1) * r.increment;
		}
	}

	private void increase(SegmentTreeNode r, int start, int end, int value) {
		if (r != null && start <= r.end && r.start <= end) {
			if (start <= r.start && r.end <= end) {
				r.increment += value;
			} else {
				r.base += (Math.min(end, r.end) - Math.max(start, r.start) + 1) * value;
				increase(r.left, start, end, value);
				increase(r.right, start, end, value);
			}
		}
	}

	public static void main(String[] args) {
		LazyPropSegmentTree s = new LazyPropSegmentTree(new int[] { -1, 0, 1, 2, 3, 4, 5 });
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
		s.increase(0, 2, 1);
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));

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
