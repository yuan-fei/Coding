/*
 * [32] Longest Valid Parentheses
 *
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * algorithms
 * Hard (23.20%)
 * Total Accepted:    118.8K
 * Total Submissions: 511.6K
 * Testcase Example:  '""'
 *
 * Given a string containing just the characters '(' and ')', find the length
 * of the longest valid (well-formed) parentheses substring.
 * 
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length
 * = 2.
 * 
 * 
 * Another example is ")()())", where the longest valid parentheses substring
 * is "()()", which has length = 4.
 * 
 */
/*
Analysis:
DP: state[i]: max match length including character s[i - 1]
*/
class Solution {
    public int longestValidParentheses(String s) {
        return solve2(s);
    }

    public int solve1(String s) {
		int[] state = new int[s.length() + 1];
		state[0] = 0;
		int maxValid = 0;
		for (int i = 1; i <= s.length(); i++) {
			state[i] = 0;
			if(s.charAt(i - 1) == ')'){
				int maxMatch = state[i - 1];
				if(i - maxMatch - 2 >= 0 && s.charAt(i - maxMatch - 2) == '('){
					state[i] = 	maxMatch + 2 + state[i - maxMatch - 2];
					maxValid = Math.max(maxValid, state[i]);
				}
			}
		}
		return maxValid;
    }

	public int solve2(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		Integer leftMostParentheses = null;
		int maxValid = 0;
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '('){
				if(leftMostParentheses == null){
					leftMostParentheses = i;
				}
				stack.push(i);
			}
			else{
				if(!stack.isEmpty()){
					stack.pop();
					int leftMostValid = leftMostParentheses;
					if(!stack.isEmpty()){
						leftMostValid = stack.peek() + 1;
					}
					maxValid = Math.max(i - leftMostValid + 1, maxValid);
				}
				else{
					leftMostParentheses = i + 1;
				}
			}
		}
		return maxValid;	
		
	}
}
