package graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSAT {

	public static void main(String[] args) {
		// (a∨¬b)∧(¬a∨b)∧(¬a∨¬b)∧(a∨¬c)
		int[][] expression = new int[][] { new int[] { 1, -2 }, new int[] { -1, 2 }, new int[] { -1, -2 },
				new int[] { 1, -3 } };
		System.out.println(solve2SAT(3, expression)); // a=0,b=0,c=0
		expression = new int[][] { new int[] { 1, 2 }, new int[] { -2, 3 }, new int[] { -1, -2 }, new int[] { 3, 4 },
				new int[] { -3, -5 }, new int[] { -3, 4 }, new int[] { -4, -5 } };
		System.out.println(solve2SAT(5, expression)); // a=1,b=0,c=1,d=1,e=0
	}

	public static Map<Integer, Boolean> solve2SAT(int n, int[][] expression) {
		Graph<Integer> g = new Graph<>();
		Map<Integer, GraphNode<Integer>> vertices = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			vertices.put(i, new GraphNode<Integer>(i));
			vertices.put(-i, new GraphNode<Integer>(-i));
			g.addVertex(vertices.get(i));
			g.addVertex(vertices.get(-i));
		}
		for (int[] exp : expression) {
			g.addEdge(new GraphEdge<Integer>(vertices.get(-exp[0]), vertices.get(exp[1])));
			g.addEdge(new GraphEdge<Integer>(vertices.get(-exp[1]), vertices.get(exp[0])));
		}
		List<List<GraphNode<Integer>>> scc = StronglyConnectedComponentForDirectedGraph.ssc(g);
		Map<Integer, Integer> comp = new HashMap<>();
		for (int i = 0; i < scc.size(); i++) {
			for (GraphNode<Integer> v : scc.get(i)) {
				comp.put(v.val, i);
			}
		}
		Map<Integer, Boolean> assignment = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			// A=>¬A & ¬A=>A
			if (comp.get(i) == comp.get(-i)) {
				return null;
			} else {
				// in case of A=>¬A or ¬A=>A, we always assign right hand side value to true
				assignment.put(i, (comp.get(i) > comp.get(-i)));
			}
		}
		return assignment;
	}
}
