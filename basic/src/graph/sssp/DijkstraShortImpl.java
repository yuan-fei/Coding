package graph.sssp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/** Short implementation but with slower runtime: O((V+E)logE) */
public class DijkstraShortImpl {

	static List<Integer>[] adj;
	private static int[] dist;
	private static int[] weight;
	private static int[] from;
	private static int[] to;
	// MAX should be greater than sum(weight)
	static int MAX = (int) 1e9 + 2;

	public static void main(String[] args) throws Exception {
		int N = 6;
		int[][] edges = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 5, 6 }, { 6, 1 }, { 1, 3 }, { 2, 3 }, { 4, 3 }, { 6, 3 } };
		int[] cost = { 7, 15, 6, 9, 14, 9, 10, 11, 2 };
		computeAllShortestPath(N, edges, cost, 1);
		System.out.println(Arrays.toString(dist));
	}

	private static void computeAllShortestPath(int N, int[][] edges, int[] cost, int s) {
		int M = edges.length;
		adj = new List[N + 1];
		from = new int[M + 1];
		to = new int[M + 1];
		dist = new int[N + 1];
		weight = cost;
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int u = edges[i][0];
			int v = edges[i][1];
			adj[u].add(i);
			adj[v].add(i);
			from[i] = u;
			to[i] = v;
		}
		dijkstra(N, M, s);
	}

	private static void dijkstra(int N, int M, int s) {
		Arrays.fill(dist, MAX);
		// int[2]: {vertex, dist}
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		q.offer(new int[] { s, 0 });
		int done = 0;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (dist[cur[0]] > cur[1]) {
				dist[cur[0]] = cur[1];
				done++;
				if (done == N) {
					return;
				} else {
					for (int e : adj[cur[0]]) {
						int v = from[e] + to[e] - cur[0];
						if (dist[v] > dist[cur[0]] + weight[e]) {
							q.offer(new int[] { v, dist[cur[0]] + weight[e] });
						}
					}
				}
			}
		}
	}

}
