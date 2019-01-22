package graph.flow.application;

public class MaxWeightedVertexIndependentSetInBipartite {

	public static void main(String[] args) {
		double[] weight = new double[] { 5, 1, 2, 1, 3, 2 };
		int[][] edges = new int[][] { new int[] { 0, 3 }, new int[] { 0, 4 }, new int[] { 1, 3 }, new int[] { 1, 5 },
				new int[] { 2, 4 }, new int[] { 2, 3 } };
		System.out.println(getMaxWeightIndependentSet(3, weight, edges));

	}

	private static double getMaxWeightIndependentSet(int sizeX, double[] weight, int[][] edges) {
		int total = 0;
		for (int i = 0; i < weight.length; i++) {
			total += weight[i];
		}
		return total - MinWeightedVertexCoveringInBipartite.getMinCovering(sizeX, weight, edges);
	}

}
