/*
 * @lc app=leetcode id=386 lang=java
 *
 * [386] Lexicographical Numbers
 *
 * https://leetcode.com/problems/lexicographical-numbers/description/
 *
 * algorithms
 * Medium (50.14%)
 * Likes:    521
 * Dislikes: 67
 * Total Accepted:    50.8K
 * Total Submissions: 101.3K
 * Testcase Example:  '13'
 *
 * Given an integer n, return 1 - n in lexicographical order.
 * 
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * Please optimize your algorithm to use less time and space. The input size
 * may be as large as 5,000,000.
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = getNumbers(n);
        
        return res;
    }

    void dfs(int n, int cur, List<Integer> result){
    	if(cur > n){
    		return;	
    	}
    	if(cur != 0){
    		result.add(cur);	
    	}
		for(int i = 0; i < 10; i++){
			if(cur == 0){
				if(i > 0){
					dfs(n, cur * 10 + i, result);
				}
			}
			else{
				dfs(n, cur * 10 + i, result);
			}
		}
    }

    List<Integer> getNumbers(int n) {
    	Stack<Integer> s = new Stack<>();
    	List<Integer> res = new ArrayList<>();
    	s.push(0);
    	while(!s.isEmpty()){
    		int top = s.pop();
    		if(top > 0){
    			res.add(top);
    		}
    		for(int i = 9; i >= 0; i--){
    			if(top == 0){
    				if(i > 0){
    					if(top * 10 + i <= n){
    						s.push(top * 10 + i);	
    					}
					}
    			}
    			else{
    				if(top * 10 + i <= n){
						s.push(top * 10 + i);	
					}
    			}
    		}
    	}
    	return res;
    }
}
// @lc code=end
