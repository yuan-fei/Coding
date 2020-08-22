/*
 * @lc app=leetcode id=516 lang=java
 *
 * [516] Longest Palindromic Subsequence
 *
 * https://leetcode.com/problems/longest-palindromic-subsequence/description/
 *
 * algorithms
 * Medium (53.37%)
 * Likes:    2147
 * Dislikes: 187
 * Total Accepted:    125.1K
 * Total Submissions: 234.4K
 * Testcase Example:  '"bbbab"'
 *
 * Given a string s, find the longest palindromic subsequence's length in s.
 * You may assume that the maximum length of s is 1000.
 * 
 * Example 1:
 * Input:
 * 
 * 
 * "bbbab"
 * 
 * Output:
 * 
 * 
 * 4
 * 
 * One possible longest palindromic subsequence is "bbbb".
 * 
 * 
 * 
 * Example 2:
 * Input:
 * 
 * 
 * "cbbd"
 * 
 * Output:
 * 
 * 
 * 2
 * 
 * One possible longest palindromic subsequence is "bb".
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestPalindromeSubseq(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        String t = sb.toString();
        return longestCommonSubsequence(s, t);
    }

    int longestCommonSubsequence(String s, String t){
    	int n = s.length();
    	int m = t.length();
    	int[][] dp = new int[n + 1][m + 1];
    	for(int i = 1; i <= s.length(); i++){
    		for(int j = 1; j <= t.length(); j++){
    			if(s.charAt(i - 1) == t.charAt(j - 1)){
    				dp[i][j] = dp[i - 1][j - 1] + 1;
    			}
    			else{
    				dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
    			}
    		}
    	}
    	return dp[n][m];
    }
}
// @lc code=end
