/*
 * @lc app=leetcode id=931 lang=java
 *
 * [931] Minimum Falling Path Sum
 *
 * https://leetcode.com/problems/minimum-falling-path-sum/description/
 *
 * algorithms
 * Medium (66.31%)
 * Likes:    5344
 * Dislikes: 132
 * Total Accepted:    282K
 * Total Submissions: 429.3K
 * Testcase Example:  '[[2,1,3],[6,5,4],[7,8,9]]'
 *
 * Given an n x n array of integers matrix, return the minimum sum of any
 * falling path through matrix.
 * 
 * A falling path starts at any element in the first row and chooses the
 * element in the next row that is either directly below or diagonally
 * left/right. Specifically, the next element from position (row, col) will be
 * (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * Output: 13
 * Explanation: There are two falling paths with a minimum sum as shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[-19,57],[-40,-5]]
 * Output: -59
 * Explanation: The falling path with a minimum sum is shown.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                dp[i + 1][j] = Integer.MAX_VALUE;
                for(int k = -1; k <= 1; k++){
                    int lastJ = j + k;
                    if(0 <= lastJ && lastJ < m){
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][lastJ] + matrix[i][j]);
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < m; j++){
            ans = Math.min(ans, dp[n][j]);
        }
        return ans;
    }
}
// @lc code=end
