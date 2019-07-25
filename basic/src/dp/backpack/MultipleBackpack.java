package dp.backpack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given backpack size limit, and each item's size, amount and value, each item
 * can be packed amount of times, pack the items as valuable as possible
 * 
 * TC:
 * 
 * O(nV) for solveByCHT,
 * 
 * O(n*m*V) for others,
 * 
 * 
 */
public class MultipleBackpack {

	public static void main(String[] args) {
		System.out.println(
				solveByZeroOneBP(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(
				solveByZeroOneBP(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 5, 2, 4 })); // 9
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
		System.out
				.println(solveByCHT(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out
				.println(solveByCHT(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 5, 2, 4 })); // 9
		System.out.println(
				solveBybinarization(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 4, 3, 4 }, new int[] { 1, 5, 2, 4 })); // 15
		System.out.println(
				solveBybinarization(10, new int[] { 2, 3, 5, 7 }, new int[] { 1, 1, 1, 1 }, new int[] { 1, 5, 2, 4 })); // 9

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

	public static int solveByZeroOneBP(int size_limit, int[] sizes, int[] amount, int[] value) {
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

	/** O(Vnlogn) */
	public static int solveBybinarization(int size_limit, int[] sizes, int[] amount, int[] value) {
		int[] dp = new int[size_limit + 1];
		for (int i = 0; i < sizes.length; i++) {
			int num = amount[i];
			for (int j = 1; num > 0; j <<= 1) {
				int mul = Math.min(j, num);
				int curSize = mul * sizes[i];
				int curValue = mul * value[i];
				num -= mul;
				for (int v = size_limit; v >= curSize; v--) {
					dp[v] = Math.max(dp[v], dp[v - curSize] + curValue);
				}
			}
		}
		return dp[size_limit];
	}

	/** O(Vn) */
	public static int solveByCHT(int size_limit, int[] sizes, int[] amount, int[] value) {
		int n = sizes.length;
		int[][] dp = new int[n + 1][size_limit + 1];
		for (int i = 0; i < n; i++) {
			// r for congruence
			for (int r = 0; r < sizes[i]; r++) {
				Deque<Integer> dq = new LinkedList<>();
				for (int j = 0; j * sizes[i] + r <= size_limit; j++) {
					int curV = dp[i][j * sizes[i] + r] - j * value[i];
					while (!dq.isEmpty()) {
						int latest = dq.peekLast();
						int latestV = dp[i][latest * sizes[i] + r] - latest * value[i];
						if (latestV <= curV) {
							dq.pollLast();
						} else {
							break;
						}
					}
					dq.offer(j);
					while (!dq.isEmpty()) {
						int oldest = dq.peekFirst();
						if (j - oldest > amount[i]) {
							dq.pollFirst();
						} else {
							break;
						}
					}
					int best = dq.peekFirst();
					dp[i + 1][j * sizes[i] + r] = dp[i][best * sizes[i] + r] - best * value[i] + j * value[i];
				}
			}
		}
		return dp[n][size_limit];
	}
}
