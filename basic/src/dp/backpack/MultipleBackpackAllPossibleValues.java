package dp.backpack;

import java.util.Arrays;

/** All possible values that can be populated not more than V */
public class MultipleBackpackAllPossibleValues {

	public static void main(String[] args) {
		System.out.println(allValuesWithSpaceEfficiency(5, new int[] { 1, 4 }, new int[] { 2, 1 })); // 4
		System.out.println(allValuesFast(5, new int[] { 1, 4 }, new int[] { 2, 1 })); // 4
	}

	/** O(V*sum(amount_i)) */
	public static int allValuesWithSpaceEfficiency(int V, int[] values, int[] amount) {
		int m = values.length;
		boolean[] state = new boolean[V + 1];
		state[0] = true;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= amount[i - 1]; j++) {
				for (int k = V; k >= 1; k--) {
					if (k - values[i - 1] >= 0) {
						state[k] = state[k] || state[k - values[i - 1]];
					}
				}
			}
		}
		int res = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i]) {
				res++;
			}
		}
		return res;
	}

	/**
	 * O(V*m)
	 * 
	 * state[i][j]: the amount left for ith item to fullfill total value j
	 */
	public static int allValuesFast(int V, int[] values, int[] amount) {
		int[] state = new int[V + 1];
		Arrays.fill(state, -1);
		state[0] = 0;
		int m = values.length;
		for (int i = 1; i <= m; i++) {
			for (int k = 0; k <= V; k++) {
				if (state[k] >= 0) {
					state[k] = amount[i - 1];
				} else if (k < values[i - 1] || state[k - values[i - 1]] <= 0) {
					state[k] = -1;
				} else {
					state[k] = state[k - values[i - 1]] - 1;
				}
			}
		}
		int res = 0;
		for (int i = 0; i < state.length; i++) {
			if (state[i] >= 0) {
				res++;
			}
		}
		return res;
	}

	/**
	 * Given max counts of m distinct numbers, count all possible permutations
	 * of choosing n numbers.
	 * 
	 * TC: O(n*sum(maxC))
	 * 
	 * Note: C can be computed in O(1) if factorials are pre-calculated
	 */
	static int countPermutation(int n, int[] maxC) {
		int[] dp = new int[n + 1];
		dp[0] = 1;
		for (int i = 0; i < maxC.length; i++) {
			for (int j = n; j >= 1; j--) {
				for (int k = 1; k <= Math.min(maxC[i], j); k++) {
					dp[j] = dp[j] + dp[j - k] * C(j, k);
				}
			}
		}
		return dp[n];
	}

	public static int C(int n, int m) {
		int f1 = 1;
		int f2 = 1;
		for (int i = 1; i <= m; i++) {
			f1 = f1 * (n - i + 1);
			f2 = f2 * i;
		}
		return f1 / f2;
	}
}
