import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import basic.GraphNode;
import basic.UnionFindSet;
import utils.Util;

public class StronglyConnectedComponentForUndirectedGraph {
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
		b.children.add(c);
		d.children.add(a);
		b.children.add(d);
		e.children.add(f);
		f.children.add(g);
		List<GraphNode<String>> nodes = Arrays.asList(h, a, b, c, d, e, f, g);
		List<List<GraphNode<String>>> sccs = sscWithUFS(nodes);
		output(sccs);
		sccs = sscWithDFS(nodes);
		output(sccs);
		sccs = sscWithBFS(nodes);
		output(sccs);
	}

	private static void output(List<List<GraphNode<String>>> sccs) {
		for (List<GraphNode<String>> scc : sccs) {
			System.out.println(Util.toString(scc.stream().map(n -> {
				return n.val;
			}).collect(Collectors.toList())));
		}
	}

	public static <T> List<List<GraphNode<T>>> sscWithUFS(List<GraphNode<T>> nodes) {
		UnionFindSet<GraphNode<T>> ufs = new UnionFindSet<GraphNode<T>>();
		for (GraphNode<T> node : nodes) {
			ufs.makeSet(node);
		}
		for (GraphNode<T> node : nodes) {
			for (GraphNode<T> child : node.children) {
				ufs.union(node, child);
			}
		}
		return new ArrayList<List<GraphNode<T>>>(ufs.getAllDisjointSets().values());
	}

	public static <T> List<List<GraphNode<T>>> sscWithDFS(List<GraphNode<T>> nodes) {
		GraphSearch<T> gs = new GraphSearch<T>();
		return gs.dfs(nodes);
	}

	public static <T> List<List<GraphNode<T>>> sscWithBFS(List<GraphNode<T>> nodes) {
		GraphSearch<T> gs = new GraphSearch<T>();
		return gs.bfs(nodes);
	}
}
