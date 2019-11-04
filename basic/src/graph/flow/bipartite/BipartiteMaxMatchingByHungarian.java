package graph.flow.bipartite;

import java.util.Arrays;

/** O(VE) */
public class BipartiteMaxMatchingByHungarian {

	public static void main(String[] args) {
		boolean[][] connection = new boolean[][] { new boolean[] { true, true, false, false, false },
				new boolean[] { true, false, false, false, true }, new boolean[] { false, false, true, true, false },
				new boolean[] { true, false, false, false, true }, new boolean[] { false, true, false, true, false } };
		System.out.println(new BipartiteMaxMatchingByHungarian().match(connection));
	}

	int[] matchx;
	int[] matchy;
	int N;
	int M;
	boolean[][] connections;

	/** connection: M person * N jobs */
	public int match(boolean[][] connections) {
		int matchCount = 0;
		this.connections = connections;
		M = connections.length;
		N = connections[0].length;

		matchx = new int[M];
		matchy = new int[N];
		Arrays.fill(matchx, -1);
		Arrays.fill(matchy, -1);

		/* For each one, check it can find a match */
		for (int i = 0; i < M; i++) {
			boolean[] seen = new boolean[N];
			if (matchx[i] != -1 || findMatch(i, matchx, matchy, seen)) {
				matchCount++;
			}
		}
		return matchCount;
	}

	/* For x, find if others can adjust so that it can find a mate */
	private boolean findMatch(int x, int[] matchx, int[] matchy, boolean[] seen) {
		for (int y = 0; y < N; y++) {
			if (!seen[y] && connections[x][y]) {
				seen[y] = true;
				if (matchy[y] == -1 || findMatch(matchy[y], matchx, matchy, seen)) {
					matchx[x] = y;
					matchy[y] = x;
					return true;
				}
			}
		}
		return false;
	}

}
