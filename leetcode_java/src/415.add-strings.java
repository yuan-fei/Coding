/*
 * @lc app=leetcode id=415 lang=java
 *
 * [415] Add Strings
 *
 * https://leetcode.com/problems/add-strings/description/
 *
 * algorithms
 * Easy (46.70%)
 * Likes:    818
 * Dislikes: 246
 * Total Accepted:    166.8K
 * Total Submissions: 357.3K
 * Testcase Example:  '"0"\n"0"'
 *
 * Given two non-negative integers num1 and num2 represented as string, return
 * the sum of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String addStrings(String num1, String num2) {
    	StringBuilder sb = new StringBuilder();
    	int carry = 0;
        for(int i = 0; i < Math.max(num1.length(), num2.length()); i++){
        	int i1 = num1.length() - i - 1;
        	int i2 = num2.length() - i - 1;
        	int d1 = 0;
        	int d2 = 0;
        	if(i1 >= 0){
        		d1 = num1.charAt(i1) - '0';
        	}
        	if(i2 >= 0){
        		d2 = num2.charAt(i2) - '0';
        	}
        	sb.insert(0, (d1 + d2 + carry) % 10);
        	carry = (d1 + d2 + carry) / 10;
        }
        if(carry > 0){
        	sb.insert(0, carry);
        }
        return sb.toString();
    }
}
// @lc code=end
