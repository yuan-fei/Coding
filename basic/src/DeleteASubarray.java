/**
 * You are given a sequence A1,A2,…,AN . Calculate the number of ways to remove
 * a non-empty contiguous subsequence from it such that the resulting sequence
 * is non-empty and strictly increasing.
 * 
 * Two-pointer
 */
public class DeleteASubarray {
	public static void main(String[] args) {
		System.out.println(solve(new int[] { 1, 2 })); // 2
		System.out.println(solve(new int[] { 1, 2, 3, 5 })); // 9
		System.out.println(solve(new int[] { 1, 1, 2 })); // 4
		System.out.println(solve(new int[] { 2, 4, 3, 5 })); // 7

	}

	private static long solve(int[] a) {
		int n = a.length;
		if (n == 1) {
			return 0;
		}
		int leftEnd = 0;
		while (leftEnd < n - 1 && a[leftEnd] < a[leftEnd + 1]) {
			leftEnd++;
		}
		int rightStart = n - 1;
		while (rightStart > 0 && a[rightStart - 1] < a[rightStart]) {
			rightStart--;
		}

		// overlap
		if (leftEnd == n - 1) {
			return (long) n * (n - 1) / 2 + n - 1;
		}

		// non-overlap
		int j = rightStart;
		long cnt = 0;
		for (int i = 0; i <= leftEnd; i++) {
			while (j < n && a[j] <= a[i]) {
				j++;
			}
			cnt += n - j + 1;
		}
		cnt += n - rightStart;

		return cnt;
	}

	static int sumDigit(int x) {
		int sum = 0;
		while (x != 0) {
			sum += x % 10;
			x /= 10;
		}
		return sum;
	}

}
