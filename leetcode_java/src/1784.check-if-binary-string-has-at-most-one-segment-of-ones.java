/*
 * @lc app=leetcode id=1784 lang=java
 *
 * [1784] Check if Binary String Has at Most One Segment of Ones
 *
 * https://leetcode.com/problems/check-if-binary-string-has-at-most-one-segment-of-ones/description/
 *
 * algorithms
 * Easy (42.69%)
 * Likes:    43
 * Dislikes: 123
 * Total Accepted:    9.8K
 * Total Submissions: 22.9K
 * Testcase Example:  '"1001"'
 *
 * Given a binary string s ​​​​​without leading zeros, return true​​​ if s
 * contains at most one contiguous segment of ones. Otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "1001"
 * Output: false
 * Explanation: The ones do not form a contiguous segment.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "110"
 * Output: true
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s[i]​​​​ is either '0' or '1'.
 * s[0] is '1'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkOnesSegment(String s) {
    	boolean seenZero = false;
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '0'){
        		seenZero = true;
        	}
        	else if(seenZero && s.charAt(i) == '1'){
        		return false;
        	}
        }
        return true;
    }
}
// @lc code=end
