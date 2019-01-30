package math;

public class ModularCombination {

	public static void main(String[] args) {
		System.out.println(combinationPrimeModulo(5, 2, 1000000007));
		System.out.println(combinationFast(5, 2, 1000000007));
		System.out.println(combination(5, 2, 1000000007));
		System.out.println(combinationPrimeModulo(100, 51, 1000000007));
		System.out.println(combinationFast(100, 51, 1000000007));
		System.out.println(combination(100, 51, 1000000007));
		System.out.println(getModularMultiplicativeInversePrimeModulo(5, 11));
		System.out.println(getModularMultiplicativeInverseFast(5, 11));
		System.out.println(getModularMultiplicativeInverse(5, 11));
		System.out.println(getModularMultiplicativeInversePrimeModulo(9, 10)); // incorrect! p is not prime
		System.out.println(getModularMultiplicativeInverseFast(9, 10));
		System.out.println(getModularMultiplicativeInverse(9, 10));
	}

	public static long combinationPrimeModulo(int n, int m, long mod) {
		if (m > n || m < 0) {
			return 0;
		} else {
			if (m > n / 2) {
				m = n - m;
			}
			long r = 1;
			for (int i = 2; i <= m; i++) {
				r = (r * getModularMultiplicativeInversePrimeModulo(i, mod)) % mod;
			}
			for (int i = 0; i < m; i++) {
				r = (r * (n - i)) % mod;
			}
			return r;
		}
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

	/** Per Fermat theorem: Note p must be prime. 1/n = n^(p-2) mod p */
	public static long getModularMultiplicativeInversePrimeModulo(int n, long p) {
		long r = FastPower.modularExp(n, p - 2, p);
		return r;
	}

	/** inv(n) = (inv(p%n) * (p - p/n)) % n */
	public static long getModularMultiplicativeInverseFast(int n, long p) {
		long[] state = new long[n + 1];
		state[0] = 1;
		state[1] = 1;
		for (int i = 2; i <= n; i++) {
			long t = (state[(int) (p % i)] * (p - p / i));
			state[i] = t % p;
		}
		return state[n];
	}

	/** Per Euler's theorem: 1/n = n^(phi(p)-1) mod p */
	public static long getModularMultiplicativeInverse(int n, long a) {
		long r = FastPower.modularExp(n, EulerTotientFunction.phi(a) - 1, a);
		return r;
	}

	public static long[] getInverseFact(int n, long p) {
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

	public static long getFact(int n, long p) {
		long r = 1;
		for (int i = 1; i <= n; i++) {
			r = (r * i) % p;
		}
		return r;
	}
}
