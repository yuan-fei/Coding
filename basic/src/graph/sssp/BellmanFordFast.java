package graph.sssp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordFast {

	static int[] firstMoveWinCache;
	static List<Integer>[] adj;
	private static long[] dist;
	private static long[] c;
	private static int[] from;
	private static int[] to;
	private static boolean[] negCycle;
	static long MAX = (long) 2e18 + 2;

	public static void main(String[] args) {
		solve(6, 7,
				new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 1, 3 }, new int[] { 3, 5 },
						new int[] { 5, 4 }, new int[] { 4, 3 }, new int[] { 6, 1 } },
				new long[] { 10, 5, 100, 7, 10, -18, -1 }, 1);
	}

	private static void solve(int N, int M, int[][] edges, long[] cost, int s) {
		adj = new List[N + 1];
		dist = new long[N + 1];
		from = new int[M + 1];
		to = new int[M + 1];
		c = new long[M + 1];
		negCycle = new boolean[N + 1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			int u = edges[i - 1][0];
			int v = edges[i - 1][1];
			adj[u].add(i);
			from[i] = u;
			to[i] = v;
			c[i] = cost[i - 1];
		}
		bellmanFord(N, M, s);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] >= MAX) {
				sb.append("unreachable\n");
			} else {
				if (negCycle[i]) {
					sb.append("vertex in negative cycle\n");
				} else {
					sb.append(dist[i] + "\n");
				}
			}
		}
		System.out.println(sb.toString());
	}

	private static void bellmanFord(int N, int M, int s) {
		Arrays.fill(dist, MAX);
		dist[s] = 0;
		for (int i = 1; i <= N; i++) {
			for (int e = 1; e <= M; e++) {
				int u = from[e];
				int v = to[e];
				if (dist[u] < MAX) {
					if (dist[v] > dist[u] + c[e]) {
						// in case negative cycle overflow
						dist[v] = Math.max(-MAX, dist[u] + c[e]);
					}
				}
			}
		}
		for (int e = 1; e <= M; e++) {
			int u = from[e];
			int v = to[e];
			if (dist[u] < MAX) {
				if (dist[v] > dist[u] + c[e] && !negCycle[v]) {
					markNegCycle(v);
				}
			}
		}
	}

	static void markNegCycle(int u) {
		negCycle[u] = true;
		for (int e : adj[u]) {
			int v = to[e];
			if (!negCycle[v]) {
				markNegCycle(v);
			}
		}
	}

}