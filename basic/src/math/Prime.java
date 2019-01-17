package math;

import java.util.ArrayList;
import java.util.List;

public class Prime {
	public static void main(String[] args) {
		System.out.println(sieve(100));
		System.out.println(linearSieve(100));
	}

	/** O(nlogn) */
	public static List<Integer> sieve(int n) {
		List<Integer> primes = new ArrayList<>();
		boolean[] isComposite = new boolean[n + 1];
		for (int i = 2; i < isComposite.length; i++) {
			if (!isComposite[i]) {
				primes.add(i);
			}
			for (int j = i * i; j < isComposite.length; j += i) {
				isComposite[j] = true;
			}
		}

		return primes;
	}

	/** O(n) */
	public static List<Integer> linearSieve(int n) {
		List<Integer> primes = new ArrayList<>();
		boolean[] isComposite = new boolean[n + 1];
		for (int i = 2; i < isComposite.length; i++) {
			if (!isComposite[i]) {
				primes.add(i);
			}
			for (int j = 0; j < primes.size() && i * primes.get(j) < isComposite.length; j++) {
				isComposite[i * primes.get(j)] = true;
				if (i % primes.get(j) == 0) {
					break;
				}
			}
		}
		return primes;
	}
}
