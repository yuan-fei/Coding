package graph.flow.bipartite;

import java.util.Arrays;

public class BipartiteMaxWeightMatchingByDFSHungarian {

	public static void main(String[] args) {
		double[][] weight = new double[][] { new double[] { 3, 5, 5, 4, 1 }, new double[] { 2, 2, 0, 2, 2 },
				new double[] { 2, 4, 4, 1, 0 }, new double[] { 0, 1, 1, 0, 0 }, new double[] { 1, 2, 1, 3, 3 }, };
		BipartiteMaxWeightMatchingByDFSHungarian dh = new BipartiteMaxWeightMatchingByDFSHungarian(weight);
		System.out.println(dh.maxMatch());

	}

	BipartiteMaxWeightMatchingByDFSHungarian(double[][] w) {
		weight = w;
	}

	double[][] weight;
	// label values of vertex x, y
	double[] labelx, labely;
	// matching between x and y
	int[] matchx, matchy;

	public double maxMatch() {
		int n = weight.length;
		labelx = new double[n];
		labely = new double[n];
		matchx = new int[n];
		matchy = new int[n];
		Arrays.fill(matchx, -1);
		Arrays.fill(matchy, -1);
		// init labelx value, labely values are initially 0
		initLabelx();
		for (int x = 0; x < n; x++) {
			while (true) {
				boolean[] seenx = new boolean[n];
				boolean[] seeny = new boolean[n];
				if (dfs(x, seenx, seeny)) {
					// found mate of x
					break;
				} else {
					// extend the edges of equality graph
					update(seenx, seeny);
				}
			}
		}
		double maxCost = 0;
		for (int x = 0; x < n; x++) {
			maxCost += weight[x][matchx[x]];
		}
		return maxCost;
	}

	// Label each x with the max weight of all matching ys with it
	private void initLabelx() {

		int n = weight.length;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				labelx[x] = Math.max(labelx[x], weight[x][y]);
			}
		}
	}

	private void update(boolean[] seenx, boolean[] seeny) {
		double d = Double.MAX_VALUE;
		int n = weight.length;
		for (int x = 0; x < n; x++) {
			if (seenx[x]) {
				for (int y = 0; y < n; y++) {
					if (!seeny[y]) {
						d = Math.min(d, labelx[x] + labely[y] - weight[x][y]);
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (seenx[i]) {
				labelx[i] -= d;
			}
			if (seeny[i]) {
				labely[i] += d;
			}
		}
	}

	// DFS hungarian to find x a matching mate
	private boolean dfs(int x, boolean[] seenx, boolean[] seeny) {
		int n = weight.length;
		seenx[x] = true;
		for (int y = 0; y < n; y++) {
			if (!seeny[y] && labelx[x] + labely[y] == weight[x][y]) {
				seeny[y] = true;
				if (matchy[y] == -1 || dfs(matchy[y], seenx, seeny)) {
					matchx[x] = y;
					matchy[y] = x;
					return true;
				}
			}
		}
		return false;
	}
}
