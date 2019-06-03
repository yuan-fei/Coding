import java.util.Arrays;
import java.util.Stack;

public class StackTrick {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] result = findFirstLowerBound(new int[] { 2, 1, 3, 6, 5, 4 });
		System.out.println(Arrays.toString(result[0]));
		System.out.println(Arrays.toString(result[1]));

		int[] lBounds = findLeftLowerBound(new int[] { 2, 1, 3, 6, 5, 4 });
		System.out.println(Arrays.toString(lBounds));
		lBounds = findLeftLowerBound(new int[] { 3, 2, 1 });
		System.out.println(Arrays.toString(lBounds));
	}

	/**
	 * For each number in the input, find the first lower heights to the left and
	 * right. i.e. [2, 1, 3, 6, 5, 4] => [-1, -1, 1, 2, 2, 2], [1, -1, -1, 4, 5, -1]
	 * 
	 * 
	 * always maintain a increasing stack
	 */
	public static int[][] findFirstLowerBound(int[] heights) {
		Stack<Integer> s = new Stack<Integer>();
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		int i = 0;

		while (i < heights.length) {
			if (s.isEmpty()) {
				left[i] = -1;
				s.push(i);
				i++;
			} else if (heights[s.peek()] <= heights[i]) {
				left[i] = s.peek();
				s.push(i);
				i++;
			} else {
				// from now on heights[i] is the first lowest bound to the left of remaining
				// elements
				right[s.pop()] = i;
			}
		}

		while (!s.isEmpty()) {
			right[s.pop()] = -1;
		}
		int[][] result = new int[2][heights.length];
		result[0] = left;
		result[1] = right;
		return result;
	}

	/**
	 * For each number in the input, find the first lower heights to the left and
	 * right. i.e. [2, 1, 3, 6, 5, 4] => [-1, -1, 1, 2, 2, 2]
	 */

	public static int[] findLeftLowerBound(int[] heights) {
		Stack<Integer> s = new Stack<Integer>();
		int[] left = new int[heights.length];
		int i = 0;
		while (i < heights.length) {
			if (s.isEmpty()) {
				left[i] = -1;
				s.push(i);
				i++;
			} else {
				if (heights[s.peek()] > heights[i]) {
					s.pop();
				} else {
					left[i] = s.peek();
					s.push(i);
					i++;
				}
			}
		}
		return left;
	}
}
