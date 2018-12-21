package tree.range;

import java.util.LinkedList;
import java.util.Queue;

/** https://codeforces.com/blog/entry/19080 */
public class DynamicSegmentTree {

	public static void main(String[] args) {
		testRangeUpdateRangeQueryTree();
		testRangeUpdatePointQueryTree();
	}

	private static void testRangeUpdateRangeQueryTree() {
		int[] a = new int[] { 0, 0, 1, 2, 3, 4, 5 };
		RangeUpdateRangeQueryTree s = new RangeUpdateRangeQueryTree(1000000000);
		s.increase(2, 6, 1);
		s.increase(3, 6, 1);
		s.increase(4, 6, 1);
		s.increase(5, 6, 1);
		s.increase(6, 6, 1);
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(50, 100));
		s.increase(0, 2, 1);
		s.print();
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(4, 6));
	}

	private static void testRangeUpdatePointQueryTree() {
		int[] a = new int[] { 0, 0, 1, 2, 3, 4, 5 };
		RangeUpdatePointQueryTree s = new RangeUpdatePointQueryTree(1000000000);
		s.increase(2, 6, 1);
		s.increase(3, 6, 1);
		s.increase(4, 6, 1);
		s.increase(5, 6, 1);
		s.increase(6, 6, 1);
		s.print();
		System.out.println(s.query(0));
		System.out.println(s.query(1));
		System.out.println(s.query(2));
		System.out.println(s.query(3));
		System.out.println(s.query(4));
		System.out.println(s.query(5));
		System.out.println(s.query(6));
		System.out.println(s.query(100));
		s.increase(0, 2, 1);
		s.print();
		System.out.println(s.query(0));
		System.out.println(s.query(1));
		System.out.println(s.query(2));
		System.out.println(s.query(3));
		System.out.println(s.query(4));
		System.out.println(s.query(5));
		System.out.println(s.query(6));
		System.out.println(s.query(100));
	}

	static class RangeUpdateRangeQueryTree {
		class SegmentTreeNode {
			public int start, end;
			public SegmentTreeNode left, right;
			public long base;
			public long increment;

			public SegmentTreeNode(int start, int end) {
				this.start = start;
				this.end = end;
			}

			public void extend() {
				if (left == null) {
					int mid = (start + end) / 2;
					left = new SegmentTreeNode(start, mid);
					right = new SegmentTreeNode(mid + 1, end);
				}
			}

			@Override
			public String toString() {
				return "[" + start + ", " + end + "] = " + base + ", " + increment;
			}
		}

		public RangeUpdateRangeQueryTree(int n) {
			root = new SegmentTreeNode(0, n - 1);
		}

		SegmentTreeNode root;

		public Long query(int start, int end) {
			return query(root, start, end);
		}

		public void increase(int start, int end, int value) {
			increase(root, start, end, value);
		}

		private long query(SegmentTreeNode r, int start, int end) {
			if (r == null) {
				return 0;
			} else if (start > r.end || end < r.start) {
				return 0;
			} else if (start <= r.start && r.end <= end) {
				return r.base + (r.end - r.start + 1) * r.increment;
			} else {
				r.extend();
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
					r.extend();
					r.base += (Math.min(end, r.end) - Math.max(start, r.start) + 1) * value;
					increase(r.left, start, end, value);
					increase(r.right, start, end, value);
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

	static class RangeUpdatePointQueryTree {
		class SegmentTreeNode {
			public int start, end;
			public SegmentTreeNode left, right;
			public long base;

			public SegmentTreeNode(int start, int end) {
				this.start = start;
				this.end = end;
			}

			public void extend() {
				if (left == null) {
					int mid = (start + end) / 2;
					left = new SegmentTreeNode(start, mid);
					right = new SegmentTreeNode(mid + 1, end);
				}
			}

			@Override
			public String toString() {
				return "[" + start + ", " + end + "] = " + base;
			}
		}

		public RangeUpdatePointQueryTree(int n) {
			root = new SegmentTreeNode(0, n - 1);
		}

		SegmentTreeNode root;

		public Long query(int index) {
			return query(root, index);
		}

		public void increase(int start, int end, int value) {
			increase(root, start, end, value);
		}

		private long query(SegmentTreeNode r, int index) {
			if (r == null) {
				return 0;
			} else if (index > r.end || index < r.start) {
				return 0;
			} else if (r.left == r.right) {
				return r.base;
			} else {
				r.extend();
				long leftSum = query(r.left, index);
				long rightSum = query(r.right, index);
				return leftSum + rightSum + r.base;
			}
		}

		private void increase(SegmentTreeNode r, int start, int end, int value) {
			if (r != null && start <= r.end && r.start <= end) {
				if (start <= r.start && r.end <= end) {
					r.base += value;
				} else {
					r.extend();
					increase(r.left, start, end, value);
					increase(r.right, start, end, value);
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
