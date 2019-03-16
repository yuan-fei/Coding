package dp;

/**
 * https://atcoder.jp/contests/dp/tasks/dp_l
 * 
 * Problem Statement:
 * 
 * Taro and Jiro will play the following game against each other.
 * 
 * Initially, they are given a sequence a=(a1,a2,…,aN).
 * 
 * Until a becomes empty, the two players perform the following operation
 * alternately, starting from Taro:
 * 
 * Remove the element at the beginning or the end of a. The player earns x
 * points, where x is the removed element.
 * 
 * Let X and Y be Taro's and Jiro's total score at the end of the game,
 * respectively.
 * 
 * Taro tries to maximize X−Y, while Jiro tries to minimize X−Y.
 * 
 * Assuming that the two players play optimally, find the resulting value of
 * X−Y.
 * 
 */
public class Deque {

	public static void main(String[] args) {
		int[] a = new int[] { 4, 2, 9, 7, 1, 5 };
		System.out.println(maxDiff(a));// 2
		System.out.println(maxFirst(a));
		a = new int[] { 10, 80, 90, 30 };
		System.out.println(maxDiff(a)); // 10
		System.out.println(maxFirst(a));

	}

	private static long maxDiff(int[] a) {
		int n = a.length;
		long[] max = new long[n + 1];
		long[] min = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				if ((n - i) % 2 == 0) {
					max[j] = Math.max(min[j + 1] + a[j], min[j] + a[j + i - 1]);
				} else {
					min[j] = Math.min(max[j + 1] - a[j], max[j] - a[j + i - 1]);
				}
			}
		}
		return max[0];
	}

	private static long maxFirst(int[] a) {
		int n = a.length;
		long[] max = new long[n + 1];
		long[] prefixSum = new long[n];

		for (int i = 0; i < a.length; i++) {
			prefixSum[i] = a[i] + ((i > 0) ? prefixSum[i - 1] : 0);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				long rSum = prefixSum[i + j - 1] - ((j > 0) ? prefixSum[j - 1] : 0);
				max[j] = Math.max(rSum - max[j + 1], rSum - max[j]);
			}
		}
		return max[0];
	}

}
