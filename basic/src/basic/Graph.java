package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public Graph<T> addEdge(GraphEdge<T> e) {
		edges.putIfAbsent(e.source, new ArrayList<GraphEdge<T>>());
		edges.get(e.source).add(e);
		return this;
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
		sb.append(Util.toString(edges.values().stream().flatMap(List::stream).collect(Collectors.toList())));
		return sb.toString();
	}
}
