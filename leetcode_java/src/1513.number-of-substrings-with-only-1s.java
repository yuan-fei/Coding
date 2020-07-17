/*
 * @lc app=leetcode id=1513 lang=java
 *
 * [1513] Number of Substrings With Only 1s
 *
 * https://leetcode.com/problems/number-of-substrings-with-only-1s/description/
 *
 * algorithms
 * Medium (39.70%)
 * Likes:    98
 * Dislikes: 4
 * Total Accepted:    9.4K
 * Total Submissions: 23.6K
 * Testcase Example:  '"0110111"'
 *
 * Given a binary string s (a string consisting only of '0' and '1's).
 * 
 * Return the number of substrings with all characters 1's.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "0110111"
 * Output: 9
 * Explanation: There are 9 substring in total with only 1's characters.
 * "1" -> 5 times.
 * "11" -> 3 times.
 * "111" -> 1 time.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "101"
 * Output: 2
 * Explanation: Substring "1" is shown 2 times in s.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "111111"
 * Output: 21
 * Explanation: Each substring contains only 1's characters.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "000"
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s[i] == '0' or s[i] == '1'
 * 1 <= s.length <= 10^5
 * 
 */

// @lc code=start
class Solution {
    public int numSub(String s) {
    	int total = 0;
    	int cur = 0;
    	int MOD = 1000000007;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '1'){
        		cur++;
        		total = total + cur;
        		total %= MOD;
        	}
        	else{
        		cur = 0;
        	}
        }
        return total;
    }
}
// @lc code=end
