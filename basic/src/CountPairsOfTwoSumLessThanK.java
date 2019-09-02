import java.util.Arrays;

/**
 * Given array of numbers, count pair of sum less than K
 * 
 * Two-pointer
 */
public class CountPairsOfTwoSumLessThanK {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8 };// 16
		System.out.println(count(arr, 10));
		System.out.println(count(arr, 10));
		arr = new int[] { 1 };// 0
		System.out.println(count(arr, 10));
		System.out.println(count(arr, 10));
		arr = new int[] { 0, 1, 2, 2, 3 };// 16
		System.out.println(count(arr, 6));
		System.out.println(count(arr, 6));
	}

	public static int count(int[] a, int K) {
		Arrays.sort(a);
		int cnt = 0;
		int right = a.length - 1;
		for (int i = 0; i < a.length; i++) {
			while (right >= 0 && a[i] + a[right] >= K) {
				right--;
			}
			// don not count 'self' in
			cnt += right + (right < i ? 1 : 0);
		}
		return cnt / 2;
	}

	public static int count1(int[] a, int K) {
		Arrays.sort(a);
		int cnt = 0;
		int right = a.length - 1;
		int left = 0;
		while (left <= right) {
			while (left <= right && a[left] + a[right] >= K) {
				right--;
			}
			if (left <= right) {
				cnt += right;
			}
		}
		return cnt;
	}
}
