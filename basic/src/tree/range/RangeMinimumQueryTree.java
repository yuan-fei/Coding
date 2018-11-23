package tree.range;

import java.util.LinkedList;
import java.util.Queue;

/** Process RMQ */
public class RangeMinimumQueryTree implements ISegmentTree<Integer> {
	class SegmentTreeNode {
		public int start, end;
		public SegmentTreeNode left, right;
		public int value;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.left = this.right = null;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + value;
		}
	}

	SegmentTreeNode root;

	public RangeMinimumQueryTree(int[] nums) {
		build(nums);
	}

	public void build(int[] nums) {
		if (nums.length > 0) {
			root = build(0, nums.length - 1, nums);
		}
	}

	public Integer query(int start, int end) {
		return query(root, start, end);
	}

	public void update(int index, int value) {
		update(root, index, value);
	}

	private SegmentTreeNode build(int start, int end, int[] nums) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		if (start == end) {
			r.value = nums[start];
		} else {
			r.left = build(start, start + (end - start) / 2, nums);
			r.right = build(start + (end - start) / 2 + 1, end, nums);
			r.value = Math.min(r.left.value, r.right.value);
		}
		return r;
	}

	private int query(SegmentTreeNode r, int start, int end) {
		if (r == null) {
			return Integer.MAX_VALUE;
		} else if (start > r.end || end < r.start) {
			return Integer.MAX_VALUE;
		} else if (start <= r.start && end >= r.end) {
			return r.value;
		} else {
			int leftSum = query(r.left, start, end);
			int rightSum = query(r.right, start, end);
			return Math.min(leftSum, rightSum);
		}
	}

	private void update(SegmentTreeNode r, int index, int value) {
		if (r != null && index >= r.start && index <= r.end) {
			if (r.start == index && r.end == index) {
				r.value = value;
			} else {
				if (r.left != null) {
					update(r.left, index, value);
				}
				if (r.right != null) {
					update(r.right, index, value);
				}
				r.value = Math.min(r.left.value, r.right.value);
			}
		}
	}

	public static void main(String[] args) {
		RangeMinimumQueryTree s = new RangeMinimumQueryTree(new int[] { 5, 0, 1, 3, 2, 4, -1 });
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(3, 5));
		s.update(0, -2);
		s.print();
		System.out.println(s.query(0, 3));

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