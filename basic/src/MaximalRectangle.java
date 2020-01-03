import java.util.Stack;

/** n*m matrix, O(n) space, O(n*m) time with stack trick */
public class MaximalRectangle {

	public static void main(String[] args) {
		char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '0' } };
		System.out.println(maximalRectangle(matrix)); // 6
	}

	public static int maximalRectangle(char[][] matrix) {
		int r = matrix.length;
		if (r == 0) {
			return 0;
		}
		int c = matrix[0].length;

		int[] h = new int[c];
		int max = 0;
		for (int i = 0; i < r; i++) {
			// update h
			for (int j = 0; j < c; j++) {
				if (matrix[i][j] == '1') {
					h[j]++;
				} else {
					h[j] = 0;
				}
			}
			max = Math.max(max, maxRectangleInHistgram(h));
			// System.out.println(max);
		}
		return max;
	}

	static int maxRectangleInHistgram(int[] h) {
		Stack<Integer> s = new Stack<>();
		int max = 0;

		for (int i = 0; i <= h.length; i++) {
			while (!s.isEmpty() && (i == h.length || h[s.peek()] >= h[i])) {
				int hi = s.pop();
				int left = s.isEmpty() ? -1 : s.peek();
				max = Math.max(max, h[hi] * (i - 1 - left));
			}
			s.push(i);
		}
		return max;
	}
}
