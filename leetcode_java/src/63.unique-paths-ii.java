/*
 * [63] Unique Paths II
 *
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * algorithms
 * Medium (32.09%)
 * Total Accepted:    127.3K
 * Total Submissions: 396.6K
 * Testcase Example:  '[[0]]'
 *
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 * ⁠ [0,0,0],
 * ⁠ [0,1,0],
 * ⁠ [0,0,0]
 * ]
 * 
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0){
    		return 0;
    	}
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	int[][] state = new int[m][n];
    	state[0][0] = (obstacleGrid[0][0] + 1) % 2;
        for (int i = 1; i < m; i++) {
        	state[i][0] = ((state[i - 1][0] == 1) && (obstacleGrid[i][0] == 0))? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
        	state[0][i] = ((state[0][i - 1] == 1) && (obstacleGrid[0][i] == 0))? 1 : 0;
        }
        for(int i = 1; i < m; i++){
        	for (int j = 1; j < n; j++) {
        		if(obstacleGrid[i][j] == 0){
        			state[i][j] = state[i - 1][j] + state[i][j - 1];	
        		}
        		else{
        			state[i][j] = 0;
        		}
        	}
        }
        return state[m - 1][n - 1];
    }
}
