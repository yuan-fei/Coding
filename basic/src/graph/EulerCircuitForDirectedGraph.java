package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Hierholzer's algorithm: O(V+E). This can also be used for euler path except
 * we need to find the proper start
 */
public class EulerCircuitForDirectedGraph {

	public static void main(String[] args) {
		// directed graph
		{
			EulerCircuitForDirectedGraph ep = new EulerCircuitForDirectedGraph();
			ep.addEdges(0, 1);
			ep.addEdges(1, 2);
			ep.addEdges(2, 0);
			ep.addEdges(0, 3);
			ep.addEdges(3, 4);
			ep.addEdges(4, 0);
			System.out.println(ep.findPathRec());
		}
		{
			EulerCircuitForDirectedGraph ep = new EulerCircuitForDirectedGraph();
			ep.addEdges(0, 1);
			ep.addEdges(1, 2);
			ep.addEdges(2, 0);
			ep.addEdges(0, 3);
			ep.addEdges(3, 4);
			ep.addEdges(4, 0);
			System.out.println(ep.findPathIter());
		}

	}

	Map<Integer, List<Edge>> edges = new HashMap<>();

	public void addEdges(int from, int to) {
		Edge e1 = new Edge(to);
		if (!edges.containsKey(from)) {
			edges.put(from, new ArrayList<>());
		}
		edges.get(from).add(e1);
	}

	public boolean hasPath() {
		if (isConnected()) {
			Map<Integer, Integer> map = new HashMap<>();
			for (int from : edges.keySet()) {
				for (Edge e : edges.get(from)) {
					map.put(from, map.getOrDefault(from, 0) + 1);
					map.put(e.to, map.getOrDefault(e.to, 0) - 1);
				}
			}
			int onePositive = 0;
			int oneNegative = 0;
			for (int c : map.values()) {
				if (c == 1) {
					if (++onePositive > 1) {
						return false;
					}
				} else if (c == -1) {
					if (++oneNegative > 1) {
						oneNegative++;
					}
				} else if (c != 0) {
					return false;
				}
			}
			return onePositive == oneNegative;
		} else {
			return false;
		}
	}

	private boolean isConnected() {
		Graph<Integer> graph = new Graph<>();
		Map<Integer, GraphNode<Integer>> map = new HashMap<>();
		for (int from : edges.keySet()) {
			map.put(from, new GraphNode<>(from));
			graph.addVertex(map.get(from));
		}
		for (int from : edges.keySet()) {
			for (Edge e : edges.get(from)) {
				graph.addEdge(new GraphEdge<Integer>(map.get(from), map.get(e.to)));
			}
		}

		List<List<GraphNode<Integer>>> sccs = StronglyConnectedComponentForDirectedGraph.ssc(graph);
		return sccs.size() == 1;
	}

	private int getStart() {
		return edges.keySet().iterator().next();
	}

	public List<Integer> findPathRec() {
		if (hasPath()) {
			int k = getStart();
			List<Integer> path = new ArrayList<>();
			findPath(k, path);
			return path;
		}
		return Collections.emptyList();
	}

	public List<Integer> findPathIter() {
		if (hasPath()) {
			int k = getStart();
			return findPath(k);
		}
		return Collections.emptyList();
	}

	public void findPath(int u, List<Integer> path) {
		while (!edges.get(u).isEmpty()) {
			Edge e = edges.get(u).remove(0);
			findPath(e.to, path);
		}
		path.add(u);
	}

	public List<Integer> findPath(int r) {
		List<Integer> path = new ArrayList<>();
		Stack<Integer> s = new Stack<>();
		s.push(r);
		while (!s.isEmpty()) {
			int u = s.peek();
			if (!edges.get(u).isEmpty()) {
				Edge e = edges.get(u).remove(0);
				s.push(e.to);
			} else {
				path.add(s.pop());
			}
		}
		return path;
	}

	class Edge {
		int to;

		public Edge(int to) {
			this.to = to;
		}
	}
}
