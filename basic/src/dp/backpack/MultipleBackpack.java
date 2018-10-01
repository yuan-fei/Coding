package dp.backpack;

/**
 * Given backpack size limit, and each item's size, amount and value, each item
 * can be packed amount of times, pack the items as valuable as possible
 */
public class MultipleBackpack {

	public static void main(String[] args) {
		System.out.println(solve(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(solve(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(solveWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 },
				new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(solveWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 },
				new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(
				solveByCompleteBP(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(
				solveByCompleteBP(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(solveByCompleteBPWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 },
				new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(solveByCompleteBPWithSpaceEfficiency(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 },
				new int[] { 1, 5, 2, 4 })); // 9

	}

	public static int solveByCompleteBP(int size_limit, int[] sizes, int[] amount, int[] value) {
		int[][] state = new int[sizes.length + 1][size_limit + 1];
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				state[i][j] = state[i - 1][j];
				for (int k = 1; k <= amount[i - 1]; k++) {
					if (j - k * sizes[i - 1] >= 0) {
						state[i][j] = Math.max(state[i][j], state[i - 1][j - k * sizes[i - 1]] + k * value[i - 1]);
					}
				}
			}
		}
		return state[sizes.length][size_limit];
	}

	public static int solveByCompleteBPWithSpaceEfficiency(int size_limit, int[] sizes, int[] amount, int[] value) {
		int[] state = new int[size_limit + 1];
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = size_limit; j >= 1; j--) {
				for (int k = 1; k <= amount[i - 1]; k++) {
					if (j - k * sizes[i - 1] >= 0) {
						state[j] = Math.max(state[j], state[j - k * sizes[i - 1]] + k * value[i - 1]);
					}
				}
			}
		}
		return state[size_limit];
	}

	public static int solve(int size_limit, int[] sizes, int[] amount, int[] value) {
		int total = 0;
		for (int i = 0; i < amount.length; i++) {
			total += amount[i];
		}
		int[][] state = new int[total + 1][size_limit + 1];
		int v = 0;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= amount[i - 1]; j++) {
				v++;
				for (int k = 1; k <= size_limit; k++) {
					state[v][k] = state[v - 1][k];
					if (k - sizes[i - 1] >= 0) {
						state[v][k] = Math.max(state[v][k], state[v - 1][k - sizes[i - 1]] + value[i - 1]);
					}
				}
			}
		}
		return state[total][size_limit];
	}

	public static int solveWithSpaceEfficiency(int size_limit, int[] sizes, int[] amount, int[] value) {
		int[] state = new int[size_limit + 1];
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= amount[i - 1]; j++) {
				for (int k = size_limit; k >= 1; k--) {
					if (k - sizes[i - 1] >= 0) {
						state[k] = Math.max(state[k], state[k - sizes[i - 1]] + value[i - 1]);
					}
				}
			}
		}
		return state[size_limit];
	}
}
