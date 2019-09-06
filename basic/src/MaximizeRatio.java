import java.util.Arrays;

/** find a subset of k items that maximize the ratio sum(v_i)/sum(w_i) */
public class MaximizeRatio {

	public static void main(String[] args) {
		int[] v = { 2, 3, 1 };
		int[] w = { 2, 5, 2 };
		int n = 3;
		int k = 2;
		double r = findMaxAverage(n, k, w, v);
		System.out.println(r);
	}

	public static double findMaxAverage(int n, int k, int[] w, int[] v) {
		double low = 0.0;
		double high = 0.0;

		for (int i = 0; i < n; i++) {
			high = Math.max(1.0d * v[i] / w[i], high);
		}
		for (int i = 0; i < 100; i++) {
			double mid = low + (high - low) / 2;
			if (feasible(n, k, w, v, mid)) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}

	private static boolean feasible(int n, int k, int[] w, int[] v, double r) {
		double[] cost = new double[n];
		for (int i = 0; i < cost.length; i++) {
			cost[i] = v[i] - r * w[i];
		}
		Arrays.sort(cost);
		int c = Math.min(n, k);
		double total = 0.0;
		for (int i = 0; i < c; i++) {
			total += cost[n - i - 1];
		}
		return total >= 0;
	}

}
