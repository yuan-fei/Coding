package graph.flow.bipartite.application;

import graph.flow.bipartite.BipartiteMaxMatchingByHungarian;

public class MinPathCoveringInDAG {

	public static void main(String[] args) {
		int[][] edges = new int[][] { new int[] { 0, 1 }, new int[] { 1, 3 }, new int[] { 4, 2 }, new int[] { 1, 2 },
				new int[] { 0, 2 }, new int[] { 0, 3 } };
		System.out.println(getMinPaths(5, edges));
	}

	/** Min # of vertex-disjoint paths that can cover all vertices */
	public static int getMinPaths(int numV, int[][] edges) {
		BipartiteMaxMatchingByHungarian bmm = new BipartiteMaxMatchingByHungarian();
		boolean[][] connection = new boolean[numV][numV];
		for (int i = 0; i < edges.length; i++) {
			connection[edges[i][0]][edges[i][1]] = true;
		}
		return numV - bmm.match(connection);
	}
}
