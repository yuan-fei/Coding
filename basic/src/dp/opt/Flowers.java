package dp.opt;

/**
 * https://atcoder.jp/contests/dp/tasks/dp_q
 * 
 * Problem Statement
 * 
 * There are N flowers arranged in a row. For each i (1≤i≤N), the height and the
 * beauty of the i-th flower from the left is hi and ai, respectively. Here,
 * h1,h2,…,hN are all distinct.
 * 
 * Taro is pulling out some flowers so that the following condition is met: The
 * heights of the remaining flowers are monotonically increasing from left to
 * right.
 * 
 * Find the maximum possible sum of the beauties of the remaining flowers.
 */
public class Flowers {
	public static void main(String[] args) {
		int n = 4;
		int[] h = { 3, 1, 4, 2 };
		int[] a = { 10, 20, 30, 40 };
		long r = solve(n, h, a);
		System.out.println(r); // 60
	}

	static int N = 200005;
	static long[] bit = new long[N + 1];

	static void upd(int i, long v) {
		while (i <= N) {
			bit[i] = Math.max(bit[i], v);
			i += i & -i;
		}
	}

	static long max(int i) {
		long r = 0;
		while (i != 0) {
			r = Math.max(r, bit[i]);
			i -= i & -i;
		}
		return r;
	}

	private static long solve(int n, int[] h, int[] a) {
		for (int i = 0; i < n; i++) {
			upd(h[i], max(h[i]) + a[i]);
		}
		return max(N);
	}

}
