/*
 * [64] Minimum Path Sum
 *
 * https://leetcode.com/problems/minimum-path-sum/description/
 *
 * algorithms
 * Medium (40.07%)
 * Total Accepted:    138.7K
 * Total Submissions: 345.7K
 * Testcase Example:  '[[1,3,1],[1,5,1],[4,2,1]]'
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example 1:
 * 
 * [[1,3,1],
 * ⁠[1,5,1],
 * ⁠[4,2,1]]
 * 
 * Given the above grid map, return 7. Because the path 1→3→1→1→1 minimizes the
 * sum.
 * 
 */

/*
Analysis:
DP: f[i][j] = min(f[i-1][j] + grid[i][j] , f[i][j-1] + grid[i][j])
*/
class Solution {
    public int minPathSum(int[][] grid) {
    	if(grid == null || grid.length == 0 ){
    		return -1;
    	}

        int[][] state = new int[grid.length][grid[0].length];
        state[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
        	state[i][0] = state[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
        	state[0][i] = state[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
        	for (int j = 1; j < grid[0].length; j++) {
        		state[i][j] = grid[i][j] + Math.min(state[i - 1][j], state[i][j - 1]);
        	}
    	}
        return state[grid.length - 1][grid[0].length - 1];
    }
}
