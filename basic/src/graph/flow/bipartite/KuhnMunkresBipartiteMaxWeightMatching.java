package graph.flow.bipartite;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.GraphEdge;
import graph.GraphNode;

/**
 * AKA Hungraian method: Find the max weight matching for bipatite graph in
 * O(V^3)
 */
public class KuhnMunkresBipartiteMaxWeightMatching<T> {

	public static void main(String[] args) {
		BipartiteGraph<String> g = new BipartiteGraph<String>();
		GraphNode<String> u1 = new GraphNode<String>("u1");
		GraphNode<String> u2 = new GraphNode<String>("u2");
		GraphNode<String> u3 = new GraphNode<String>("u3");

		GraphNode<String> v1 = new GraphNode<String>("v1");
		GraphNode<String> v2 = new GraphNode<String>("v2");
		GraphNode<String> v3 = new GraphNode<String>("v3");
		g.addVertex(u1, 0).addVertex(u2, 0).addVertex(u3, 0).addVertex(v1, 1).addVertex(v2, 1).addVertex(v3, 1);

		g.addEdge(new GraphEdge<String>(u1, v1, 1)).addEdge(new GraphEdge<String>(u1, v2, 6))
				.addEdge(new GraphEdge<String>(u2, v2, 8)).addEdge(new GraphEdge<String>(u2, v3, 6))
				.addEdge(new GraphEdge<String>(u3, v1, 4)).addEdge(new GraphEdge<String>(u3, v3, 1));
		g.addEdge(new GraphEdge<String>(v1, u1, 1)).addEdge(new GraphEdge<String>(v2, u1, 6))
				.addEdge(new GraphEdge<String>(v2, u2, 8)).addEdge(new GraphEdge<String>(v3, u2, 6))
				.addEdge(new GraphEdge<String>(v1, u3, 4)).addEdge(new GraphEdge<String>(v3, u3, 1));
		KuhnMunkresBipartiteMaxWeightMatching<String> h = new KuhnMunkresBipartiteMaxWeightMatching<String>(g);
		System.out.println(h.findMaxMatching());
		System.out.println(h.getMatchings());

	}

	BipartiteGraph<T> g;
	Map<GraphNode<T>, GraphNode<T>> matching = new HashMap<GraphNode<T>, GraphNode<T>>();
	Map<GraphNode<T>, Double> labeling = new HashMap<GraphNode<T>, Double>();
	Set<GraphNode<T>> s = new HashSet<GraphNode<T>>();
	Set<GraphNode<T>> t = new HashSet<GraphNode<T>>();
	BipartiteGraph<T> equalityGraph;

	public KuhnMunkresBipartiteMaxWeightMatching(BipartiteGraph<T> g) {
		this.g = g;
	}

	public Map<GraphNode<T>, GraphNode<T>> getMatchings() {
		return Collections.unmodifiableMap(matching);
	}

	private boolean isFree(GraphNode<T> u) {
		return !matching.containsKey(u);
	}

	public double findMaxMatching() {
		initilize();
		while (!isEqualityGraphPerfectMatching()) {
			GraphNode<T> u = getNewCandidate();
			while (true) {
				if (equalityGraph.getNeighborVertices(s).equals(t)) {
					updateLabeling();
					updateEqualtyGraph();
				} else {
					Set<GraphNode<T>> neighbors = equalityGraph.getNeighborVertices(s);
					neighbors.removeAll(t);
					if (!neighbors.isEmpty()) {
						GraphNode<T> v = neighbors.stream().findFirst().get();
						if (!isFree(v)) {
							// Set S and T changed
							t.add(v);
							s.add(matching.get(v));
						} else {
							// matching changed
							augmentPath(u, v);
							break;
						}
					}
				}
			}
		}
		double maxMetchingWeight = 0;
		for (GraphNode<T> u : g.getPart(0)) {
			maxMetchingWeight += equalityGraph.getEdge(u, matching.get(u)).weight;
		}
		return maxMetchingWeight;
	}

	private void augmentPath(GraphNode<T> u, GraphNode<T> v) {
		List<GraphNode<T>> neighbors = equalityGraph.getNeighborVertices(u);
		if (neighbors.contains(v)) {
			matching.put(u, v);
			matching.put(v, u);
		} else {
			for (GraphNode<T> vNeighbor : neighbors) {
				if (!isFree(vNeighbor)) {
					augmentPath(matching.get(vNeighbor), v);
					matching.put(u, vNeighbor);
					matching.put(v, vNeighbor);
				}
			}
		}
	}

	private void updateLabeling() {
		double slack = getMinSlack();
		for (GraphNode<T> u : s) {
			labeling.compute(u, (key, value) -> value - slack);
		}
		for (GraphNode<T> v : t) {
			labeling.compute(v, (key, value) -> value + slack);
		}
	}

	private void updateEqualtyGraph() {
		for (GraphNode<T> u : s) {
			for (GraphEdge<T> e : g.edges.get(u)) {
				GraphNode<T> v = e.target;
				if (!t.contains(e.target)) {
					if (equalityGraph.getEdge(u, v) == null
							&& labeling.get(u) + labeling.get(v) == g.getEdge(u, v).weight) {
						if (!equalityGraph.getPart(1).contains(v)) {
							equalityGraph.addVertex(v, 1);
						}
						equalityGraph.addEdge(g.getEdge(u, v)).addEdge(g.getEdge(v, u));
					}
				}
			}
		}
	}

	private double getMinSlack() {
		double minSlack = Double.MAX_VALUE;
		for (GraphNode<T> u : s) {
			for (GraphEdge<T> e : g.edges.get(u)) {
				GraphNode<T> v = e.target;
				if (!t.contains(e.target)) {
					double slack = labeling.get(u) + labeling.get(v) - g.getEdge(u, v).weight;
					minSlack = Math.min(minSlack, slack);
				}
			}
		}
		return minSlack;
	}

	private void initilize() {
		equalityGraph = new BipartiteGraph<T>();
		for (GraphNode<T> v : g.getPart(1)) {
			labeling.put(v, 0d);
		}
		for (GraphNode<T> u : g.getPart(0)) {
			double max = 0;
			GraphNode<T> target = null;
			for (GraphEdge<T> e : g.edges.get(u)) {
				if (max < e.weight) {
					max = Math.max(max, e.weight);
					target = e.target;
				}
			}
			if (target != null) {
				labeling.put(u, max);
				equalityGraph.addVertex(u, 0).addVertex(target, 1);
				// if (equaltyGraph.edges.get(target) == null) {
				equalityGraph.addEdge(g.getEdge(u, target)).addEdge(g.getEdge(target, u));
				// }
			}
		}
	}

	private GraphNode<T> getNewCandidate() {
		t.clear();
		GraphNode<T> u = g.getPart(0).stream().filter(n -> isFree(n)).findFirst().get();
		s.add(u);
		return u;
	}

	private boolean isEqualityGraphPerfectMatching() {
		for (GraphNode<T> n : g.getPart(0)) {
			if (!matching.containsKey(n)) {
				return false;
			}
		}
		return true;
	}

}
