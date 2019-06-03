package math;

import java.util.List;
import java.util.Set;

public class PolyaCountingTheorem {

	public static void main(String[] args) {
		System.out.println(countNecklaceColorings(10, 1));
		System.out.println(countNecklaceColorings(2, 2));
		System.out.println(countNecklaceColorings(4, 2));
	}

	/**
	 * different colorings of a necklace of length n, each bead can be colored in k
	 * colors, 2 colorings are same if one can be transformed to another by rotation
	 */
	public static long countNecklaceColorings(int n, int k) {
		List<Integer> factors = Factorization.getAllFactors(n);
		Set<Long> primes = Factorization.getAllPrimeFactors(n).keySet();
		// Euler's totient for each factor
		long total = 0;

		for (int i = 0; i < factors.size(); i++) {
			long factor = factors.get(i);
			long euler = factor;
			for (long p : primes) {
				if (factor % p == 0) {
					euler = euler * (p - 1) / p;
				}
			}
			total += euler * Math.pow(k, n / factor);
		}
		return total / n;
	}
}
