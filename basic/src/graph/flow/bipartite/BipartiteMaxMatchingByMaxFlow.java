package graph.flow.bipartite;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import graph.flow.PushToFrontMaxFlow;
import graph.flow.ResidualNetworkState;

/**
 * Find the max matching for bipatite graph by max flow solutions in O(VE).
 */
public class BipartiteMaxMatchingByMaxFlow<T> {

	public static void main(String[] args) {
		BipartiteGraph<String> g = new BipartiteGraph<String>();
		GraphNode<String> u0 = new GraphNode<String>("u0");
		GraphNode<String> u1 = new GraphNode<String>("u1");
		GraphNode<String> u2 = new GraphNode<String>("u2");
		GraphNode<String> u3 = new GraphNode<String>("u3");
		GraphNode<String> u4 = new GraphNode<String>("u4");
		GraphNode<String> v0 = new GraphNode<String>("v0");
		GraphNode<String> v1 = new GraphNode<String>("v1");
		GraphNode<String> v2 = new GraphNode<String>("v2");
		GraphNode<String> v3 = new GraphNode<String>("v3");
		GraphNode<String> v4 = new GraphNode<String>("v4");
		g.addVertex(u0, 0).addVertex(u1, 0).addVertex(u2, 0).addVertex(u3, 0).addVertex(u4, 0).addVertex(v0, 1)
				.addVertex(v1, 1).addVertex(v2, 1).addVertex(v3, 1).addVertex(v4, 1);

		g.addEdge(new GraphEdge<String>(u0, v0)).addEdge(new GraphEdge<String>(u0, v1))
				.addEdge(new GraphEdge<String>(u1, v0)).addEdge(new GraphEdge<String>(u1, v4))
				.addEdge(new GraphEdge<String>(u2, v2)).addEdge(new GraphEdge<String>(u2, v3))
				.addEdge(new GraphEdge<String>(u3, v0)).addEdge(new GraphEdge<String>(u3, v4))
				.addEdge(new GraphEdge<String>(u4, v1)).addEdge(new GraphEdge<String>(u4, v3));
		g.addEdge(new GraphEdge<String>(v0, u0)).addEdge(new GraphEdge<String>(v1, u0))
				.addEdge(new GraphEdge<String>(v0, u1)).addEdge(new GraphEdge<String>(v4, u1))
				.addEdge(new GraphEdge<String>(v2, u2)).addEdge(new GraphEdge<String>(v3, u2))
				.addEdge(new GraphEdge<String>(v0, u3)).addEdge(new GraphEdge<String>(v4, u3))
				.addEdge(new GraphEdge<String>(v1, u4)).addEdge(new GraphEdge<String>(v3, u4));
		BipartiteMaxMatchingByMaxFlow<String> h = new BipartiteMaxMatchingByMaxFlow<String>(g);
		System.out.println(h.findMaxMatching());
		System.out.println(h.getMatchings());
	}

	BipartiteGraph<T> g;
	Map<GraphNode<T>, GraphNode<T>> matching = new HashMap<GraphNode<T>, GraphNode<T>>();

	public BipartiteMaxMatchingByMaxFlow(BipartiteGraph<T> g) {
		this.g = g;
	}

	public Map<GraphNode<T>, GraphNode<T>> getMatchings() {
		return Collections.unmodifiableMap(matching);
	}

	public int findMaxMatching() {
		GraphNode<T> s = new GraphNode<T>(null);
		GraphNode<T> t = new GraphNode<T>(null);
		// ResidualNetworkState<T> rn =
		// EdmondsKarpMaxFlow.getMaxFlow(getFlowNetworkGraph(s, t), s, t);
		// ResidualNetworkState<T> rn =
		// GenericPushRelabelMaxFlow.getMaxFlow(getFlowNetworkGraph(s, t), s, t);
		ResidualNetworkState<T> rn = PushToFrontMaxFlow.getMaxFlow(getFlowNetworkGraph(s, t), s, t);
		for (GraphNode<T> u : g.getPart(0)) {
			for (GraphNode<T> v : g.getPart(1)) {
				if (rn.getFlow(u, v) != 0d) {
					matching.put(u, v);
					matching.put(v, u);
				}
			}
		}
		return (int) rn.getNetworkFlow();
	}

	private Graph<T> getFlowNetworkGraph(GraphNode<T> s, GraphNode<T> t) {
		Graph<T> cg = new Graph<T>();
		cg.vertices.addAll(g.vertices);
		cg.edges = new HashMap<GraphNode<T>, List<GraphEdge<T>>>(g.edges);
		for (GraphNode<T> v : g.parts.get(0)) {
			cg.addEdge(new GraphEdge<T>(s, v, 1));
		}
		for (GraphNode<T> v : g.parts.get(1)) {
			cg.addEdge(new GraphEdge<T>(v, t, 1));
		}
		cg.addVertex(s).addVertex(t);
		return cg;
	}
}
