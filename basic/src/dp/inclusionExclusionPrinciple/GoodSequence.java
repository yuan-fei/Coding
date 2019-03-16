package dp.inclusionExclusionPrinciple;
/**
 * Given 1, ..., n, a good sequence is for each k, there exists at least one
 * a[i] > k where i<=k, how many good sequence is there?
 */
public class GoodSequence {

	public static void main(String[] args) {
		System.out.println(solve(1));
		System.out.println(solve(2));
		System.out.println(solve(3));
		System.out.println(solve(4));
		System.out.println(solve(5));
		System.out.println(solve(6));
	}

	public static int solve(int n) {
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			dp[i] = fact(i);
			for (int j = 1; j < i; j++) {
				dp[i] -= dp[j] * fact(i - j);
			}
		}
		return dp[n];
	}

	private static int fact(int i) {
		if (i == 0) {
			return 1;
		} else {
			return i * fact(i - 1);
		}
	}
}
