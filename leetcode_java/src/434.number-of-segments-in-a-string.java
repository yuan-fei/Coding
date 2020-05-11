/*
 * @lc app=leetcode id=434 lang=java
 *
 * [434] Number of Segments in a String
 *
 * https://leetcode.com/problems/number-of-segments-in-a-string/description/
 *
 * algorithms
 * Easy (37.53%)
 * Likes:    197
 * Dislikes: 697
 * Total Accepted:    72.8K
 * Total Submissions: 193.9K
 * Testcase Example:  '"Hello, my name is John"'
 *
 * Count the number of segments in a string, where a segment is defined to be a
 * contiguous sequence of non-space characters.
 * 
 * Please note that the string does not contain any non-printable characters.
 * 
 * Example:
 * 
 * Input: "Hello, my name is John"
 * Output: 5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countSegments(String s) {
    	s = s.trim();
    	if(s.isEmpty()){
    		return 0;
    	}
        return s.split("\\s+").length;
    }
}
// @lc code=end
