import java.util.Arrays;

/**
 * refer to http://www.cnblogs.com/codingmylife/archive/2010/12/24/1915728.html
 */
public class MagicSquare {

	public static void main(String[] args) {

		print(solve(3));
		print(solve(4));
		print(solve(5));
		print(solve(6));
		print(solve(8));
		print(solve(9));
		print(solve(10));
	}

	static void print(int[][] square) {
		for (int i = 0; i < square.length; i++) {
			System.out.println(Arrays.toString(square[i]));
		}
	}

	public static int[][] solve(int n) {
		if (n < 3) {
			return null;
		} else if (n % 2 == 1) {
			return solveOdd(n);
		} else if (n % 4 == 0) {
			return solveDoublyEven(n);
		} else {
			return solveSinglyEven(n);
		}
	}

	// n=2m+1
	public static int[][] solveOdd(int n) {
		return solveOdd(n, 0);
	}

	private static int[][] solveOdd(int n, int offset) {
		int[][] s = new int[n][n];
		int row = 0;
		int col = n / 2;
		s[row][col] = 1 + offset;
		for (int i = 2; i <= n * n; i++) {
			if (s[(n + row - 1) % n][(col + 1) % n] == 0) {
				row = (n + row - 1) % n;
				col = (col + 1) % n;
			} else {
				row++;
			}
			s[row][col] = i + offset;
		}
		return s;
	}

	// n=4*m
	public static int[][] solveDoublyEven(int n) {
		int[][] s = new int[n][n];
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s.length; j++) {
				s[i][j] = i * n + j + 1;
			}
		}
		// divided into blocks of 4*4
		for (int i = 0; i < s.length / 2; i++) {
			for (int j = 0; j < s.length; j++) {
				if ((i - j) % 4 == 0 || (i + j + 1) % 4 == 0) {
					// not on the diagonal
					swap(s, i, j, n - i - 1, n - j - 1);
				}
			}
		}
		return s;
	}

	// n=4*m+2
	public static int[][] solveSinglyEven(int n) {
		int m = (n - 2) / 4;
		int nBlock = n / 2;
		int[][] A = solveOdd(nBlock, 0);
		int[][] B = solveOdd(nBlock, 2 * nBlock * nBlock);
		int[][] C = solveOdd(nBlock, 3 * nBlock * nBlock);
		int[][] D = solveOdd(nBlock, nBlock * nBlock);
		for (int i = 0; i < A.length; i++) {
			int offset = 0;
			if (i == nBlock / 2) {
				offset = i;
			}
			for (int j = 0; j < m; j++) {
				swap(A, i, offset + j, C, i, offset + j);
			}
		}
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < m - 1; j++) {
				swap(B, i, nBlock / 2 - j, D, i, nBlock / 2 - j);
			}
		}
		int[][] s = merge(n, A, B, C, D);
		return s;
	}

	private static int[][] merge(int n, int[][] a, int[][] b, int[][] c, int[][] d) {
		int[][] s = new int[n][n];
		fill(s, a, 0, 0);
		fill(s, b, 0, n / 2);
		fill(s, c, n / 2, 0);
		fill(s, d, n / 2, n / 2);
		return s;
	}

	private static void fill(int[][] s, int[][] a, int x, int y) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.arraycopy(a[i], 0, s[x + i], y, a.length);
			}
		}
	}

	private static void swap(int[][] a, int i, int j, int k, int l) {
		int tmp = a[i][j];
		a[i][j] = a[k][l];
		a[k][l] = tmp;
	}

	private static void swap(int[][] a, int i, int j, int[][] b, int k, int l) {
		int tmp = a[i][j];
		a[i][j] = b[k][l];
		b[k][l] = tmp;
	}
}
