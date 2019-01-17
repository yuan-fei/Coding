package math;

public class Fibbonacci {
	public static void main(String[] args) {
		System.out.println(fastFib(0));
		System.out.println(fastFib(1));
		System.out.println(fastFib(2));
		System.out.println(fastFib(3));
		System.out.println(fastFib(4));
		System.out.println(fastFib(5));
	}

	public static long fastFib(int i) {
		long[][] a = new long[][] { new long[] { 1, 1 }, new long[] { 1, 0 } };
		long[][] r = FastPower.fastMatrixExp(a, i);
		// r * [[1], [0]]
		return r[1][0];
	}
}
