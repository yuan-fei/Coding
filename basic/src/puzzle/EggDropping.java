package puzzle;

/**
 * https://www.geeksforgeeks.org/tag/egg-dropping/
 * 
 * minimum trial count with k eggs for egg-broken threshold of a n-floor
 * building
 */
public class EggDropping {

	public static void main(String[] args) {
		System.out.println(minTrialWithDp(100, 2));
		System.out.println(minTrialWithDp(36, 2));
	}

	/** O(nk^2) */
	static int minTrialWithDp(int n, int k) {
		int MAX = n + 5;
		// min trial count
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = MAX;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				dp[i][j] = MAX;
				for (int i1 = 1; i1 <= i; i1++) {
					int curCost = Math.max(dp[i - i1][j], dp[i1 - 1][j - 1]) + 1;
					dp[i][j] = Math.min(dp[i][j], curCost);
				}
			}
		}
		return dp[n][k];
	}

}
