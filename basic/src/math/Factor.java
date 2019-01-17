package math;

import java.util.ArrayList;
import java.util.List;

public class Factor {

	public static void main(String[] args) {
		System.out.println(findAllFactors(32));

	}

	/** Find all factors of a number in O(n^0.5) */
	public static List<Integer> findAllFactors(int n) {
		List<Integer> factors = new ArrayList<>();
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				factors.add(i);
				factors.add(n / i);
			}
		}
		return factors;
	}

}
