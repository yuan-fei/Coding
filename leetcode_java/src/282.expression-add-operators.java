/*
 * @lc app=leetcode id=282 lang=java
 *
 * [282] Expression Add Operators
 *
 * https://leetcode.com/problems/expression-add-operators/description/
 *
 * algorithms
 * Hard (34.38%)
 * Likes:    928
 * Dislikes: 137
 * Total Accepted:    85.2K
 * Total Submissions: 247.5K
 * Testcase Example:  '"123"\n6'
 *
 * Given a string that contains only digits 0-9 and a target value, return all
 * possibilities to add binary operators (not unary) +, -, or * between the
 * digits so they evaluate to the target value.
 * 
 * Example 1:
 * 
 * 
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"] 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * 
 * Example 3:
 * 
 * 
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * 
 * Example 4:
 * 
 * 
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: num = "3456237490", target = 9191
 * Output: []
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, 0, 0, 0, "", ans);
        return ans;
    }

    void dfs(String num, int target, int pos, long acc, long mulAcc, String exp, List<String> res){
    	// System.out.println(pos + ", "+ acc +", "+ mulAcc +", "+ exp);
    	if(pos == num.length()){
    		if(acc == target){
    			res.add(exp);
    		}
    		return;
    	}

    	long d = 0;
    	for(int i = pos; i < num.length(); i++){
    		d *= 10;
    		d += num.charAt(i) - '0';
    		if(pos == 0){
	    		dfs(num, target, i + 1, d, d, "" + d, res);
    		}
    		else{
	    		dfs(num, target, i + 1, acc + d, d, exp + "+" + d, res);
	    		dfs(num, target, i + 1, acc - d, -d, exp + "-" + d, res);
	    		dfs(num, target, i + 1, acc - mulAcc + mulAcc * d, mulAcc * d, exp + "*" + d, res);	
    		}
    		if(d == 0){
    			break;
    		}
    	}
    }
}
// @lc code=end
