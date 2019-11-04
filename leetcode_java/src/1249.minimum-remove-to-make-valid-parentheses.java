/*
 * @lc app=leetcode id=1249 lang=java
 *
 * [1249] Minimum Remove to Make Valid Parentheses
 *
 * https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
 *
 * algorithms
 * Medium (53.43%)
 * Likes:    31
 * Dislikes: 2
 * Total Accepted:    2.5K
 * Total Submissions: 4.6K
 * Testcase Example:  '"lee(t(c)o)de)"'
 *
 * Given a string s of '(' , ')' and lowercase English characters. 
 * 
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in
 * any positions ) so that the resulting parentheses string is valid and return
 * any valid string.
 * 
 * Formally, a parentheses string is valid if and only if:
 * 
 * 
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid
 * strings, or
 * It can be written as (A), where A is a valid string.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public String minRemoveToMakeValid(String s) {
    	StringBuilder sb = new StringBuilder();
    	Stack<String> stk= new Stack<>();
    	int bal = 0;
    	String sub = "";
        for (char c : s.toCharArray() ) {
        	if(c == '('){
        		bal++;
        		stk.push(sub);
        		sub = "";
        	}
        	else if(c == ')'){
        		if(bal - 1 >= 0){
        			bal--;
        			sub += c;
        		} 
        	}
        	else{
        		sub += c;
        	}
        }
        sb.append(sub);
        while(!stk.isEmpty()){
        	if(bal > 0){
        		bal--;
        		sb.insert(0, stk.pop());
        	}
        	else{
        		sb.insert(0, stk.pop()+"(");	
        	}
        }
        
        return sb.toString();
    }
}
// @lc code=end
