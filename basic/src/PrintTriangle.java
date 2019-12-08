
public class PrintTriangle {

	public static void main(String[] args) {
		print(1);
		System.out.println();
		print(2);
		System.out.println();
		print(3);
		System.out.println();
		print(4);
		System.out.println();
		print(5);
	}

	static char[][] output;

	public static void print(int n) {
		int height = getHeight(n);
		output = new char[height][height * 2];
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output[i].length; j++) {
				output[i][j] = ' ';
			}
		}
		print(n, 0, height - 1);
		for (int i = 0; i < output.length; i++) {
			System.out.println(String.valueOf(output[i]));
		}
	}

	private static void print(int n, int r, int c) {
		if (n == 1) {
			output[r][c] = '/';
			output[r][c + 1] = '\\';
			output[r + 1][c - 1] = '/';
			output[r + 1][c] = '_';
			output[r + 1][c + 1] = '_';
			output[r + 1][c + 2] = '\\';
		} else {
			int height = getHeight(n - 1);
			print(n - 1, r, c);
			print(n - 1, r + height, c - height);
			print(n - 1, r + height, c + height);
		}

	}

	private static int getHeight(int n) {
		return 1 << n;
	}
}
