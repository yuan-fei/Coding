package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Factorization {

	public static void main(String[] args) {
		System.out.println(getAllPrimeFactors(36));
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
		while (n % 2 == 0) {
			pFactors.put(2L, ++cnt);
			n /= 2;
		}
		long factor = 3;
		cnt = 0;
		while (factor * factor <= n) {
			if (n % factor == 0) {
				pFactors.put(factor, ++cnt);
				n /= factor;
			} else {
				factor += 2;
				cnt = 0;
			}
		}

		// This condition is to handle the case when
		// n is a prime number greater than 2
		if (n > 2) {
			pFactors.put(n, ++cnt);
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
