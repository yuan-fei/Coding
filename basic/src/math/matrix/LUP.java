package math.matrix;

import java.util.Arrays;

/**
 * LUP decomposition for solving linear equation or matrix inversion in O(n^3)
 */
public class LUP {

	public static void main(String[] args) {
		double[][] A = new double[][] { new double[] { 1, 2, 0 }, new double[] { 3, 4, 4 }, new double[] { 5, 6, 3 } };
		double[][] AL = new double[3][3];
		double[][] AU = new double[3][3];
		int[] AP = new int[3];
		decompose(A, AL, AU, AP);
		System.out.println(Arrays.deepToString(AL));
		System.out.println(Arrays.deepToString(AU));
		System.out.println(Arrays.toString(AP));
		double[][] L = new double[][] { new double[] { 1, 0, 0 }, new double[] { 0.2, 1, 0 },
				new double[] { 0.6, 0.5, 1 } };
		double[][] U = new double[][] { new double[] { 5, 6, 3 }, new double[] { 0, 0.8, -0.6 },
				new double[] { 0, 0, 2.5 } };
		double[][] P = new double[][] { new double[] { 0, 0, 1 }, new double[] { 1, 0, 0 }, new double[] { 0, 1, 0 } };
		double[] b = new double[] { 3, 7, 8 };
		double[] x = solveDecomposedEquation(L, U, getPermutationTable(P), b);
		System.out.println(Arrays.toString(x));
		A = new double[][] { new double[] { 1, 5, 4 }, new double[] { 2, 0, 3 }, new double[] { 5, 8, 2 } };
		b = new double[] { 12, 9, 5 };
		x = solveLinearEquation(A, b);
		System.out.println(Arrays.toString(x));
	}

	public static double[] solveLinearEquation(double[][] a, double[] b) {
		int n = a.length;
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		int[] p = new int[n];
		if (decompose(a, l, u, p)) {
			return solveDecomposedEquation(l, u, p, b);
		}
		return new double[0];
	}

	public static boolean decompose(double[][] a, double[][] l, double[][] u, int[] p) {
		int n = p.length;
		double[][] aA = MatrixUtil.copyOf(a);
		for (int i = 0; i < n; i++) {
			p[i] = i;
		}
		for (int i = 0; i < n; i++) {
			int indexMax = i;
			double absMax = 0d;
			// find pivot
			for (int j = i; j < n; j++) {
				if (Math.abs(aA[j][i]) > absMax) {
					absMax = Math.abs(aA[j][i]);
					indexMax = j;
				}
			}
			if (absMax == 0d) {
				return false;
			}
			swap(p, i, indexMax);
			MatrixUtil.swapRows(aA, i, indexMax);

			// Gaussian eliminate
			for (int j = i + 1; j < n; j++) {
				aA[j][i] /= aA[i][i];
				for (int k = i + 1; k < n; k++) {
					aA[j][k] -= aA[i][k] * aA[j][i];
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j < i) {
					l[i][j] = aA[i][j];
				} else if (i == j) {
					l[i][j] = 1d;
					u[i][j] = aA[i][j];
				} else {
					u[i][j] = aA[i][j];
				}
			}
		}
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int d = arr[i];
		arr[i] = arr[j];
		arr[j] = d;
	}

	/**
	 * L is unit lower triangle matrix, U is upper triangle matrix, P is permutation
	 * matrix
	 */
	public static double[] solveDecomposedEquation(double[][] l, double[][] u, int[] p, double[] b) {
		// Solve Ly=Pb with forward substitution
		double[] y = new double[b.length];
		for (int i = 0; i < l.length; i++) {
			y[i] = b[p[i]];
			for (int j = 0; j <= i - 1; j++) {
				y[i] -= l[i][j] * y[j];
			}
		}

		// Solve Ux=y with backward substitution
		double[] x = new double[b.length];
		for (int i = x.length - 1; i >= 0; i--) {
			x[i] = y[i];
			for (int j = x.length - 1; j >= i + 1; j--) {
				x[i] -= u[i][j] * x[j];
			}
			x[i] /= u[i][i];
		}
		return x;
	}

	private static int[] getPermutationTable(double[][] p) {
		int[] pTable = new int[p.length];
		for (int i = 0; i < pTable.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				if (p[i][j] == 1d) {
					pTable[i] = j;
				}
			}
		}
		return pTable;
	}
}
