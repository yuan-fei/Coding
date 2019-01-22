package graph.flow.application;

import graph.flow.FordFulkersonMaxFlow;

public class MinWeightedVertexCoveringInBipartite {

	public static void main(String[] args) {
		double[] weight = new double[] { 5, 1, 2, 1, 3, 2 };
		int[][] edges = new int[][] { new int[] { 0, 3 }, new int[] { 0, 4 }, new int[] { 1, 3 }, new int[] { 1, 5 },
				new int[] { 2, 4 }, new int[] { 2, 3 } };
		System.out.println(getMinCovering(3, weight, edges));
	}

	public static double getMinCovering(int sizeX, double[] weight, int[][] edges) {
		FordFulkersonMaxFlow ff = new FordFulkersonMaxFlow();
		int s = weight.length;
		int t = weight.length + 1;
		double maxWeight = Double.MIN_VALUE;
		for (int i = 0; i < weight.length; i++) {
			if (i < sizeX) {
				ff.addEdges(s, i, weight[i]);
			} else {
				ff.addEdges(i, t, weight[i]);
			}
			maxWeight = Math.max(maxWeight, weight[i]);
		}
		for (int i = 0; i < edges.length; i++) {
			ff.addEdges(edges[i][0], edges[i][1], maxWeight + 1);
		}
		double minCut = ff.getMaxFlow(s, t);
		return minCut;

	}
}
