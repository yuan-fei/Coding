package dp.backpack;
/**
 * Given backpack size limit, and size and value of each item, each item can be
 * packed multiple times, pack the items as valuable as possible
 */
public class CompleteBackpack {

	public static void main(String[] args) {
		System.out.println(solve(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(solveWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 5, 2, 4 })); // 15
	}

	public static int solve(int size_limit, int[] sizes, int[] values) {
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
				if (j < sizes[i - 1]) {
					state[i][j] = state[i - 1][j];
				} else {
					state[i][j] = Math.max(state[i - 1][j], state[i][j - sizes[i - 1]] + values[i - 1]);
				}
			}
		}
		return state[sizes.length][size_limit];
	}

	public static int solveWithSpaceEfficiency(int size_limit, int[] sizes, int[] values) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[] state = new int[size_limit + 1];

		for (int i = 1; i <= sizes.length; i++) {
			for (int j = sizes[i - 1]; j <= size_limit; j++) {
				state[j] = Math.max(state[j], state[j - sizes[i - 1]] + values[i - 1]);
			}
		}
		return state[size_limit];
	}
}
