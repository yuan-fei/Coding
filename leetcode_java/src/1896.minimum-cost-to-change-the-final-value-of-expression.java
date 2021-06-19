/*
 * @lc app=leetcode id=1896 lang=java
 *
 * [1896] Minimum Cost to Change the Final Value of Expression
 *
 * https://leetcode.com/problems/minimum-cost-to-change-the-final-value-of-expression/description/
 *
 * algorithms
 * Hard (48.41%)
 * Likes:    57
 * Dislikes: 26
 * Total Accepted:    1.2K
 * Total Submissions: 2.4K
 * Testcase Example:  '"1&(0|1)"'
 *
 * You are given a valid boolean expression as a string expression consisting
 * of the characters '1','0','&' (bitwise AND operator),'|' (bitwise OR
 * operator),'(', and ')'.
 * 
 * 
 * For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))",
 * and "1|(0&(1))" are valid expressions.
 * 
 * 
 * Return the minimum cost to change the final value of the expression.
 * 
 * 
 * For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 =
 * 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations so that the new
 * expression evaluates to 0.
 * 
 * 
 * The cost of changing the final value of an expression is the number of
 * operations performed on the expression. The types of operations are
 * described as follows:
 * 
 * 
 * Turn a '1' into a '0'.
 * Turn a '0' into a '1'.
 * Turn a '&' into a '|'.
 * Turn a '|' into a '&'.
 * 
 * 
 * Note: '&' does not take precedence over '|' in the order of calculation.
 * Evaluate parentheses first, then in left-to-right order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: expression = "1&(0|1)"
 * Output: 1
 * Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a
 * '&' using 1 operation.
 * The new expression evaluates to 0. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: expression = "(0&0)&(0&0&0)"
 * Output: 3
 * Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3
 * operations.
 * The new expression evaluates to 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: expression = "(0|(1|0&1))"
 * Output: 1
 * Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
 * The new expression evaluates to 0.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= expression.length <= 10^5
 * expression only contains '1','0','&','|','(', and ')'
 * All parentheses are properly matched.
 * There will be no empty parentheses (i.e: "()" is not a substring of
 * expression).
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minOperationsToFlip(String expression) {
        Stack<int[]> val = new Stack<>();
		Stack<Character> op = new Stack<>();
		char curOp = 0;
		int[] curVal = null;
		for(char c : expression.toCharArray()){
			switch(c){
				case '(':
					val.push(curVal);
					op.push(curOp);	
					curOp = 0;
					curVal = null;
					break;
				case ')':
					if(!val.isEmpty()){
						curVal = calc(val.pop(), curVal, op.pop());
					}
					break;
				case '|':
				case '&':
					curOp = c;
					break;
				default:
					curVal = calc(curVal, new int[]{c == '0' ? 0 : 1, c == '1' ? 0 : 1}, curOp);
					curOp = 0;
					break;
			}
		}
		System.out.println(Arrays.toString(curVal));
		return Math.max(curVal[0], curVal[1]);
    }
    
    int MAX = (int)1e5 + 7;
    
    int[] calc(int[] v1, int[] v2, char op){
    	if(op == 0){
    		return v2;
    	}
    	int[] ret = new int[]{MAX, MAX};
    	if(op == '|'){
    		ret[0] = Math.min(ret[0], v1[0] + v2[0]);
    		ret[0] = Math.min(ret[0], 1 + v1[1] + v2[0]);
    		ret[0] = Math.min(ret[0], 1 + v1[0] + v2[1]);
    		ret[0] = Math.min(ret[0], 1 + v1[0] + v2[0]);
    		ret[1] = Math.min(ret[1], v1[1] + v2[1]);
    		ret[1] = Math.min(ret[1], v1[1] + v2[0]);
    		ret[1] = Math.min(ret[1], v1[0] + v2[1]);
    	}
    	else{
    		ret[0] = Math.min(ret[0], v1[0] + v2[0]);
    		ret[0] = Math.min(ret[0], v1[1] + v2[0]);
    		ret[0] = Math.min(ret[0], v1[0] + v2[1]);
    		ret[1] = Math.min(ret[1], v1[1] + v2[1]);
    		ret[1] = Math.min(ret[1], 1 + v1[1] + v2[0]);
    		ret[1] = Math.min(ret[1], 1 + v1[0] + v2[1]);
    		ret[1] = Math.min(ret[1], 1 + v1[1] + v2[1]);
    	}
    	return ret;
    }
}
// @lc code=end
