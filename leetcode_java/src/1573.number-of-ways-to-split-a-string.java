/*
 * @lc app=leetcode id=1573 lang=java
 *
 * [1573] Number of Ways to Split a String
 *
 * https://leetcode.com/problems/number-of-ways-to-split-a-string/description/
 *
 * algorithms
 * Medium (25.87%)
 * Likes:    24
 * Dislikes: 4
 * Total Accepted:    2.9K
 * Total Submissions: 11.3K
 * Testcase Example:  '"10101"'
 *
 * Given a binary string s (a string consisting only of '0's and '1's), we can
 * split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).
 * 
 * Return the number of ways s can be split such that the number of characters
 * '1' is the same in s1, s2, and s3.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part
 * contain the same number of letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "1001"
 * Output: 0
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "0000"
 * Output: 3
 * Explanation: There are three ways to split s in 3 parts.
 * "0|0|00"
 * "0|00|0"
 * "00|0|0"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "100100010100110"
 * Output: 12
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s[i] == '0' or s[i] == '1'
 * 3 <= s.length <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numWays(String s) {
        int[] pos = new int[s.length()];
        int cur = 0;
        for (int i = 0; i < s.length(); i++) {
        	if(s.charAt(i) == '1'){
        		pos[cur++] = i;
        	}
        }
        if(cur % 3 != 0){
        	return 0;
        }
        cur /= 3;
        int mod = 1000000007;
        if(cur == 0){
        	return (int)((1L * (s.length() - 1) * (s.length() - 2) / 2) % mod);
        }
        return (int)(1L * (pos[cur] - pos[cur - 1]) * (pos[cur * 2] - pos[cur * 2 - 1]) % mod);
    }
}
// @lc code=end
