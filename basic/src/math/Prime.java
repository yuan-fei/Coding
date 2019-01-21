package math;

import java.util.ArrayList;
import java.util.List;

public class Prime {
	public static void main(String[] args) {
		System.out.println(sieve(100));
		System.out.println(linearSieve(100));
		System.out.println(segmentSieve(1, 100));
	}

	/** O(nloglogn) */
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

	public static List<Integer> segmentSieve(int start, int end) {
		List<Integer> primes = new ArrayList<>();
		int smallPrimesEnd = (int) Math.sqrt(end);
		boolean[] isSmallComposite = new boolean[smallPrimesEnd + 1];
		boolean[] isLargeComposite = new boolean[end - start + 1];
		for (int i = 0; start + i < 2; i++) {
			isLargeComposite[i] = true;
		}
		for (int i = 2; i <= smallPrimesEnd; i++) {
			if (!isSmallComposite[i]) {
				for (int j = i * i; j < smallPrimesEnd; j += i) {
					isSmallComposite[j] = true;
				}
			}
			for (int j = Math.max(i, (start + i - 1) / i) * i; j <= end; j += i) {
				isLargeComposite[j - start] = true;
			}
		}
		for (int i = 0; i < isLargeComposite.length; i++) {
			if (isLargeComposite[i] == false) {
				primes.add(i + start);
			}
		}
		return primes;
	}
}
