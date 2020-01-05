/*
 * @lc app=leetcode id=1312 lang=java
 *
 * [1312] Minimum Insertion Steps to Make a String Palindrome
 *
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
 *
 * algorithms
 * Hard (51.35%)
 * Likes:    41
 * Dislikes: 2
 * Total Accepted:    2.2K
 * Total Submissions: 4.4K
 * Testcase Example:  '"zzazz"'
 *
 * Given a string s. In one step you can insert any character at any index of
 * the string.
 * 
 * Return the minimum number of steps to make s palindrome.
 * 
 * A Palindrome String is one that reads the same backward as well as
 * forward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any
 * insertions.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "g"
 * Output: 0
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: s = "no"
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 500
 * All characters of s are lower case English letters.
 * 
 */

// @lc code=start
class Solution {
    public int minInsertions(String s) {
    	int n = s.length();
        dp = new int[n][n];
        for(int[] dpi: dp){
        	Arrays.fill(dpi, MAX);
        }
        return minInsertions(s, 0, n - 1);
    }

    int[][] dp;
    int MAX = 505;
    private int minInsertions(String s, int start, int end){
    	if(end <= start){
    		return 0;
    	}
    	if(dp[start][end] == MAX){
			if(s.charAt(start) == s.charAt(end)){
	    		dp[start][end] = minInsertions(s, start + 1, end - 1);
	    	}
	    	else{
	    		dp[start][end] = 1 + Math.min(minInsertions(s, start + 1, end), minInsertions(s, start, end - 1));
	    	}
    	}
    	return dp[start][end];
    }
}
// @lc code=end
