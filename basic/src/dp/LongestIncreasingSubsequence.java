package dp;

import java.util.Arrays;
import java.util.TreeSet;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 4, 2, 3, 1, 5 })); // 3
		System.out.println(solveWithBinarySearch(new int[] { 4, 2, 3, 3, 1, 5, 6 })); // 4
		System.out.println(solveWithTreeSet(new int[] { 4, 2, 3, 3, 1, 5, 6 })); // 4
		System.out.println(longestNonDecreasingSubequence(new int[] { 4, 2, 3, 3, 1, 5, 6 })); // 5
	}

	/** O(n^2) */
	public static int solve(int[] a) {
		// state[i] is LIS that contains a[i]
		int[] state = new int[a.length + 1];

		state[0] = 0;
		for (int i = 1; i <= a.length; i++) {
			state[i] = 1;
			for (int j = 1; j <= i; j++) {
				if (a[i - 1] > a[j - 1]) {
					state[i] = Math.max(state[i], state[j] + 1);
				}
			}
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < state.length; i++) {
			res = Math.max(res, state[i]);
		}
		return res;
	}

	/**
	 * Patient sorting: O(nlogn)
	 * 
	 * http://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence-2x2.pdf
	 */
	public static int solveWithBinarySearch(int[] a) {
		// state[i] min last element with LIS length i
		int[] state = new int[a.length + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			max = Math.max(max, a[i]);
		}
		Arrays.fill(state, max + 1);
		state[0] = 0;
		for (int i = 1; i <= a.length; i++) {
			int index = Arrays.binarySearch(state, a[i - 1]);
			if (index < 0) {
				state[-index - 1] = a[i - 1];
			} else {
				state[index] = a[i - 1];
			}
		}
		for (int i = state.length - 1; i >= 0; i--) {
			if (state[i] < max + 1) {
				return i;
			}
		}
		return -1;
	}

	/** O(nlogn): inspired by the binary search method (patient sorting) */
	public static int solveWithTreeSet(int[] a) {
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i = 0; i < a.length; i++) {
			Integer ceiling = ts.ceiling(a[i]);
			if (ceiling != null) {
				ts.remove(ceiling);
			}
			ts.add(a[i]);
		}
		return ts.size();
	}

	/** O(nlogn): inspired by the binary search method (patient sorting) */
	public static int longestNonDecreasingSubequence(int[] a) {
		TreeSet<Integer> ts = new TreeSet<>(
				(i, j) -> (Integer.compare(a[i], a[j]) != 0) ? Integer.compare(a[i], a[j]) : Integer.compare(i, j));
		for (int i = 0; i < a.length; i++) {
			Integer ceiling = ts.ceiling(i);
			if (ceiling != null) {
				ts.remove(ceiling);
			}
			ts.add(i);
		}
		return ts.size();
	}
}
