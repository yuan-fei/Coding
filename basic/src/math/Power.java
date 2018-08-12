package math;

public class Power {

	public static void main(String[] args) {
		// System.out.println(fastExp(3, 4));
		// System.out.println(fastExp(3, 5));
		// System.out.println(modularExp(3, 4, 5));
		// System.out.println(modularExp(3, 5, 5));
		// 2052166149
		System.out.println(modularExp(413606424, 582988447, 837153029));
	}

	public static long fastExp(int base, int exp) {
		if (exp == 0) {
			return 1;
		} else if (exp % 2 == 1) {
			return base * fastExp(base, exp - 1);
		} else {
			long t = fastExp(base, exp / 2);
			return t * t;
		}
	}

	public static long modularExp(int base, int exp, long n) {
		return modularExpRecursive(base, exp, n);
	}

	private static long modularExpRecursive(int base, int exp, long n) {
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
}
