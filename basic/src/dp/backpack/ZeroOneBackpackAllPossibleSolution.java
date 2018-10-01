package dp.backpack;

public class ZeroOneBackpackAllPossibleSolution {

	public static void main(String[] args) {
		System.out.println(combination(12, new int[] { 2, 3, 5, 7 })); // 2
		System.out.println(combinationWithSpaceEfficiency(12, new int[] { 2, 3, 5, 7 })); // 2
	}

	/**
	 * Given backpack size limit, and size of each item, how many ways are there for
	 * a backpack to be fully packed
	 */
	public static int combination(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[][] state = new int[sizes.length + 1][size_limit + 1];
		for (int i = 1; i <= sizes.length; i++) {
			state[i][0] = 1;
		}
		for (int i = 1; i <= size_limit; i++) {
			state[0][i] = 0;
		}
		state[0][0] = 1;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				if (j < sizes[i - 1]) {
					state[i][j] = state[i - 1][j];
				} else {
					state[i][j] = state[i - 1][j] + state[i - 1][j - sizes[i - 1]];
				}
			}
		}
		return state[sizes.length][size_limit];
	}

	public static int combinationWithSpaceEfficiency(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[] state = new int[size_limit + 1];
		state[0] = 1;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = size_limit; j >= sizes[i - 1]; j--) {
				state[j] += state[j - sizes[i - 1]];
			}
		}
		return state[size_limit];
	}

}
