
import java.util.PriorityQueue;

public class BreakingString {

	public static void main(String[] args) {
		System.out.println(cut(21, new int[] { 5, 8, 8 })); // 34
		System.out.println(cutAtIndex(20, new int[] { 2, 8, 10 })); // 38
	}

	/**
	 * Problem: Cut a string into sizes, a cut of s cost len(s), find the minimum
	 * cost cut of a string
	 * 
	 * Solution:greedy
	 */
	public static int cut(int totalSize, int[] size) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int s : size) {
			q.offer(s);
		}
		q.offer(Integer.MAX_VALUE);
		int cost = 0;
		while (!q.isEmpty()) {
			int min1 = q.poll();
			int min2 = q.poll();
			if (min2 == Integer.MAX_VALUE) {
				return cost;
			} else {
				cost += (min1 + min2);
				q.offer(min1 + min2);
			}
		}
		return -1;
	}

	/**
	 * Problem: Cut a string at indices, a cut of s cost len(s), find the minimum
	 * cost cut of a string
	 * 
	 * Solution: DP
	 */
	public static int cutAtIndex(int totalSize, int[] indices) {
		int[] extendedIndices = new int[indices.length + 2];
		System.arraycopy(indices, 0, extendedIndices, 1, indices.length);
		extendedIndices[extendedIndices.length - 1] = totalSize;
		return cutAtIndex(extendedIndices);

	}

	private static int cutAtIndex(int[] indices) {
		int[][] state = new int[indices.length][indices.length];

		for (int j = 2; j < indices.length; j++) {
			for (int i = j - 2; i >= 0; i--) {
				state[i][j] = Integer.MAX_VALUE;
				for (int k = i + 1; k < j; k++) {
					state[i][j] = Math.min(state[i][j], state[i][k] + state[k][j] + indices[j] - indices[i]);
				}
			}
		}
		return state[0][indices.length - 1];
	}
}
