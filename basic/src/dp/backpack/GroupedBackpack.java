package dp.backpack;

/**
 * Given backpack size limit, and size and value of each item, groups of items,
 * pack the items as valuable as possible
 * 
 * Note: at most 1 item can be packed for each group
 */
public class GroupedBackpack {

	public static void main(String[] args) {
		System.out.println(solve(10, new int[][] { { 2, 3 }, { 5, 7 } }, new int[][] { { 1, 5 }, { 2, 4 } })); // 9
		System.out.println(solve(10, new int[][] { { 2, 5, 3 }, { 7 } }, new int[][] { { 1, 5, 2 }, { 4 } })); // 6
	}

	public static int solve(int size_limit, int[][] sizes, int[][] values) {
		int[] state = new int[size_limit + 1];
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = size_limit; j >= 1; j--) {
				for (int k = 0; k < sizes[i - 1].length; k++) {
					if (j >= sizes[i - 1][k]) {
						state[j] = Math.max(state[j], state[j - sizes[i - 1][k]] + values[i - 1][k]);
					}
				}
			}
		}
		return state[size_limit];
	}
}
