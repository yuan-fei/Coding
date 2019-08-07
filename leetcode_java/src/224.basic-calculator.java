/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 *
 * https://leetcode.com/problems/basic-calculator/description/
 *
 * algorithms
 * Hard (33.46%)
 * Total Accepted:    115.9K
 * Total Submissions: 346.6K
 * Testcase Example:  '"1 + 1"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces  .
 * 
 * Example 1:
 * 
 * 
 * Input: "1 + 1"
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: " 2-1 + 2 "
 * Output: 3
 * 
 * Example 3:
 * 
 * 
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * 
 * 
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 * 
 * 
 */
class Solution {
	int i;

	public int calculate(String str) {
		Stack<Integer> s = new Stack<>();
		i = 0;
		int r = 0;
		int lastSign = 1;
		while (hasNextToken(str)) {
			String t = getNextToken(str);
			switch (t) {
			case ")":
				lastSign = s.pop();
				r = r * lastSign + s.pop();
				lastSign = 1;
				break;
			case "(":
				s.push(r);
				s.push(lastSign);
				lastSign = 1;
				r = 0;
				break;
			case "+":
				lastSign = 1;
				break;
			case "-":
				lastSign = -1;
				break;
			default:
				r += lastSign * Integer.parseInt(t);
				lastSign = 1;

			}
		}

		return r;
	}

	boolean hasNextToken(String s) {
		while (i < s.length()) {
			if (s.charAt(i) == ' ') {
				i++;
			} else {
				return true;
			}
		}
		return false;
	}

	String getNextToken(String s) {
		String r = "";
		boolean isDigit = false;
		while (i < s.length()) {
			char c = s.charAt(i);
			switch (c) {
			case ' ':
				return r;
			case '(':
			case ')':
			case '+':
			case '-':
				if (isDigit == false) {
					r = c + "";
					i++;
				}
				return r;
			default:
				isDigit = true;
				r += c;
				break;
			}
			i++;
		}
		return r;
	}
}
