/*
 * @lc app=leetcode id=118 lang=java
 *
 * [118] Pascal's Triangle
 *
 * https://leetcode.com/problems/pascals-triangle/description/
 *
 * algorithms
 * Easy (49.25%)
 * Likes:    977
 * Dislikes: 85
 * Total Accepted:    315.8K
 * Total Submissions: 641.1K
 * Testcase Example:  '5'
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's
 * triangle.
 * 
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 * 
 * Example:
 * 
 * 
 * Input: 5
 * Output:
 * [
 * ⁠    [1],
 * ⁠   [1,1],
 * ⁠  [1,2,1],
 * ⁠ [1,3,3,1],
 * ⁠[1,4,6,4,1]
 * ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> ans = new ArrayList<>();
    	if(numRows == 0){
    		return ans;
    	}
    	List<Integer> l0 = new ArrayList<>();
    	l0.add(1);
    	ans.add(l0);
        for(int i = 1; i < numRows; i++){
        	List<Integer> l = new ArrayList<>();
        	ans.add(l);
        	for(int j = 0; j <= i; j++){
        		int left = 0;
        		if(j - 1 >= 0){
        			left = ans.get(i - 1).get(j - 1);
        		}
        		int center = 0;
        		if(j <= i - 1){
        			center = ans.get(i - 1).get(j);	
        		}
        		l.add(left + center);
        	}
        }
        return ans;
    }
}
// @lc code=end
