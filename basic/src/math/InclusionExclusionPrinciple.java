package math;

import java.util.ArrayList;
import java.util.List;

public class InclusionExclusionPrinciple {
	public static void main(String[] args) {
		System.out.println(countCoPrime(2, 20, 2));
		System.out.println(countCoPrime(15, 20, 2));
		System.out.println(countCoPrime(15, 30, 6));
	}

	/** count the numbers which is coprime with n in [l, r] */
	public static int countCoPrime(int l, int r, int n) {
		return countCoPrime(r, n) - countCoPrime(l - 1, n);
	}

	/** count the numbers which is coprime with n in [1, m] */
	public static int countCoPrime(int m, int n) {
		List<Long> primeFactors = new ArrayList<>();
		primeFactors.addAll(Factorization.getAllPrimeFactors(n).keySet());
		int k = primeFactors.size();
		int total = 0;
		for (int i = 0; i < (1 << k); i++) {
			int factor = 1;
			int cnt = 0;
			for (int j = 0; j < k; j++) {
				if (((i >> j) & 1) != 0) {
					factor *= primeFactors.get(j);
					cnt++;
				}
			}
			if (cnt % 2 == 1) {
				total -= (m / factor);
			} else {
				total += (m / factor);
			}
		}
		return total;
	}
}
