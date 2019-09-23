package dp.backpack;

/**
 * Given backpack size limit, and size and value of each item, pack the items as
 * valuable as possible
 */
public class ZeroOneBackpack {

	public static void main(String[] args) {
		System.out.println(solveBySize(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(solveWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(solveByValue(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 5, 2, 4 })); // 9
	}

	public static int solveWithSpaceEfficiency(int size_limit, int[] sizes, int[] values) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[] state = new int[size_limit + 1];

		for (int i = 1; i <= sizes.length; i++) {
			for (int j = size_limit; j >= sizes[i - 1]; j--) {
				state[j] = Math.max(state[j], state[j - sizes[i - 1]] + values[i - 1]);
			}
		}
		return state[size_limit];
	}

	/**
	 * Size limit is not large
	 */
	public static int solveBySize(int size_limit, int[] sizes, int[] values) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[][] state = new int[sizes.length + 1][size_limit + 1];
		for (int i = 0; i <= sizes.length; i++) {
			state[i][0] = 0;
		}
		for (int i = 0; i <= size_limit; i++) {
			state[0][i] = 0;
		}
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				state[i][j] = state[i - 1][j];
				if (sizes[i - 1] <= j) {
					state[i][j] = Math.max(state[i - 1][j - sizes[i - 1]] + values[i - 1], state[i][j]);
				}
			}
		}
		return state[sizes.length][size_limit];
	}

	/**
	 * When size limit is large, we can solve the problem which finding the
	 * items with smallest sizes that can make up a max value
	 */
	public static int solveByValue(int size_limit, int[] sizes, int[] values) {
		int N = sizes.length;
		if (N == 0) {
			return 0;
		}
		int maxValue = 0;
		for (int i = 0; i < N; i++) {
			maxValue = Math.max(maxValue, N * values[i]);
		}

		int[][] state = new int[N + 1][maxValue + 1];
		for (int i = 1; i <= maxValue; i++) {
			state[0][i] = size_limit + 1;
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= maxValue; j++) {
				state[i][j] = state[i - 1][j];
				if (values[i - 1] <= j) {
					state[i][j] = Math.min(state[i - 1][j - values[i - 1]] + sizes[i - 1], state[i][j]);
				}
			}
		}
		int res = 0;
		for (int i = 0; i <= maxValue; i++) {
			if (state[N][i] <= size_limit) {
				res = i;
			}
		}
		return res;
	}

}
