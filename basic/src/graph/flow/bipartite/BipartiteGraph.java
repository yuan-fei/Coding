package graph.flow.bipartite;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Graph;
import graph.GraphNode;

public class BipartiteGraph<T> extends Graph<T> {
	List<Set<GraphNode<T>>> parts = Arrays.asList(new HashSet<GraphNode<T>>(), new HashSet<GraphNode<T>>());

	public BipartiteGraph<T> addVertex(GraphNode<T> u, int part) {
		addVertex(u);
		parts.get(part).add(u);
		return this;
	}

	public Set<GraphNode<T>> getPart(int part) {
		return Collections.unmodifiableSet(parts.get(part));
	}

}
