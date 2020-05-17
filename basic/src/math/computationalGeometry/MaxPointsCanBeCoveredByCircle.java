package math.computationalGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of points, find how many points can be covered by circle with
 * radius r at most.
 * 
 * https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/
 * https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636659/Java-Angular-Sweep-with-Comments
 * https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/discuss/636416/c%2B%2B-O(n2logn)-angular-sweep-(with-picture)
 * 
 * Angular sweep: O(n^2logn)
 * https://www.geeksforgeeks.org/angular-sweep-maximum-points-can-enclosed-circle-given-radius/
 */
public class MaxPointsCanBeCoveredByCircle {

	public static void main(String[] args) {
		System.out.println(numPoints(new int[][] { { -3, 0 }, { 3, 0 }, { 2, 6 }, { 5, 4 }, { 0, 9 }, { 7, 8 } }, 5)); // 5
	}

	static class Angle implements Comparable<Angle> {
		double a;
		boolean enter;

		Angle(double a, boolean enter) {
			this.a = a;
			this.enter = enter;
		}

		@Override
		public int compareTo(Angle o) {
			// make sure enter is ahead of exit
			return (a > o.a) ? 1 : (a < o.a) ? -1 : enter == o.enter ? 0 : enter ? -1 : 1;
		}
	}

	static int getPointsInside(int i, double r, int n, int[][] points, double[][] dis) {
		List<Angle> angles = new ArrayList<>(2 * n);

		for (int j = 0; j < n; j++) {
			if (i != j && dis[i][j] <= 2 * r) {
				// angle between line [i, j] and line [i, center]
				double B = Math.acos(dis[i][j] / (2 * r));
				// angle between line [i, j] and x axis
				double A = Math.atan2(points[j][1] - points[i][1], points[j][0] - points[i][0]);
				double alpha = A - B;
				double beta = A + B;
				angles.add(new Angle(alpha, true));
				angles.add(new Angle(beta, false));
			}
		}

		Collections.sort(angles);

		int count = 1, res = 1;
		for (Angle a : angles) {
			if (a.enter) {
				count++;
			} else {
				count--;
			}

			if (count > res) {
				res = count;
			}
		}

		return res;
	}

	public static int numPoints(int[][] points, int r) {
		int n = points.length;
		double[][] dis = new double[n][n];
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				dis[i][j] = dis[j][i] = Math
						.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans = Math.max(ans, getPointsInside(i, r, n, points, dis));
		}

		return ans;
	}
}
