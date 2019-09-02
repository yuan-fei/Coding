package tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** find centroid of tree in O(n) */
public class CentroidOfTree {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 },
				new int[] { 3, 5 }, new int[] { 4, 6 }, new int[] { 6, 7 }, new int[] { 5, 8 } };
		UnrootedTree t = new UnrootedTree(8, edges);
		System.out.println(findCentroid(8, t.edges)[0]); // 2
	}

	static Map<Integer, Integer> subTreeSize;

	/** O(SubTreeSize(r)) */
	static int getSubTreeSize(Map<Integer, List<Integer>> edges, int r, int p) {
		int size = 1;
		List<Integer> neighbors = edges.get(r);
		if (neighbors != null) {
			for (int n : neighbors) {
				if (n != p) {
					size += getSubTreeSize(edges, n, r);
				}
			}
		}
		subTreeSize.put(r, size);
		return size;
	}

	static int[] findCentroid(int nV, Map<Integer, List<Integer>> edges) {
		subTreeSize = new HashMap<>();
		Iterator<Integer> it = edges.keySet().iterator();
		if (!it.hasNext()) {
			return new int[0];
		}
		int r = it.next();
		int total = getSubTreeSize(edges, r, -1);
		return findCentroid(edges, r, -1, total);
	}

	/** O(SubTreeSize(r)) with cached subtree size */
	private static int[] findCentroid(Map<Integer, List<Integer>> edges, int r, int p, int totalSize) {
		int centroid = r;
		int totalSubTreeSize = 1;
		int maxSubTreeSize = 0;
		List<Integer> neighbors = edges.get(r);
		if (neighbors != null) {
			for (int n : neighbors) {
				if (n != p) {
					// O(1)
					int s = subTreeSize.get(n);
					maxSubTreeSize = Math.max(s, maxSubTreeSize);
					totalSubTreeSize += s;
				}
			}
			maxSubTreeSize = Math.max(totalSize - totalSubTreeSize, maxSubTreeSize);
			for (int n : neighbors) {
				if (n != p) {
					int[] candidate = findCentroid(edges, n, r, totalSize);
					if (candidate[1] < maxSubTreeSize) {
						centroid = candidate[0];
						maxSubTreeSize = candidate[1];
					}
				}
			}
		}
		return new int[] { centroid, maxSubTreeSize };
	}
}
