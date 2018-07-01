package graph.flow;

import java.util.List;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import graph.GraphSearch;
import graph.ResidualNetworkState;

/**
 * Find max flow in a network with O(VE^2). A implementation of Ford-Fulkerson
 * method
 */
public class EdmondsKarpMaxFlow {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> v1 = new GraphNode<String>("1");
		GraphNode<String> v2 = new GraphNode<String>("2");
		GraphNode<String> v3 = new GraphNode<String>("3");
		GraphNode<String> v4 = new GraphNode<String>("4");
		GraphNode<String> s = new GraphNode<String>("s");
		GraphNode<String> t = new GraphNode<String>("t");
		g.addVertex(v1).addVertex(v2).addVertex(v3).addVertex(v4).addVertex(s).addVertex(t);

		g.addEdge(new GraphEdge<String>(s, v1, 16)).addEdge(new GraphEdge<String>(s, v2, 13))
				.addEdge(new GraphEdge<String>(v1, v2, 10)).addEdge(new GraphEdge<String>(v1, v3, 12))
				.addEdge(new GraphEdge<String>(v2, v1, 4)).addEdge(new GraphEdge<String>(v2, v4, 14))
				.addEdge(new GraphEdge<String>(v3, v2, 9)).addEdge(new GraphEdge<String>(v4, v3, 7))
				.addEdge(new GraphEdge<String>(v4, t, 4)).addEdge(new GraphEdge<String>(v3, t, 20));
		System.out.println(g);
		ResidualNetworkState<String> st = getMaxFlow(g, s, t);
		System.out.println(st);
	}

	public static <T> ResidualNetworkState<T> getMaxFlow(Graph<T> g, GraphNode<T> s, GraphNode<T> t) {
		ResidualNetworkState<T> r = new ResidualNetworkState<T>(g, s, t);
		List<GraphEdge<T>> path = findAugmentPath(r, s, t);
		while (!path.isEmpty()) {
			double deltaFlow = findMinResidualCapacity(path);
			updateFlow(r, path, deltaFlow);
			path = findAugmentPath(r, s, t);
		}
		return r;
	}

	private static <T> double findMinResidualCapacity(List<GraphEdge<T>> path) {
		return path.stream().mapToDouble(e -> e.weight).min().orElse(0);
	}

	private static <T> void updateFlow(ResidualNetworkState<T> r, List<GraphEdge<T>> path, double deltaFlow) {
		for (GraphEdge<T> e : path) {
			double netFlow = r.getFlow(e.source, e.target) + deltaFlow;
			r.updateFlow(e.source, e.target, netFlow);
		}
	}

	private static <T> List<GraphEdge<T>> findAugmentPath(ResidualNetworkState<T> r, GraphNode<T> s, GraphNode<T> t) {
		Graph<T> rn = r.getResidualNetwork();
		GraphSearch<T> gs = new GraphSearch<T>(rn);
		List<GraphEdge<T>> edges = gs.bfsFindPath(s, t);
		return edges;
	}

}
