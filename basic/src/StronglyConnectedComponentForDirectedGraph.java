import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import basic.Graph;
import basic.GraphEdge;
import basic.GraphNode;
import utils.Util;

/**
 * Strongly Connected Component for directed graph
 */
public class StronglyConnectedComponentForDirectedGraph {
	public static void main(String[] args) {
		Graph<String> graph = new Graph<String>();
		GraphNode<String> a = new GraphNode<String>("a");
		GraphNode<String> b = new GraphNode<String>("b");
		GraphNode<String> c = new GraphNode<String>("c");
		GraphNode<String> d = new GraphNode<String>("d");
		GraphNode<String> e = new GraphNode<String>("e");
		GraphNode<String> f = new GraphNode<String>("f");
		GraphNode<String> g = new GraphNode<String>("g");
		GraphNode<String> h = new GraphNode<String>("h");
		graph.addVertex(c).addVertex(b).addVertex(a).addVertex(d).addVertex(e).addVertex(f).addVertex(g).addVertex(h);
		graph.addEdge(new GraphEdge<String>(a, b)).addEdge(new GraphEdge<String>(b, e))
				.addEdge(new GraphEdge<String>(e, a)).addEdge(new GraphEdge<String>(b, f))
				.addEdge(new GraphEdge<String>(e, f)).addEdge(new GraphEdge<String>(b, c))
				.addEdge(new GraphEdge<String>(f, g)).addEdge(new GraphEdge<String>(g, f))
				.addEdge(new GraphEdge<String>(c, d)).addEdge(new GraphEdge<String>(d, c))
				.addEdge(new GraphEdge<String>(c, g)).addEdge(new GraphEdge<String>(d, h))
				.addEdge(new GraphEdge<String>(g, h));
		List<List<GraphNode<String>>> sccs = sscWithDFS(graph);
		output(sccs);
	}

	private static void output(List<List<GraphNode<String>>> sccs) {
		for (List<GraphNode<String>> scc : sccs) {
			System.out.println(Util.toString(scc.stream().map(n -> {
				return n.val;
			}).collect(Collectors.toList())));
		}
	}

	public static <T> List<List<GraphNode<T>>> sscWithDFS(Graph<T> graph) {
		Map<GraphNode<T>, Integer> visited = new HashMap<GraphNode<T>, Integer>();
		List<GraphNode<T>> nodesByFinishTime = new ArrayList<GraphNode<T>>();
		for (GraphNode<T> node : graph.vertices) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				dfs1(graph, node, visited, nodesByFinishTime);
			}
		}

		Collections.reverse(nodesByFinishTime);
		Graph<T> transposedGraph = transposeAndSortGraph(graph, nodesByFinishTime);
		List<List<GraphNode<T>>> sccs = new ArrayList<List<GraphNode<T>>>();
		visited.clear();
		for (GraphNode<T> node : transposedGraph.vertices) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				List<GraphNode<T>> scc = new ArrayList<GraphNode<T>>();
				dfs2(transposedGraph, node, visited, scc);
				sccs.add(scc);
			}
		}
		return sccs;
	}

	/**
	 * DFS Output nodes in finish time ascending order
	 */
	private static <T> void dfs1(Graph<T> g, GraphNode<T> root, Map<GraphNode<T>, Integer> visited,
			List<GraphNode<T>> result) {
		visited.put(root, 1);
		for (GraphEdge<T> edge : g.edges.getOrDefault(root, Collections.<GraphEdge<T>>emptyList())) {
			visited.putIfAbsent(edge.target, 0);
			if (visited.get(edge.target) == 0) {
				dfs1(g, edge.target, visited, result);
			}
		}
		result.add(root);
		visited.put(root, 2);
	}

	private static <T> void dfs2(Graph<T> g, GraphNode<T> root, Map<GraphNode<T>, Integer> visited,
			List<GraphNode<T>> scc) {
		visited.put(root, 1);
		scc.add(root);
		for (GraphEdge<T> edge : g.edges.getOrDefault(root, Collections.<GraphEdge<T>>emptyList())) {
			visited.putIfAbsent(edge.target, 0);
			if (visited.get(edge.target) == 0) {
				dfs2(g, edge.target, visited, scc);
			}
		}
		visited.put(root, 2);
	}

	/**
	 * nodes in list must be in finish time descending order
	 */
	private static <T> Graph<T> transposeAndSortGraph(Graph<T> g, List<GraphNode<T>> nodes) {
		Graph<T> tg = new Graph<T>();
		tg.vertices = nodes;
		for (int i = 0; i < nodes.size(); i++) {
			for (GraphEdge<T> edge : g.edges.getOrDefault(nodes.get(i), Collections.<GraphEdge<T>>emptyList())) {
				// This guarantee the children is stored in finish time descending order
				tg.addEdge(edge.reverse());
			}
		}
		return tg;
	}

}
