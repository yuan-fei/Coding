package math;

/**
 * inclusion-exclusion principle
 * 
 * see dp.backpack.IntegerComposition for DP solution
 */
public class IntegerComposition {

	public static void main(String[] args) {
		System.out.println(positiveComposition(2, 2)); // 1
		System.out.println(nonNegativeComposition(2, 2)); // 3
		System.out.println(nonNegativeExcUpperboundComposition(2, 2, 1)); // 0
		System.out.println(nonNegativeIncUpperboundComposition(2, 2, 1)); // 1
		System.out.println(nonNegativeExcUpperboundComposition(3, 2, 1)); // 0
		System.out.println(nonNegativeIncUpperboundComposition(3, 2, 1)); // 3
		System.out.println(nonNegativeIncUpperboundComposition(3, 4, 2)); // 6
	}

	/** a1+...an=k, 0<ai */
	public static long positiveComposition(int n, int k) {
		return C(k - 1, n - 1);
	}

	/** a1+...an=k, 0<=ai */
	public static long nonNegativeComposition(int n, int k) {
		return C(k + n - 1, n - 1);
	}

	/** a1+...an=k, 0<=ai<ub */
	public static long nonNegativeExcUpperboundComposition(int n, int k, int ub) {
		int maxCnt = k / ub;
		long r = 0;
		long sign = 1;
		for (int i = 0; i <= maxCnt; i++) {
			long t = nonNegativeComposition(n, k - i * ub);
			r += sign * C(n, i) * t;
			sign *= -1;
		}
		return r;
	}

	/** a1+...an=k, 0<=ai<=ub */
	public static long nonNegativeIncUpperboundComposition(int n, int k, int ub) {
		int maxCnt = k / ub;
		long r = 0;
		long sign = 1;
		for (int i = 0; i <= maxCnt; i++) {
			long t = nonNegativeComposition(n, k - i * (ub + 1));
			r += sign * C(n, i) * t;
			sign *= -1;
		}
		return r;
	}

	public static long C(int n, int m) {
		if (m > n || m < 0) {
			return 0;
		} else {
			if (m > n / 2) {
				m = n - m;
			}
			long r = 1;
			for (int i = 0; i < m; i++) {
				r = r * (n - i);
			}
			for (int i = 2; i <= m; i++) {
				r = r / i;
			}
			return r;
		}
	}

}
