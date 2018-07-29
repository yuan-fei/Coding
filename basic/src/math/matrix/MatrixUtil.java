package math.matrix;

import utils.Util;

public class MatrixUtil {
	public static void swapRows(double[][] arr, int i, int j) {
		double[] d = arr[i];
		arr[i] = arr[j];
		arr[j] = d;
	}

	public static double[][] copyOf(double[][] a) {
		double[][] aA = new double[a.length][a[0].length];
		Util.copy(a, aA);
		return aA;
	}

	public static double[][] transpose(double[][] matrix) {
		double[][] tMatrix = new double[matrix[0].length][matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				tMatrix[j][i] = matrix[i][j];
			}
		}
		return tMatrix;
	}
}
