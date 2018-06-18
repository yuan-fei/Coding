package graph;

import java.util.List;
import java.util.stream.Collectors;

import basic.SortedMapByValue;
import basic.UnionFindSet;
import utils.Util;

public class MinimumSpanningTree {

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
		GraphNode<String> i = new GraphNode<String>("i");
		graph.addVertex(a).addVertex(b).addVertex(c).addVertex(d).addVertex(e).addVertex(f).addVertex(g).addVertex(h)
				.addVertex(i);
		graph.addEdge(new GraphEdge<String>(a, b, 4)).addEdge(new GraphEdge<String>(a, h, 8))
				.addEdge(new GraphEdge<String>(b, h, 11)).addEdge(new GraphEdge<String>(b, c, 8))
				.addEdge(new GraphEdge<String>(c, d, 7)).addEdge(new GraphEdge<String>(d, e, 9))
				.addEdge(new GraphEdge<String>(d, f, 14)).addEdge(new GraphEdge<String>(e, f, 10))
				.addEdge(new GraphEdge<String>(c, i, 2)).addEdge(new GraphEdge<String>(c, f, 4))
				.addEdge(new GraphEdge<String>(i, h, 7)).addEdge(new GraphEdge<String>(i, g, 6))
				.addEdge(new GraphEdge<String>(h, g, 1)).addEdge(new GraphEdge<String>(g, f, 2))
				.addEdge(new GraphEdge<String>(b, a, 4)).addEdge(new GraphEdge<String>(h, a, 8))
				.addEdge(new GraphEdge<String>(h, b, 11)).addEdge(new GraphEdge<String>(c, b, 8))
				.addEdge(new GraphEdge<String>(d, c, 7)).addEdge(new GraphEdge<String>(e, d, 9))
				.addEdge(new GraphEdge<String>(f, d, 14)).addEdge(new GraphEdge<String>(f, e, 10))
				.addEdge(new GraphEdge<String>(i, c, 2)).addEdge(new GraphEdge<String>(f, c, 4))
				.addEdge(new GraphEdge<String>(h, i, 7)).addEdge(new GraphEdge<String>(g, i, 6))
				.addEdge(new GraphEdge<String>(g, h, 1)).addEdge(new GraphEdge<String>(f, g, 2));
		Graph<String> mst = mstWithKruskal(graph);
		output(mst.getEdges());
		mst = mstWithPrim(graph, a);
		output(mst.getEdges());
	}

	private static void output(List<GraphEdge<String>> edges) {
		System.out.println(Util.toString(edges));
	}

	/** O(ElgV) */
	public static <T> Graph<T> mstWithKruskal(Graph<T> g) {
		UnionFindSet<GraphNode<T>> ufs = new UnionFindSet<GraphNode<T>>();
		for (GraphNode<T> node : g.vertices) {
			ufs.makeSet(node);
		}

		// Edges ordered by weight
		List<GraphEdge<T>> edgesOrderByWeight = g.getEdges().stream().sorted(new GraphEdge.ComparatorByWeight<T>(true))
				.collect(Collectors.toList());
		Graph<T> mst = new Graph<T>();
		mst.vertices = g.vertices;
		for (GraphEdge<T> e : edgesOrderByWeight) {
			if (!ufs.connected(e.source, e.target)) {
				ufs.union(e.source, e.target);
				mst.addEdge(e);
			}
		}
		return mst;
	}

	/** O(ElgV) */
	public static <T> Graph<T> mstWithPrim(Graph<T> g, GraphNode<T> r) {
		Graph<T> mst = new Graph<T>();
		mst.vertices = g.vertices;
		SortedMapByValue<GraphNode<T>, GraphEdge<T>> map = new SortedMapByValue<GraphNode<T>, GraphEdge<T>>(
				new GraphEdge.ComparatorByWeight<T>(true));
		for (GraphNode<T> node : g.vertices) {
			if (node.equals(r)) {
				map.put(node, new GraphEdge<T>(node, node, Double.POSITIVE_INFINITY));
			} else {
				map.put(r, new GraphEdge<T>(r, r, 0));
			}
		}

		while (!map.isEmpty()) {
			GraphEdge<T> minEdge = map.removeFirst().getValue();
			mst.addEdge(minEdge);
			List<GraphEdge<T>> edges = g.edges.get(minEdge.target);
			if (edges != null) {
				for (GraphEdge<T> e : edges) {
					GraphEdge<T> curMinEdge = map.get(e.target);
					if (curMinEdge != null && curMinEdge.weight > e.weight) {
						map.put(e.target, e);
					}
				}
			}
		}
		return mst;
	}

}
