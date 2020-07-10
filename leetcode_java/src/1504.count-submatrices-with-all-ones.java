/*
 * @lc app=leetcode id=1504 lang=java
 *
 * [1504] Count Submatrices With All Ones
 *
 * https://leetcode.com/problems/count-submatrices-with-all-ones/description/
 *
 * algorithms
 * Medium (60.11%)
 * Likes:    235
 * Dislikes: 10
 * Total Accepted:    6.1K
 * Total Submissions: 10.1K
 * Testcase Example:  '[[1,0,1],[1,1,0],[1,1,0]]'
 *
 * Given a rows * columns matrix mat of ones and zeros, return how many
 * submatrices have all ones.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,0,1],
 * [1,1,0],
 * [1,1,0]]
 * Output: 13
 * Explanation:
 * There are 6 rectangles of side 1x1.
 * There are 2 rectangles of side 1x2.
 * There are 3 rectangles of side 2x1.
 * There is 1 rectangle of side 2x2. 
 * There is 1 rectangle of side 3x1.
 * Total number of rectangles = 6 + 2 + 3 + 1 + 1 = 13.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[0,1,1,0],
 * [0,1,1,1],
 * [1,1,1,0]]
 * Output: 24
 * Explanation:
 * There are 8 rectangles of side 1x1.
 * There are 5 rectangles of side 1x2.
 * There are 2 rectangles of side 1x3. 
 * There are 4 rectangles of side 2x1.
 * There are 2 rectangles of side 2x2. 
 * There are 2 rectangles of side 3x1. 
 * There is 1 rectangle of side 3x2. 
 * Total number of rectangles = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[1,1,1,1,1,1]]
 * Output: 21
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: mat = [[1,0,1],[0,1,0],[1,0,1]]
 * Output: 5
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rows <= 150
 * 1 <= columns <= 150
 * 0 <= mat[i][j] <= 1
 * 
 */

// @lc code=start
class Solution {
    public int numSubmat(int[][] mat) {
        
	int M = mat.length, N = mat[0].length;

	int res = 0;

	int[] h = new int[N];
	for (int i = 0; i < M; ++i) {
		for (int j = 0; j < N; ++j) {
			h[j] = (mat[i][j] == 0 ? 0 : h[j] + 1);
		}
		res += helper(h);
	}

	return res;
}

private int helper(int[] A) {

	int[] sum = new int[A.length];
	Stack<Integer> stack = new Stack<>();

	for (int i = 0; i < A.length; ++i) {

		while (!stack.isEmpty() && A[stack.peek()] >= A[i]) stack.pop();

		if (!stack.isEmpty()) {
			int preIndex = stack.peek();
			sum[i] = sum[preIndex];
			sum[i] += A[i] * (i - preIndex);
		} else {
			sum[i] = A[i] * (i + 1);
		}

		stack.push(i);
	}

	int res = 0;
	for (int s : sum) res += s;

	return res;
}
}
// @lc code=end
