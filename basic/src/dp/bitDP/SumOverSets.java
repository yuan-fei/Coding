package dp.bitDP;

import java.util.Arrays;

/**
 * sum over all subsets/supersets, O(n*2^n)
 * 
 * http://codeforces.com/blog/entry/45223
 */

public class SumOverSets {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(sumOverSubsets(3, new int[] { 1, 1, 1, 0, 0, 0, 1, 1 })));
		System.out.println(Arrays.toString(sumOverSupersets(3, new int[] { 1, 1, 1, 0, 0, 0, 1, 1 })));
	}

	/** s[11]=s[01]+s[00]+s[10]+s[11] */
	public static int[] sumOverSubsets(int n, int[] subsets) {
		int[] dp = new int[1 << n];
		for (int i = 0; i < subsets.length; i++) {
			dp[i] = subsets[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (1 << n); j++) {
				if ((j & (1 << i)) != 0) {
					dp[j] += dp[j ^ (1 << i)];
					// System.out.println(i + ":" + (j ^ (1 << i)) + "->" + j);
				}
			}
		}
		return dp;
	}

	/** s[01]=s[01]+s[11] */
	public static int[] sumOverSupersets(int n, int[] subsets) {
		int[] dp = new int[1 << n];
		for (int i = 0; i < subsets.length; i++) {
			dp[i] = subsets[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < (1 << n); j++) {
				if ((j & (1 << i)) != 0) {
					dp[j ^ (1 << i)] += dp[j];
				}
			}
		}
		return dp;
	}
}
