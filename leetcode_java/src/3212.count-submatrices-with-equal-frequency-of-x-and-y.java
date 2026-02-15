/*
 * @lc app=leetcode id=3212 lang=java
 *
 * [3212] Count Submatrices With Equal Frequency of X and Y
 *
 * https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/description/
 *
 * algorithms
 * Medium (51.32%)
 * Likes:    160
 * Dislikes: 27
 * Total Accepted:    26.5K
 * Total Submissions: 51.5K
 * Testcase Example:  '[["X","Y","."],["Y",".","."]]'
 *
 * Given a 2D character matrix grid, where grid[i][j] is either 'X', 'Y', or
 * '.', return the number of submatrices that contain:
 * 
 * 
 * grid[0][0]
 * an equal frequency of 'X' and 'Y'.
 * at least one 'X'.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [["X","Y","."],["Y",".","."]]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [["X","X"],["X","Y"]]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * No submatrix has an equal frequency of 'X' and 'Y'.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[".","."],[".","."]]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * No submatrix has at least one 'X'.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] is either 'X', 'Y', or '.'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] freq = new int[n + 1][m + 1][2];
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int b = 0; b < 2; b++) {
                    freq[i][j][b] = freq[i - 1][j][b] + freq[i][j - 1][b] - freq[i - 1][j - 1][b];
                }
                if (grid[i - 1][j - 1] == 'X') {
                    freq[i][j][0]++;
                } else if (grid[i - 1][j - 1] == 'Y') {
                    freq[i][j][1]++;
                }
                if (freq[i][j][0] == freq[i][j][1] && freq[i][j][0] != 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
// @lc code=end
