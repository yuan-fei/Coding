package math;

/** https://cp-algorithms.com/algebra/gray-code.html */
public class GrayCode {

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			System.out.println(g(i));
			System.out.println(reverse(g(i)));
		}
		generateSequenceStartAt(5, 3);
	}

	/** return the nth Gray code */
	static int g(int n) {
		return n ^ (n >> 1);
	}

	/** return the number corresponding to the Gray code */
	static int reverse(int c) {
		int n = 0;
		while (c != 0) {
			n ^= c;
			c >>= 1;
		}
		return n;
	}

	static void generateSequenceStartAt(int start, int n) {
		for (int i = 0; i < (1 << n); i++) {
			System.out.println(start ^ i ^ (i >> 1));
		}
	}
}
