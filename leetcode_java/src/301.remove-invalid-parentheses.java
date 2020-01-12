/*
 * @lc app=leetcode id=301 lang=java
 *
 * [301] Remove Invalid Parentheses
 *
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 *
 * algorithms
 * Hard (41.28%)
 * Likes:    1963
 * Dislikes: 82
 * Total Accepted:    166.7K
 * Total Submissions: 403.3K
 * Testcase Example:  '"()())()"'
 *
 * Remove the minimum number of invalid parentheses in order to make the input
 * string valid. Return all possible results.
 * 
 * Note: The input string may contain letters other than the parentheses ( and
 * ).
 * 
 * Example 1:
 * 
 * 
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: ")("
 * Output: [""]
 * 
 */

// @lc code=start
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        Stack<Integer> stk = new Stack<>();
        for(int i = 0; i < s.length(); i++){
        	if(s.charAt(i) == '('){
        		stk.push(i);	
        	}
        	else if(s.charAt(i) == ')'){
        		if(!stk.isEmpty() && s.charAt(stk.peek()) == '('){
        			stk.pop();
        		}
        		else{
        			stk.push(i);
        		}
        	}
        }


        int leftPCnt = 0;
        while(!stk.isEmpty() && s.charAt(stk.peek()) == '('){
        	stk.pop();
        	leftPCnt++;
        }


		int rightPCnt = 0;
        while(!stk.isEmpty() && s.charAt(stk.peek()) == ')'){
        	stk.pop();
        	rightPCnt++;
        }

        Set<String> set = new HashSet<>();
        dfs(s, 0, 0, leftPCnt, rightPCnt, "", set);
        return new ArrayList<String>(set);
    }


    void dfs(String s, int pos, int pair, int leftPCnt, int rightPCnt, String cur, Set<String> res){
    	// System.out.println(pos + "," + pair + "," + leftPCnt + "," + rightPCnt + cur);
    	
    	if(pos == s.length()){
    		if(pair == 0 && leftPCnt == 0 && rightPCnt == 0){
    			res.add(cur);
    		}
    		return;
    	}
    	char c = s.charAt(pos);
    	switch(c){
    		case '(': 
    			dfs(s, pos + 1, pair + 1, leftPCnt, rightPCnt, cur + c, res);
    			if(leftPCnt > 0){
    				dfs(s, pos + 1, pair, leftPCnt - 1, rightPCnt, cur, res);	
    			}
    			break;
    		case ')': 
    			if(rightPCnt > 0){
    				dfs(s, pos + 1, pair, leftPCnt, rightPCnt - 1, cur, res);
    			}
    			if(pair > 0){
    				dfs(s, pos + 1, pair - 1, leftPCnt, rightPCnt, cur + c, res);
    			}
    			break;
    		default:
    			dfs(s, pos + 1, pair, leftPCnt, rightPCnt, cur + c, res);
    	}
    }
}
// @lc code=end
