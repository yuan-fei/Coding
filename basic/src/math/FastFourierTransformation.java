package math;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Cooley-Tukey Fast fourier transformation and invert fourier transformation in
 * O(nlogn)
 */
public class FastFourierTransformation {

	public static void main(String[] args) {
		Complex[] a = { Complex.ofReal(-0.03480425839330703), Complex.ofReal(0.07910192950176387),
				Complex.ofReal(0.7233322451735928), Complex.ofReal(0.1659819820667019) };
		System.out.println("a: " + Arrays.toString(a));
		Complex[] y = fft(a);
		System.out.println("fft: " + Arrays.toString(y));
		/*-------------------
		*  0.9336118983487516
		*  -0.7581365035668999 + 0.08688005256493803i
		*  0.44344407521182005
		*  -0.7581365035668999 - 0.08688005256493803i
		-------------------*/
		Complex[] aa = ifft(y);
		System.out.println("ifft: " + Arrays.toString(aa));
	}

	public static Complex[] fft(Complex[] a) {
		Complex[] extendedA = getExtendArray(a);
		Complex[] result = fftExt(extendedA);
		return getShrinkedArray(result, a.length);
	}

	public static Complex[] ifft(Complex[] y) {
		Complex[] extendedY = getExtendArray(y);
		Complex[] result = ifftExt(extendedY);
		return getShrinkedArray(result, y.length);
	}

	private static Complex[] fftExt(Complex[] a) {
		return fftCoreRecursive(a, false);
	}

	private static Complex[] ifftExt(Complex[] y) {
		Complex[] rawA = fftCoreRecursive(y, true);
		for (int i = 0; i < rawA.length; i++) {
			rawA[i] = rawA[i].devide(Complex.ofReal(y.length));
		}
		return rawA;
	}

	private static Complex[] fftCoreRecursive(Complex[] a, boolean isInversion) {
		int n = a.length;
		if (n == 1) {
			return a;
		}
		Complex[] even = IntStream.range(0, n).filter(i -> i % 2 == 0).mapToObj(j -> a[j]).toArray(Complex[]::new);
		Complex[] odd = IntStream.range(0, n).filter(i -> i % 2 == 1).mapToObj(j -> a[j]).toArray(Complex[]::new);
		Complex[] y0 = fftCoreRecursive(even, isInversion);
		Complex[] y1 = fftCoreRecursive(odd, isInversion);
		Complex[] y = new Complex[n];
		for (int i = 0; i < n / 2; i++) {
			int kth = isInversion ? -i : i;
			Complex root = Complex.kthRootOfOne(kth, n);
			Complex t = root.multiply(y1[i]);
			y[i] = y0[i].add(t);
			y[i + n / 2] = y0[i].subtract(t);
		}
		return y;
	}

	private static Complex[] getExtendArray(Complex[] c) {
		int size = 1;
		while (size < c.length) {
			size <<= 1;
		}
		if (size == c.length) {
			return c;
		}
		Complex[] e = Arrays.copyOf(c, size);
		Arrays.fill(e, c.length, size, Complex.ofReal(0));
		return e;
	}

	private static Complex[] getShrinkedArray(Complex[] c, int originalSize) {
		return Arrays.copyOf(c, originalSize);
	}

}
