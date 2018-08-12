package math;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PollardRhoFactorization {

	public static void main(String[] args) {
		System.out.println(getFactor(10403));
		System.out.println(factorize(10403));
		System.out.println(getFactor(new BigInteger("9007199254740991")));
		System.out.println(factorize(new BigInteger("9007199254740991")));
	}

	public static int getFactor(int n) {
		int x = new Random().nextInt(n);
		int y = x;
		int cycle = 2;
		int factor = 1;
		while (factor == 1) {
			int count = 1;
			while (count < cycle) {
				x = (x * x - 1) % n;
				int gcd = GCD.euclid(Math.abs(x - y), n);
				if (gcd != 1 && gcd != n) {
					factor = gcd;
				}
				count++;
			}
			y = x;
			cycle *= 2;
		}
		return factor;
	}

	public static BigInteger getFactor(BigInteger n) {
		BigInteger x = new BigInteger(n.bitLength(), new Random());
		BigInteger y = x;
		int cycle = 2;
		BigInteger factor = BigInteger.ONE;
		while (factor == BigInteger.ONE) {
			int count = 1;
			while (count < cycle) {
				x = x.pow(2).subtract(BigInteger.ONE).mod(n);
				BigInteger gcd = n.gcd(x.subtract(y).abs());
				if (!gcd.equals(BigInteger.ONE) && !gcd.equals(n.subtract(BigInteger.ONE))) {
					factor = gcd;
				}
				count++;
			}
			y = x;
			cycle *= 2;
		}
		return factor;
	}

	public static List<Integer> factorize(int n) {
		if (MillerRabinPrimalityTest.isPrime(n, 10)) {
			return new ArrayList<Integer>(Arrays.asList(n));
		} else {
			int factor = getFactor(n);
			List<Integer> factorization1 = factorize(factor);
			List<Integer> factorization2 = factorize(n / factor);
			factorization1.addAll(factorization2);
			factorization1.sort(Integer::compareTo);
			return factorization1;
		}
	}

	public static List<BigInteger> factorize(BigInteger n) {
		// if (n.isProbablePrime(10)) {
		if (MillerRabinPrimalityTest.isPrime(n, 10)) {
			return new ArrayList<BigInteger>(Arrays.asList(n));
		} else {
			BigInteger factor = getFactor(n);
			List<BigInteger> factorization1 = factorize(factor);
			List<BigInteger> factorization2 = factorize(n.divide(factor));
			factorization1.addAll(factorization2);
			factorization1.sort(BigInteger::compareTo);
			return factorization1;
		}
	}

}
