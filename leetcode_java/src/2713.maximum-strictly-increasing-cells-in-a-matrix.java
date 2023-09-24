/*
 * @lc app=leetcode id=2713 lang=java
 *
 * [2713] Maximum Strictly Increasing Cells in a Matrix
 *
 * https://leetcode.com/problems/maximum-strictly-increasing-cells-in-a-matrix/description/
 *
 * algorithms
 * Hard (26.06%)
 * Likes:    384
 * Dislikes: 8
 * Total Accepted:    5.7K
 * Total Submissions: 22K
 * Testcase Example:  '[[3,1],[3,4]]'
 *
 * Given a 1-indexed m x n integer matrix mat, you can select any cell in the
 * matrix as your starting cell.
 * 
 * From the starting cell, you can move to any other cell in the same row or
 * column, but only if the value of the destination cell is strictly greater
 * than the value of the current cell. You can repeat this process as many
 * times as possible, moving from cell to cell until you can no longer make any
 * moves.
 * 
 * Your task is to find the maximum number of cells that you can visit in the
 * matrix by starting from some cell.
 * 
 * Return an integer denoting the maximum number of cells that can be
 * visited.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: mat = [[3,1],[3,4]]
 * Output: 2
 * Explanation: The image shows how we can visit 2 cells starting from row 1,
 * column 2. It can be shown that we cannot visit more than 2 cells no matter
 * where we start from, so the answer is 2. 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: mat = [[1,1],[1,1]]
 * Output: 1
 * Explanation: Since the cells must be strictly increasing, we can only visit
 * one cell in this example. 
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: mat = [[3,1,6],[-9,5,7]]
 * Output: 4
 * Explanation: The image above shows how we can visit 4 cells starting from
 * row 2, column 1. It can be shown that we cannot visit more than 4 cells no
 * matter where we start from, so the answer is 4. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length 
 * n == mat[i].length 
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * -10^5 <= mat[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxIncreasingCells(int[][] M) {
        int m = M.length, n = M[0].length;
        Map<Integer, List<int[]>> A = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = M[i][j];
                if (!A.containsKey(val)) {
                    A.put(val, new ArrayList<int[]>());
                }
                A.get(val).add(new int[] {i, j});
            }
        }
        int[][] dp = new int[m][n];
        int[] res = new int[n + m];
        for (int a : A.keySet()) {
            for (int[] pos : A.get(a)) {
                int i = pos[0], j = pos[1];
                dp[i][j] = Math.max(res[i], res[m + j]) + 1;
            }
            for (int[] pos : A.get(a)) {
                int i = pos[0], j = pos[1];
                res[m + j] = Math.max(res[m + j], dp[i][j]);
                res[i] = Math.max(res[i], dp[i][j]);
            }
        }
        int ans = 0;
        for (int a : res)
            ans = Math.max(ans, a);
        return ans;
    }
}
// @lc code=end
