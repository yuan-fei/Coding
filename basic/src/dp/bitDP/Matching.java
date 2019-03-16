package dp.bitDP;

/**
 * https://atcoder.jp/contests/dp/tasks/dp_o
 * 
 * Problem Statement
 *
 * There are N men and N women, both numbered 1,2,…,N.
 *
 * For each i,j (1≤i,j≤N), the compatibility of Man i and Woman j is given as an
 * integer a[i,j]. If a[i,j]=1, Man i and Woman j are compatible; if ai,j=0,
 * they are not.
 *
 * Taro is trying to make N pairs, each consisting of a man and a woman who are
 * compatible. Here, each man and each woman must belong to exactly one
 * pair.Find the number of ways in which Taro can make N pairs, modulo 10^9+7.
 *
 * Constraints
 *
 * All values in input are integers.
 *
 * 1≤N≤21
 *
 * ai,j is 0 or 1.
 * 
 * bitDP
 */
public class Matching {
	public static void main(String[] args) {
		int[][] a = new int[][] { new int[] { 0, 1, 1 }, new int[] { 1, 0, 1 }, new int[] { 1, 1, 1 } };
		long r = solve(3, a); // 3
		System.out.println(r);
	}

	static long mod = 1000000007;

	private static long solve(int n, int[][] a) {
		long[] dp = new long[1 << n];
		dp[0] = 1;
		for (int mask = 0; mask < 1 << n; mask++) {
			int ones = Integer.bitCount(mask);
			for (int j = 0; j < n; j++) {
				if ((mask & (1 << j)) != 0 && a[ones - 1][j] == 1) {
					int subMask = mask ^ (1 << j);
					dp[mask] = add(dp[mask], dp[subMask]);
				}
			}
		}
		return dp[(1 << n) - 1];
	}

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
