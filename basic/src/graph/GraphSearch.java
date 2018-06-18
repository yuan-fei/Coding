package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import utils.Util;

public class GraphSearch<T> {
	public static void main(String[] args) {
		GraphNode<String> s = new GraphNode<String>("s");
		GraphNode<String> w = new GraphNode<String>("w");
		GraphNode<String> r = new GraphNode<String>("r");
		GraphNode<String> v = new GraphNode<String>("v");
		GraphNode<String> t = new GraphNode<String>("t");
		GraphNode<String> x = new GraphNode<String>("x");
		GraphNode<String> u = new GraphNode<String>("u");
		GraphNode<String> y = new GraphNode<String>("y");
		Graph<String> graph = new Graph<String>();
		graph.addVertex(s).addVertex(w).addVertex(r).addVertex(v).addVertex(t).addVertex(x).addVertex(u).addVertex(y);
		graph.addEdge(new GraphEdge<String>(s, w)).addEdge(new GraphEdge<String>(s, r))
				.addEdge(new GraphEdge<String>(r, v)).addEdge(new GraphEdge<String>(w, t))
				.addEdge(new GraphEdge<String>(w, x)).addEdge(new GraphEdge<String>(x, y))
				.addEdge(new GraphEdge<String>(x, u)).addEdge(new GraphEdge<String>(x, t))
				.addEdge(new GraphEdge<String>(t, u)).addEdge(new GraphEdge<String>(y, u));

		GraphSearch<String> gs = new GraphSearch<String>(graph);
		List<List<GraphNode<String>>> result = gs.bfs();
		gs.output(result);
		result = gs.dfs();
		gs.output(result);
		gs.outputTS(gs.topologicalSort());
	}

	private Graph<T> graph;

	public GraphSearch(Graph<T> g) {
		graph = g;
	}

	public void output(List<List<GraphNode<String>>> sccs) {
		for (List<GraphNode<String>> scc : sccs) {
			System.out.println(Util.toString(scc.stream().map(n -> {
				return "n:" + n.val + ",s:" + startTime.get(n) + ",e:" + endTime.get(n);
			}).collect(Collectors.toList())));
		}
	}

	public void outputTS(List<GraphNode<String>> scc) {
		System.out.println(Util.toString(scc.stream().map(n -> {
			return "n:" + n.val + ",s:" + startTime.get(n) + ",e:" + endTime.get(n);
		}).collect(Collectors.toList())));
	}

	int timer = 0;
	/** Visited state: 0-white, 1-gray, 2-black */
	Map<GraphNode<T>, Integer> visited = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, Integer> startTime = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, Integer> endTime = new HashMap<GraphNode<T>, Integer>();

	public List<List<GraphNode<T>>> dfs() {
		visited.clear();
		startTime.clear();
		endTime.clear();
		timer = 0;
		List<List<GraphNode<T>>> result = new ArrayList<List<GraphNode<T>>>();
		for (GraphNode<T> node : graph.vertices) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				List<GraphNode<T>> scc = new ArrayList<GraphNode<T>>();
				dfsHelper1(graph, node, scc);
				result.add(scc);
			}
		}

		return result;
	}

	private void dfsHelper1(Graph<T> g, GraphNode<T> r, List<GraphNode<T>> result) {
		result.add(r);
		visited.put(r, 1);
		startTime.put(r, timer++);
		for (GraphEdge<T> edge : g.edges.getOrDefault(r, Collections.<GraphEdge<T>>emptyList())) {
			visited.putIfAbsent(edge.target, 0);
			if (visited.get(edge.target) == 0) {
				dfsHelper1(g, edge.target, result);
			}
		}
		visited.put(r, 2);
		endTime.put(r, timer++);
	}

	public List<List<GraphNode<T>>> bfs() {
		timer = 0;
		visited.clear();
		startTime.clear();
		endTime.clear();
		List<List<GraphNode<T>>> result = new ArrayList<List<GraphNode<T>>>();
		for (GraphNode<T> node : graph.vertices) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				result.add(bfsHelper(graph, node));
			}
		}
		return result;
	}

	private List<GraphNode<T>> bfsHelper(Graph<T> g, GraphNode<T> r) {
		List<GraphNode<T>> result = new ArrayList<GraphNode<T>>();
		Queue<GraphNode<T>> queue = new LinkedList<GraphNode<T>>();
		visited.put(r, 0);
		startTime.put(r, timer++);
		queue.offer(r);
		while (!queue.isEmpty()) {
			GraphNode<T> n = queue.poll();
			visited.put(n, 2);
			endTime.put(n, timer++);
			result.add(n);
			for (GraphEdge<T> edge : g.edges.getOrDefault(n, Collections.<GraphEdge<T>>emptyList())) {
				visited.putIfAbsent(edge.target, 0);
				if (visited.get(edge.target) == 0) {
					visited.put(edge.target, 1);
					startTime.put(edge.target, timer++);
					queue.offer(edge.target);
				}
			}
		}
		return result;
	}

	public List<GraphNode<T>> topologicalSort() {
		visited.clear();
		startTime.clear();
		endTime.clear();
		timer = 0;
		List<GraphNode<T>> scc = new ArrayList<GraphNode<T>>();
		for (GraphNode<T> node : graph.vertices) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				dfsHelper2(graph, node, scc);
			}
		}
		Collections.reverse(scc);
		return scc;
	}

	private void dfsHelper2(Graph<T> g, GraphNode<T> r, List<GraphNode<T>> result) {
		visited.put(r, 1);
		startTime.put(r, timer++);
		for (GraphEdge<T> edge : g.edges.getOrDefault(r, Collections.<GraphEdge<T>>emptyList())) {
			visited.putIfAbsent(edge.target, 0);
			if (visited.get(edge.target) == 0) {
				dfsHelper2(g, edge.target, result);
			}
		}
		visited.put(r, 2);
		endTime.put(r, timer++);
		result.add(r);
	}
}
