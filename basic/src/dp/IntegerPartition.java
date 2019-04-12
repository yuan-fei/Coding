package dp;

/**
 * All combinations sum to k
 * 
 * For example, partition of 4:
 * 
 * 4
 * 
 * 3 + 1
 * 
 * 2 + 2
 * 
 * 2 + 1 + 1
 * 
 * 1 + 1 + 1 + 1
 */
public class IntegerPartition {

	public static void main(String[] args) {
		System.out.println(positivePartition(5)); // 7
		System.out.println(positiveIncUpperboundPartition(3, 2)); // 2
		System.out.println(positiveIncUpperboundPartition(5, 3, 3)); // 2
	}

	/**
	 * a1+...=k, 0<ai
	 * 
	 * all combination of positive numbers sum to k
	 */
	public static long positivePartition(int k) {
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int j = 1; j <= k; j++) {
			for (int i = j; i <= k; i++) {
				dp[i] += dp[i - j];
			}
		}
		return dp[k];
	}

	/**
	 * a1+...=k, 0<ai<=ub
	 * 
	 * all combination of numbers sum to k, each number should be in bound (0, ub]
	 */
	public static long positiveIncUpperboundPartition(int k, int ub) {
		int[] dp = new int[k + 1];
		dp[0] = 1;
		for (int j = 1; j <= ub; j++) {
			for (int i = 0; i <= k; i++) {
				if (i + j <= k) {
					dp[i + j] += dp[i];
				}
			}
		}
		return dp[k];
	}

	/**
	 * a1+...an=k, 0<ai<=ub
	 * 
	 * all combination of n numbers sum to k, each number should be in bound (0, ub]
	 */
	public static long positiveIncUpperboundPartition(int k, int n, int ub) {
		int[][] dp = new int[n + 1][k + 1];
		dp[0][0] = 1;

		for (int j = 1; j <= ub; j++) {
			for (int l = 1; l <= n; l++) {
				for (int i = 0; i <= k; i++) {
					if (i >= j) {
						dp[l][i] += dp[l - 1][i - j];
					}
				}
			}
		}
		return dp[n][k];
	}

}
