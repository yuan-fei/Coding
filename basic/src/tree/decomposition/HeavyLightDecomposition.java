package tree.decomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Decompose a tree into a set of vertex-disjoint chains. With the help of
 * segment tree on the chains, We can do range update in O(logn) and query in
 * O(logn*logn)
 * 
 * https://www.geeksforgeeks.org/heavy-light-decomposition-set-1-introduction/
 */
public class HeavyLightDecomposition {

	public static void main(String[] args) {
		HeavyLightDecomposition hld = new HeavyLightDecomposition(11);
		hld.addEdge(0, 1, 13);
		hld.addEdge(0, 2, 9);
		hld.addEdge(0, 3, 23);
		hld.addEdge(1, 4, 4);
		hld.addEdge(1, 5, 25);
		hld.addEdge(5, 7, 5);
		hld.addEdge(7, 9, 1);
		hld.addEdge(7, 10, 6);
		hld.addEdge(2, 6, 29);
		hld.addEdge(6, 8, 30);
		hld.build();
		System.out.println(hld.query(4, 5));
		System.out.println(hld.query(10, 8));
		System.out.println(hld.query(0, 0));
		System.out.println(hld.query(0, 1));
	}

	Node[] nodes;
	List<Edge>[] edges;
	int[] chainHeads;
	RangeIncRangeSum segTree;

	public HeavyLightDecomposition(int n) {
		edges = new List[n];
		nodes = new Node[n];
		chainHeads = new int[n];
		Arrays.fill(chainHeads, -1);
		segTree = new RangeIncRangeSum(n);
	}

	public void addEdge(int u, int v, int weight) {
		if (edges[u] == null) {
			edges[u] = new ArrayList<>();
		}
		if (edges[v] == null) {
			edges[v] = new ArrayList<>();
		}
		edges[u].add(new Edge(v, weight));
		edges[v].add(new Edge(u, weight));
	}

	public void build() {
		dfs(0, -1);
		curSegTreeIdx = 0;
		currentChainIdx = 0;
		hld(0);
		for (int i = 0; i < edges.length; i++) {
			for (Edge e : edges[i]) {
				if (i < e.to) {
					increase(i, e.to, e.weight);
				}
			}
		}
	}

	public void increase(int u, int v, int weight) {
		int lca = lca(u, v);
		increaseUp(u, lca, weight);
		increaseUp(v, lca, weight);
	}

	public int query(int u, int v) {
		int lca = lca(u, v);
		return queryUp(u, lca) + queryUp(v, lca);
	}

	private int lca(int u, int v) {
		Set<Integer> s = new HashSet<>();
		while (u != -1) {
			s.add(u);
			u = nodes[u].parent;
		}
		while (v != -1) {
			if (s.contains(v)) {
				return v;
			}
			v = nodes[v].parent;
		}
		return -1;
	}

	private void dfs(int u, int prev) {
		if (nodes[u] == null) {
			nodes[u] = new Node(prev, 1);
		}
		for (Edge e : edges[u]) {
			if (e.to != prev) {
				dfs(e.to, u);
				nodes[u].subTreeSize += nodes[e.to].subTreeSize;
			}
		}

	}

	private int curSegTreeIdx = 0;
	private int currentChainIdx = 0;

	private void hld(int u) {
		if (chainHeads[currentChainIdx] == -1) {
			chainHeads[currentChainIdx] = u;
		}
		nodes[u].chain = currentChainIdx;
		nodes[u].segTreePos = curSegTreeIdx++;
		Edge heavyEdge = null;
		for (Edge e : edges[u]) {
			if (e.to != nodes[u].parent) {
				if (heavyEdge == null || nodes[e.to].subTreeSize > nodes[heavyEdge.to].subTreeSize) {
					heavyEdge = e;
				}
			}
		}
		if (heavyEdge != null) {
			hld(heavyEdge.to);
		}
		for (Edge e : edges[u]) {
			if (e.to != nodes[u].parent) {
				if (e != heavyEdge) {
					currentChainIdx++;
					hld(e.to);
				}
			}
		}
	}

	private void increaseUp(int u, int lca, int weight) {
		while (nodes[u].chain != nodes[lca].chain) {
			int chainHead = chainHeads[nodes[u].chain];
			segTree.increase(nodes[chainHead].segTreePos, nodes[u].segTreePos, weight);
			u = nodes[chainHead].parent;
		}
		if (u != lca) {
			segTree.increase(nodes[lca].segTreePos + 1, nodes[u].segTreePos, weight);
		}
	}

	private int queryUp(int u, int lca) {
		int sum = 0;
		while (nodes[u].chain != nodes[lca].chain) {
			int chainHead = chainHeads[nodes[u].chain];
			sum += segTree.query(nodes[chainHead].segTreePos, nodes[u].segTreePos);
			u = nodes[chainHead].parent;
		}
		if (u != lca) {
			sum += segTree.query(nodes[lca].segTreePos + 1, nodes[u].segTreePos);
		}
		return sum;
	}

	class Node {
		int parent;
		int subTreeSize;
		int segTreePos;
		int chain;

		public Node(int parent, int subTreeSize) {
			this.parent = parent;
			this.subTreeSize = subTreeSize;
		}

	}

	class Edge {
		int weight;
		int to;

		public Edge(int v, int weight) {
			this.weight = weight;
			this.to = v;
		}

	}

	class RangeIncRangeSum {

		public RangeIncRangeSum(int[] nums) {
			build(nums);
		}

		public RangeIncRangeSum(int n) {
			build(new int[n]);
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

		long zeroAnswer() {
			return 0;
		}

		long combineAnswer(long a, long b) {
			return a + b;
		}

		void applyInc(SegmentTreeNode r, long inc) {
			r.answer += (r.end - r.start + 1) * inc;
			r.pendingPushDownInc += inc;
		}

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
}
