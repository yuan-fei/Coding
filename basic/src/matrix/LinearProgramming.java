package matrix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LinearProgramming {

	public static void main(String[] args) {
		double[][] A = new double[][] { new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 0, 0, 0, 0, 0, 0 },
				new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 1, 1, 1, 0, 0, 0 }, new double[] { 2, 2, 5, 0, 0, 0 },
				new double[] { 4, 1, 2, 0, 0, 0 } };
		double[] b = new double[] { 0, 0, 0, 30, 24, 36 };
		double[] c = new double[] { 3, 1, 2, 0, 0, 0 };

		LinearProgramming lp = new LinearProgramming(A, b, c, new HashSet<Integer>(Arrays.asList(3, 4, 5)),
				new HashSet<Integer>(Arrays.asList(0, 1, 2)));
		lp.pivot(5, 0);
		System.out.println(lp.c0);
	}

	double[][] A;
	double[] b;
	double[] c;
	double c0;
	Set<Integer> basicVars = new HashSet<Integer>();
	Set<Integer> nonBasicVars = new HashSet<Integer>();

	/* a should be of size (b+n)*(b+n) */
	public LinearProgramming(double[][] a, double[] b, double[] c, Set<Integer> basicVars, Set<Integer> nonBasicVars) {
		super();
		A = a;
		this.b = b;
		this.c = c;
		this.basicVars = basicVars;
		this.nonBasicVars = nonBasicVars;
		c0 = 0;
	}

	private void pivot(int leavingVar, int enteringVar) {
		nonBasicVars.remove(enteringVar);
		basicVars.remove(leavingVar);

		// A, b
		for (int nv : nonBasicVars) {
			A[enteringVar][nv] = A[leavingVar][nv] / A[leavingVar][enteringVar];
		}
		A[enteringVar][leavingVar] = 1 / A[leavingVar][enteringVar];
		b[enteringVar] = b[leavingVar] / A[leavingVar][enteringVar];

		for (int bv : basicVars) {
			for (int nv : nonBasicVars) {
				A[bv][nv] = A[bv][nv] - A[bv][enteringVar] * A[enteringVar][nv];
			}
			b[bv] = b[bv] - A[bv][enteringVar] * b[enteringVar];
			A[bv][leavingVar] = A[bv][enteringVar] * A[enteringVar][leavingVar];
		}

		// v
		c0 += c[enteringVar] * b[enteringVar];

		// c
		for (int nv : nonBasicVars) {
			c[nv] = c[nv] - c[enteringVar] * A[enteringVar][nv];
		}
		c[leavingVar] = -A[enteringVar][leavingVar];
		c[enteringVar] = 0d;

		nonBasicVars.add(leavingVar);
		basicVars.add(enteringVar);
	}

	private LinearProgramming initializeSimplex(double[][] a, double[] b, double[] c) {
		return null;
	}
}
