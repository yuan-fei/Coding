package dp.backpack;

public class CompleteBackpackAllPossibleSolutions {

	public static void main(String[] args) {
		System.out.println(permutation(12, new int[] { 2, 3, 5, 7 })); // 35
		System.out.println(permutation(6, new int[] { 2, 3, 5, 7 })); // 2
		System.out.println(permutation(7, new int[] { 2, 3, 6, 7 })); // 4
		System.out.println(permutationRecursive(12, new int[] { 2, 3, 5, 7 })); // 35
		System.out.println(permutationRecursive(6, new int[] { 2, 3, 5, 7 })); // 2
		System.out.println(permutationRecursive(7, new int[] { 2, 3, 6, 7 })); // 4
		System.out.println(combination(12, new int[] { 2, 3, 5, 7 })); // 7
		System.out.println(combination(6, new int[] { 2, 3, 5, 7 })); // 2
		System.out.println(combination(7, new int[] { 2, 3, 6, 7 })); // 2
		System.out.println(combinationWithSpaceEfficiency(12, new int[] { 2, 3, 5, 7 })); // 7
		System.out.println(combinationWithSpaceEfficiency(6, new int[] { 2, 3, 5, 7 })); // 2
		System.out.println(combinationWithSpaceEfficiency(7, new int[] { 2, 3, 6, 7 })); // 2
		System.out.println(combinationWithSpaceEfficiency(3, new int[] { 2, 1, 1, 1, 1, 1, 1, 1, 1, 1 })); // 174
	}

	/**
	 * Given backpack size limit, and size of each item, each item can be packed
	 * multiple times, how many ways are there for a backpack to be fully packed.
	 * Note that different sequences are different ways
	 */
	public static int permutation(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[] state = new int[size_limit + 1];
		state[0] = 1;
		for (int i = 1; i <= size_limit; i++) {
			for (int j = 1; j <= sizes.length; j++) {
				if (i >= sizes[j - 1]) {
					state[i] += state[i - sizes[j - 1]];
				}
			}
		}
		return state[size_limit];
	}

	public static int permutationRecursive(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		if (size_limit == 0) {
			return 1;
		}
		int cnt = 0;
		for (int i = 0; i < sizes.length; i++) {
			if (size_limit >= sizes[i]) {
				cnt += permutationRecursive(size_limit - sizes[i], sizes);
			}
		}
		return cnt;
	}

	/**
	 * Given backpack size limit, and size of each item, each item can be packed
	 * multiple times, how many ways are there for a backpack to be fully packed.
	 * 
	 * Note that
	 * 
	 * 1. no duplicates
	 * 
	 * 2. different sequences are counted as same solution
	 */
	public static int combinationWithSpaceEfficiency(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int[] state = new int[size_limit + 1];
		state[0] = 1;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				if (j >= sizes[i - 1]) {
					state[j] += state[j - sizes[i - 1]];
				}
			}
		}
		return state[size_limit];
	}

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
					state[i][j] = state[i - 1][j] + state[i][j - sizes[i - 1]];
				}
			}
		}
		return state[sizes.length][size_limit];
	}

}
