package tree.range;

import java.util.LinkedList;
import java.util.Queue;

/** Given an array of integers and query the max subarray sum in a range */
public class MaxSubarraySumInRange {

	public static void main(String[] args) {
		int[] a = new int[] { -1, 0, 1, 2, -3, 4, 5 };
		RangeMaxSubArraySumQueryTree s = new RangeMaxSubArraySumQueryTree(a.length);
		for (int i = 0; i < a.length; i++) {
			s.update(i, a[i]);
		}
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
		s.update(0, 1);
		s.print();
		System.out.println(s.query(0, 7));
	}

	static public class RangeMaxSubArraySumQueryTree {
		static class SegmentTreeNode {
			public int start, end;
			public SegmentTreeNode left, right;
			public long maxSubSum;
			public long totalSum;
			public long maxPrefixSum;
			public long maxSuffixSum;

			public SegmentTreeNode(int start, int end) {
				this.start = start;
				this.end = end;
			}

			public static SegmentTreeNode merge(SegmentTreeNode l, SegmentTreeNode r) {
				if (l == null) {
					return r;
				}
				if (r == null) {
					return l;
				}
				SegmentTreeNode ret = new SegmentTreeNode(l.start, r.end);
				ret.left = l;
				ret.right = r;
				ret.maxSubSum = Math.max(Math.max(l.maxSubSum, r.maxSubSum), l.maxSuffixSum + r.maxPrefixSum);
				ret.totalSum = l.totalSum + r.totalSum;
				ret.maxPrefixSum = Math.max(l.maxPrefixSum, l.totalSum + r.maxPrefixSum);
				ret.maxSuffixSum = Math.max(l.maxSuffixSum + r.totalSum, r.maxSuffixSum);
				return ret;
			}

			@Override
			public String toString() {
				return "[" + start + ", " + end + "] = " + maxSubSum + ", " + totalSum + ", " + maxPrefixSum + ", "
						+ maxSuffixSum;
			}
		}

		SegmentTreeNode root;
		int n;

		public RangeMaxSubArraySumQueryTree(int n) {
			build(n);
		}

		public void build(int n) {
			this.n = n;
			root = build(0, n - 1);
		}

		public long query(int start, int end) {
			SegmentTreeNode res = query(root, start, end);
			return res.maxSubSum;
		}

		public void update(int index, int value) {
			update(root, index, value);
		}

		private SegmentTreeNode build(int start, int end) {
			SegmentTreeNode r = new SegmentTreeNode(start, end);
			if (start < end) {
				r.left = build(start, start + (end - start) / 2);
				r.right = build(start + (end - start) / 2 + 1, end);
				r = SegmentTreeNode.merge(r.left, r.right);
			}
			return r;
		}

		private SegmentTreeNode query(SegmentTreeNode r, int start, int end) {
			if (r == null) {
				return null;
			} else if (start > r.end || end < r.start) {
				return null;
			} else if (start <= r.start && end >= r.end) {
				return r;
			} else {
				SegmentTreeNode left = query(r.left, start, end);
				SegmentTreeNode right = query(r.right, start, end);
				return SegmentTreeNode.merge(left, right);
			}
		}

		private void update(SegmentTreeNode r, int index, int value) {
			if (r != null && index >= r.start && index <= r.end) {
				if (r.start == index && r.end == index) {
					r.maxSubSum = value;
					r.totalSum = value;
					r.maxPrefixSum = value;
					r.maxSuffixSum = value;
				} else {
					if (r.left != null) {
						update(r.left, index, value);
					}
					if (r.right != null) {
						update(r.right, index, value);
					}
					SegmentTreeNode newR = SegmentTreeNode.merge(r.left, r.right);
					r.maxSubSum = newR.maxSubSum;
					r.totalSum = newR.totalSum;
					r.maxPrefixSum = newR.maxPrefixSum;
					r.maxSuffixSum = newR.maxSuffixSum;
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
}
