/*
 * @lc app=leetcode id=1556 lang=java
 *
 * [1556] Thousand Separator
 *
 * https://leetcode.com/problems/thousand-separator/description/
 *
 * algorithms
 * Easy (61.74%)
 * Likes:    15
 * Dislikes: 4
 * Total Accepted:    6.6K
 * Total Submissions: 10.7K
 * Testcase Example:  '987'
 *
 * Given an integer n, add a dot (".") as the thousands separator and return it
 * in string format.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 987
 * Output: "987"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1234
 * Output: "1.234"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 123456789
 * Output: "123.456.789"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 0
 * Output: "0"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= n < 2^31
 * 
 * 
 */

// @lc code=start
class Solution {
    public String thousandSeparator(int n) {
        String s = "" + n;
        String ret = "";
        for(int i = 0; i < s.length(); i++){
        	ret += s.charAt(i);
        	if(s.length() != i + 1 && (s.length() - i - 1) % 3 == 0 ){
        		ret += '.';
        	}
        }
        return ret;
    }
}
// @lc code=end
