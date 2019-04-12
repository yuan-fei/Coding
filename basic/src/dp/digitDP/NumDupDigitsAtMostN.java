package dp.digitDP;

import java.util.Arrays;

public class NumDupDigitsAtMostN {

	public static void main(String[] args) {
		System.out.println(numDupDigitsAtMostN(1000)); // 262
	}

	static String s;
	static int[][][] dp;

	public static int numDupDigitsAtMostN(int N) {

		s = N + "";
		dp = new int[s.length()][2][1 << 10];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		return N + 1 - solve(0, false, 0); // including 0
	}

	static int solve(int i, boolean isLess, int mask) {
		if (i == s.length()) {
			return 1;
		}
		if (dp[i][isLess ? 1 : 0][mask] >= 0) {
			return dp[i][isLess ? 1 : 0][mask];
		}
		int x = s.charAt(i) - '0';
		int max = isLess ? 9 : x;
		int r = 0;
		for (int k = 0; k <= max; k++) {
			if ((mask & (1 << k)) == 0) {
				boolean isLessNext = isLess || k < max;
				int newMask = mask | (1 << k);
				if (k == 0 && mask == 0) {
					newMask = 0;
				}
				r += solve(i + 1, isLessNext, newMask);
			}
		}
		return dp[i][isLess ? 1 : 0][mask] = r;
	}
}