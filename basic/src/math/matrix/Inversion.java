package math.matrix;

import java.util.Arrays;

/** Invert a matrix with LUP decomposition in O(n^3) */
public class Inversion {

	public static void main(String[] args) {
		double[][] A = new double[][] { new double[] { -3, 1 }, new double[] { 5, 0 } };
		double[][] inversion = invertMatrix(A);
		System.out.println(Arrays.deepToString(inversion));
	}

	public static double[][] invertMatrix(double[][] a) {
		int n = a.length;
		double[][] transposedInvertedMatrix = new double[n][n];
		double[][] identityMatrix = new double[n][n];
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		int[] p = new int[n];
		boolean decomposable = LUP.decompose(a, l, u, p);
		if (!decomposable) {
			return new double[0][0];
		}
		for (int i = 0; i < identityMatrix.length; i++) {
			identityMatrix[i][i] = 1;
		}
		for (int i = 0; i < identityMatrix.length; i++) {
			transposedInvertedMatrix[i] = LUP.solveDecomposedEquation(l, u, p, identityMatrix[i]);
		}
		return MatrixUtil.transpose(transposedInvertedMatrix);
	}
}
