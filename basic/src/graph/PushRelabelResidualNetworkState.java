package graph;

import java.util.HashMap;
import java.util.Map;

public class PushRelabelResidualNetworkState<T> extends ResidualNetworkState<T> {

	Map<GraphNode<T>, Integer> height = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, Double> preflow = new HashMap<GraphNode<T>, Double>();

	public PushRelabelResidualNetworkState(Graph<T> g, GraphNode<T> s, GraphNode<T> t) {
		super(g, s, t);
		initializeHeight();
		initializePreflow();
	}

	private void initializeHeight() {
		for (GraphNode<T> n : g.vertices) {
			height.put(n, 0);
		}
		height.put(source, g.vertices.size());
	}

	private void initializePreflow() {
		for (GraphNode<T> n : g.vertices) {
			preflow.put(n, 0d);
		}
		for (GraphEdge<T> e : g.edges.get(source)) {
			updateFlow(source, e.target, e.weight);
			movePreflow(source, e.target, e.weight);
		}
	}

	private void movePreflow(GraphNode<T> u, GraphNode<T> v, double deltaFlow) {
		preflow.put(u, preflow.get(u) - deltaFlow);
		preflow.put(v, preflow.get(v) + deltaFlow);
	}

	private boolean isOverflowing(GraphNode<T> u) {
		return preflow.get(u) > 0;
	}

	private boolean IsPushApplicable(GraphNode<T> u, GraphNode<T> v) {
		double residualCapacity = getResidualCapacity(u, v);
		int heightDiff = height.get(u) - height.get(v);
		return isOverflowing(u) && residualCapacity > 0 && (heightDiff == 1);
	}

	private boolean IsRelabelApplicable(GraphNode<T> u) {
		if (!isOverflowing(u)) {
			return false;
		}
		for (GraphEdge<T> e : getResidualNetworkEdges(u)) {
			if (height.get(u) > height.get(e.target))
				;
			{
				return false;
			}
		}
		return true;
	}

	private int getLowestAdjacentHeight(GraphNode<T> u) {
		if (!isOverflowing(u)) {
			return -1;
		}
		int lowestHeight = 0;
		for (GraphEdge<T> e : getResidualNetworkEdges(u)) {
			if (height.get(u) <= height.get(e.target)) {
				lowestHeight = Math.min(lowestHeight, height.get(e.target));
			} else {
				return -1;
			}
		}
		return lowestHeight;
	}

	public void push(GraphNode<T> u, GraphNode<T> v) {
		double rc = getResidualCapacity(u, v);
		double f = Math.min(preflow.get(u), rc);
		updateFlow(u, v, rc + f);
		movePreflow(u, v, f);
	}

	public void relabel(GraphNode<T> u) {
		int newHeight = getLowestAdjacentHeight(u);
		if (newHeight >= 0) {
			height.put(u, newHeight + 1);
		}
	}
}
