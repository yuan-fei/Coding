package sorting;

import java.util.Arrays;

/** O(k+n) stable sorting */
public class CountingSort implements ISorting {

	public static void main(String[] args) {
		int[] nums = new int[] { 3, 2, 5, 4, 7, 9, 7 };
		CountingSort q = new CountingSort(16);
		q.sort(nums);
		print(nums);
		nums = new int[] { 1, 2, 3, 4, 7, 9 };
		q.sort(nums);
		print(nums);
		nums = new int[0];
		q.sort(nums);
		print(nums);
		nums = new int[] { 1 };
		q.sort(nums);
		print(nums);
	}

	int k;

	public CountingSort(int k) {
		this.k = k;
	}

	public static void print(int[] nums) {
		System.out.println(Arrays.toString(nums));
	}

	@Override
	public void sort(int[] a) {
		int[] counter = new int[k];
		for (int i = 0; i < a.length; i++) {
			counter[a[i]]++;
		}
		for (int i = 0; i < counter.length - 1; i++) {
			counter[i + 1] += counter[i];
		}
		int[] b = new int[a.length];
		for (int i = a.length - 1; i >= 0; i--) {
			b[counter[a[i]] - 1] = a[i];
			counter[a[i]]--;
		}
		System.arraycopy(b, 0, a, 0, a.length);
	}

	public void sortWithSatellites(int[] a, int[] s) {
		int[] counter = new int[k];
		for (int i = 0; i < a.length; i++) {
			counter[a[i]]++;
		}
		for (int i = 0; i < counter.length - 1; i++) {
			counter[i + 1] += counter[i];
		}
		int[] t = new int[s.length];
		for (int i = a.length - 1; i >= 0; i--) {
			t[counter[a[i]] - 1] = s[i];
			counter[a[i]]--;
		}
		System.arraycopy(t, 0, s, 0, s.length);
	}
}
