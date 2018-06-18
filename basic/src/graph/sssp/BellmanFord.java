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
import utils.Util;

/**
 * Bellman-Ford single source shortest path is used for Directed graph with
 * O(VE). The weights of edge can be negative, and it can be used to detect
 * negative weight circle
 */
public class BellmanFord<T> {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> s = new GraphNode<String>("s");
		GraphNode<String> t = new GraphNode<String>("t");
		GraphNode<String> x = new GraphNode<String>("x");
		GraphNode<String> y = new GraphNode<String>("y");
		GraphNode<String> z = new GraphNode<String>("z");
		g.addVertex(s).addVertex(t).addVertex(x).addVertex(x).addVertex(y).addVertex(z);
		GraphEdge<String> edge = new GraphEdge<String>(z, x, 7);
		g.addEdge(new GraphEdge<String>(s, t, 6)).addEdge(new GraphEdge<String>(s, y, 7))
				.addEdge(new GraphEdge<String>(t, x, 5)).addEdge(new GraphEdge<String>(x, t, -2))
				.addEdge(new GraphEdge<String>(t, y, 8)).addEdge(new GraphEdge<String>(y, x, -3))
				.addEdge(new GraphEdge<String>(y, z, 9)).addEdge(new GraphEdge<String>(t, z, -4))
				.addEdge(new GraphEdge<String>(z, s, 2)).addEdge(edge);
		testBellmanFord(g, s);
		g.removeEdge(edge).addEdge(new GraphEdge<String>(z, x, 4));
		testBellmanFord(g, s);
	}

	private static void testBellmanFord(Graph<String> g, GraphNode<String> s) {
		Map<GraphNode<String>, GraphNode<String>> predecessor = new HashMap<GraphNode<String>, GraphNode<String>>();
		Map<GraphNode<String>, Double> distance = new HashMap<GraphNode<String>, Double>();
		List<GraphNode<String>> negativeWeightCircle = new ArrayList<GraphNode<String>>();
		boolean hasNoNegativeCircle = getShortestPath(g, s, predecessor, distance, negativeWeightCircle);
		if (hasNoNegativeCircle) {
			System.out.println("No negative weight circle");
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
		} else {
			System.out.println("Negative weight circle");
			System.out.println(Util.toString(negativeWeightCircle));
		}
	}

	/**
	 * Get single source shortest path. Populate the distances and return true if
	 * there is no negative circle, or populate the negativeWeightCircle and return
	 * false if there is no negative circle.
	 */
	public static <T> boolean getShortestPath(Graph<T> g, GraphNode<T> s, Map<GraphNode<T>, GraphNode<T>> predecessor,
			Map<GraphNode<T>, Double> distance, List<GraphNode<T>> negativeWeightCircle) {
		distance.put(s, 0d);
		for (int i = 0; i < g.vertices.size() - 1; i++) {
			for (GraphEdge<T> e : g.getEdges()) {
				if (distance.containsKey(e.source)) {
					if (distance.getOrDefault(e.target, Double.MAX_VALUE) > distance.get(e.source) + e.weight) {
						distance.put(e.target, distance.get(e.source) + e.weight);
						predecessor.put(e.target, e.source);
					}
				}
			}
		}
		for (GraphEdge<T> e : g.getEdges()) {
			if (distance.containsKey(e.source) && distance.containsKey(e.target)) {
				if (distance.get(e.target) > distance.get(e.source) + e.weight) {
					negativeWeightCircle.addAll(getCircle(e.target, predecessor));
					return false;
				}
			}
		}
		return true;
	}

	private static <T> List<GraphNode<T>> getCircle(GraphNode<T> n, Map<GraphNode<T>, GraphNode<T>> predecessor) {
		List<GraphNode<T>> circle = new ArrayList<GraphNode<T>>();
		GraphNode<T> slow = n;
		GraphNode<T> fast = n;
		while (fast != null && predecessor.get(fast) != null) {
			slow = predecessor.get(slow);
			fast = predecessor.get(predecessor.get(fast));
			if (slow == fast) {
				break;
			}
		}
		slow = n;
		while (fast != slow) {
			slow = predecessor.get(slow);
			fast = predecessor.get(fast);
		}

		circle.add(slow);
		fast = predecessor.get(slow);
		while (fast != slow) {
			circle.add(fast);
			fast = predecessor.get(fast);
		}
		return circle;
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
