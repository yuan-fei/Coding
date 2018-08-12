package math;

import java.util.Arrays;

public class ModularLinearEquationSolver {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solve(14, 30, 100)));
		System.out.println(Arrays.toString(solve(14, 1, 100)));

	}

	/** Solve ax = b (mod n), return [] if not solvable */
	public static int[] solve(int a, int b, int n) {
		GCDResult gcd = GCD.euclidExtended(a, n);
		if (b % gcd.d == 0) {
			int[] r = new int[gcd.d];
			int x0 = (gcd.x * (b / gcd.d)) % n;
			// make sure x0 > 0
			x0 = (x0 + n) % n;
			for (int i = 0; i < r.length; i++) {
				r[i] = (x0 + i * (n / gcd.d)) % n;
			}
			return r;
		} else {
			return new int[0];
		}
	}

	public static int getMultiplicativeInverse(int a, int n) {
		int[] r = solve(a, 1, n);
		if (r.length == 1) {
			return r[0];
		} else {
			return -1;
		}
	}
}
