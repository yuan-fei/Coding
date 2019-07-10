package tree.lca;

import java.util.List;
import java.util.Map;

import tree.UnrootedTree;

/**
 * LCA with RMQ. Preprocessing in O(n), auery in O(logn)
 * 
 * segment tree implementation: https://codeforces.com/blog/entry/18051
 */
public class LowestCommonAncestorWithRMQ {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 1, 3 }, new int[] { 1, 4 },
				new int[] { 2, 5 }, new int[] { 5, 6 } };
		LowestCommonAncestorWithRMQ lca = new LowestCommonAncestorWithRMQ(7, 0, edges);
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {
				System.out.println(i + "," + j + "=" + lca.lca(i, j));
				System.out.println(j + "," + i + "=" + lca.lca(j, i));
			}
		}
	}

	int nV;
	int root;
	Map<Integer, List<Integer>> edges;
	int[] evt;
	int[] start;
	int[] height;
	int MAX_HEIGHT;
	int k;

	public LowestCommonAncestorWithRMQ(int nV, int root, int[][] edges) {
		UnrootedTree urt = new UnrootedTree(nV, edges);
		this.root = root;
		this.nV = urt.nV;
		this.edges = urt.edges;
		preprocess();
	}

	void preprocess() {
		evt = new int[2 * nV + 2];
		start = new int[nV];
		MAX_HEIGHT = (int) Math.ceil(Math.log(nV));
		k = 0;
		height = new int[nV];
		eulerTour(root, root, 0);
		init();
	}

	int[] tree;

	// O(n)
	private void init() {
		tree = new int[2 * k];
		for (int i = 0; i < k; i++) {
			tree[k + i] = i;
		}
		for (int i = k - 1; i > 0; i--) {
			if (height[evt[tree[2 * i + 1]]] < height[evt[tree[2 * i]]]) {
				tree[i] = tree[2 * i + 1];
			} else {
				tree[i] = tree[2 * i];
			}
		}
	}

	// O(logn)
	private int query(int start, int end) {
		int min = -1;
		for (start += k, end += k; start < end; start >>= 1, end >>= 1) {
			if ((start & 1) > 0) {
				if (min == -1 || height[evt[tree[start]]] < height[evt[tree[min]]]) {
					min = start;
				}
				start++;
			}
			if ((end & 1) > 0) {
				end--;
				if (min == -1 || height[evt[tree[end]]] < height[evt[tree[min]]]) {
					min = end;
				}
			}
		}
		return tree[min];
	}

	void eulerTour(int u, int p, int h) {
		start[u] = k;
		evt[k++] = u;
		height[u] = h;
		for (int v : edges.get(u)) {
			if (v != p) {
				eulerTour(v, u, h + 1);
				evt[k++] = u;
			}
		}
	}

	int lca(int u, int v) {
		int idx = query(Math.min(start[u], start[v]), Math.max(start[u], start[v]) + 1);
		return evt[idx];
	}

}
