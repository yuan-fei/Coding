package math;

import java.math.BigInteger;
import java.util.Random;

public class MillerRabinPrimalityTest {

	public static void main(String[] args) {
		System.out.println(isPrime(561, 10));
		System.out.println(isPrime(7919, 10));
		System.out.println(isPrime(new BigInteger("561"), 10));
		System.out.println(isPrime(new BigInteger("7919"), 10));
	}

	public static boolean isPrime(int n, int s) {
		Random r = new Random();
		for (int i = 0; i < s; i++) {
			if (!witness(n, r.nextInt(n - 1) + 1)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isPrime(BigInteger n, int s) {
		for (int i = 0; i < s; i++) {

			if (!witness(n,
					new BigInteger(n.bitLength(), new Random()).mod(n.subtract(BigInteger.ONE).add(BigInteger.ONE)))) {
				return false;
			}
		}
		return true;
	}

	/** return true if it's possibly a prime, or false if it's composite */
	private static boolean witness(int n, int base) {
		int[] decomposion = decompose(n - 1);
		int x_pre = (int) FastPower.modularExp(base, decomposion[0], n);
		int x = x_pre;
		for (int e = 0; e < decomposion[1]; e++) {
			x = (int) FastPower.modularExp(x_pre, 2, n);
			if (x == 1 && x_pre != 1 && x_pre != n - 1) {// non-trival square root of 1, modulo n
				return false;
			}
			x_pre = x;
		}
		if (x != 1) {
			return false;
		} else {
			return true;
		}
	}

	/** return true if it's possibly a prime, or false if it's composite */
	private static boolean witness(BigInteger n, BigInteger base) {
		BigInteger[] decomposion = decompose(n.subtract(BigInteger.ONE));
		BigInteger x_pre = base.modPow(decomposion[0], n);
		BigInteger x = x_pre;
		for (int e = 0; e < decomposion[1].intValue(); e++) {
			x = x_pre.modPow(BigInteger.valueOf(2), n);
			if (x.equals(BigInteger.ONE) && !x_pre.equals(BigInteger.ONE)
					&& !x_pre.equals(n.subtract(BigInteger.ONE))) {// non-trival square root
				// of 1, modulo n
				return false;
			}
			x_pre = x;
		}
		if (!x.equals(BigInteger.ONE)) {
			return false;
		} else {
			return true;
		}
	}

	/** Decompose m into factor 2^t and odd */
	private static int[] decompose(int m) {
		int mask = 1;
		int t = 0;
		while ((m & mask) == 0) {
			m >>= 1;
			t++;
		}
		return new int[] { m, t };
	}

	private static BigInteger[] decompose(BigInteger m) {
		int index = m.getLowestSetBit();
		return new BigInteger[] { m.shiftRight(index), BigInteger.valueOf(index) };
	}
}
