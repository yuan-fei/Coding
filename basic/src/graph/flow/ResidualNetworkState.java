package graph.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import utils.Util;

public class ResidualNetworkState<T> {
	protected Graph<T> g;
	protected GraphNode<T> source;
	protected GraphNode<T> target;
	// W_res(u,v)=W_g(u,v)-flow(u,v)
	protected Map<GraphNode<T>, Map<GraphNode<T>, Double>> flow = new HashMap<GraphNode<T>, Map<GraphNode<T>, Double>>();

	public ResidualNetworkState(Graph<T> g, GraphNode<T> s, GraphNode<T> t) {
		this.g = g;
		this.source = s;
		this.target = t;
		initilizeFlow();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Net flow: " + getNetworkFlow());
		List<GraphEdge<T>> flattendEdges = new ArrayList<GraphEdge<T>>();
		for (Entry<GraphNode<T>, Map<GraphNode<T>, Double>> sourceEntry : flow.entrySet()) {
			for (Entry<GraphNode<T>, Double> targetEntry : sourceEntry.getValue().entrySet()) {
				flattendEdges.add(new GraphEdge<T>(sourceEntry.getKey(), targetEntry.getKey(), targetEntry.getValue()));
			}
		}
		sb.append("\nFlow network: ");
		sb.append(Util.toString(flattendEdges));
		sb.append("\nResidual network: ");
		sb.append(getResidualNetwork());
		return sb.toString();
	}

	protected void initilizeFlow() {
		for (GraphEdge<T> e : g.getEdges()) {
			updateFlow(e.source, e.target, 0d);
		}
	}

	public Graph<T> getResidualNetwork() {
		Graph<T> residualNetwork = new Graph<T>();
		residualNetwork.vertices.addAll(g.vertices);
		for (GraphNode<T> u : g.vertices) {
			residualNetwork.edges.put(u, getResidualEdges(u));
		}
		return residualNetwork;
	}

	public List<GraphNode<T>> getResidualVertices() {
		return new ArrayList<GraphNode<T>>(g.vertices);
	}

	public List<GraphEdge<T>> getResidualEdges(GraphNode<T> u) {
		List<GraphEdge<T>> edges = new ArrayList<GraphEdge<T>>();
		for (GraphNode<T> v : g.vertices) {
			double rc = getResidualCapacity(u, v);
			if (rc > 0) {
				edges.add(new GraphEdge<T>(u, v, rc));
			}
		}
		return edges;

	}

	public void updateFlow(GraphNode<T> u, GraphNode<T> v, double f) {
		updateFlowOneWay(u, v, f);
		updateFlowOneWay(v, u, -f);
	}

	private void updateFlowOneWay(GraphNode<T> u, GraphNode<T> v, double f) {
		Map<GraphNode<T>, Double> map = flow.get(u);
		if (map == null) {
			map = new HashMap<GraphNode<T>, Double>();
			flow.put(u, map);
		}
		map.put(v, f);
	}

	public double getFlow(GraphNode<T> u, GraphNode<T> v) {
		Map<GraphNode<T>, Double> map = flow.get(u);
		if (map == null) {
			return 0;
		} else {
			return map.getOrDefault(v, 0d);
		}
	}

	public double getNetworkFlow() {
		return flow.get(source).values().stream().reduce(0d, Double::sum);
	}

	protected double getResidualCapacity(GraphEdge<T> originEdge) {
		return originEdge.weight - getFlow(originEdge.source, originEdge.target);
	}

	protected double getResidualCapacity(GraphNode<T> u, GraphNode<T> v) {
		GraphEdge<T> e = g.getEdge(u, v);
		double originalCapacity = (e != null) ? e.weight : 0;
		return originalCapacity - getFlow(u, v);
	}
}
