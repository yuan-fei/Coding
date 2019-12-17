package greedy;

import java.util.Arrays;

/**
 * Given a list of intervals, remove all intervals that are covered by another
 * interval in the list. Interval [a,b) is covered by interval [c,d) if and only
 * if c <= a and b <= d.
 * 
 * After doing so, return the number of remaining intervals.
 */
public class RemoveCoveredIntervals {

	public static void main(String[] args) {
		int[][] A = { { 1, 4 }, { 3, 6 }, { 2, 8 } };
		System.out.println(removeCoveredIntervals(A)); // 2
	}

	public static int removeCoveredIntervals(int[][] A) {
		int res = 0, right = 0;
		Arrays.sort(A, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
		for (int[] v : A) {
			if (v[1] > right) {
				++res;
			}
			right = Math.max(right, v[1]);
		}
		return res;
	}
}
