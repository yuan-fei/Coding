package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Articulation Point: an point is Articulation Point if when removed, graph
 * disconnected
 * 
 * O(V+E):
 * https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 */
public class ArticulationPointsOfGraph {

	public static void main(String[] args) {
		int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 } };
		List<Integer> r = new ArticulationPointsOfGraph().findArticulationPoints(4, edges); // [1]
		System.out.println(r);
		edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 1, 3 }, { 3, 4 }, { 4, 1 } };
		r = new ArticulationPointsOfGraph().findArticulationPoints(5, edges); // [1]
		System.out.println(r);
		edges = new int[][] { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 0, 3 }, { 3, 4 }, { 4, 0 } };
		r = new ArticulationPointsOfGraph().findArticulationPoints(5, edges); // [0]
		System.out.println(r);
	}

	private List<Integer>[] adj;
	private int[] inTime;
	private int[] lowestInTimeReached;
	private int counter = 0;

	public List<Integer> findArticulationPoints(int n, int[][] edges) {
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
		List<Integer> ans = new ArrayList<>();
		inTime = new int[n];
		lowestInTimeReached = new int[n];
		Arrays.fill(inTime, -1);
		dfs(0, -1, ans);
		return ans;
	}

	private void dfs(int u, int p, List<Integer> ans) {
		inTime[u] = counter++;
		lowestInTimeReached[u] = inTime[u];
		int unVisitedChildren = 0;
		for (int v : adj[u]) {
			if (v != p) {
				if (inTime[v] == -1) {
					unVisitedChildren++;
					dfs(v, u, ans);
					// non-root case
					if (inTime[u] > 0 && inTime[u] <= lowestInTimeReached[v]) {
						ans.add(u);
					}
					lowestInTimeReached[u] = Math.min(lowestInTimeReached[u], lowestInTimeReached[v]);
				} else {
					lowestInTimeReached[u] = Math.min(lowestInTimeReached[u], inTime[v]);
				}

			}
		}
		// root case
		if (inTime[u] == 0 && unVisitedChildren > 1) {
			ans.add(u);
		}
	}

}
