/*
 * @lc app=leetcode id=241 lang=java
 *
 * [241] Different Ways to Add Parentheses
 *
 * https://leetcode.com/problems/different-ways-to-add-parentheses/description/
 *
 * algorithms
 * Medium (52.64%)
 * Likes:    1266
 * Dislikes: 64
 * Total Accepted:    88.1K
 * Total Submissions: 167.1K
 * Testcase Example:  '"2-1-1"'
 *
 * Given a string of numbers and operators, return all possible results from
 * computing all the different possible ways to group numbers and operators.
 * The valid operators are +, - and *.
 * 
 * Example 1:
 * 
 * 
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation: 
 * ((2-1)-1) = 0 
 * (2-(1-1)) = 2
 * 
 * Example 2:
 * 
 * 
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation: 
 * (2*(3-(4*5))) = -34 
 * ((2*3)-(4*5)) = -14 
 * ((2*(3-4))*5) = -10 
 * (2*((3-4)*5)) = -10 
 * (((2*3)-4)*5) = 10
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
    	int l = input.length();
    	dp = new List[l][l];
    	return diffWaysToCompute(input, 0, l - 1); 
    }
    List<Integer>[][] dp; 
    List<Integer> diffWaysToCompute(String input, int l, int r) {
    	if(dp[l][r] == null){
	    	List<Integer> ret = new ArrayList<>();
	        for(int i = l; i <= r; i++){
	        	char c = input.charAt(i);
	        	switch(c){
	        		case '+': 
	        		case '-': 
	        		case '*':  
	        			ret.addAll(merge(diffWaysToCompute(input, l, i - 1), diffWaysToCompute(input, i + 1, r), c));
	        			break;
	        	}
	        }
	        if(ret.size() == 0){
	        	ret.add(Integer.parseInt(input.substring(l, r + 1)));
	        }
	        dp[l][r] = ret;	
    	}
        return dp[l][r];
    }

    List<Integer> merge(List<Integer> s1, List<Integer> s2, char op){
    	List<Integer> s = new ArrayList<>();
    	for(int e1: s1){
    		for(int e2 : s2){
    			switch(op){
    				case '+': 
    					s.add(e1 + e2);
    					break;
	        		case '-': 
	        			s.add(e1 - e2);
    					break;
	        		case '*':  
	        			s.add(e1 * e2);
    					break;
    			}
    			
    		}
    	}
    	return s;
    }
}
// @lc code=end
