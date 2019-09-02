package tree;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DiameterOfTree {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 },
				new int[] { 3, 5 }, new int[] { 4, 6 }, new int[] { 6, 7 }, new int[] { 5, 8 } };
		// edges = new int[][] { new int[] { 0, 1 } };
		UnrootedTree t = new UnrootedTree(8, edges);
		System.out.println(getDiameter1(8, t.edges)); // 6
		System.out.println(getDiameter2(8, t.edges)); // 6
	}

	/**
	 * https://www.geeksforgeeks.org/longest-path-undirected-tree/
	 * 
	 * Find v with max height from any root, take v as root, find max height
	 */
	public static int getDiameter1(int nV, Map<Integer, List<Integer>> edges) {
		Iterator<Integer> it = edges.keySet().iterator();
		if (!it.hasNext()) {
			return -1;
		}
		int[] start = findMaxHeight(it.next(), -1, edges);
		int[] end = findMaxHeight(start[1], -1, edges);
		return end[0];
	}

	private static int[] findMaxHeight(int r, int p, Map<Integer, List<Integer>> edges) {
		List<Integer> neiboughs = edges.get(r);
		if (neiboughs.size() == 1 && neiboughs.get(0) == p) {
			return new int[] { 0, r };
		}
		int maxHeight = -1;
		int maxV = -1;
		for (int c : neiboughs) {
			if (c != p) {
				int[] rc = findMaxHeight(c, r, edges);
				if (rc[0] > maxHeight) {
					maxHeight = rc[0];
					maxV = rc[1];
				}
			}
		}
		return new int[] { maxHeight + 1, maxV };
	}

	/** Recursive find the max height */
	public static int getDiameter2(int nV, Map<Integer, List<Integer>> edges) {
		Iterator<Integer> it = edges.keySet().iterator();
		if (!it.hasNext()) {
			return -1;
		}
		int[] d = getDiameterRec(it.next(), -1, edges);
		return Math.max(d[0], d[1]);
	}

	static int[] getDiameterRec(int r, int p, Map<Integer, List<Integer>> edges) {
		int max = 0;
		int maxLengthThroughRoot1 = 0;
		int maxLengthThroughRoot2 = 0;
		List<Integer> neiboughs = edges.get(r);
		if (neiboughs.size() == 1 && neiboughs.get(0) == p) {
			return new int[] { 0, 0 };
		}
		for (int c : neiboughs) {
			if (c != p) {
				int[] cd = getDiameterRec(c, r, edges);
				// System.out.println(c + ", " + cd[0] + ", " + cd[1]);
				max = Math.max(max, cd[0]);
				if (maxLengthThroughRoot1 < cd[1] + 1) {
					maxLengthThroughRoot2 = maxLengthThroughRoot1;
					maxLengthThroughRoot1 = cd[1] + 1;
				} else if (maxLengthThroughRoot2 < cd[1] + 1) {
					maxLengthThroughRoot2 = cd[1] + 1;
				}
			}
		}
		max = Math.max(max, maxLengthThroughRoot1 + maxLengthThroughRoot2);
		return new int[] { max, maxLengthThroughRoot1 };
	}

}
