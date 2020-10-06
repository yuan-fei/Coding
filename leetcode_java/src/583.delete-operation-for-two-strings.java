/*
 * @lc app=leetcode id=583 lang=java
 *
 * [583] Delete Operation for Two Strings
 *
 * https://leetcode.com/problems/delete-operation-for-two-strings/description/
 *
 * algorithms
 * Medium (49.06%)
 * Likes:    1200
 * Dislikes: 26
 * Total Accepted:    54.3K
 * Total Submissions: 110.6K
 * Testcase Example:  '"sea"\n"eat"'
 *
 * 
 * Given two words word1 and word2, find the minimum number of steps required
 * to make word1 and word2 the same, where in each step you can delete one
 * character in either string.
 * 
 * 
 * Example 1:
 * 
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to
 * make "eat" to "ea".
 * 
 * 
 * 
 * Note:
 * 
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDistance(String word1, String word2) {
    	int n = word1.length();
    	int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
        		dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
        		if(word1.charAt(i - 1) == word2.charAt(j - 1)){
        			dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);	
        		}
        	}
        }
        return n + m - 2 * dp[n][m];
    }
}
// @lc code=end
