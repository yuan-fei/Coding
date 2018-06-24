package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPairShortestPathResult {
	public boolean hasNegativeCircle;
	public double[][] distance;
	public int[][] predecessor;

	public AllPairShortestPathResult(int n) {
		distance = new double[n][n];
		predecessor = new int[n][n];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("hasNegativeCircle: " + hasNegativeCircle);
		sb.append("\n");
		sb.append("distance: ");
		sb.append(Arrays.deepToString(distance));
		sb.append("\n");
		sb.append("predecessor: ");
		sb.append(Arrays.deepToString(predecessor));
		sb.append("\n");
		sb.append("all paths:\n");
		sb.append(getAllPathsString());
		return sb.toString();
	}

	public List<Integer> getPath(int i, int j) {
		List<Integer> result = new ArrayList<Integer>();
		getPath(i, j, result);
		return result;
	}

	private void getPath(int i, int j, List<Integer> result) {
		if (i != j) {
			if (predecessor[i][j] == -1) {
				return;
			}
			getPath(i, predecessor[i][j], result);
		}
		result.add(j);
	}

	public String getAllPathsString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance.length; j++) {
				sb.append(i + "," + j + ": " + getPath(i, j) + "\n");
			}
		}
		return sb.toString();
	}

	public AllPairShortestPathResult asTransitiveClosure() {
		AllPairShortestPathResult r = new AllPairShortestPathResult(distance.length);
		r.hasNegativeCircle = hasNegativeCircle;
		r.predecessor = predecessor;
		double[][] transitiveClosure = new double[r.distance.length][r.distance.length];
		for (int i = 0; i < distance.length; i++) {
			for (int j = 0; j < distance.length; j++) {
				transitiveClosure[i][j] = Double.isFinite(distance[i][j]) ? 1 : 0;
			}
		}
		r.distance = transitiveClosure;
		return r;
	}
}
