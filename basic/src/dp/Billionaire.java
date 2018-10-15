package dp;

/**
 * GCJ APAC 2008 local onsite C
 * (https://code.google.com/codejam/contest/32005/dashboard#s=p2&a=2)
 * 
 * Given initial capital x, each round you have probability p to win and get
 * your wager doubled (you can bet any value in [0, current wager]). What is the
 * probability of has 1000000 in total after M rounds?
 */
public class Billionaire {

	public static void main(String[] args) {
		System.out.println(solve(500000d, 0.5d, 1)); // 0.5
		System.out.println(solve(600000d, 0.75d, 3)); // 0.843750
	}

	/** discrete the */
	public static double solve(double x, double p, int M) {
		int n = 1 << M;
		double[][] prob = new double[M + 1][n + 1];
		prob[M][n] = 1;
		for (int i = M - 1; i >= 0; i--) {
			for (int j = 0; j <= n; j++) {
				int maxBet = Math.min(j, n - j);
				double d = 0;
				for (int k = 0; k <= maxBet; k++) {
					d = Math.max(d, p * prob[i + 1][j + k] + (1 - p) * prob[i + 1][j - k]);
				}
				prob[i][j] = d;
			}
		}
		int inititalCapital = (int) (x * n / 1000000);
		return prob[0][inititalCapital];
	}

}
