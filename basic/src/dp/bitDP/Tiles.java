package dp.bitDP;

/** how may ways to fill a n*m board with 1*2 and 2*1 tiles only */
public class Tiles {

	public static void main(String[] args) {
		boolean[][] color = new boolean[][] { new boolean[] { false, false, false },
				new boolean[] { false, true, false }, new boolean[] { false, false, false } };
		System.out.println(solve(3, 3, color));
	}

	/**
	 * if color[i][j] == 1, then there is a bad point which can not place any
	 * tile
	 */
	static int solve(int n, int m, boolean[][] color) {
		// The 'used' bitmask is state of 'rolling' m columns. if we start at
		// (i,j), then the bitmask represents (i+1, 0), (i+1, 1), ..., (i,j),
		// (i,j+1), ... (i, m)
		int[] dp = new int[1 << m];
		dp[0] = 1;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				int[] newDp = new int[1 << m];
				for (int used = 0; used < (1 << m); used++) {
					if (((used >> j) & 1) != 0 || color[i][j]) {
						newDp[used] = dp[used & ~(1 << j)];
					} else {
						// horizontal
						if (j + 1 < m && (used >> (j + 1) & 1) == 0 && !color[i][j + 1]) {
							newDp[used] += dp[used | (1 << (j + 1))];
						}
						// vertical
						if (i + 1 < n && !color[i + 1][j]) {
							newDp[used] += dp[used | (1 << j)];
						}
					}
				}
				dp = newDp;
			}
		}
		return dp[0];
	}

}
