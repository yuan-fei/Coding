package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factorization {

	public static void main(String[] args) {
		System.out.println(getAllPrimeFactorsQuick(15));
		System.out.println(getAllPrimeFactors(36));
		System.out.println(getAllPrimeFactors(35));
		System.out.println(getAllPrimeFactors(1));
		System.out.println(getAllPrimeFactors(11));
		System.out.println(getAllFactors(36));
		System.out.println(getAllFactors(1));
		System.out.println(getAllFactors(11));
	}

	/** O(n^0.5) */
	public static Map<Long, Integer> getAllPrimeFactors(long n) {
		Map<Long, Integer> pFactors = new HashMap<>();
		int cnt = 0;
		long factor = 2;
		while (factor * factor <= n) {
			if (n % factor == 0) {
				pFactors.put(factor, ++cnt);
				n /= factor;
			} else {
				factor += 1;
				cnt = 0;
			}
		}
		if (n > 1) {
			pFactors.put(n, 1);
		}

		return pFactors;
	}

	public static Map<Long, Integer> getAllPrimeFactorsQuick(long n) {
		Map<Long, Integer> pFactors = new HashMap<>();

		long factor = 2;
		while (n % 2 == 0) {
			int cnt = 0;
			if (pFactors.containsKey(factor)) {
				cnt = pFactors.get(factor);
			}
			pFactors.put(2L, cnt + 1);
			n /= 2;
		}
		factor += 1;
		while (factor * factor <= n) {
			if (n % factor == 0) {
				int cnt = 0;
				if (pFactors.containsKey(factor)) {
					cnt = pFactors.get(factor);
				}
				pFactors.put(factor, cnt + 1);
				n /= factor;
			} else {
				factor += 2;
			}
		}

		// This condition is to handle the case when
		// n is a prime number greater than 2
		if (n > 2) {
			int cnt = 0;
			if (pFactors.containsKey(n)) {
				cnt = pFactors.get(n);
			}
			pFactors.put(n, cnt + 1);
		}
		return pFactors;
	}

	/** O(n^0.5) */
	public static List<Integer> getAllFactors(int n) {
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				res.add(i);
				if (i != n / i) {
					res.add(n / i);
				}

			}
		}
		return res;
	}

}
