package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/** Hierholzer's algorithm: O(E) */
public class EulerPath {

	public static void main(String[] args) {
		{
			EulerPath ep = new EulerPath();
			ep.addEdges(0, 1);
			ep.addEdges(1, 2);
			ep.addEdges(2, 3);
			ep.addEdges(3, 4);
			ep.addEdges(2, 4);
			System.out.println(ep.findPathRec());
		}
		{
			EulerPath ep = new EulerPath();
			ep.addEdges(0, 1);
			ep.addEdges(1, 2);
			ep.addEdges(2, 3);
			ep.addEdges(3, 4);
			ep.addEdges(2, 4);
			System.out.println(ep.findPathIter());
		}

	}

	Map<Integer, List<Edge>> edges = new HashMap<>();

	public void addEdges(int from, int to) {
		Edge e1 = new Edge(to);
		Edge e2 = new Edge(from);
		e1.reverse = e2;
		e2.reverse = e1;
		if (!edges.containsKey(from)) {
			edges.put(from, new ArrayList<>());
		}
		edges.get(from).add(e1);
		if (!edges.containsKey(to)) {
			edges.put(to, new ArrayList<>());
		}
		edges.get(to).add(e2);
	}

	public boolean hasPath() {
		if (isConnected()) {
			int oddEdges = 0;
			for (List<Edge> es : edges.values()) {
				if (es.size() % 2 == 1) {
					oddEdges++;
					if (oddEdges > 2) {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean isConnected() {
		boolean[] visited = new boolean[edges.size()];
		dfs(edges.keySet().iterator().next(), visited);
		for (int i = 0; i < visited.length; i++) {
			if (visited[i] == false) {
				return false;
			}
		}
		return true;
	}

	private void dfs(int u, boolean[] visited) {
		visited[u] = true;
		for (Edge e : edges.get(u)) {
			if (!visited[e.to]) {
				dfs(e.to, visited);
			}
		}
	}

	public List<Integer> findPathRec() {
		if (hasPath()) {
			for (int k : edges.keySet()) {
				if (edges.get(k).size() % 2 == 1) {
					List<Integer> path = new ArrayList<>();
					findPath(k, path);
					return path;
				}
			}
		}
		return Collections.emptyList();
	}

	public List<Integer> findPathIter() {
		if (hasPath()) {
			for (int k : edges.keySet()) {
				if (edges.get(k).size() % 2 == 1) {
					return findPath(k);
				}
			}
		}
		return Collections.emptyList();
	}

	public void findPath(int u, List<Integer> path) {
		while (!edges.get(u).isEmpty()) {
			Edge e = edges.get(u).remove(0);
			edges.get(e.to).remove(e.reverse);
			findPath(e.to, path);
		}
		path.add(u);
	}

	public List<Integer> findPath(int r) {
		List<Integer> path = new ArrayList<>();
		Stack<Integer> s = new Stack<>();
		s.push(r);
		while (!s.isEmpty()) {
			int u = s.peek();
			if (!edges.get(u).isEmpty()) {
				Edge e = edges.get(u).remove(0);
				edges.get(e.to).remove(e.reverse);
				s.push(e.to);
			} else {
				path.add(s.pop());
			}
		}
		return path;
	}

	class Edge {
		int to;
		Edge reverse;

		public Edge(int to) {
			this.to = to;
		}

		@Override
		public String toString() {
			return reverse.to + "->" + to;
		}
	}
}
