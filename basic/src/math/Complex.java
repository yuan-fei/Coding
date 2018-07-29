package math;

public class Complex {
	public double r;
	public double i;

	public Complex(double r, double i) {
		this.r = r;
		this.i = i;
	}

	public static Complex ofReal(double r) {
		return new Complex(r, 0);
	}

	public static Complex kthRootOfOne(int kth, int n) {
		double theta = kth * 2 * Math.PI / n;
		return new Complex(Math.cos(theta), Math.sin(theta));
	}

	@Override
	public String toString() {
		if (i == 0) {
			return "" + r;
		} else if (r == 0) {
			return i + "i";
		}
		return "" + r + "+" + i + "i";
	}

	public Complex add(Complex c) {
		return new Complex(this.r + c.r, this.i + c.i);
	}

	public Complex subtract(Complex c) {
		return new Complex(this.r - c.r, this.i - c.i);
	}

	public Complex multiply(Complex c) {
		return new Complex(this.r * c.r - this.i * c.i, this.r * c.i + this.i * c.r);
	}

	public Complex reciprocal() {
		double denominator = Math.pow(this.r, 2) + Math.pow(this.i, 2);
		return new Complex(this.r / denominator, this.i / denominator);
	}

	public Complex devide(Complex c) {
		return multiply(c.reciprocal());
	}

}
