/*
 * [120] Triangle
 *
 * https://leetcode.com/problems/triangle/description/
 *
 * algorithms
 * Medium (34.70%)
 * Total Accepted:    126K
 * Total Submissions: 363.1K
 * Testcase Example:  '[[-10]]'
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step
 * you may move to adjacent numbers on the row below.
 * 
 * 
 * For example, given the following triangle
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * 
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n
 * is the total number of rows in the triangle.
 * 
 */

/*
Analysis:
DP: f[i][j] = min(f[i - 1][j], f[i - 1][j - 1]) + t[i][j]
*/
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
    	if(triangle == null || triangle.size() == 0){
    		return -1;
    	}

    	Integer[][] state = new Integer[triangle.size()][];

        for (int i = 0; i < triangle.size(); i++) {	
        	state[i] = new Integer[triangle.get(i).size()];
        }

        state[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i++) {	
        	for (int j = 0; j < i + 1; j++) {
        		if(j == 0){
        			state[i][j] = state[i - 1][j] + triangle.get(i).get(j);
        		}
        		else if (j == i){
        			state[i][j] = state[i - 1][j - 1] + triangle.get(i).get(j);
        		}
        		else{
        			state[i][j] = Math.min(state[i - 1][j], state[i - 1][j - 1]) + triangle.get(i).get(j);
        		}
        	}
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0 ; i < state.length; i++) {
        	min = Math.min(min, state[state.length - 1][i]);
        }
        return min;
    }
}
