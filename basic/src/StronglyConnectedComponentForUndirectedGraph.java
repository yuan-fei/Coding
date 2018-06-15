import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import basic.Graph;
import basic.GraphEdge;
import basic.GraphNode;
import basic.UnionFindSet;

public class StronglyConnectedComponentForUndirectedGraph {
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
		graph.addEdge(new GraphEdge<String>(a, b)).addEdge(new GraphEdge<String>(b, c))
				.addEdge(new GraphEdge<String>(d, a)).addEdge(new GraphEdge<String>(b, d))
				.addEdge(new GraphEdge<String>(e, f)).addEdge(new GraphEdge<String>(f, g))
				.addEdge(new GraphEdge<String>(b, a)).addEdge(new GraphEdge<String>(c, b))
				.addEdge(new GraphEdge<String>(a, d)).addEdge(new GraphEdge<String>(d, b))
				.addEdge(new GraphEdge<String>(f, e)).addEdge(new GraphEdge<String>(g, f));
		List<Graph<String>> sccs = sscWithUFS(graph);
		System.out.println("UFS");
		for (Graph<String> scc : sccs) {
			System.out.println(scc);
		}

		sccs = sscWithDFS(graph);
		System.out.println("DFS");
		for (Graph<String> scc : sccs) {
			System.out.println(scc);
		}
		sccs = sscWithBFS(graph);
		System.out.println("BFS");
		for (Graph<String> scc : sccs) {
			System.out.println(scc);
		}
	}

	public static <T> List<Graph<T>> sscWithUFS(Graph<T> graph) {
		UnionFindSet<GraphNode<T>> ufs = new UnionFindSet<GraphNode<T>>();
		for (GraphNode<T> node : graph.vertices) {
			ufs.makeSet(node);
		}
		for (GraphNode<T> node : graph.vertices) {
			for (GraphEdge<T> edge : graph.edges.getOrDefault(node, Collections.<GraphEdge<T>>emptyList())) {
				ufs.union(node, edge.target);
			}
		}
		List<Graph<T>> sccs = new ArrayList<Graph<T>>();
		for (List<GraphNode<T>> scc : ufs.getAllDisjointSets().values()) {
			Graph<T> g = generateSubGraph(graph, scc);
			sccs.add(g);
		}
		return sccs;
	}

	public static <T> List<Graph<T>> sscWithDFS(Graph<T> graph) {
		GraphSearch<T> gs = new GraphSearch<T>();
		List<List<GraphNode<T>>> nodes = gs.dfs(graph);
		List<Graph<T>> sccs = new ArrayList<Graph<T>>();
		for (List<GraphNode<T>> scc : nodes) {
			Graph<T> g = generateSubGraph(graph, scc);
			sccs.add(g);
		}
		return sccs;
	}

	public static <T> List<Graph<T>> sscWithBFS(Graph<T> graph) {
		GraphSearch<T> gs = new GraphSearch<T>();
		List<List<GraphNode<T>>> nodes = gs.bfs(graph);
		List<Graph<T>> sccs = new ArrayList<Graph<T>>();
		for (List<GraphNode<T>> scc : nodes) {
			Graph<T> g = generateSubGraph(graph, scc);
			sccs.add(g);
		}
		return sccs;
	}

	private static <T> Graph<T> generateSubGraph(Graph<T> graph, List<GraphNode<T>> scc) {
		Graph<T> g = new Graph<T>();
		g.vertices = scc;
		for (GraphNode<T> node : scc) {
			List<GraphEdge<T>> edge = graph.edges.get(node);
			if (edge != null) {
				g.edges.put(node, graph.edges.get(node));
			}
		}
		return g;
	}
}
