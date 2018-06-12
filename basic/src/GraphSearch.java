import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import basic.GraphNode;
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
		s.children.add(w);
		s.children.add(r);
		r.children.add(v);
		w.children.add(t);
		w.children.add(x);
		x.children.add(y);
		x.children.add(u);
		x.children.add(t);
		t.children.add(u);
		y.children.add(u);
		List<GraphNode<String>> nodes = Arrays.asList(s, w, r, v, t, x, u, v);
		GraphSearch<String> gs = new GraphSearch<String>();
		List<List<GraphNode<String>>> result = gs.bfs(nodes);
		gs.output(result);
		result = gs.dfs(nodes);
		gs.output(result);
	}

	public void output(List<List<GraphNode<String>>> sccs) {
		for (List<GraphNode<String>> scc : sccs) {
			System.out.println(Util.toString(scc.stream().map(n -> {
				return "n:" + n.val + ",s:" + startTime.get(n) + ",e:" + endTime.get(n);
			}).collect(Collectors.toList())));
		}
	}

	int timer = 0;
	/** Visited state: 0-white, 1-gray, 2-black */
	Map<GraphNode<T>, Integer> visited = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, Integer> startTime = new HashMap<GraphNode<T>, Integer>();
	Map<GraphNode<T>, Integer> endTime = new HashMap<GraphNode<T>, Integer>();

	public List<List<GraphNode<T>>> dfs(List<GraphNode<T>> nodes) {
		visited.clear();
		startTime.clear();
		endTime.clear();
		timer = 0;
		List<List<GraphNode<T>>> result = new ArrayList<List<GraphNode<T>>>();
		for (GraphNode<T> node : nodes) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				List<GraphNode<T>> scc = new ArrayList<GraphNode<T>>();
				dfsHelper(node, visited, startTime, endTime, scc);
				result.add(scc);
			}
		}

		return result;
	}

	public void dfsHelper(GraphNode<T> g, Map<GraphNode<T>, Integer> visited, Map<GraphNode<T>, Integer> startTime,
			Map<GraphNode<T>, Integer> endTime, List<GraphNode<T>> result) {
		result.add(g);
		visited.put(g, 1);
		startTime.put(g, timer++);
		for (GraphNode<T> child : g.children) {
			visited.putIfAbsent(child, 0);
			if (visited.get(child) == 0) {
				dfsHelper(child, visited, startTime, endTime, result);
			}
		}
		visited.put(g, 2);
		endTime.put(g, timer++);
	}

	public List<List<GraphNode<T>>> bfs(List<GraphNode<T>> nodes) {
		timer = 0;
		visited.clear();
		startTime.clear();
		endTime.clear();
		List<List<GraphNode<T>>> result = new ArrayList<List<GraphNode<T>>>();
		for (GraphNode<T> node : nodes) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				result.add(bfs(node));
			}
		}
		return result;
	}

	public List<GraphNode<T>> bfs(GraphNode<T> g) {
		List<GraphNode<T>> result = new ArrayList<GraphNode<T>>();
		Queue<GraphNode<T>> queue = new LinkedList<GraphNode<T>>();
		visited.put(g, 0);
		startTime.put(g, timer++);
		queue.offer(g);
		while (!queue.isEmpty()) {
			GraphNode<T> n = queue.poll();
			visited.put(n, 2);
			endTime.put(n, timer++);
			result.add(n);
			for (GraphNode<T> child : n.children) {
				visited.putIfAbsent(child, 0);
				if (visited.get(child) == 0) {
					visited.put(child, 1);
					startTime.put(child, timer++);
					queue.offer(child);
				}
			}
		}
		return result;
	}

}
