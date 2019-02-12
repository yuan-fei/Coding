package math.matrix;

import java.util.Arrays;

public class GaussianElimination {

	public static void main(String[] args) {
		double[][] A = new double[][] { new double[] { 1, 5, 4 }, new double[] { 2, 0, 3 }, new double[] { 5, 8, 2 } };
		double[] b = new double[] { 12, 9, 5 };
		double[] x = GaussianElimination.solveLinearEquation(A, b);
		System.out.println(Arrays.toString(x));

	}

	public static double[] solveLinearEquation(double[][] a, double[] b) {
		int n = a.length;
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		int[] p = new int[n];
		return null;
	}

}
