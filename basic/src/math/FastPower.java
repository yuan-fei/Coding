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
		// 2052166149
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

	public static long modularExp(int base, long exp, long n) {
		return modularExpRecursive(base, exp, n);
	}

	private static long modularExpRecursive(int base, long exp, long n) {
		if (exp == 0) {
			return 1;
		} else if (exp % 2 == 1) {
			long l = base * modularExpRecursive(base, exp - 1, n);
			return l % n;
		} else {
			long t = modularExpRecursive(base, exp / 2, n);
			return (t * t) % n;
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
