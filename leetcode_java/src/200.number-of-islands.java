/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 *
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (42.92%)
 * Total Accepted:    430.4K
 * Total Submissions: 1M
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * Output: 3
 * 
 */
class Solution {
    public int numIslands(char[][] grid) {
    	int N = grid.length;
    	if(N == 0){
    		return 0;
    	}
    	int M = grid[0].length;
    	int cnt = 0;
        for (int x = 0; x < N; x++) {
        	for (int y = 0; y < M; y++) {
        		if(grid[x][y] == '1'){
        			cnt++;
        			dfs(grid, x, y);
					// System.out.println(Arrays.deepToString(grid));
        		}
        	}
        }
        return cnt;
    }

    void dfs(char[][] grid, int x, int y){
    	int N = grid.length;
    	int M = grid[0].length;
    	int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
    	grid[x][y] = '0';
    	for (int[] d : dir) {
    		int xx = x + d[0];
    		int yy = y + d[1];
    		if(xx >= 0 && yy >= 0 && xx < N && yy < M && grid[xx][yy] == '1'){
    			dfs(grid, xx, yy);
    		}
    	}
    }
}
