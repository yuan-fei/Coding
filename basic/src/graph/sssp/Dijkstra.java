package graph.sssp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import basic.SortedMapByValue;
import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import utils.Util;

/** Dijkstra for SSSP: O((V+E)lgV) */
public class Dijkstra {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> s = new GraphNode<String>("s");
		GraphNode<String> t = new GraphNode<String>("t");
		GraphNode<String> x = new GraphNode<String>("x");
		GraphNode<String> y = new GraphNode<String>("y");
		GraphNode<String> z = new GraphNode<String>("z");
		g.addVertex(s).addVertex(t).addVertex(x).addVertex(x).addVertex(y).addVertex(z);
		g.addEdge(new GraphEdge<String>(s, t, 10)).addEdge(new GraphEdge<String>(s, y, 5))
				.addEdge(new GraphEdge<String>(t, x, 1)).addEdge(new GraphEdge<String>(t, y, 2))
				.addEdge(new GraphEdge<String>(y, t, 3)).addEdge(new GraphEdge<String>(y, x, 9))
				.addEdge(new GraphEdge<String>(y, z, 2)).addEdge(new GraphEdge<String>(x, z, 4))
				.addEdge(new GraphEdge<String>(z, s, 7)).addEdge(new GraphEdge<String>(z, x, 6));
		testDijkstra(g, s);
	}

	private static void testDijkstra(Graph<String> g, GraphNode<String> s) {
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
		SortedMapByValue<GraphNode<T>, Double> map = new SortedMapByValue<GraphNode<T>, Double>();
		for (GraphNode<T> v : g.vertices) {
			if (v.equals(s)) {
				map.put(s, 0d);
				distance.put(s, 0d);
			} else {
				map.put(v, Double.POSITIVE_INFINITY);
			}
		}
		while (!map.isEmpty()) {
			Entry<GraphNode<T>, Double> entry = map.removeFirst();
			for (GraphEdge<T> e : g.edges.getOrDefault(entry.getKey(), Collections.<GraphEdge<T>>emptyList())) {
				if (map.containsKey(e.target)) {
					if (distance.getOrDefault(e.target, Double.POSITIVE_INFINITY) > distance.get(entry.getKey())
							+ e.weight) {
						distance.put(e.target, distance.get(entry.getKey()) + e.weight);
						predecessor.put(e.target, entry.getKey());
						map.put(e.target, distance.get(entry.getKey()) + e.weight);
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
