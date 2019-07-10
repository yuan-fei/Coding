import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StackTrick {

	public static void main(String[] args) {
		int[][] result = findFirstLowerBound(new int[] { 2, 1, 3, 6, 5, 4 });
		System.out.println(Arrays.toString(result[0]));
		System.out.println(Arrays.toString(result[1]));

		result = findFirstLowerBoundWithTreeSet(new int[] { 2, 1, 3, 6, 5, 4 });
		System.out.println(Arrays.toString(result[0]));
		System.out.println(Arrays.toString(result[1]));

		int[] lBounds = findLeftLowerBound(new int[] { 2, 1, 3, 6, 5, 4 });
		System.out.println(Arrays.toString(lBounds));
		lBounds = findLeftLowerBound(new int[] { 3, 2, 1 });
		System.out.println(Arrays.toString(lBounds));
	}

	/**
	 * For each number in the input, find the first lower heights to the left and
	 * right. i.e. [2, 1, 3, 6, 5, 4] => [-1, -1, 1, 2, 2, 2], [1, 6, 6, 4, 5, 6]
	 * 
	 * 
	 * always maintain a increasing stack
	 * 
	 * TC: O(n)
	 */
	public static int[][] findFirstLowerBound(int[] heights) {
		Stack<Integer> s = new Stack<Integer>();
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		int n = heights.length;
		for (int i = 0; i <= n; i++) {
			while (!s.isEmpty() && (i == n || heights[s.peek()] > heights[i])) {
				int cur = s.pop();
				right[cur] = i;
				left[cur] = s.isEmpty() ? -1 : s.peek();
			}
			s.push(i);
		}

		return new int[][] { left, right };
	}

	/** TC: O(nlogn) */
	public static int[][] findFirstLowerBoundWithTreeSet(int[] heights) {
		int n = heights.length;
		List<Integer> idx = IntStream.range(0, n).boxed().collect(Collectors.toList());
		Collections.sort(idx, Comparator.comparingInt(i -> heights[i]));
		TreeSet<Integer> t = new TreeSet<>();
		t.add(-1);
		t.add(n);
		int[] left = new int[heights.length];
		int[] right = new int[heights.length];
		for (int i = 0; i < n; i++) {
			int cur = idx.get(i);
			left[cur] = t.lower(cur);
			right[cur] = t.higher(cur);
			t.add(cur);
		}
		return new int[][] { left, right };
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
