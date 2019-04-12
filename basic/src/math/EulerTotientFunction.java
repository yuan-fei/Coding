package math;

import java.util.Map;
import java.util.Set;

/**
 * Euler's totient function: count of numbers in {1, 2, 3, …, n} that are
 * relatively prime to n
 * 
 * O(n^0.5) for prime factorization
 */
public class EulerTotientFunction {

	public static void main(String[] args) {
		System.out.println(phi(7));
		System.out.println(phi(10));
		System.out.println(phi(6));
	}

	/** phi(n)=n*((p1-1)/p1) * ((p2-1)/p2)... */
	public static long phi(long n) {
		Map<Long, Integer> fcMap = Factorization.getAllPrimeFactors(n);
		Set<Long> primeFactors = fcMap.keySet();
		long res = n;
		for (Long f : primeFactors) {
			res /= f;
			res *= f - 1;
		}
		return res;
	}

}
