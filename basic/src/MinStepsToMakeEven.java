/**
 * Given a non-empty integer array of size n, find the minimum number of moves
 * required to make all array elements equal, where a move is incrementing n - 1
 * elements by 1.
 * 
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
 */
public class MinStepsToMakeEven {

	public static void main(String[] args) {
		System.out.println(minMoves(new int[] { 3, 2, 1 })); // 3
	}

	public static int minMoves(int[] nums) {
		int n = nums.length;
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int x : nums) {
			sum += x;
			min = Math.min(min, x);
		}
		// think of this problem as decrease 1 instead of increase 1 each
		// operation, then we just need to get rid of the 'redundant' 1s
		return sum - min * n;
	}
}
