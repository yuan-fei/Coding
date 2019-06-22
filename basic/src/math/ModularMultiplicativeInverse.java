package math;

public class ModularMultiplicativeInverse {

	public static void main(String[] args) {
		System.out.println(getModularMultiplicativeInversePrimeModulo(5, 11));
		System.out.println(getModularMultiplicativeInverseFast(5, 11));
		System.out.println(getModularMultiplicativeInverse(5, 11));
		System.out.println(getModularMultiplicativeInversePrimeModulo(9, 10)); // incorrect! p is not prime
		System.out.println(getModularMultiplicativeInverseFast(9, 10));
		System.out.println(getModularMultiplicativeInverse(9, 10));

	}

	/** Per Fermat theorem: Note p must be prime. 1/n = n^(p-2) mod p */
	public static long getModularMultiplicativeInversePrimeModulo(int n, long p) {
		long r = FastPower.modularExp(n, p - 2, p);
		return r;
	}

	/** Note modulo p must be prime. inv(n) = (inv(p%n) * (p - p/n)) % n */
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
}
