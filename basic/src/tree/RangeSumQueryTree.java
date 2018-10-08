package tree;

import java.util.LinkedList;
import java.util.Queue;

public class RangeSumQueryTree implements ISegmentTree<Long> {
	class SegmentTreeNode {
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
	}

	SegmentTreeNode root;

	public RangeSumQueryTree(int[] nums) {
		build(nums);
	}

	public void build(int[] nums) {
		if (nums.length > 0) {
			root = build(0, nums.length - 1, nums);
		}
	}

	public Long query(int start, int end) {
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
				r.value = r.left.value + r.right.value;
			}
		}
	}

	public static void main(String[] args) {
		RangeSumQueryTree s = new RangeSumQueryTree(new int[] { -1, 0, 1, 2, 3, 4, 5 });
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
		s.update(0, 1);
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