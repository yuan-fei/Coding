package tree.lca;

import java.util.List;
import java.util.Map;

import tree.UnrootedTree;

/** LCA with b inary lifting. Preprocessing in O(nlogn), auery in O(logn) */
public class LowestCommonAncestorWithBinaryLifting {
	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 1, 3 }, new int[] { 1, 4 },
				new int[] { 2, 5 }, new int[] { 5, 6 } };
		LowestCommonAncestorWithBinaryLifting lca = new LowestCommonAncestorWithBinaryLifting(7, 0, edges);
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
	int[] startTime;
	int[] endTime;
	int[][] ancestor;
	int MAX_HEIGHT;
	int k;

	public LowestCommonAncestorWithBinaryLifting(int nV, int root, int[][] edges) {
		UnrootedTree urt = new UnrootedTree(nV, edges);
		this.root = root;
		this.nV = urt.nV;
		this.edges = urt.edges;
		preprocess();
	}

	void preprocess() {
		startTime = new int[nV + 1];
		endTime = new int[nV + 1];
		MAX_HEIGHT = (int) Math.ceil(Math.log(nV));
		ancestor = new int[nV][MAX_HEIGHT + 1];
		k = 0;
		dfs(root, root);
	}

	void dfs(int u, int p) {
		startTime[u] = k++;
		ancestor[u][0] = p;
		for (int i = 1; i < MAX_HEIGHT; i++) {
			ancestor[u][i] = ancestor[ancestor[u][i - 1]][i - 1];
		}
		for (int v : edges.get(u)) {
			if (v != p) {
				dfs(v, u);
			}
		}
		endTime[u] = k++;
	}

	boolean isAncestor(int u, int v) {
		return startTime[u] <= startTime[v] && endTime[u] >= endTime[v];
	}

	int lca(int u, int v) {
		if (isAncestor(u, v)) {
			return u;
		}
		if (isAncestor(v, u)) {
			return v;
		}

		for (int i = MAX_HEIGHT; i >= 0; i--) {
			if (!isAncestor(ancestor[u][i], v)) {
				u = ancestor[u][i];
			}
		}
		return ancestor[u][0];
	}
}
