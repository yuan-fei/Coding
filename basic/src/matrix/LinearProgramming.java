package matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import matrix.LinearProgramming.SolutionType;
import utils.Util;

public class LinearProgramming {

	static enum SolutionType {
		NOT_FEASIBLE, FEASIBLE, OPTIMAL, UNBOUNDED
	}

	public static void main(String[] args) {
		testPivot();
		testSlackSimplex();
		testSimplex();
	}

	private static void testPivot() {
		double[][] A = { new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 0, 0, 0, 0, 0, 0 },
				new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 1, 1, 1, 0, 0, 0 }, new double[] { 2, 2, 5, 0, 0, 0 },
				new double[] { 4, 1, 2, 0, 0, 0 } };
		double[] b = { 0, 0, 0, 30, 24, 36 };
		double[] c = { 3, 1, 2, 0, 0, 0 };

		LinearProgrammingSlackForm lp = new LinearProgrammingSlackForm(A, b, c,
				new ArrayList<Integer>(Arrays.asList(3, 4, 5)), new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
		lp.pivot(5, 0);

		System.out.println(lp.c0);
	}

	private static void testSlackSimplex() {
		double[][] A = { new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 0, 0, 0, 0, 0, 0 },
				new double[] { 0, 0, 0, 0, 0, 0 }, new double[] { 1, 1, 1, 0, 0, 0 }, new double[] { 2, 2, 5, 0, 0, 0 },
				new double[] { 4, 1, 2, 0, 0, 0 } };
		double[] b = { 0, 0, 0, 30, 24, 36 };
		double[] c = { 3, 1, 2, 0, 0, 0 };

		LinearProgrammingSlackForm lp = new LinearProgrammingSlackForm(A, b, c,
				new ArrayList<Integer>(Arrays.asList(3, 4, 5)), new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
		lp.simplex();
		System.out.println(lp.c0);
	}

	private static void testSimplex() {
		double[][] A = { new double[] { 2, -1 }, new double[] { 1, -5 } };
		double[] b = { 2, -4 };
		double[] c = { 2, -1 };

		LinearProgrammingStandardForm lp = new LinearProgrammingStandardForm(A, b, c);
		SolutionType result = lp.simplex();
		System.out.println(result);
	}
}

/**
 * Standard form
 * 
 * maximize: z = 3x_1 + x_2+ 2x_3
 * 
 * subject to:
 * 
 * x_1 + x_2 + 3x_3 <= 30
 * 
 * 2x_1 + 2x_2 + 5x_3 <= 24
 * 
 * 4x_1 + x_2 + 2x_3 <= 36
 * 
 * x_1, x_2, x_3 >= 0
 */
class LinearProgrammingStandardForm {

	double[][] A;
	double[] b;
	double[] c;

	LinearProgrammingSlackForm slackForm;
	public double objective;
	public double[] solution;

	public LinearProgrammingStandardForm(double[][] a, double[] b, double[] c) {
		super();
		A = a;
		this.b = b;
		this.c = c;
	}

	private SolutionType initializeSimplex() {
		int iMinB = Util.indexOfFirst(b, i -> b[i] < 0);
		if (iMinB == -1) {
			slackForm = toSlackForm(A, b, c);
			return SolutionType.FEASIBLE;
		} else {
			double[][] auxA = new double[A.length][];
			for (int i = 0; i < auxA.length; i++) {
				auxA[i] = Arrays.copyOf(A[i], A[i].length + 1);
				auxA[i][A[i].length] = -1;
			}
			double[] auxC = new double[c.length + 1];
			auxC[c.length] = -1;
			LinearProgrammingSlackForm aux = toSlackForm(auxA, b, auxC);
			aux.pivot(iMinB + auxC.length, c.length);
			if (aux.simplex() == SolutionType.OPTIMAL && aux.solution[c.length] == 0) {
				double[][] slackA = aux.A;
				double[] slackB = aux.b;
				double[] slackC = Arrays.copyOf(c, c.length + b.length + 1);
				// List<Double> slackCList =
				// Arrays.stream(c).boxed().collect(Collectors.toList());
				// slackCList.add(c.length, 0d);
				// double[] slackC =
				// slackCList.stream().mapToDouble(Double::doubleValue).toArray();

				List<Integer> basicVar = aux.basicVars;
				List<Integer> nonBasicVar = aux.nonBasicVars;
				basicVar.remove((Object) (auxC.length - 1));
				nonBasicVar.remove((Object) (auxC.length - 1));
				slackForm = new LinearProgrammingSlackForm(slackA, slackB, slackC, basicVar, nonBasicVar);
				slackForm.adjustObjectiveCoefficients();
				return SolutionType.FEASIBLE;

			} else {
				return SolutionType.NOT_FEASIBLE;
			}
		}
	}

