package math;

import java.util.ArrayList;
import java.util.List;

public class Factorization {

	public static void main(String[] args) {
		System.out.println(primeFactorize(36));
		System.out.println(primeFactorize(1));
		System.out.println(primeFactorize(11));
		System.out.println(getAllFactors(36));
		System.out.println(getAllFactors(1));
		System.out.println(getAllFactors(11));
	}

	/** O(n^0.5) */
	public static List<Integer> primeFactorize(int n) {
		List<Integer> res = new ArrayList<>();

		while (n % 2 == 0) {
			res.add(2);
			n /= 2;
		}
		int factor = 3;
		while (factor * factor <= n) {
			if (n % factor == 0) {
				res.add(factor);
				n /= factor;
			} else {
				factor += 2;
			}
		}

		// This condition is to handle the case whien
		// n is a prime number greater than 2
		if (n > 2) {
			res.add(n);
		}
		return res;
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
