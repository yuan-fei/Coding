package dp.treeDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** find height for every root in O(n) */
public class AllRootedTreeHeight {
	public static void main(String[] args) {
		int n = 4;
		int[][] edges = new int[][] { { 1, 0 }, { 1, 2 }, { 1, 3 } };
		int[] heights = getAllHeightTrees(n, edges);
		System.out.println(Arrays.toString(heights)); // [2, 1, 2, 2]
		n = 6;
		edges = new int[][] { { 0, 3 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 5, 4 } };
		heights = getAllHeightTrees(n, edges);
		System.out.println(Arrays.toString(heights)); // [3, 3, 3, 2, 2, 3]
	}

	static List<Integer>[] adj;
	// max height
	static int[] height1;
	// 2nd max height
	static int[] height2;
	// height
	static int[] dp;

	public static int[] getAllHeightTrees(int n, int[][] edges) {
		adj = new List[n];
		height1 = new int[n];
		height2 = new int[n];
		dp = new int[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		// randomly pick a root
		dfs1(0, -1);
		dfs2(0, -1, 0);
		return dp;
	}

	/** compute rooted subtree heights */
	static int dfs1(int u, int p) {
		for (int v : adj[u]) {
			if (v != p) {
				dfs1(v, u);
				if (height1[v] + 1 > height1[u]) {
					height2[u] = height1[u];
					height1[u] = height1[v] + 1;
				} else if (height1[v] + 1 > height2[u]) {
					height2[u] = height1[v] + 1;
				}
			}
		}
		return height1[u];
	}

	static void dfs2(int u, int p, int maxHeightUp) {
		dp[u] = Math.max(maxHeightUp, height1[u]);
		for (int v : adj[u]) {
			if (v != p) {
				int maxHeightUpNext = 0;
				if (height1[v] + 1 == height1[u]) {
					maxHeightUpNext = Math.max(maxHeightUp + 1, height2[u] + 1);
				} else {
					maxHeightUpNext = Math.max(maxHeightUp + 1, height1[u] + 1);
				}
				dfs2(v, u, maxHeightUpNext);
			}
		}
	}
}
