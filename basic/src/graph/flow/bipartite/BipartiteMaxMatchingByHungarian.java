package graph.flow.bipartite;

import java.util.Arrays;

/** O(VE) */
public class BipartiteMaxMatchingByHungarian {

	public static void main(String[] args) {
		boolean[][] connection = new boolean[][] { new boolean[] { true, true, false, false, false },
				new boolean[] { true, false, false, false, true }, new boolean[] { false, false, true, true, false },
				new boolean[] { true, false, false, false, true }, new boolean[] { false, true, false, true, false } };
		System.out.println(match(connection));
	}

	public static int match(boolean[][] connection) {
		int matchCount = 0;
		int[] matchx = new int[connection.length];
		int[] matchy = new int[connection[0].length];
		Arrays.fill(matchx, -1);
		Arrays.fill(matchy, -1);

		/* For each one, check it can find a match */
		for (int i = 0; i < connection.length; i++) {
			boolean[] seen = new boolean[connection[0].length];
			if (findMatch(i, connection, matchx, matchy, seen)) {
				matchCount++;
			}
		}
		return matchCount;
	}

	/* For x, find if others can adjust so that it can find a mate */
	private static boolean findMatch(int x, boolean[][] connection, int[] matchx, int[] matchy, boolean[] seen) {
		for (int y = 0; y < connection[x].length; y++) {
			if (!seen[y] && connection[x][y]) {
				seen[y] = true;
				if (matchy[y] == -1 || findMatch(matchy[y], connection, matchx, matchy, seen)) {
					matchx[x] = y;
					matchy[y] = x;
					return true;
				}
			}
		}
		return false;
	}

}
