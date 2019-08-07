package tree.range;

import java.util.LinkedList;
import java.util.Queue;

/** Support range update and query in O(logn) */
abstract public class LazyPropSegmentTree {

	public static void main(String[] args) {
		System.out.println("RangeIncRangeMax");
		LazyPropSegmentTree s = new RangeIncRangeMax(4);
		s.increase(0, 6, -1);
		s.print();
		s.increase(0, 2, 3);
		s.print();
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
		s.increase(0, 0, -9);
		s.print();
		System.out.println(s.query(0, 0));

		System.out.println("RangeIncRangeSum");

		s = new RangeIncRangeSum(new int[] { -1, 0, 1, 2, 3, 4, 5 });
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

	class SegmentTreeNode {
		public int start, end;
		public SegmentTreeNode left, right;
		public boolean pendingPushDown;
		public long answer;
		public long pendingPushDownInc;

		public SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.left = this.right = null;
		}

		@Override
		public String toString() {
			return "[" + start + ", " + end + "] = " + answer + ", " + pendingPushDownInc + ", " + pendingPushDown;
		}
	}

	SegmentTreeNode root;

	public void build(int[] nums) {
		if (nums.length > 0) {
			root = build(0, nums.length - 1, nums);
		}
	}

	public long query(int start, int end) {
		return query(root, start, end);
	}

	public void increase(int start, int end, long value) {
		increase(root, start, end, value);
	}

	private SegmentTreeNode build(int start, int end, int[] nums) {
		SegmentTreeNode r = new SegmentTreeNode(start, end);
		if (start == end) {
			r.answer = nums[start];
		} else {
			r.left = build(start, start + (end - start) / 2, nums);
			r.right = build(start + (end - start) / 2 + 1, end, nums);
			build(r);
		}
		return r;
	}

	/** combine(zero, x) = x */
	abstract long zeroAnswer();

	/** combine child answers to current node answer */
	abstract long combineAnswer(long a, long b);

	/**
	 * if an inc update is applied for the whole interval of node r, then
	 * 
	 * 1. calculate and update the new answer to node r
	 * 
	 * 2. accumulate the pendingPushdownInc
	 */
	abstract void applyInc(SegmentTreeNode r, long inc);

	void apply(SegmentTreeNode r, long inc) {
		if (r != null) {
			r.pendingPushDown = true;
			applyInc(r, inc);
		}
	}

	void build(SegmentTreeNode r) {
		r.answer = combineAnswer(r.left.answer, r.right.answer);
	}

	void pushDown(SegmentTreeNode r) {
		if (r.pendingPushDown) {
			apply(r.left, r.pendingPushDownInc);
			apply(r.right, r.pendingPushDownInc);
		}
		r.pendingPushDown = false;
		r.pendingPushDownInc = 0;
	}

	private long query(SegmentTreeNode r, int start, int end) {
		if (r == null) {
			return zeroAnswer();
		} else if (start > r.end || end < r.start) {
			return zeroAnswer();
		} else if (start <= r.start && r.end <= end) {
			return r.answer;
		} else {
			pushDown(r);
			long leftSum = query(r.left, start, end);
			long rightSum = query(r.right, start, end);
			return combineAnswer(leftSum, rightSum);
		}
	}

	private void increase(SegmentTreeNode r, int start, int end, long value) {
		if (r != null && start <= r.end && r.start <= end) {
			if (start <= r.start && r.end <= end) {
				apply(r, value);
			} else {
				pushDown(r);
				increase(r.left, start, end, value);
				increase(r.right, start, end, value);
				build(r);
			}
		}
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

class RangeIncRangeMax extends LazyPropSegmentTree {
	public RangeIncRangeMax(int[] nums) {
		build(nums);
	}

	public RangeIncRangeMax(int n) {
		build(new int[n]);
	}

	long zeroAnswer() {
		return Long.MIN_VALUE;
	}

	long combineAnswer(long a, long b) {
		return Math.max(a, b);
	}

	void applyInc(SegmentTreeNode r, long inc) {
		r.answer += inc;
		r.pendingPushDownInc += inc;
	}
}

class RangeIncRangeSum extends LazyPropSegmentTree {

	public RangeIncRangeSum(int[] nums) {
		build(nums);
	}

	public RangeIncRangeSum(int n) {
		build(new int[n]);
	}

	@Override
	long zeroAnswer() {
		return 0;
	}

	@Override
	long combineAnswer(long a, long b) {
		return a + b;
	}

	@Override
	void applyInc(SegmentTreeNode r, long inc) {
		r.answer += (r.end - r.start + 1) * inc;
		r.pendingPushDownInc += inc;
	}
}
