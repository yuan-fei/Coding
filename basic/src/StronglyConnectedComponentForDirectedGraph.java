import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import basic.GraphNode;
import utils.Util;

/**
 * Strongly Connected Component for directed graph
 */
public class StronglyConnectedComponentForDirectedGraph {
	public static void main(String[] args) {
		GraphNode<String> a = new GraphNode<String>("a");
		GraphNode<String> b = new GraphNode<String>("b");
		GraphNode<String> c = new GraphNode<String>("c");
		GraphNode<String> d = new GraphNode<String>("d");
		GraphNode<String> e = new GraphNode<String>("e");
		GraphNode<String> f = new GraphNode<String>("f");
		GraphNode<String> g = new GraphNode<String>("g");
		GraphNode<String> h = new GraphNode<String>("h");
		a.children.add(b);
		b.children.add(e);
		e.children.add(a);
		b.children.add(f);
		e.children.add(f);
		b.children.add(c);
		f.children.add(g);
		g.children.add(f);
		c.children.add(d);
		d.children.add(c);
		c.children.add(g);
		d.children.add(h);
		g.children.add(h);
		List<GraphNode<String>> nodes = Arrays.asList(h, a, b, c, d, e, f, g);
		List<List<GraphNode<String>>> sccs = sscWithDFS(nodes);
		output(sccs);
	}

	private static void output(List<List<GraphNode<String>>> sccs) {
		for (List<GraphNode<String>> scc : sccs) {
			System.out.println(Util.toString(scc.stream().map(n -> {
				return n.val;
			}).collect(Collectors.toList())));
		}
	}

	public static <T> List<List<GraphNode<T>>> sscWithDFS(List<GraphNode<T>> nodes) {
		Map<GraphNode<T>, Integer> visited = new HashMap<GraphNode<T>, Integer>();
		List<GraphNode<T>> nodesByFinishTime = new ArrayList<GraphNode<T>>();
		for (GraphNode<T> node : nodes) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				dfs1(node, visited, nodesByFinishTime);
			}
		}

		Collections.reverse(nodesByFinishTime);
		List<GraphNode<T>> transposedNodes = transposeGraph(nodesByFinishTime);
		List<List<GraphNode<T>>> sccs = new ArrayList<List<GraphNode<T>>>();
		visited.clear();
		for (GraphNode<T> node : transposedNodes) {
			visited.putIfAbsent(node, 0);
			if (visited.get(node) == 0) {
				List<GraphNode<T>> scc = new ArrayList<GraphNode<T>>();
				dfs2(node, visited, scc);
				sccs.add(scc);
			}
		}
		return sccs;
	}

	/**
	 * DFS Output nodes in finish time ascending order
	 */
	private static <T> void dfs1(GraphNode<T> g, Map<GraphNode<T>, Integer> visited, List<GraphNode<T>> result) {
		visited.put(g, 1);
		for (GraphNode<T> child : g.children) {
			visited.putIfAbsent(child, 0);
			if (visited.get(child) == 0) {
				dfs1(child, visited, result);
			}
		}
		result.add(g);
		visited.put(g, 2);
	}

	private static <T> void dfs2(GraphNode<T> g, Map<GraphNode<T>, Integer> visited, List<GraphNode<T>> scc) {
		visited.put(g, 1);
		scc.add(g);
		for (GraphNode<T> child : g.children) {
			visited.putIfAbsent(child, 0);
			if (visited.get(child) == 0) {
				dfs2(child, visited, scc);
			}
		}
		visited.put(g, 2);
	}

	/**
	 * nodes in list must be in finish time descending order
	 */
	private static <T> List<GraphNode<T>> transposeGraph(List<GraphNode<T>> nodes) {
		Map<GraphNode<T>, GraphNode<T>> map = new HashMap<GraphNode<T>, GraphNode<T>>();
		List<GraphNode<T>> result = new ArrayList<GraphNode<T>>();
		for (int i = 0; i < nodes.size(); i++) {
			GraphNode<T> n = new GraphNode<T>(nodes.get(i).val);
			map.put(nodes.get(i), n);
			result.add(n);
		}
		for (int i = 0; i < nodes.size(); i++) {
			for (GraphNode<T> child : nodes.get(i).children) {
				// This guarantee the children is stored in finish time descending order
				map.get(child).children.add(map.get(nodes.get(i)));
			}
		}
		return result;
	}

}
