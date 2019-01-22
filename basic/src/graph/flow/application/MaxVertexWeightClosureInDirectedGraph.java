package graph.flow.application;

import graph.flow.FordFulkersonMaxFlow;

public class MaxVertexWeightClosureInDirectedGraph {

	public static void main(String[] args) {
		double[] weight = new double[] { 5, -6, 7, 0, -3 };
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 0, 3 }, new int[] { 2, 3 }, new int[] { 1, 4 },
				new int[] { 3, 4 } };
		System.out.println(getMaxWeightClosure(weight, edges));
	}

	/* Closure: for each edge u->v, if u is in closure, then v must be in closure */
	public static double getMaxWeightClosure(double[] weight, int[][] edges) {
		FordFulkersonMaxFlow ff = new FordFulkersonMaxFlow();
		int s = weight.length;
		int t = weight.length + 1;
		double maxWeight = Double.MIN_VALUE;
		double totalPositive = 0;
		for (int i = 0; i < weight.length; i++) {
			if (weight[i] > 0) {
				ff.addEdges(s, i, weight[i]);
				totalPositive += weight[i];
			} else if (weight[i] < 0) {
				ff.addEdges(i, t, -weight[i]);
			}
			maxWeight = Math.max(maxWeight, Math.abs(weight[i]));
		}
		for (int i = 0; i < edges.length; i++) {
			ff.addEdges(edges[i][0], edges[i][1], maxWeight + 1);
		}
		// minimize included negative and excluded positive
		double minCut = ff.getMaxFlow(s, t);
		return totalPositive - minCut;
	}
}
