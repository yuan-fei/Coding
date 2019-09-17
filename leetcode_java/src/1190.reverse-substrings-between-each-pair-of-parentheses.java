/*
 * @lc app=leetcode id=1190 lang=java
 *
 * [1190] Reverse Substrings Between Each Pair of Parentheses
 *
 * https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/description/
 *
 * algorithms
 * Medium (54.02%)
 * Total Accepted:    3.4K
 * Total Submissions: 6.1K
 * Testcase Example:  '"(abcd)"'
 *
 * Given a string s that consists of lower case English letters and brackets. 
 * 
 * Reverse the strings in each pair of matching parentheses, starting from the
 * innermost one.
 * 
 * Your result should not contain any bracket.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "(abcd)"
 * Output: "dcba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "(ed(et(oc))el)"
 * Output: "leetcode"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= s.length <= 2000
 * s only contains lower case English characters and parentheses.
 * It's guaranteed that all parentheses are balanced.
 * 
 * 
 */
class Solution {
	int idx = 0;
    public String reverseParentheses(String s) {
        return reverseRec(s);
    }

    public String reverseRec(String s){
    	int N = s.length();
    	StringBuffer sb = new StringBuffer();
    	while(idx < N){
    		switch(s.charAt(idx)){
    			case '(':
    				++idx;
    				sb.append(reverseRec(s));
    				break;
    			case ')':
    				++idx;
    				sb = sb.reverse();
    				return sb.toString();
    			default:
    				sb.append(s.charAt(idx));
    				++idx;
    				break;
    		}
    	}
    	return sb.toString();
    }
}
