package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fraction implements Comparable<Fraction> {

	public static void main(String[] args) {
		System.out.println(new Fraction(6, 10));
		System.out.println(new Fraction(6, 10).add(new Fraction(2, 6)));
		System.out.println(new Fraction(6, 10).mul(new Fraction(2, 6)));
		System.out.println(Arrays.toString(new Fraction(649, 200).getContinuedFractionForm()));
		System.out.println(Fraction.fromContinuedFractionForm(new Fraction(9, 4).getContinuedFractionForm()[0]));
		System.out.println(Fraction.fromContinuedFractionForm(new Fraction(649, 200).getContinuedFractionForm()[0]));
		System.out.println(Fraction.fromContinuedFractionForm(new Fraction(649, 200).getContinuedFractionForm()[1]));
		Fraction f1 = Fraction.fromContinuedFractionForm(Arrays.asList(3L, 7L, 15L, 2L, 7L, 1L, 4L, 2L));
		Fraction f2 = Fraction.fromContinuedFractionForm(Arrays.asList(3L, 7L, 16L, 1L, 3L, 4L, 2L, 4L));
		System.out.println(Fraction.getBestFraction(f1, f2));
		System.out.println(Fraction.getBestFraction(new Fraction(1, 4), new Fraction(3, 5)));
		System.out.println(Fraction.getBestFraction(new Fraction(1, 4), new Fraction(1, 2)));
	}

	long num, den;

	public Fraction(long n, long d) {
		num = n;
		den = d;
		if (den < 0) {
			num *= -1;
			den *= -1;
		}
		reduce();
	}

	public int compareTo(Fraction f) {
		return Long.compare(num * f.den, den * f.num);
	}

	private void reduce() {
		long g = gcd(Math.abs(num), den);
		num /= g;
		den /= g;
	}

	public Fraction add(Fraction f) {
		long gcd = gcd(den, f.den);
		long gcm = gcm(den, f.den);
		return new Fraction(f.den / gcd * num + den / gcd * f.num, gcm);
	}

	public Fraction mul(Fraction f) {
		return new Fraction(num * f.num, den * f.den);
	}

	public Fraction reciprocals() {
		return new Fraction(den, num);
	}

	private static long gcd(long a, long b) {
		if (a == 0) {
			return b;
		} else {
			return gcd(b % a, a);
		}
	}

	private static long gcm(long a, long b) {
		long gcd = gcd(a, b);
		return a / gcd * b;
	}

	public String toString() {
		return num + " " + den;
	}

	public List<Long>[] getContinuedFractionForm() {
		List<Long>[] res = new List[2];
		List<Long> shortForm = new ArrayList<>();
		getContinuedFractionForm(shortForm, this);
		res[0] = shortForm;
		List<Long> longForm = new ArrayList<>(shortForm);
		longForm.set(shortForm.size() - 1, longForm.get(shortForm.size() - 1) - 1);
		longForm.add(1L);
		res[1] = longForm;
		return res;
	}

	private static void getContinuedFractionForm(List<Long> result, Fraction f) {
		if (f.den == 0) {
			return;
		}
		result.add(f.num / f.den);
		getContinuedFractionForm(result, new Fraction(f.den, f.num % f.den));
	}

	public static Fraction fromContinuedFractionForm(List<Long> cf) {
		return fromContinuedFractionForm(cf, 0);
	}

	private static Fraction fromContinuedFractionForm(List<Long> cf, int i) {
		if (i == cf.size() - 1) {
			return new Fraction(cf.get(i), 1);
		}
		Fraction f = fromContinuedFractionForm(cf, i + 1);
		return new Fraction(cf.get(i), 1).add(f.reciprocals());
	}

	/**
	 * https://en.wikipedia.org/wiki/Continued_fraction#Best_rational_within_an_interval
	 * 
	 * find fraction f such that f1<f<f2, and f has the smallest numerator and a
	 * smallest denominator (see Stern-Brocot Tree and Farey sequence for detail)
	 */
	public static Fraction getBestFraction(Fraction f1, Fraction f2) {
		for (List<Long> cf1 : f1.getContinuedFractionForm()) {
			for (List<Long> cf2 : f2.getContinuedFractionForm()) {
				Fraction f = getBestFraction(cf1, cf2);
				if (f1.compareTo(f) < 0 && f.compareTo(f2) < 0) {
					return f;
				}
			}
		}
		return null;
	}

	public static Fraction getBestFraction(List<Long> cf1, List<Long> cf2) {
		List<Long> cf = new ArrayList<>();
		int i = 0;
		while (i < Math.min(cf1.size(), cf2.size()) && cf1.get(i) == cf2.get(i)) {
			cf.add(cf1.get(i));
			i++;
		}
		if (i < Math.min(cf1.size(), cf2.size())) {
			cf.add(Math.min(cf1.get(i), cf2.get(i)) + 1);
		} else if (i < cf1.size()) {
			cf.add(cf1.get(i) + 1);
		} else {
			cf.add(cf2.get(i) + 1);
		}
		return Fraction.fromContinuedFractionForm(cf);
	}
}
