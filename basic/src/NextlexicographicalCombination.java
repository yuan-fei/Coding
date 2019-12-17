import java.util.Arrays;

/** https://cp-algorithms.com/combinatorics/generating_combinations.html */
public class NextlexicographicalCombination {

	public static void main(String[] args) {
		int n = 5;
		int k = 3;
		while (nextCombination(n, k)) {
			System.out.println(Arrays.toString(a));
		}

	}

	static int[] a;

	static boolean nextCombination(int n, int k) {
		if (a == null) {
			a = new int[k];
			for (int i = 1; i <= k; i++) {
				a[i - 1] = i;
			}
			return true;
		} else {
			for (int i = k - 1; i >= 0; i--) {
				if (a[i] < n - k + i + 1) {
					a[i]++;
					for (int j = i + 1; j < k; j++) {
						a[j] = a[j - 1] + 1;
					}
					return true;
				}

			}
			return false;
		}
	}

}
