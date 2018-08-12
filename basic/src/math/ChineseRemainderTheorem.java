package math;

import java.util.Arrays;

public class ChineseRemainderTheorem {

	public static void main(String[] args) {
		System.out.println(solve(new int[] { 5, 13 }, new int[] { 2, 3 }));
		System.out.println(solve(new int[] { 3, 5, 7 }, new int[] { 2, 3, 5 }));

	}

	/** Solve {x mod ni = ri} where ni are pairwise relatively prime */
	public static int solve(int[] n, int[] r) {
		int nTotal = Arrays.stream(n).reduce(1, (e, a) -> e * a);
		int solution = 0;
		for (int i = 0; i < r.length; i++) {
			int nProduct = nTotal / n[i];
			int inverse = ModularLinearEquationSolver.getMultiplicativeInverse(nProduct, n[i]);
			solution += inverse * nProduct * r[i];
			solution %= nTotal;
		}
		return solution;
	}
}
