package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

public class MobiusInversionFormula {

	public static long mobiusInversion(int n, Function<Long, Long> f) {
		Map<Long, Integer> mu = getAllMobius(n);
		long ret = 0L;
		for (Entry<Long, Integer> p : mu.entrySet()) {
			ret += f.apply(n / p.getKey()) * p.getValue();
		}
		return ret;
	}

	/**
	 * return mobius function value (mu) for all factors of n, omitted the factors
	 * whose mu=0
	 */
	public static Map<Long, Integer> getAllMobius(long n) {
		Map<Long, Integer> ret = new HashMap<>();
		List<Long> factors = new ArrayList<>();
		factors.addAll(Factorization.getAllPrimeFactors(n).keySet());
		int size = factors.size();
		for (int i = 0; i < (1 << size); i++) {
			long factor = 1;
			int mu = 1;
			for (int j = 0; j < size; j++) {
				if (((i >> j) & 1) == 1) {
					factor *= factors.get(j);
					mu *= -1;
				}
			}
			ret.put(factor, mu);
		}
		return ret;
	}

	/**
	 * Mobius function mu is defined:
	 * 
	 * mu(d) = 0 if there is square prime factor in d
	 * 
	 * mu(d) = -1^k k is the # of different prime factors
	 */
	public static int mobius(long n) {
		int mu = 1;
		for (Entry<Long, Integer> p : Factorization.getAllPrimeFactors(n).entrySet()) {
			if (p.getValue() > 1) {
				return 0;
			} else {
				mu *= -1;
			}
		}
		return mu;
	}

	/**
	 * count # of strings of length n and alphabet size m who has no period
	 * 
	 * i.e.
	 * 
	 * "abcabc" is period of 3
	 * 
	 * "abc" has no period
	 * 
	 * 
	 */
	public static long countStringWithNoPeriod(int n, int m) {
		return mobiusInversion(n, d -> (long) Math.pow(m, d));
	}

	public static void main(String[] args) {
		System.out.println(getAllMobius(60));
		System.out.println(countStringWithNoPeriod(2, 26));
		System.out.println(countStringWithNoPeriod(4, 26));
	}
}
