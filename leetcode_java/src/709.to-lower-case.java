/*
 * @lc app=leetcode id=709 lang=java
 *
 * [709] To Lower Case
 *
 * https://leetcode.com/problems/to-lower-case/description/
 *
 * algorithms
 * Easy (78.92%)
 * Likes:    451
 * Dislikes: 1463
 * Total Accepted:    200.4K
 * Total Submissions: 253.8K
 * Testcase Example:  '"Hello"'
 *
 * Implement function ToLowerCase() that has a string parameter str, and
 * returns the same string in lowercase.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: "Hello"
 * Output: "hello"
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "here"
 * Output: "here"
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "LOVELY"
 * Output: "lovely"
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String toLowerCase(String str) {
    	String ans = "";
        for (char c : str.toCharArray()) {
        	if(c >= 'A' && c <= 'Z'){
        		ans += (char)(c - 'A' + 'a');
        	}
        	else{
        		ans += c;
        	}
        }
        return ans;
    }
}
// @lc code=end
