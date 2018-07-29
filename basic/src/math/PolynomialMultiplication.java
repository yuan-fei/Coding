package math;

import java.util.Arrays;
import java.util.stream.IntStream;

/** Polynomial multiplication/convolution with FFT in O(nlogn) */
public class PolynomialMultiplication {

	public static void main(String[] args) {
		double[] a = { 9, -10, 7, 6 };
		double[] b = { -5, 4, 0, -2 };
		double[] c = multiplyByFFT(a, b);
		System.out.println(Arrays.toString(c));
	}

	/** P_n_a * P_n_b = IFFT_2n(FFT_2n(P_2n_a).FFT_2n(P_2n_b)) */
	public static double[] multiplyByFFT(double[] a, double[] b) {
		int size = Math.max(a.length, b.length);
		int paddedSize = 1;
		while (paddedSize < size) {
			paddedSize <<= 1;
		}
		paddedSize <<= 1;

		a = padding(a, paddedSize);
		b = padding(b, paddedSize);
		Complex[] ca = toComplex(a);
		Complex[] cb = toComplex(b);
		Complex[] ffta = FastFourierTransformation.fft(ca);
		Complex[] fftb = FastFourierTransformation.fft(cb);
		// Complex test = valueAt(ca, Complex.kthRootOfOne(1, ca.length));
		Complex[] fftab = pairwiseProduct(ffta, fftb);
		Complex[] cab = FastFourierTransformation.ifft(fftab);
		return toReal(cab);
	}

	public static Complex valueAt(Complex[] a, Complex c) {
		Complex result = Complex.ofReal(0);
		for (int i = a.length - 1; i >= 0; i--) {
			result = a[i].add(c.multiply(result));
		}
		return result;
	}

	private static Complex[] pairwiseProduct(Complex[] ffta, Complex[] fftb) {
		return IntStream.range(0, ffta.length).mapToObj(i -> ffta[i].multiply(fftb[i])).toArray(Complex[]::new);
	}

	private static Complex[] toComplex(double[] a) {
		return Arrays.stream(a).mapToObj(r -> Complex.ofReal(r)).toArray(Complex[]::new);
	}

	private static double[] toReal(Complex[] c) {
		return Arrays.stream(c).mapToDouble(r -> r.r).toArray();
	}

	private static double[] padding(double[] a, int length) {
		double[] pa = Arrays.copyOf(a, length);
		return pa;
	}

}
