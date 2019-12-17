import java.util.Arrays;

public class NextLexicographicPermutation {

	public static void main(String[] args) {
		printAllPermutation(new int[] { 1, 2, 3, 4 });
	}

	public static void printAllPermutation(int[] a) {
		System.out.println(Arrays.toString(a));
		while (permuteNext(a)) {
			System.out.println(Arrays.toString(a));
		}
	}

	/**
	 * 1. Find the longest suffix that is in decreasing order.
	 * 
	 * 2. Swap the preceding element x with the smallest one from the decreasing
	 * sequence larger than x, let's say y
	 * 
	 * 3. Sort the suffix after y in increasing order by reverse it
	 * 
	 * With sortFast the TC is O(n)
	 */
	static boolean permuteNext(int[] a) {
		int n = a.length;
		int i = n - 2;
		while (i >= 0 && a[i] > a[i + 1]) {
			i--;
		}
		if (i < 0) {
			return false;
		}
		int j = i + 1;
		while (j < n && a[j] > a[i]) {
			j++;
		}
		swap(a, i, j - 1);
		sortQuick(a, i + 1);
		return true;
	}

	private static void sort(int[] a, int i) {
		Arrays.sort(a, i, a.length);
	}

	private static void sortQuick(int[] a, int i) {
		int start = i;
		int end = a.length - 1;
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}

	}

	static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
