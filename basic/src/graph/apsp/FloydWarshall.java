package graph.apsp;

import java.util.Arrays;

import graph.AllPairShortestPathResult;
import graph.Graph;
import graph.GraphEdge;
import graph.GraphNode;
import utils.Util;

/** Time complexity: O(V^3). It requires only O(n^2) space in place */
public class FloydWarshall {

	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		GraphNode<String> v1 = new GraphNode<String>("1");
		GraphNode<String> v2 = new GraphNode<String>("2");
		GraphNode<String> v3 = new GraphNode<String>("3");
		GraphNode<String> v4 = new GraphNode<String>("4");
		GraphNode<String> v5 = new GraphNode<String>("5");
		g.addVertex(v1).addVertex(v2).addVertex(v3).addVertex(v4).addVertex(v5);
		GraphEdge<String> e1 = new GraphEdge<String>(v3, v2, 4);
		GraphEdge<String> e2 = new GraphEdge<String>(v3, v2, 0);
		g.addEdge(new GraphEdge<String>(v1, v2, 3)).addEdge(new GraphEdge<String>(v1, v5, -4))
				.addEdge(new GraphEdge<String>(v1, v3, 8)).addEdge(new GraphEdge<String>(v2, v5, 7))
				.addEdge(new GraphEdge<String>(v2, v4, 1)).addEdge(e1).addEdge(new GraphEdge<String>(v4, v1, 2))
				.addEdge(new GraphEdge<String>(v4, v3, -5)).addEdge(new GraphEdge<String>(v5, v4, 6));
		double[][] weights = g.getAdjacentMatrix();
		int n = weights.length;
		System.out.println(Arrays.deepToString(weights));
		System.out.println(getShortestPaths(n, weights));

		System.out.println();
		g.removeEdge(e1).addEdge(e2);
		weights = g.getAdjacentMatrix();
		n = weights.length;
		System.out.println(Arrays.deepToString(weights));
		System.out.println(getShortestPaths(n, weights));

	}

	static final int MAX = Integer.MAX_VALUE;

	public static AllPairShortestPathResult getShortestPaths(int n, double[][] weights) {
		AllPairShortestPathResult r = new AllPairShortestPathResult(n);
		Util.copy(weights, r.distance);
		initializePredecessor(n, weights, r.predecessor);
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (r.distance[i][k] < MAX && r.distance[k][j] < MAX) {
						if (r.distance[i][j] > r.distance[i][k] + r.distance[k][j]) {
							r.distance[i][j] = r.distance[i][k] + r.distance[k][j];
							r.predecessor[i][j] = r.predecessor[k][j];
						}
						if (i == j && r.distance[i][j] < 0) {
							r.hasNegativeCircle = true;
							return r;
						}
					}
				}
			}
		}

		return r;
	}

	private static void initializePredecessor(int n, double[][] weights, int[][] predecessor) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < n; k++) {
				if (j == k && weights[j][k] == 0) {
					predecessor[j][k] = -1;
				} else {
					predecessor[j][k] = (Double.isFinite(weights[j][k])) ? j : -1;
				}
			}
		}
	}

}
