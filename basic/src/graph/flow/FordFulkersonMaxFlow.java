package graph.flow;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println(ffmf.minCut(0, 5));
		ffmf = new FordFulkersonMaxFlow();
		ffmf.addEdges(0, 1, 5);
		ffmf.addEdges(0, 3, 7);
		ffmf.addEdges(2, 6, 6);
		ffmf.addEdges(5, 6, 3);
		ffmf.addEdges(1, 2, 100);
		ffmf.addEdges(1, 4, 100);
		ffmf.addEdges(3, 4, 100);
		ffmf.addEdges(2, 5, 100);
		ffmf.addEdges(4, 5, 100);
		System.out.println(ffmf.getMaxFlow(0, 6));
		System.out.println(ffmf.minCut(0, 6));
	}

	Map<Integer, List<Edge>> edges = new HashMap<>();
	boolean[] visited;

	public void addEdges(int from, int to, double capacity) {
		Edge e1 = new Edge(to, capacity);
		// init reverse edge capacity to 0
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

	private double dfs(int v, int t, double minCap) {
		if (v == t) {
			return minCap;
		}
		visited[v] = true;

		for (Edge e : edges.get(v)) {
			if (!visited[e.to] && e.capacity > 0) {
				double d = dfs(e.to, t, Math.min(minCap, e.capacity));
				if (d > 0) {
					e.capacity -= d;
					e.reverse.capacity += d;
					return d;
				}
			}
		}
		return 0;
	}

	private void getMinCut(int v, List<Integer> res) {
		visited[v] = true;
		res.add(v);
		for (Edge e : edges.get(v)) {
			if (!visited[e.to] && e.capacity > 0) {
				getMinCut(e.to, res);
			}
		}
	}

	public double getMaxFlow(int s, int t) {
		double flow = 0;
		while (true) {
			visited = new boolean[edges.size()];
			double f = dfs(s, t, Integer.MAX_VALUE);
			if (f == 0) {
				return flow;
			}
			flow += f;
		}
	}

	public List<Integer> minCut(int s, int t) {
		Arrays.fill(visited, false);
		List<Integer> res = new ArrayList<Integer>();
		getMinCut(s, res);
		return res;
	}

	class Edge {
		int to;
		double capacity;
		Edge reverse;

		public Edge(int to, double capacity) {
			this.to = to;
			this.capacity = capacity;
		}

		@Override
		public String toString() {
			return reverse.to + "->" + to + ":" + capacity;
		}
	}
}
