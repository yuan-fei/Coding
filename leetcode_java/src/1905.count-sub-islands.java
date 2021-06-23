/*
 * @lc app=leetcode id=1905 lang=java
 *
 * [1905] Count Sub Islands
 *
 * https://leetcode.com/problems/count-sub-islands/description/
 *
 * algorithms
 * Medium (59.87%)
 * Likes:    215
 * Dislikes: 11
 * Total Accepted:    7.5K
 * Total Submissions: 12.5K
 * Testcase Example:  '[[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]]\n' +
  '[[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]'
 *
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's
 * (representing water) and 1's (representing land). An island is a group of
 * 1's connected 4-directionally (horizontal or vertical). Any cells outside of
 * the grid are considered water cells.
 * 
 * An island in grid2 is considered a sub-island if there is an island in grid1
 * that contains all the cells that make up this island in grid2.
 * 
 * Return the number of islands in grid2 that are considered sub-islands.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid1 =
 * [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 =
 * [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * Output: 3
 * Explanation: In the picture above, the grid on the left is grid1 and the
 * grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island.
 * There are three sub-islands.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid1 =
 * [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 =
 * [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * Output: 2 
 * Explanation: In the picture above, the grid on the left is grid1 and the
 * grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island.
 * There are two sub-islands.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] and grid2[i][j] are either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
	boolean[][] visited;
	int n,m;
	int[][] grid1;
	int[][] grid2;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
    	this.grid1 = grid1;
    	this.grid2 = grid2;
    	n = grid2.length;
    	m = grid2[0].length;
        visited = new boolean[n][m];
        int cnt = 0;
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(!visited[i][j] && grid2[i][j] == 1 && dfs(i, j)){
        			cnt++;
        		}
        	}
        }
        return cnt;
    }

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean dfs(int r, int c){
    	visited[r][c] = true;
    	boolean ret = true;
    	if(grid1[r][c] == 0){
    		ret = false;
    	}
    	for(int[] dir : dirs){
    		int newR = r + dir[0];
    		int newC = c + dir[1];
    		if(newR >= 0 && newR < n & newC >= 0 && newC < m && grid2[newR][newC] == 1 && !visited[newR][newC]){
    			ret &= dfs(newR, newC);
    		}
    	}
    	return ret;
    }
}
// @lc code=end
