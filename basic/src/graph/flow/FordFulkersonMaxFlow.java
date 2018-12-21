package graph.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Fast implementation with O(|F|v) */
public class FordFulkersonMaxFlow {

	public static void main(String[] args) {
		FordFulkersonMaxFlow ffmf = new FordFulkersonMaxFlow();
		ffmf.addEdges(0, 1, 16);
		ffmf.addEdges(0, 2, 13);
		ffmf.addEdges(1, 2, 10);
		ffmf.addEdges(1, 3, 12);
		ffmf.addEdges(2, 1, 4);
		ffmf.addEdges(2, 4, 14);
		ffmf.addEdges(3, 2, 9);
		ffmf.addEdges(4, 3, 7);
		ffmf.addEdges(4, 5, 4);
		ffmf.addEdges(3, 5, 20);
		System.out.println(ffmf.getMaxFlow(0, 5));
	}

	Map<Integer, List<Edge>> edges = new HashMap<>();
	boolean[] visited;

	public void addEdges(int from, int to, int capacity) {
		Edge e1 = new Edge(to, capacity);
		Edge e2 = new Edge(from, 0);
		e1.reverse = e2;
		e2.reverse = e1;
		if (!edges.containsKey(from)) {
			edges.put(from, new ArrayList<>());
		}
		edges.get(from).add(e1);
		if (!edges.containsKey(to)) {
			edges.put(to, new ArrayList<>());
		}
		edges.get(to).add(e2);
	}

	private int dfs(int v, int t, int minCap) {
		if (v == t) {
			return minCap;
		}
		visited[v] = true;

		for (Edge e : edges.get(v)) {
			if (!visited[e.to] && e.capacity > 0) {
				int d = dfs(e.to, t, Math.min(minCap, e.capacity));
				if (d > 0) {
					e.capacity -= d;
					e.reverse.capacity += d;
					return d;
				}
			}
		}
		return 0;
	}

	public int getMaxFlow(int s, int t) {
		int flow = 0;
		while (true) {
			visited = new boolean[edges.size()];
			int f = dfs(s, t, Integer.MAX_VALUE);
			if (f == 0) {
				return flow;
			}
			flow += f;
		}
	}

	class Edge {
		int to;
		int capacity;
		Edge reverse;

		public Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
		}

		@Override
		public String toString() {
			return reverse.to + "->" + to + ":" + capacity;
		}
	}
}
