/*
 * @lc app=leetcode id=3122 lang=java
 *
 * [3122] Minimum Number of Operations to Satisfy Conditions
 *
 * https://leetcode.com/problems/minimum-number-of-operations-to-satisfy-conditions/description/
 *
 * algorithms
 * Medium (40.80%)
 * Likes:    246
 * Dislikes: 12
 * Total Accepted:    16.2K
 * Total Submissions: 40.6K
 * Testcase Example:  '[[1,0,2],[1,0,2]]'
 *
 * You are given a 2D matrix grid of size m x n. In one operation, you can
 * change the value of any cell to any non-negative number. You need to perform
 * some operations such that each cell grid[i][j] is:
 * 
 * 
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it
 * exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if
 * it exists).
 * 
 * 
 * Return the minimum number of operations needed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,2],[1,0,2]]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * 
 * All the cells in the matrix already satisfy the properties.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,1],[0,0,0]]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * 
 * 
 * The matrix becomes [[1,0,1],[1,0,1]] which satisfies the properties, by
 * doing these 3 operations:
 * 
 * 
 * Change grid[1][0] to 1.
 * Change grid[0][1] to 0.
 * Change grid[1][2] to 1.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1],[2],[3]]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * 
 * There is a single column. We can change the value to 1 in each cell using 2
 * operations.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, m <= 1000
 * 0 <= grid[i][j] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumOperations(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] changes = new int[m][10];
        for (int k = 0; k <= 9; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] != k) {
                        changes[j][k]++;
                    }
                }
            }
        }
        int[][] dp = new int[m + 1][10];
        for (int i = 1; i <= m; i++) {
            for (int k1 = 0; k1 < 10; k1++) {
                dp[i][k1] = Integer.MAX_VALUE;
                for (int k2 = 0; k2 < 10; k2++) {
                    if (k1 != k2) {
                        dp[i][k1] = Math.min(dp[i][k1], dp[i - 1][k2] + changes[i - 1][k1]);
                    }
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= 9; i++) {
            ans = Math.min(ans, dp[m][i]);
        }
        return ans;
    }
}
// @lc code=end
