/*
 * @lc app=leetcode id=1254 lang=java
 *
 * [1254] Number of Closed Islands
 *
 * https://leetcode.com/problems/number-of-closed-islands/description/
 *
 * algorithms
 * Medium (59.17%)
 * Likes:    55
 * Dislikes: 2
 * Total Accepted:    3.6K
 * Total Submissions: 6K
 * Testcase Example:  '[[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]'
 *
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a
 * maximal 4-directionally connected group of 0s and a closed island is an
 * island totally (all left, top, right, bottom) surrounded by 1s.
 * 
 * Return the number of closed islands.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid =
 * [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation: 
 * Islands in gray are closed because they are completely surrounded by water
 * (group of 1s).
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * ⁠              [1,1,1,1,1,1,1]]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 * 
 */

// @lc code=start
class Solution {
	boolean[][] visited;
	int[][] g;
	int N;
	int M;
    public int closedIsland(int[][] grid) {
    	g = grid;
    	N = grid.length;
		M = grid[0].length;
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < M; j++){
        		if(i == 0 || j == 0 || i == N - 1 || j == M - 1){
        			if(grid[i][j] == 0 && !visited[i][j]){
        				dfs(i, j);
        			}
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(visited));
        int cnt = 0;
        for(int i = 0; i < N; i++){
        	for(int j = 0; j < M; j++){
        		if(grid[i][j] == 0 && !visited[i][j]){
    				dfs(i, j);
    				cnt++;
    			}
        	}
        }
        return cnt;
    }

    int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    void dfs(int x, int y){
    	visited[x][y] = true;
    	for(int i = 0; i < 4; i++){
    		int xx = x + dir[i][0];
    		int yy = y + dir[i][1];
    		if(xx >= 0 && xx < N && yy >= 0 && yy < M && g[xx][yy] == 0 && !visited[xx][yy]){
    			dfs(xx, yy);
    		}
    	}
    }
}
// @lc code=end
