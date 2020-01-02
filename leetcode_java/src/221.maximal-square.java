/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 *
 * https://leetcode.com/problems/maximal-square/description/
 *
 * algorithms
 * Medium (34.86%)
 * Likes:    1936
 * Dislikes: 48
 * Total Accepted:    172K
 * Total Submissions: 493.2K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input: 
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Output: 4
 * 
 */

// @lc code=start
class Solution {
    public int maximalSquare(char[][] matrix) {
    	int n = matrix.length;
    	if(n == 0){
    		return 0;
    	}
    	int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];
        int maxArea = 0;
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		if(matrix[i - 1][j - 1] == '1'){
	        		dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
        		}
        		maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
        	}
        }
        return maxArea;
    }
}
// @lc code=end
