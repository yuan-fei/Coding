package graph.flow;

import java.util.List;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;

public class AdmissibleNetworkState<T> extends PushRelabelResidualNetworkState<T> {

	public AdmissibleNetworkState(Graph<T> g, GraphNode<T> s, GraphNode<T> t) {
		super(g, s, t);
	}

	public boolean isAdmissibleEdge(GraphNode<T> u, GraphNode<T> v) {
		return getResidualCapacity(u, v) > 0 && (height.get(u) == height.get(v) + 1);
	}

	public boolean isPushApplicable(GraphNode<T> u, GraphNode<T> v) {
		return isOverflowing(u) && isAdmissibleEdge(u, v);
	}

	public boolean isRelabelApplicable(GraphNode<T> u) {
		if (!isOverflowing(u)) {
			return false;
		}
		for (GraphEdge<T> e : getResidualEdges(u)) {
			if (isAdmissibleEdge(u, e.target)) {
				return false;
			}
		}
		return true;
	}

	public void discharge(GraphNode<T> u) {
		List<GraphNode<T>> neighbors = g.getNeighborVertices(u);
		int i = 0;
		while (isOverflowing(u)) {
			if (i == neighbors.size()) {
				relabel(u);
				i = 0;
			} else if (isPushApplicable(u, neighbors.get(i))) {
				push(u, neighbors.get(i));
			} else {
				i++;
			}
		}
	}
}
