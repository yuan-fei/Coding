package graph.sssp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import graph.GraphSearch;
import utils.Util;

/**
 * SSSP for DAG which has no circle: O(V+E),
 */
public class SingleSourceShortestPathForDAG {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> r = new GraphNode<String>("r");
		GraphNode<String> s = new GraphNode<String>("s");
		GraphNode<String> t = new GraphNode<String>("t");
		GraphNode<String> x = new GraphNode<String>("x");
		GraphNode<String> y = new GraphNode<String>("y");
		GraphNode<String> z = new GraphNode<String>("z");
		g.addVertex(r).addVertex(s).addVertex(t).addVertex(x).addVertex(x).addVertex(y).addVertex(z);
		g.addEdge(new GraphEdge<String>(r, s, 5)).addEdge(new GraphEdge<String>(r, t, 3))
				.addEdge(new GraphEdge<String>(s, t, 2)).addEdge(new GraphEdge<String>(s, x, 6))
				.addEdge(new GraphEdge<String>(x, y, -1)).addEdge(new GraphEdge<String>(x, z, 1))
				.addEdge(new GraphEdge<String>(t, x, 7)).addEdge(new GraphEdge<String>(t, y, 4))
				.addEdge(new GraphEdge<String>(t, z, 2)).addEdge(new GraphEdge<String>(y, z, -2));
		testSSSPForDAG(g, s);
	}

	private static void testSSSPForDAG(Graph<String> g, GraphNode<String> s) {
		Map<GraphNode<String>, GraphNode<String>> predecessor = new HashMap<GraphNode<String>, GraphNode<String>>();
		Map<GraphNode<String>, Double> distance = new HashMap<GraphNode<String>, Double>();
		getShortestPath(g, s, predecessor, distance);

		System.out.println(Util.toString(predecessor.entrySet().stream().map(e -> {
			return "<" + e.getKey() + ", " + e.getValue() + ">";
		}).collect(Collectors.toList())));
		System.out.println(Util.toString(distance.entrySet().stream().map(e -> {
			return "<" + e.getKey() + ", " + e.getValue() + ">";
		}).collect(Collectors.toList())));
		Map<GraphNode<String>, List<GraphEdge<String>>> paths = populatePath(g, s, predecessor);
		System.out.println(Util.toString(paths.entrySet().stream().map(e -> {
			return "<" + e.getKey() + ", " + Util.toString(e.getValue()) + ">";
		}).collect(Collectors.toList())));

	}

	public static <T> void getShortestPath(Graph<T> g, GraphNode<T> s, Map<GraphNode<T>, GraphNode<T>> predecessor,
			Map<GraphNode<T>, Double> distance) {
		GraphSearch<T> gs = new GraphSearch<T>(g);
		List<GraphNode<T>> tOrderedNodes = gs.topologicalSort();
		distance.put(s, 0d);
		for (GraphNode<T> node : tOrderedNodes) {
			for (GraphEdge<T> e : g.edges.getOrDefault(node, Collections.<GraphEdge<T>>emptyList())) {
				if (distance.containsKey(e.source)) {
					if (distance.getOrDefault(e.target, Double.MAX_VALUE) > distance.get(e.source) + e.weight) {
						distance.put(e.target, distance.get(e.source) + e.weight);
						predecessor.put(e.target, e.source);
					}
				}
			}
		}
	}

	private static <T> Map<GraphNode<T>, List<GraphEdge<T>>> populatePath(Graph<T> g, GraphNode<T> s,
			Map<GraphNode<T>, GraphNode<T>> predecessor) {
		Map<GraphNode<T>, List<GraphEdge<T>>> map = new HashMap<GraphNode<T>, List<GraphEdge<T>>>();
		map.put(s, Collections.emptyList());
		for (GraphNode<T> n : predecessor.keySet()) {
			if (!map.containsKey(n)) {
				populatePath(g, s, n, predecessor, map);
			}
		}
		return map;
	}

	private static <T> void populatePath(Graph<T> g, GraphNode<T> s, GraphNode<T> t,
			Map<GraphNode<T>, GraphNode<T>> predecessor, Map<GraphNode<T>, List<GraphEdge<T>>> map) {
		if (map.containsKey(t)) {
			return;
		}
		populatePath(g, s, predecessor.get(t), predecessor, map);
		List<GraphEdge<T>> edges = new ArrayList<GraphEdge<T>>();
		edges.addAll(map.get(predecessor.get(t)));
		edges.add(g.edges.get(predecessor.get(t)).stream().filter(e -> e.target == t).findFirst().get());
		map.put(t, edges);
	}
}
