/*
 * @lc app=leetcode id=796 lang=java
 *
 * [796] Rotate String
 *
 * https://leetcode.com/problems/rotate-string/description/
 *
 * algorithms
 * Easy (54.44%)
 * Likes:    2447
 * Dislikes: 101
 * Total Accepted:    185.6K
 * Total Submissions: 340.9K
 * Testcase Example:  '"abcde"\n"cdeab"'
 *
 * Given two strings s and goal, return true if and only if s can become goal
 * after some number of shifts on s.
 * 
 * A shift on s consists of moving the leftmost character of s to the rightmost
 * position.
 * 
 * 
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 * 
 * 
 * 
 * Example 1:
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 * Example 2:
 * Input: s = "abcde", goal = "abced"
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, goal.length <= 100
 * s and goal consist of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean rotateString(String s, String goal) {
        for(int r = 1; r <= s.length(); r++){
            s = s.substring(1) + s.charAt(0);
            if(s.equals(goal)){
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
