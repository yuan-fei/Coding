package tree.lca;

import java.util.List;
import java.util.Map;

import tree.UnrootedTree;

/** LCA with recursion. No preprocessing, O(n) for each query */
public class LowestCommonAncestor {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 1, 3 }, new int[] { 1, 4 },
				new int[] { 2, 5 }, new int[] { 5, 6 } };
		LowestCommonAncestor lca = new LowestCommonAncestor(7, 0, edges);
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

	public LowestCommonAncestor(int nV, int root, int[][] edges) {
		UnrootedTree urt = new UnrootedTree(nV, edges);
		this.root = root;
		this.nV = urt.nV;
		this.edges = urt.edges;
	}

	public int lca(int u, int v) {
		return lca(root, -1, u, v);
	}

	private int lca(int r, int p, int u, int v) {
		if (r == u || r == v) {
			return r;
		}

		int candidate = -1;
		for (int c : edges.get(r)) {
			if (c != p) {
				int rt = lca(c, r, u, v);
				if (rt != -1) {
					if (candidate == -1) {
						candidate = rt;
					} else {
						return r;
					}
				}
			}
		}
		return candidate;
	}

}
