package tree.decomposition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tree.UnrootedTree;

public class CentroidDecomposition {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 },
				new int[] { 3, 5 }, new int[] { 4, 6 }, new int[] { 6, 7 }, new int[] { 5, 8 } };
		UnrootedTree t = new UnrootedTree(8, edges);
		CentroidDecomposition cd = new CentroidDecomposition(8, t.edges);
		System.out.println(cd.getCentroidTreeRoot()); // 2
		System.out.println(Arrays.toString(cd.getCentroidTree()));
		cd.visit();
	}

	int nV;
	int MAXN;
	Map<Integer, List<Integer>> edges;
	int[] subTreeSize;
	boolean[] centroidMarked;
	List<Integer>[] centroidTree;
	int centroidTreeRoot;

	public CentroidDecomposition(int nV, Map<Integer, List<Integer>> edges) {
		this.nV = nV;
		this.edges = edges;
		build();
	}

	public int getCentroidTreeRoot() {
		return centroidTreeRoot;
	}

	public List<Integer>[] getCentroidTree() {
		return centroidTree;
	}

	/** O(SubTreeSize(r)) */
	private int getSubTreeSize(int r, int p) {
		int size = 1;
		List<Integer> neighbors = edges.getOrDefault(r, new ArrayList<>());
		for (int n : neighbors) {
			if (n != p && !centroidMarked[n]) {
				size += getSubTreeSize(n, r);
			}
		}
		subTreeSize[r] = size;
		return size;
	}

	/** O(nlogn) for building all centroids */
	private void build() {
		MAXN = nV + 1;
		subTreeSize = new int[MAXN];
		centroidMarked = new boolean[MAXN];
		centroidTree = new List[MAXN];
		Iterator<Integer> it = edges.keySet().iterator();
		if (!it.hasNext()) {
			centroidTreeRoot = -1;
		} else {
			int r = it.next();
			centroidTreeRoot = build(r);
		}
	}

	/** O(nlogn) for building all centroids */
	private int build(int r) {
		int total = getSubTreeSize(r, -1);
		int centroid = findCentroid(r, -1, total)[0];
		centroidTree[centroid] = new ArrayList<Integer>();
		centroidMarked[centroid] = true;
		List<Integer> neighbors = edges.getOrDefault(centroid, new ArrayList<>());
		for (int n : neighbors) {
			if (n != centroid && !centroidMarked[n]) {
				centroidTree[centroid].add(build(n));
			}
		}
		centroidMarked[centroid] = false;
		return centroid;
	}

	/** O(SubTreeSize(r)) with precalculated subtree size */
	private int[] findCentroid(int r, int p, int totalSize) {
		int centroid = r;
		int maxSubTreeSize = totalSize - subTreeSize[r];
		List<Integer> neighbors = edges.getOrDefault(r, new ArrayList<>());
		// r as centroid
		for (int n : neighbors) {
			if (n != p && !centroidMarked[n]) {
				maxSubTreeSize = Math.max(subTreeSize[n], maxSubTreeSize);
			}
		}

		// other nodes as centroids
		for (int n : neighbors) {
			if (n != p && !centroidMarked[n]) {
				int[] candidate = findCentroid(n, r, totalSize);
				if (candidate[1] < maxSubTreeSize) {
					centroid = candidate[0];
					maxSubTreeSize = candidate[1];
				}
			}
		}
		return new int[] { centroid, maxSubTreeSize };
	}

	/**
	 * Modify this part to add code for visiting a specific centroid during dfs
	 */
	boolean[] visitedCentroid;

	public void visit() {
		visitedCentroid = new boolean[MAXN];
		dfs(centroidTreeRoot);
	}

	private void dfs(int centroid) {
		// do sth here
		System.out.println(centroid);

		visitedCentroid[centroid] = true;
		for (int subCentroid : centroidTree[centroid]) {
			if (!visitedCentroid[subCentroid]) {
				dfs(subCentroid);
			}
		}
		visitedCentroid[centroid] = false;
	}
}
