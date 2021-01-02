/*
 * @lc app=leetcode id=647 lang=java
 *
 * [647] Palindromic Substrings
 *
 * https://leetcode.com/problems/palindromic-substrings/description/
 *
 * algorithms
 * Medium (61.55%)
 * Likes:    3447
 * Dislikes: 134
 * Total Accepted:    232.6K
 * Total Submissions: 377.5K
 * Testcase Example:  '"abc"'
 *
 * Given a string, your task is to count how many palindromic substrings in
 * this string.
 * 
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 * 
 * Example 1:
 * 
 * 
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The input string length won't exceed 1000.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSubstrings(String s) {
    	int n = s.length();
        boolean[][] dp = new boolean[n + 1][n];
        int cnt = 0;
        for(int i = 0; i < n; i++){
        	dp[1][i] = true;
        	cnt++;
        	if(i < n - 1){
        		dp[2][i] = (s.charAt(i) == s.charAt(i + 1));	
        		if(dp[2][i]){
        			cnt++;	
        		}
        	}
        }


        for(int l = 3; l <= n; l++){
        	for(int i = 0; i <= n - l; i++){
        		dp[l][i] = dp[l - 2][i + 1] && (s.charAt(i) == s.charAt(i + l - 1));
        		if(dp[l][i]){
        			cnt++;
        		}
        	}
        }
        return cnt;
    }
}
// @lc code=end
