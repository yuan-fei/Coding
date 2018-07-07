package graph.flow.bipartite;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import graph.GraphEdge;
import graph.GraphNode;

/** Find the max matching for bipatite graph in O(EV^0.5). */
public class HopcroftKarpBipartiteMaxMatching<T> {

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
		HopcroftKarpBipartiteMaxMatching<String> h = new HopcroftKarpBipartiteMaxMatching<String>(g);
		System.out.println(h.findMaxMatching());
		System.out.println(h.getMatchings());
	}

	BipartiteGraph<T> g;

	public HopcroftKarpBipartiteMaxMatching(BipartiteGraph<T> g) {
		this.g = g;
	}

	public int findMaxMatching() {
		distance.clear();
		matching.clear();
		int matchingNum = 0;
		Set<GraphNode<T>> uSet = g.getPart(0);
		while (markShortestAlternativePathsByBfs()) {
			for (GraphNode<T> u : uSet) {
				if (isFree(u) && augmentWithAlternativePathByDFS(u)) {
					matchingNum++;
				}
			}
		}
		return matchingNum;
	}

	public Map<GraphNode<T>, GraphNode<T>> getMatchings() {
		return Collections.unmodifiableMap(matching);
	}

	Map<GraphNode<T>, Integer> distance = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, GraphNode<T>> matching = new HashMap<GraphNode<T>, GraphNode<T>>();

	private boolean markShortestAlternativePathsByBfs() {
		Set<GraphNode<T>> uSet = g.getPart(0);
		Queue<GraphNode<T>> queue = new LinkedList<GraphNode<T>>();
		for (GraphNode<T> n : uSet) {
			if (isFree(n)) {
				distance.put(n, 0);
				queue.offer(n);
			} else {
				distance.put(n, Integer.MAX_VALUE);
			}
		}
		int shortestDist = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			GraphNode<T> u = queue.poll();
			if (distance.containsKey(u) && distance.get(u) < shortestDist) {
				List<GraphEdge<T>> edges = g.edges.get(u);
				for (GraphEdge<T> e : edges) {
					GraphNode<T> v = e.target;
					if (isFree(v)) {// find a alternative path
						shortestDist = distance.get(u) + 1;
					} else {
						GraphNode<T> uNext = matching.get(v);
						if (distance.get(uNext) == Integer.MAX_VALUE) {
							// Note: find a intermediate vertex on a **possible** alternative path.
							// Here 'possible' means the path may not be a legal alternative path at the end
							// due to no free vertex end in V set. So the DFS search need to check if the
							// ending vertex is free.
							distance.put(uNext, distance.get(u) + 1);
							queue.offer(uNext);
						} else {
							// distance.get(uNext) != Integer.MAX_VALUE: a shorter alternative path has been
							// found before
						}
					}
				}
			}
		}
		return shortestDist != Integer.MAX_VALUE;
	}

	private boolean augmentWithAlternativePathByDFS(GraphNode<T> u) {
		List<GraphEdge<T>> edges = g.edges.get(u);
		for (GraphEdge<T> e : edges) {
			GraphNode<T> v = e.target;
			if (isFree(v)) {
				// find a free vertex in V Set as the end of a alternative path
				matching.put(v, u);
				matching.put(u, v);
				return true;
			}
			GraphNode<T> uNext = matching.get(v);
			if (distance.get(uNext) == distance.get(u) + 1) {
				if (augmentWithAlternativePathByDFS(uNext)) {
					matching.put(v, u);
					matching.put(u, v);
					return true;
				}
			}
		}
		distance.put(u, Integer.MAX_VALUE);
		return false;
	}

	private boolean isFree(GraphNode<T> u) {
		return !matching.containsKey(u);
	}

}
