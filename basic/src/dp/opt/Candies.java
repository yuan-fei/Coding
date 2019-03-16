package dp.opt;

/**
 * https://atcoder.jp/contests/dp/tasks/dp_m
 * 
 * Problem Statement There are N children, numbered 1,2,…,N.
 * 
 * They have decided to share K candies among themselves. Here, for each i
 * (1≤i≤N), Child i must receive between 0 and ai candies (inclusive). Also, no
 * candies should be left over.
 * 
 * Find the number of ways for them to share candies, modulo 10^9+7. Here, two
 * ways are said to be different when there exists a child who receives a
 * different number of candies.
 * 
 * https://codeforces.com/blog/entry/64250
 * 
 * Prefix sum optimization
 */
public class Candies {
	public static void main(String[] args) {
		int k = 100000;
		int[] a = new int[] { 100000, 100000, 100000, 100000 };
		long r = solve(a.length, k, a); // 665683269
		System.out.println(r);
	}

	private static long solve(int n, int k, int[] a) {
		long[][] dp = new long[n + 1][k + 1];
		long[][] prefixSum = new long[n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
			prefixSum[i][0] = 1;
		}
		for (int i = 0; i <= k; i++) {
			prefixSum[0][i] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				dp[i][j] = prefixSum[i - 1][j];
				if (j >= a[i - 1] + 1) {
					dp[i][j] = add(dp[i][j], -prefixSum[i - 1][j - a[i - 1] - 1]);
				}
				prefixSum[i][j] = add(prefixSum[i][j - 1], dp[i][j]);
			}
		}
		return dp[n][k];
	}

	static long mod = 1000000007;

	static long add(long a, long b) {
		long r = a + b;
		if (r < 0) {
			r += mod;
		}
		return r % mod;
	}

	static long mul(long a, long b) {
		return (a * b) % mod;
	}
}
