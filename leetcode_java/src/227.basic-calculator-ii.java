/*
 * @lc app=leetcode id=227 lang=java
 *
 * [227] Basic Calculator II
 *
 * https://leetcode.com/problems/basic-calculator-ii/description/
 *
 * algorithms
 * Medium (34.09%)
 * Total Accepted:    119.9K
 * Total Submissions: 351.7K
 * Testcase Example:  '"3+2*2"'
 *
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string contains only non-negative integers, +, -, *, /
 * operators and empty spaces  . The integer division should truncate toward
 * zero.
 * 
 * Example 1:
 * 
 * 
 * Input: "3+2*2"
 * Output: 7
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: " 3/2 "
 * Output: 1
 * 
 * Example 3:
 * 
 * 
 * Input: " 3+5 / 2 "
 * Output: 5
 * 
 * 
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
		String lastSign = "+";
		while (hasNextToken(str)) {
			String t = getNextToken(str);
			switch (t) {
			case "*":
			case "/":
			case "+":
			case "-":
				lastSign = t;
				break;
			default:
                int n = Integer.parseInt(t);
                if(lastSign.equals("-")){
                    n*=-1;
                }
                if(lastSign.equals("*")){
                    n = s.pop()*n;
                }
                if(lastSign.equals("/")){
                    n = s.pop()/n;
                }
				s.push(n);
				lastSign = "+";
			}
		}
        int r = 0;
        while(!s.isEmpty()){
            r += s.pop();
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
			case '/':
			case '*':
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
