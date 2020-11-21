/*
 * @lc app=leetcode id=1653 lang=java
 *
 * [1653] Minimum Deletions to Make String Balanced
 *
 * https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/description/
 *
 * algorithms
 * Medium (34.05%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    1.3K
 * Total Submissions: 3.7K
 * Testcase Example:  '"aababbab"'
 *
 * You are given a string s consisting only of characters 'a' and 'b'​​​​.
 * 
 * You can delete any number of characters in s to make s balanced. s is
 * balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b'
 * and s[j]= 'a'.
 * 
 * Return the minimum number of deletions needed to make s balanced.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" ->
 * "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" ->
 * "aabbbb").
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bbaaaaabb"
 * Output: 2
 * Explanation: The only solution is to delete the first two characters.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is 'a' or 'b'​​.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumDeletions(String s) {
        int[] dp = new int[2]; 
        int min = s.length();
        for(int i = 0; i < s.length(); i++){
        	char c = s.charAt(i);
        	int[] newDp = new int[2];
        	if(c == 'a'){
        		newDp[0] = dp[0];
        		newDp[1] = dp[1] + 1;
        	}
        	else{
        		newDp[0] = dp[0] + 1;
        		newDp[1] = Math.min(dp[1], dp[0]);
        	}
        	dp = newDp;
        	min = Math.min(dp[0] + s.length() - i - 1, min);
        	min = Math.min(dp[1] + s.length() - i - 1, min);
        }
        return min;
    }
}
// @lc code=end
