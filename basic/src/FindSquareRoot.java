
public class FindSquareRoot {

	public static void main(String[] args) {
		double eps = 1e-15;
		System.out.println(sqrt(1, eps));
		System.out.println(sqrt(2, eps));
		System.out.println(sqrt(3, eps));
		System.out.println(sqrt(4, eps));
		System.out.println(sqrt(9, eps));
	}

	public static double sqrt(int x, double eps) {
		double low = 0.0;
		double high = x;
		while (high - low > eps) {
			double mid = low + (high - low) / 2;
			if (mid * mid <= x) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return low;
	}
}
