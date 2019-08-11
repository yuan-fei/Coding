/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (36.98%)
 * Total Accepted:    656.7K
 * Total Submissions: 1.8M
 * Testcase Example:  '"()"'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * 
 * 
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * 
 * 
 * Note that an empty string is also considered valid.
 * 
 * Example 1:
 * 
 * 
 * Input: "()"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "()[]{}"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: "(]"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: "([)]"
 * Output: false
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: "{[]}"
 * Output: true
 * 
 * 
 */
class Solution {
    public boolean isValid(String str) {
        Stack<Character> s = new Stack<Character>();
        for (char c: str.toCharArray()) {
        	switch(c){
        		case '(':
        		case '[':
        		case '{':
        			s.push(c);
        			break;
        		case ')':
        			if(s.isEmpty() || s.peek()!='('){
        				return false;
        			}
        			s.pop();
        			break;
        		case ']':
        			if(s.isEmpty() || s.peek()!='['){
        				return false;
        			}
        			s.pop();
        			break;
        		case '}':
        			if(s.isEmpty() || s.peek()!='{'){
        				return false;
        			}
        			s.pop();
        			break;
        	}
        }
        return s.isEmpty();
    }
}
