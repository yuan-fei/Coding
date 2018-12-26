package graph.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given unit cost of each edge and a given amount of flow, the minimum cost to
 * consume the flow
 */
public class MinimumCostFlow {

	public static void main(String[] args) {
		MinimumCostFlow m = new MinimumCostFlow();
		m.addEdges(0, 1, 10, 2);
		m.addEdges(0, 2, 2, 4);
		m.addEdges(1, 2, 6, 6);
		m.addEdges(1, 3, 6, 2);
		m.addEdges(3, 2, 3, 3);
		m.addEdges(2, 4, 5, 2);
		m.addEdges(3, 4, 8, 6);
		System.out.println(m.getMinCostMaxFlow(0, 4, 9));
	}

	class Edge {
		int to;
		double capacity;
		double cost;
		Edge reverse;

		public Edge(int to, double capacity, double cost) {
			this.to = to;
			this.capacity = capacity;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return reverse.to + "->" + to + ":" + capacity;
		}
	}

	Map<Integer, List<Edge>> edges = new HashMap<>();

	public void addEdges(int from, int to, double capacity, double cost) {
		Edge e1 = new Edge(to, capacity, cost);
		Edge e2 = new Edge(from, 0, -cost);
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

	public double getMinCostMaxFlow(int s, int t, double f) {
		double res = 0;

		while (f > 0) {
			Map<Integer, Double> cost = new HashMap<>();
			Map<Integer, Edge> preEdge = new HashMap<>();
			cost.put(s, 0d);
			boolean updated = true;
			while (updated) {
				updated = false;
				for (int u : edges.keySet()) {
					if (cost.containsKey(u)) {
						double costU = cost.get(u);
						for (Edge e : edges.get(u)) {
							if (e.capacity > 0 && (!cost.containsKey(e.to) || e.cost + costU < cost.get(e.to))) {
								cost.put(e.to, e.cost + costU);
								preEdge.put(e.to, e);
								updated = true;
							}
						}
					}
				}
			}
			if (!cost.containsKey(t)) {
				// no augment path
				return -1;
			}
			// find residual flow
			double minCapacity = f;
			for (int v = t; v != s; v = preEdge.get(v).reverse.to) {
				minCapacity = Math.min(minCapacity, preEdge.get(v).capacity);
			}
			f -= minCapacity;
			res += minCapacity * cost.get(t);

			for (int v = t; v != s; v = preEdge.get(v).reverse.to) {
				preEdge.get(v).capacity -= minCapacity;
				preEdge.get(v).reverse.capacity += minCapacity;
			}

		}
		return res;

	}
}
