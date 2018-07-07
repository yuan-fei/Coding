package graph.flow;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;

/**
 * Find max flow in a network with push-relabel operation in O(EV^2). Idea: only
 * flow from high position to low position. Invariant: height(u) <= height(v) +
 * 1
 */
public class GenericPushRelabelMaxFlow {

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
		PushRelabelResidualNetworkState<T> r = new PushRelabelResidualNetworkState<T>(g, s, t);
		boolean operated = true;
		while (operated) {
			operated = false;
			for (GraphNode<T> u : r.getResidualVertices()) {
				if (r.isRelabelApplicable(u)) {
					r.relabel(u);
					operated = true;
				}
				for (GraphEdge<T> e : r.getResidualEdges(u)) {
					if (r.isPushApplicable(u, e.target)) {
						r.push(u, e.target);
						operated = true;
					}
				}
			}
		}
		return r;
	}
}
