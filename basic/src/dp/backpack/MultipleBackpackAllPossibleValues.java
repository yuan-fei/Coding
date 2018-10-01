package dp.backpack;

import java.util.Arrays;

public class MultipleBackpackAllPossibleValues {

	public static void main(String[] args) {
		System.out.println(solveWithSpaceEfficiency(5, new int[] { 1, 4 }, new int[] { 2, 1 })); // 4
		System.out.println(solve(5, new int[] { 1, 4 }, new int[] { 2, 1 })); // 4
	}

	/** All possible values that can be populated */
	public static int solveWithSpaceEfficiency(int n, int[] values, int[] amount) {
		boolean[] state = new boolean[n + 1];
		state[0] = true;
		for (int i = 1; i <= values.length; i++) {
			for (int j = 1; j <= amount[i - 1]; j++) {
				for (int k = n; k >= 1; k--) {
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

	/** state[i][j]: the amount left for ith item to fullfill total value j */
	public static int solve(int n, int[] values, int[] amount) {
		int[] state = new int[n + 1];
		Arrays.fill(state, -1);
		state[0] = 0;
		for (int i = 1; i <= values.length; i++) {
			for (int k = 0; k <= n; k++) {
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
}
