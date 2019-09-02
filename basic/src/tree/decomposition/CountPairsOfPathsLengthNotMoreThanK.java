package tree.decomposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CountPairsOfPathsLengthNotMoreThanK {
	public static void main(String[] args) {
		int n = 5;
		int k = 4;
		int[][] arr = { { 1, 2, 3 }, { 1, 3, 1 }, { 1, 4, 2 }, { 3, 5, 1 } };
		long r = new CountPairsOfPathsLengthNotMoreThanK().solve(n, k, arr);
		System.out.println(r);
	}

	int nV;
	int MAXN;
	Map<Integer, List<Edge>> edges;
	int[] subTreeSize;
	boolean[] centroidMarked;
	List<Integer>[] centroidTree;
	int centroidTreeRoot;

	static class Edge {
		int to;
		int l;

		public Edge(int to, int l) {
			this.to = to;
			this.l = l;
		}

	}

	static Map<Integer, List<Edge>> getEdges(int[][] arr) {
		Map<Integer, List<Edge>> edges = new HashMap<Integer, List<Edge>>();
		int size = arr.length;
		for (int i = 0; i < size; i++) {
			int from = arr[i][0];
			int to = arr[i][1];
			int weight = arr[i][2];
			if (!edges.containsKey(from)) {
				edges.put(from, new ArrayList<Edge>());
			}
			edges.get(from).add(new Edge(to, weight));
			if (!edges.containsKey(to)) {
				edges.put(to, new ArrayList<Edge>());
			}
			edges.get(to).add(new Edge(from, weight));
		}
		return edges;
	}

	public long solve(int n, int K, int[][] arr) {
		nV = n;
		edges = getEdges(arr);
		build();
		return divideAndConquer(n, K, centroidTreeRoot);
	}

	private long divideAndConquer(int n, int K, int centroid) {
		centroidMarked[centroid] = true;
		int count = 0;
		for (int subCentroid : centroidTree[centroid]) {
			if (!centroidMarked[subCentroid]) {
				long c = divideAndConquer(n, K, subCentroid);
				count += c;
			}
		}
		List<Integer> all = new ArrayList<Integer>();
		all.add(0);
		for (Edge e : edges.get(centroid)) {
			if (!centroidMarked[e.to]) {
				List<Integer> subPaths = new ArrayList<Integer>();
				enumeratePaths(K, edges, e.to, centroid, e.l, subPaths);
				long c = count(subPaths, K);
				count -= c;
				all.addAll(subPaths);
			}
		}
		long c = count(all, K);
		count += c;
		centroidMarked[centroid] = false;
		return count;
	}

	private void enumeratePaths(int K, Map<Integer, List<Edge>> edges, int r, int p, int l, List<Integer> paths) {
		if (l <= K) {
			paths.add(l);
		}
		List<Edge> neighbors = edges.get(r);
		if (neighbors != null) {
			for (Edge n : neighbors) {
				if (n.to != p && !centroidMarked[n.to]) {
					enumeratePaths(K, edges, n.to, r, l + n.l, paths);
				}
			}
		}
	}

	private static long count(List<Integer> l, int K) {
		Collections.sort(l);
		int cnt = 0;
		int right = l.size() - 1;
		for (int i = 0; i < l.size(); i++) {
			while (right >= 0 && l.get(i) + l.get(right) > K) {
				right--;
			}
			// don not count 'self' in
			cnt += right + (right < i ? 1 : 0);
		}
		return cnt / 2;
	}

	/**
	 * -------------------build centroid decomposition tree------------------
	 */
	/** O(SubTreeSize(r)) */
	int getSubTreeSize(int r, int p) {
		int size = 1;
		List<Edge> neighbors = edges.get(r);
		if (neighbors != null) {
			for (Edge n : neighbors) {
				if (n.to != p && !centroidMarked[n.to]) {
					size += getSubTreeSize(n.to, r);
				}
			}
		}
		subTreeSize[r] = size;
		return size;
	}

	public void build() {
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

	int build(int r) {
		int total = getSubTreeSize(r, -1);
		int centroid = findCentroid(r, -1, total)[0];
		centroidTree[centroid] = new ArrayList<Integer>();
		centroidMarked[centroid] = true;
		List<Edge> neighbors = edges.get(centroid);
		if (neighbors != null) {
			for (Edge n : neighbors) {
				if (n.to != centroid && !centroidMarked[n.to]) {
					centroidTree[centroid].add(build(n.to));
				}
			}
		}
		centroidMarked[centroid] = false;
		return centroid;
	}

	/** O(SubTreeSize(r)) with cached subtree size */
	private int[] findCentroid(int r, int p, int totalSize) {
		int centroid = r;
		int maxSubTreeSize = totalSize - subTreeSize[r];
		List<Edge> neighbors = edges.get(r);
		if (neighbors != null) {
			for (Edge n : neighbors) {
				if (n.to != p && !centroidMarked[n.to]) {
					// O(1)
					maxSubTreeSize = Math.max(subTreeSize[n.to], maxSubTreeSize);
				}
			}
			for (Edge n : neighbors) {
				if (n.to != p && !centroidMarked[n.to]) {
					int[] candidate = findCentroid(n.to, r, totalSize);
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
