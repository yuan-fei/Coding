package graph.apsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import graph.AllPairShortestPathResult;
import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import graph.sssp.BellmanFord;
import graph.sssp.Dijkstra;
import utils.Util;

public class JohnsonSparseGraph {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> v1 = new GraphNode<String>("1");
		GraphNode<String> v2 = new GraphNode<String>("2");
		GraphNode<String> v3 = new GraphNode<String>("3");
		GraphNode<String> v4 = new GraphNode<String>("4");
		GraphNode<String> v5 = new GraphNode<String>("5");
		g.addVertex(v1).addVertex(v2).addVertex(v3).addVertex(v4).addVertex(v5);
		GraphEdge<String> e1 = new GraphEdge<String>(v3, v2, 4);
		GraphEdge<String> e2 = new GraphEdge<String>(v3, v2, 0);
		g.addEdge(new GraphEdge<String>(v1, v2, 3)).addEdge(new GraphEdge<String>(v1, v5, -4))
				.addEdge(new GraphEdge<String>(v1, v3, 8)).addEdge(new GraphEdge<String>(v2, v5, 7))
				.addEdge(new GraphEdge<String>(v2, v4, 1)).addEdge(e1).addEdge(new GraphEdge<String>(v4, v1, 2))
				.addEdge(new GraphEdge<String>(v4, v3, -5)).addEdge(new GraphEdge<String>(v5, v4, 6));
		double[][] weights = g.getAdjacentMatrix();
		System.out.println(Arrays.deepToString(weights));
		System.out.println(getShortestPaths(g));

		System.out.println();
		g.removeEdge(e1).addEdge(e2);
		weights = g.getAdjacentMatrix();
		System.out.println(Arrays.deepToString(weights));
		System.out.println(getShortestPaths(g));

	}

	public static <T> AllPairShortestPathResult getShortestPaths(Graph<T> g) {
		AllPairShortestPathResult r = new AllPairShortestPathResult(g.vertices.size());
		Util.fill(r.distance, Double.POSITIVE_INFINITY);
		Util.fill(r.predecessor, -1);
		GraphNode<T> superSrc = new GraphNode<T>(null);
		Graph<T> eg = genrateExtendedGraph(g, superSrc);
		Map<GraphNode<T>, Double> vertexWeight = new HashMap<GraphNode<T>, Double>();
		if (getVertexWeight(eg, superSrc, vertexWeight)) {
			Graph<T> reWerightedGraph = reWeight(g, vertexWeight);
			Map<GraphNode<T>, GraphNode<T>> predecessor = new HashMap<GraphNode<T>, GraphNode<T>>();
			Map<GraphNode<T>, Double> distance = new HashMap<GraphNode<T>, Double>();

			for (GraphNode<T> s : reWerightedGraph.vertices) {
				distance.clear();
				predecessor.clear();
				Dijkstra.getShortestPath(reWerightedGraph, s, predecessor, distance);
				for (GraphNode<T> t : distance.keySet()) {
					distance.put(t, distance.get(t) - getWeightAdjustment(vertexWeight, s, t));
				}
				addSingleToAllResult(r, predecessor, distance, reWerightedGraph, s);
			}
		} else {
			r.hasNegativeCircle = true;
		}
		return r;
	}

	private static <T> void addSingleToAllResult(AllPairShortestPathResult r,
			Map<GraphNode<T>, GraphNode<T>> predecessor, Map<GraphNode<T>, Double> distance, Graph<T> reWerightedGraph,
			GraphNode<T> s) {
		Map<GraphNode<T>, Integer> map = reWerightedGraph.getVerticeIndexMapping();
		for (Entry<GraphNode<T>, Double> e : distance.entrySet()) {
			r.distance[map.get(s)][map.get(e.getKey())] = e.getValue();
		}
		for (Entry<GraphNode<T>, GraphNode<T>> e : predecessor.entrySet()) {
			r.predecessor[map.get(s)][map.get(e.getKey())] = map.get(e.getValue());
		}
	}

	private static <T> Graph<T> genrateExtendedGraph(Graph<T> g, GraphNode<T> s) {
		Graph<T> eg = new Graph<T>();
		eg.addVertex(s);
		eg.vertices.addAll(g.vertices);
		eg.edges.putAll(g.edges);
		for (GraphNode<T> v : g.vertices) {
			eg.addEdge(new GraphEdge<T>(s, v, 0));
		}
		return eg;
	}

	private static <T> boolean getVertexWeight(Graph<T> g, GraphNode<T> s, Map<GraphNode<T>, Double> vertexWeight) {
		return BellmanFord.getShortestPath(g, s, new HashMap<GraphNode<T>, GraphNode<T>>(), vertexWeight,
				new ArrayList<GraphNode<T>>());
	}

	private static <T> Graph<T> reWeight(Graph<T> g, Map<GraphNode<T>, Double> vertexWeight) {
		Graph<T> rwg = new Graph<T>();
		rwg.vertices = g.vertices;
		for (GraphEdge<T> e : g.getEdges()) {
			double adjust = getWeightAdjustment(vertexWeight, e.source, e.target);
			rwg.addEdge(new GraphEdge<T>(e.source, e.target, e.weight + adjust));
		}
		return rwg;
	}

	private static <T> double getWeightAdjustment(Map<GraphNode<T>, Double> vertexWeight, GraphNode<T> s,
			GraphNode<T> t) {
		return vertexWeight.get(s) - vertexWeight.get(t);
	}
}
