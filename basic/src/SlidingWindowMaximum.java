import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.TreeMap;

public class SlidingWindowMaximum {

	public static void main(String[] args) {
		int arr[] = { 12, 1, 78, 90, 57, 89, 56, 89, 57 };
		int k = 3;
		System.out.println(Arrays.toString(getWindowMaxWithBST(arr, k)));
		System.out.println(Arrays.toString(getWindowMaxWithDeque(arr, k)));
	}

	/** O(nlogk) */
	static int[] getWindowMaxWithBST(int[] a, int k) {
		if (k > a.length) {
			return new int[0];
		}
		int n = a.length;
		TreeMap<Integer, Integer> window = new TreeMap<>();
		int[] ret = new int[n - k + 1];
		int i = 0;
		for (; i < k; i++) {
			int cnt = 0;
			if (window.containsKey(a[i])) {
				cnt = window.get(a[i]);
			}
			window.put(a[i], cnt + 1);
		}
		ret[0] = window.lastKey();
		for (; i < n; i++) {
			int cnt = window.get(a[i - k]);
			if (--cnt == 0) {
				window.remove(a[i - k]);
			} else {
				window.put(a[i - k], cnt);
			}
			cnt = 0;
			if (window.containsKey(a[i])) {
				cnt = window.get(a[i]);
			}
			window.put(a[i], cnt + 1);
			ret[i - k + 1] = window.lastKey();
		}
		return ret;
	}

	/** O(n): keep a decreasing queue */
	static int[] getWindowMaxWithDeque(int[] a, int k) {

		if (k > a.length) {
			return new int[0];
		}
		int n = a.length;
		Deque<Integer> window = new ArrayDeque<Integer>();
		int[] ret = new int[n - k + 1];
		int i = 0;
		for (; i < k; i++) {
			while (!window.isEmpty() && a[window.peekLast()] < a[i]) {
				window.pollLast();
			}
			window.offer(i);
		}
		ret[0] = a[window.peekFirst()];
		for (; i < n; i++) {
			if (window.peekFirst() <= i - k) {
				window.pollFirst();
			}
			while (!window.isEmpty() && a[window.peekLast()] < a[i]) {
				window.pollLast();
			}
			window.offer(i);
			ret[i - k + 1] = a[window.peekFirst()];
		}
		return ret;
	}
}
