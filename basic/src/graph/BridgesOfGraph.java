package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bridge: an edge is bridge if when removed, graph disconnected
 * 
 * O(V+E): https://www.geeksforgeeks.org/bridge-in-a-graph/
 */
public class BridgesOfGraph {

	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } };
		List<int[]> r = new BridgesOfGraph().findBridges(4, edges); // [1,3]
		for (int[] e : r) {
			System.out.println(Arrays.toString(e));
		}
	}

	private List<Integer>[] adj;
	private int[] inTime;
	private int[] lowestInTimeReached;
	private int counter = 0;

	public List<int[]> findBridges(int n, int[][] edges) {
		adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int[] e : edges) {
			int u = e[0];
			int v = e[1];
			adj[u].add(v);
			adj[v].add(u);
		}
		List<int[]> ans = new ArrayList<>();
		inTime = new int[n];
		lowestInTimeReached = new int[n];
		Arrays.fill(inTime, -1);
		dfs(0, -1, ans);
		return ans;
	}

	private void dfs(int u, int p, List<int[]> ans) {
		inTime[u] = counter++;
		lowestInTimeReached[u] = inTime[u];
		for (int v : adj[u]) {
			if (v != p) {
				if (inTime[v] == -1) {
					dfs(v, u, ans);
					// v or subtree can not reach lower vertex than u, than u-v
					// is a critical edge (bridge)
					if (inTime[u] < lowestInTimeReached[v]) {
						ans.add(new int[] { u, v });
					}
				}
				lowestInTimeReached[u] = Math.min(lowestInTimeReached[u], lowestInTimeReached[v]);
			}
		}
	}

}
