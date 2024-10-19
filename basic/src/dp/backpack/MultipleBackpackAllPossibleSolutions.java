package dp.backpack;

/**
 * Given backpack size limit, and each item's size and amount, each item can be
 * packed amount of times, how many ways are there for a backpack to be fully
 * packed.
 * 
 * TC:
 * 
 * O(nV) for solveBySlidingWindow,
 * 
 * 
 * 
 */
public class MultipleBackpackAllPossibleSolutions {

	public static void main(String[] args) {
		System.out.println(solveBySlidingWindow(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 })); // 3
		System.out.println(solveBySlidingWindow(10, new int[] { 1 }, new int[] { 15 })); // 1
	}

	// https://leetcode.com/problems/count-of-sub-multisets-with-bounded-sum/description/
	public static int solveBySlidingWindow(int size_limit, int[] sizes, int[] amounts) {
		int[] dp = new int[size_limit + 1];
		dp[0] = 1;
		for (int i = 0; i < sizes.length; i++) {
			int size = sizes[i];
			int amount = amounts[i];
			if (size == 0) {
				for (int j = 0; j <= size_limit; j++) {
					dp[j] *= amount + 1;
				}
			} else {
				// c for congruence group
				for (int c = 0; c < size; c++) {
					int[] pSum = new int[Math.max(0, (size_limit - c + size - 1)) / size + 2];
					for (int j = 0; j * size + c <= size_limit; j++) {
						pSum[j + 1] = pSum[j];
						pSum[j + 1] += dp[j * size + c];
					}
					for (int j = 0; j * size + c <= size_limit; j++) {
						dp[j * size + c] = pSum[j + 1] - pSum[Math.max(0, j - amount)];
					}

				}
			}
		}
		return dp[size_limit];
	}

}