	private LinearProgrammingSlackForm toSlackForm(double[][] a, double[] b, double[] c) {
		int varCount = c.length + b.length;
		List<Integer> nonBasicVars = IntStream.range(0, c.length).boxed().collect(Collectors.toList());
		List<Integer> basicVars = IntStream.range(c.length, varCount).boxed().collect(Collectors.toList());

		double[][] slackA = new double[varCount][varCount];
		for (int i = 0; i < slackA.length; i++) {
			if (basicVars.contains(i)) {
				slackA[i] = Arrays.copyOf(a[i - c.length], varCount);
			}

		}
		double[] slackB = new double[varCount];
		for (int i = 0; i < slackB.length; i++) {
			if (basicVars.contains(i)) {
				slackB[i] = b[i - c.length];
			}
		}
		double[] slackC = Arrays.copyOf(c, varCount);

		return new LinearProgrammingSlackForm(slackA, slackB, slackC, basicVars, nonBasicVars);
	}

	public SolutionType simplex() {
		if (initializeSimplex() != SolutionType.NOT_FEASIBLE) {
			SolutionType result = slackForm.simplex();
			solution = slackForm.solution;
			objective = slackForm.c0;
			return result;
		}
		return SolutionType.NOT_FEASIBLE;
	}
}

/**
 * Slack form:
 * 
 * maximize: z = 3x_1 + x_2+ 2x_3
 * 
 * subject to:
 * 
 * x_4 = 30 - (x_1 + x_2 + 3x_3)
 * 
 * x_5 = 24 - (2x_1 + 2x_2 + 5x_3)
 * 
 * x_6 = 36 - (4x_1 + x_2 + 2x_3)
 * 
 * x_1, x_2, x_3, x_4, x_5, x_6 >= 0
 */
class LinearProgrammingSlackForm {

	double[][] A;
	double[] b;
	double[] c;
	List<Integer> basicVars;
	List<Integer> nonBasicVars;
	public double c0;
	public double[] solution;

	/* a should be of size (b+n)*(b+n) */
	public LinearProgrammingSlackForm(double[][] a, double[] b, double[] c, List<Integer> basicVars,
			List<Integer> nonBasicVars) {
		super();
		A = a;
		this.b = b;
		this.c = c;
		this.basicVars = basicVars;
		this.nonBasicVars = nonBasicVars;
		this.solution = null;
		c0 = 0;
	}

	/** Reform the constraint to replace the leavingVar with enteringVar */
	void pivot(int leavingVar, int enteringVar) {
		nonBasicVars.remove((Object) enteringVar);
		basicVars.remove((Object) leavingVar);

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
			A[bv][leavingVar] = -A[bv][enteringVar] * A[enteringVar][leavingVar];
		}

		// v
		c0 += c[enteringVar] * b[enteringVar];

		// c
		for (int nv : nonBasicVars) {
			c[nv] = c[nv] - c[enteringVar] * A[enteringVar][nv];
		}
		c[leavingVar] = -c[enteringVar] * A[enteringVar][leavingVar];
		c[enteringVar] = 0d;

		nonBasicVars.add(leavingVar);
		basicVars.add(enteringVar);
	}

	/** Solve linear programming with slack form by simplex method */
	public SolutionType simplex() {
		int enteringVar = Util.indexOfFirst(c, i -> c[i] > 0);
		while (enteringVar >= 0) {
			double min = Double.POSITIVE_INFINITY;
			int leavingVar = -1;
			for (int bv : basicVars) {
				if (A[bv][enteringVar] > 0 && b[bv] / A[bv][enteringVar] < min) {
					min = b[bv] / A[bv][enteringVar];
					leavingVar = bv;
				}
			}
			if (leavingVar == -1) {
				return SolutionType.UNBOUNDED;
			} else {
				pivot(leavingVar, enteringVar);
			}
			enteringVar = Util.indexOfFirst(c, i -> c[i] > 0);
		}
		solution = new double[b.length];
		for (int bv : basicVars) {
			solution[bv] = b[bv];
		}
		return SolutionType.OPTIMAL;
	}

	void adjustObjectiveCoefficients() {
		int bv = Util.indexOfFirst(c, i -> c[i] != 0 && basicVars.contains(i));
		while (bv != -1) {
			double delta = c[bv] * b[bv];
			c0 += delta;
			for (int nv : nonBasicVars) {
				c[nv] = c[nv] - c[bv] * A[bv][nv];
			}
			c[bv] = 0d;
			bv = Util.indexOfFirst(c, i -> c[i] != 0 && basicVars.contains(i));
		}
	}
}
