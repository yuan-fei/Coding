package sorting;

import java.util.Arrays;

public class RadixSort implements ISorting {

	public static void main(String[] args) {
		int[] nums = new int[] { 310, 229, 598, 401, 747, 955, 78 };
		RadixSort q = new RadixSort(3);
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

	public static void print(int[] nums) {
		System.out.println(Arrays.toString(nums));
	}

	private int k;

	public RadixSort(int k) {
		this.k = k;
	}

	@Override
	public void sort(int[] a) {
		CountingSort c = new CountingSort(10);
		for (int i = 0; i < k; i++) {
			int[] digits = getArrayOfDigit(a, i);
			c.sortWithSatellites(digits, a);
		}
	}

	private int[] getArrayOfDigit(int[] a, int i) {
		return Arrays.stream(a).mapToObj(n -> getDigit(n, i)).mapToInt(Integer::intValue).toArray();
	}

	private int getDigit(int n, int i) {
		int d = 0;
		while (d < i) {
			n /= 10;
			d++;
		}
		return n % 10;
	}

}
