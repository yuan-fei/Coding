/*
 * @lc app=leetcode id=1568 lang=java
 *
 * [1568] Minimum Number of Days to Disconnect Island
 *
 * https://leetcode.com/problems/minimum-number-of-days-to-disconnect-island/description/
 *
 * algorithms
 * Medium (46.59%)
 * Likes:    50
 * Dislikes: 37
 * Total Accepted:    1.6K
 * Total Submissions: 3.4K
 * Testcase Example:  '[[0,1,1,0],[0,1,1,0],[0,0,0,0]]'
 *
 * Given a 2D grid consisting of 1s (land) and 0s (water).  An island is a
 * maximal 4-directionally (horizontal or vertical) connected group of 1s.
 * 
 * The grid is said to be connected if we have exactly one island, otherwise is
 * said disconnected.
 * 
 * In one day, we are allowed to change any single land cell (1) into a water
 * cell (0).
 * 
 * Return the minimum number of days to disconnect the grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected
 * island.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0
 * islands.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,0,1,0]]
 * Output: 0
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [[1,1,0,1,1],
 * [1,1,1,1,1],
 * [1,1,0,1,1],
 * [1,1,0,1,1]]
 * Output: 1
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: grid = [[1,1,0,1,1],
 * [1,1,1,1,1],
 * [1,1,0,1,1],
 * [1,1,1,1,1]]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] is 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minDays(int[][] grid) {
    	int change = 0;
    	boolean hasOne = false;
    	int n = grid.length;
    	int m = grid[0].length;
    	if(!isConnected(grid)){
    		return 0;
    	}
        for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			if(grid[i][j] == 1){
    				hasOne = true;
    				grid[i][j] = 0;
    				if(!isConnected(grid)){
    					return 1;
    				}
    				grid[i][j] = 1;
    			}
    		}
    	}
    	return hasOne ? 2 : 0;
    }

    boolean isConnected(int[][] grid){
    	int n = grid.length;
    	int m = grid[0].length;
    	boolean[][] visited = new boolean[n][m];
    	int component = 0;
    	boolean hasOne = false;
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			if(!visited[i][j] && grid[i][j] == 1){
    				hasOne = true;
    				dfs(i, j, grid, visited);
    				component++;
    				if(component > 1){
    					return false;
    				}
    			}
    		}
    	}
    	return hasOne;
    }

    void dfs(int r, int c, int[][] grid, boolean[][] visited){
    	int n = grid.length;
    	int m = grid[0].length;
    	visited[r][c] = true;
    	for(int[] d: new int[][]{{0, 1},{1, 0},{-1, 0},{0, -1}}){
    		int rr = r + d[0];
    		int cc = c + d[1];
    		if(rr >= 0 && rr < n && cc >= 0 && cc < m && grid[rr][cc] == 1 && visited[rr][cc] == false){
    			dfs(rr, cc, grid, visited);
    		}
    	}
    }
}
// @lc code=end
