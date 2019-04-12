package dp;

/**
 * All permutations sum to k
 * 
 * Composition of 5
 * 
 * 5
 * 
 * 4 + 1
 * 
 * 3 + 2
 * 
 * 3 + 1 + 1
 * 
 * 2 + 3
 * 
 * 2 + 2 + 1
 * 
 * 2 + 1 + 2
 * 
 * 2 + 1 + 1 + 1
 * 
 * 1 + 4
 * 
 * 1 + 3 + 1
 * 
 * 1 + 2 + 2
 * 
 * 1 + 2 + 1 + 1
 * 
 * 1 + 1 + 3
 * 
 * 1 + 1 + 2 + 1
 * 
 * 1 + 1 + 1 + 2
 * 
 * 1 + 1 + 1 + 1 + 1.
 * 
 * Dynamic Programming
 * 
 * see math.IntegerComposition for inclusion-exclusion principle solution
 */
public class IntegerComposition {

	public static void main(String[] args) {
		System.out.println(nonNegativeIncUpperboundComposition(3, 2, 1)); // 3
		System.out.println(nonNegativeIncUpperboundComposition(3, 4, 2)); // 6
	}

	/**
	 * a1+...an=k, 0<=ai<=ub
	 */
	public static long nonNegativeIncUpperboundComposition(int n, int k, int ub) {
		long[][] dp = new long[n + 1][k + 2];
		long[][] pSum = new long[n + 1][k + 2];
		dp[0][1] = 1;
		for (int i = 1; i <= k + 1; i++) {
			pSum[0][i] = 1;
		}
		for (int k1 = 0; k1 <= k; k1++) {
			for (int i = 1; i <= n; i++) {
				dp[i][k1 + 1] = pSum[i - 1][k1 + 1] - pSum[i - 1][Math.max(k1 - ub, 0)];
				pSum[i][k1 + 1] = pSum[i][k1] + dp[i][k1 + 1];
			}
		}
		return dp[n][k + 1];
	}

}
