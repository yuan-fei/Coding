/*
 * [22] Generate Parentheses
 *
 * https://leetcode.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (47.33%)
 * Total Accepted:    198.4K
 * Total Submissions: 416.5K
 * Testcase Example:  '3'
 *
 * 
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * 
 * 
 * For example, given n = 3, a solution set is:
 * 
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 */
class Solution {
    public List<String> generateParenthesis(int n) {
    	List<String> result = new ArrayList<String>();
        generateParenthesis(0, 0, n, result, "");
        return result;
    }

    private void generateParenthesis(int numOfOpenParenthesis, int numOfCloseParenthesis, int n, List<String> result, String current){
    	if(numOfCloseParenthesis == n){
    		result.add(current);
    		return;
    	}
    	if(numOfCloseParenthesis + numOfOpenParenthesis < n){
    		generateParenthesis(numOfOpenParenthesis + 1, numOfCloseParenthesis, n, result, current + "(");
    	}
    	if (numOfOpenParenthesis > 0) {
    		generateParenthesis(numOfOpenParenthesis - 1, numOfCloseParenthesis + 1, n, result, current + ")");	
    	}
    }
}
