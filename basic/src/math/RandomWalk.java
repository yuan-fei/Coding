package math;

import math.matrix.LUP;

/**
 * given a matrix of M * N, random walk with equal prob to 4 adjacent grids (up,
 * down, left, right), find the EV of walks from top-left to bottom-right
 */
public class RandomWalk {

	public static void main(String[] args) {
		System.out.println(walk(2, 1));
		System.out.println(walk(3, 1));
		System.out.println(walk(4, 1));
		System.out.println(walk(5, 1));
		System.out.println(walk(2, 2));
		System.out.println(walk(3, 2));
		System.out.println(walk(4, 2));
		System.out.println(walk(5, 2));
		System.out.println(walk(10, 10));
	}

	static int[][] d4 = new int[][] { new int[] { 0, -1 }, new int[] { 0, 1 }, new int[] { -1, 0 },
			new int[] { 1, 0 } };

	/**
	 * Solve Equation: E(x, y) is the expectation from (x, y) to (M-1, N-1)
	 * 
	 * 1. E(x, y) = 0.25 * E(x-1, y) + 0.25 * E(x+1, y) + 0.25 * E(x, y-1) +
	 * 0.25 * E(x, y+1) + 1
	 * 
	 * 2. E(M, N) = 0
	 */
	public static double walk(int n, int m) {
		double[][] A = new double[n * m][n * m];
		double[] b = new double[n * m];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (x == n - 1 && y == m - 1) {
					A[x * m + y][x * m + y] = 1;
				} else {
					int count = 0;
					for (int k = 0; k < d4.length; k++) {
						int dx = x + d4[k][0];
						int dy = y + d4[k][1];
						if (dx >= 0 && dx < n && dy >= 0 && dy < m) {
							count++;
							A[x * m + y][dx * m + dy] = -1;
						}
					}
					A[x * m + y][x * m + y] = count;
					b[x * m + y] = count;
				}
			}
		}
		A[n * m - 1][n * m - 1] = 1;
		b[n * m - 1] = 0;
		double[] r = LUP.solveLinearEquation(A, b);
		return r[0];
	}
}
