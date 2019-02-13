
public class PartitionSumToK {

	public static void main(String[] args) {
		System.out.println(positivePartition(2, 2)); // 1
		System.out.println(nonNegativePartition(2, 2)); // 3
		System.out.println(nonNegativeExcUpperboundPartition(2, 2, 1)); // 0
		System.out.println(nonNegativeIncUpperboundPartition(2, 2, 1)); // 1
	}

	/** a1+...an=k, 0<ai */
	public static long positivePartition(int n, int k) {
		return C(k - 1, n - 1);
	}

	/** a1+...an=k, 0<=ai */
	public static long nonNegativePartition(int n, int k) {
		return C(k + n - 1, n - 1);
	}

	/** a1+...an=k, 0<=ai<ub */
	public static long nonNegativeExcUpperboundPartition(int n, int k, int ub) {
		int maxCnt = k / ub;
		long r = 0;
		long sign = 1;
		for (int i = 0; i <= maxCnt; i++) {
			long t = nonNegativePartition(n, k - i * ub);
			r += sign * C(n, i) * t;
			sign *= -1;
		}
		return r;
	}

	/** a1+...an=k, 0<=ai<=ub */
	public static long nonNegativeIncUpperboundPartition(int n, int k, int ub) {
		int maxCnt = k / ub;
		long r = 0;
		long sign = 1;
		for (int i = 0; i <= maxCnt; i++) {
			long t = positivePartition(n, k - i * ub);
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
