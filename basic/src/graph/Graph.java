package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import utils.Util;

public class Graph<T> {
	public List<GraphNode<T>> vertices = new ArrayList<GraphNode<T>>();
	public Map<GraphNode<T>, List<GraphEdge<T>>> edges = new HashMap<GraphNode<T>, List<GraphEdge<T>>>();

	public Graph<T> addVertex(GraphNode<T> v) {
		if (!vertices.contains(v)) {
			vertices.add(v);
		}
		return this;
	}

	public List<GraphNode<T>> getNeighborVertices(GraphNode<T> u) {
		List<GraphNode<T>> neighbors = new ArrayList<GraphNode<T>>();
		for (GraphNode<T> v : vertices) {
			if (getEdge(u, v) != null || getEdge(v, u) != null) {
				neighbors.add(v);
			}
		}
		return neighbors;
	}

	public Set<GraphNode<T>> getNeighborVertices(Set<GraphNode<T>> s) {
		return s.stream().flatMap(u -> getNeighborVertices(u).stream()).collect(Collectors.toSet());
	}

	public Graph<T> addEdge(GraphEdge<T> e) {
		edges.putIfAbsent(e.source, new ArrayList<GraphEdge<T>>());
		edges.get(e.source).add(e);
		return this;
	}

	public Graph<T> removeEdge(GraphEdge<T> e) {
		List<GraphEdge<T>> edgeList = edges.get(e.source);
		edgeList.remove(e);
		if (edgeList.isEmpty()) {
			edges.remove(e.source);
		}
		return this;
	}

	public List<GraphEdge<T>> getEdges() {
		List<GraphEdge<T>> flattendEdges = new ArrayList<GraphEdge<T>>();
		for (List<GraphEdge<T>> edgeList : edges.values()) {
			flattendEdges.addAll(edgeList);
		}
		return flattendEdges;
	}

	public GraphEdge<T> getEdge(GraphNode<T> u, GraphNode<T> v) {
		List<GraphEdge<T>> edgeList = edges.getOrDefault(u, Collections.<GraphEdge<T>>emptyList());
		return edgeList.stream().filter(e -> e.target == v).findFirst().orElse(null);
	}

	public double[][] getAdjacentMatrix() {
		Map<GraphNode<T>, Integer> map = getVerticeIndexMapping();

		double[][] adjMatrix = new double[vertices.size()][vertices.size()];
		for (int i = 0; i < adjMatrix.length; i++) {
			Arrays.fill(adjMatrix[i], Double.POSITIVE_INFINITY);
			adjMatrix[i][i] = 0;
		}

		for (GraphEdge<T> edge : getEdges()) {
			adjMatrix[map.get(edge.source)][map.get(edge.target)] = edge.weight;
		}
		return adjMatrix;
	}

	public Map<GraphNode<T>, Integer> getVerticeIndexMapping() {
		Map<GraphNode<T>, Integer> map = new HashMap<GraphNode<T>, Integer>();
		for (int i = 0; i < vertices.size(); i++) {
			map.put(vertices.get(i), i);
		}
		return map;
	}

	public Graph<T> getTransposedGraph() {
		Graph<T> g = new Graph<T>();
		for (GraphNode<T> node : vertices) {
			g.addVertex(node);
		}
		for (List<GraphEdge<T>> edges : edges.values()) {
			for (GraphEdge<T> edge : edges) {
				g.addEdge(edge.reverse());
			}
		}
		return g;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Vertices: ");
		sb.append(Util.toString(vertices));
		sb.append("\nEdges: ");
		sb.append(Util.toString(getEdges()));
		return sb.toString();
	}
}
