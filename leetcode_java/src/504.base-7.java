/*
 * @lc app=leetcode id=504 lang=java
 *
 * [504] Base 7
 *
 * https://leetcode.com/problems/base-7/description/
 *
 * algorithms
 * Easy (46.19%)
 * Likes:    240
 * Dislikes: 148
 * Total Accepted:    56.3K
 * Total Submissions: 121.9K
 * Testcase Example:  '100'
 *
 * Given an integer, return its base 7 string representation.
 * 
 * Example 1:
 * 
 * Input: 100
 * Output: "202"
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: -7
 * Output: "-10"
 * 
 * 
 * 
 * Note:
 * The input will be in range of [-1e7, 1e7].
 * 
 */

// @lc code=start
class Solution {
    public String convertToBase7(int num) {
    	if(num == 0){
    		return "0";
    	}
        if(num < 0){
        	return "-" + convertToBase7(-num);
        }
        String ret = "";
        while(num != 0){
        	ret = (num % 7) + ret;
        	num -= (num % 7);
        	num /= 7;
        }
        return ret;
    }
}
// @lc code=end
