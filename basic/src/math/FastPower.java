package math;

import java.util.Arrays;

public class FastPower {

	public static void main(String[] args) {
		System.out.println(fastExpRecursive(3, 4));
		System.out.println(fastExpRecursive(3, 5));
		System.out.println(fastExpIterative(3, 4));
		System.out.println(fastExpIterative(3, 5));

		System.out.println(modularExp(3, 4, 5));
		System.out.println(modularExp(3, 5, 5));

		System.out.println(modularExpWithPrimeModulo(3, 5, 5));
		System.out.println(modularExpWithPrimeModulo(3, -1, 5));
		System.out.println(modularExpWithPrimeModulo(3, -6, 5));
		System.out.println(modularExpWithPrimeModulo(3, 0, 5));

		// 377860091
		System.out.println(modularExp(413606424, 582988447, 837153029));
		long[][] a = new long[][] { new long[] { 1, 1 }, new long[] { 1, 0 } };
		System.out.println(Arrays.deepToString(fastMatrixExp(a, 3)));
	}

	public static long fastExpRecursive(int base, int exp) {
		if (exp == 0) {
			return 1;
		} else if (exp % 2 == 1) {
			return base * fastExpRecursive(base, exp - 1);
		} else {
			long t = fastExpRecursive(base, exp / 2);
			return t * t;
		}
	}

	public static long fastExpIterative(int base, int exp) {
		long res = 1;
		while (exp != 0) {
			if (exp % 2 == 1) {
				// only add to result when exp is odd
				res *= base;
			}
			base *= base;
			exp /= 2;
		}
		return res;
	}

	/**
	 * modulo m is prime, exp can be any integer i.e. 3^4 , 3^-3
	 */
	public static long modularExpWithPrimeModulo(int base, long exp, long m) {
		exp %= m - 1;
		// in case negative exp
		exp += m - 1;
		exp %= m - 1;
		base %= m;
		return modularExp(base, exp, m);
	}

	/** exp>=0 */
	public static long modularExp(int base, long exp, long m) {
		return modularExpRecursive(base, exp, m);
	}

	private static long modularExpRecursive(int base, long exp, long m) {
		if (exp == 0) {
			return 1 % m;
		} else if (exp % 2 == 1) {
			long l = base * modularExpRecursive(base, exp - 1, m);
			return l % m;
		} else {
			long t = modularExpRecursive(base, exp / 2, m);
			return (t * t) % m;
		}
	}

	/** a^n in O(m^3 * logn) */
	public static long[][] fastMatrixExp(long[][] a, int exp) {
		if (exp == 0) {
			long[][] b = new long[a.length][a.length];
			for (int i = 0; i < a.length; i++) {
				b[i][i] = 1;
			}
			return b;
		} else if (exp % 2 == 1) {
			return multiplyMatrice(a, fastMatrixExp(a, exp - 1));
		} else {
			long[][] half = fastMatrixExp(a, exp / 2);
			return multiplyMatrice(half, half);
		}
	}

	private static long[][] multiplyMatrice(long[][] a, long[][] b) {
		long[][] mul = new long[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[i].length; k++) {
					mul[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return mul;
	}
}
