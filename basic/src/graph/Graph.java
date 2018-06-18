package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
