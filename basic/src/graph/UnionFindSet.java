package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.Util;

/**
 * This is data structure for disjoint set. To build all connected components of
 * a graph with m vertex, n edges, it takes O(mlog(n)) with rank optimization,
 * and nearly O(m) with path compression optimization
 */
public class UnionFindSet<T> {
	public static void main(String[] args) {
		Map<Integer, int[]> adjacentList = new HashMap<Integer, int[]>();
		adjacentList.put(0, new int[] { 1, 2 });
		adjacentList.put(1, new int[] { 2, 3 });
		adjacentList.put(2, new int[0]);
		adjacentList.put(3, new int[0]);
		adjacentList.put(4, new int[] { 5, 6 });
		adjacentList.put(7, new int[] { 8 });
		adjacentList.put(5, new int[0]);
		adjacentList.put(6, new int[0]);
		adjacentList.put(8, new int[0]);
		adjacentList.put(9, new int[0]);
		UnionFindSet<Integer> ufs = new UnionFindSet<Integer>();
		for (int i : adjacentList.keySet()) {
			ufs.makeSet(i);
		}
		for (int i : adjacentList.keySet()) {
			int[] adjVertex = adjacentList.get(i);
			for (int j : adjVertex) {
				ufs.union(i, j);
			}
		}
		System.out.println(ufs.connected(0, 1));
		System.out.println(ufs.connected(0, 8));
		for (int key : ufs.getAllDisjointSets().keySet()) {
			System.out.println(key + " " + Util.toString(ufs.getAllDisjointSets().get(key)));
		}
	}

	Map<T, T> parents = new HashMap<T, T>();
	Map<T, Integer> ranks = new HashMap<T, Integer>();

	public void makeSet(T x) {
		parents.put(x, x);
		ranks.put(x, 1);
	}

	/**
	 * Find representation of a set which contains x while path compression
	 */
	public T findSet(T x) {
		T parent = parents.get(x);
		if (parent == x) {
			return x;
		} else {
			T root = findSet(parent);
			parents.put(x, root);
			return root;
		}
	}

	private T findSetWithoutCompression(T x) {
		T parent = parents.get(x);
		if (parent == x) {
			return x;
		} else {
			return findSetWithoutCompression(parent);
		}
	}

	public boolean connected(T x, T y) {
		return findSetWithoutCompression(x) == findSetWithoutCompression(y);
	}

	/**
	 * Union 2 sets with respect to their ranks
	 */
	public void union(T x, T y) {
		T xRoot = findSet(x);
		T yRoot = findSet(y);
		if (xRoot != yRoot) {
			int xRank = ranks.get(x);
			int yRank = ranks.get(y);
			if (xRank > yRank) {
				parents.put(yRoot, xRoot);
			} else {
				parents.put(xRoot, yRoot);
				if (xRank == yRank) {
					ranks.put(yRoot, ranks.get(yRoot) + 1);
				}
			}
		}
	}

	public int countSets() {
		Set<T> s = new HashSet<>();
		for (T k : parents.keySet()) {
			s.add(findSet(k));
		}
		return s.size();
	}

	public Map<T, List<T>> getAllDisjointSets() {
		Map<T, List<T>> map = new HashMap<T, List<T>>();
		for (T x : parents.keySet()) {
			T root = findSetWithoutCompression(x);
			List<T> set = map.getOrDefault(root, new ArrayList<T>());
			set.add(x);
			map.put(root, set);
		}
		return map;
	}
}
