package math;

public class ModularCombination {

	public static void main(String[] args) {
		System.out.println(combination(5, 2, 1000000007));
		System.out.println(combinationFast(5, 2, 1000000007));
		System.out.println(combination(100, 51, 1000000007));
		System.out.println(combinationFast(100, 51, 1000000007));

	}

	public static long combination(int n, int m, long mod) {
		if (m > n || m < 0) {
			return 0;
		} else {
			if (m > n / 2) {
				m = n - m;
			}
			long r = 1;
			for (int i = 2; i <= m; i++) {
				r = (r * getModularMultiplicativeInverse(i, mod)) % mod;
			}
			for (int i = 0; i < m; i++) {
				r = (r * (n - i)) % mod;
			}
			return r;
		}
	}

	public static long combinationFast(int n, int m, long mod) {
		if (m > n || m < 0) {
			return 0;
		} else {
			if (m > n / 2) {
				m = n - m;
			}
			long[] iFact = getInverseFact(n, mod);
			long r = (getFact(n, mod) * ((iFact[m] * iFact[n - m]) % mod)) % mod;
			return r;
		}
	}

	/** Note x and n must be coprime. 1/n = n^(p-2) mod p */
	private static long getModularMultiplicativeInverse(int n, long p) {
		long r = FastPower.modularExp(n, p - 2, p);
		return r;
	}

	/** inv(n) = (inv(p%n) * (p - p/n)) % n */
	private static long getModularMultiplicativeInverseFast(int n, long p) {
		long[] state = new long[n + 1];
		state[0] = 1;
		state[1] = 1;
		for (int i = 2; i <= n; i++) {
			long t = (state[(int) (p % i)] * (p - p / i));
			state[i] = t % p;
		}
		return state[n];
	}

	private static long[] getInverseFact(int n, long p) {
		long[] state = new long[n + 1];
		long[] iFact = new long[n + 1];
		state[0] = 1;
		state[1] = 1;
		iFact[0] = 1;
		iFact[1] = 1;
		for (int i = 2; i <= n; i++) {
			long t = (state[(int) (p % i)] * (p - p / i));
			state[i] = t % p;
			iFact[i] = (iFact[i - 1] * state[i]) % p;
		}
		return iFact;
	}

	private static long getFact(int n, long p) {
		long r = 1;
		for (int i = 1; i <= n; i++) {
			r = (r * i) % p;
		}
		return r;
	}
}
