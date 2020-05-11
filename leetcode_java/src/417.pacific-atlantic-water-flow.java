/*
 * @lc app=leetcode id=417 lang=java
 *
 * [417] Pacific Atlantic Water Flow
 *
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 *
 * algorithms
 * Medium (40.16%)
 * Likes:    1093
 * Dislikes: 220
 * Total Accepted:    68.8K
 * Total Submissions: 171.4K
 * Testcase Example:  '[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]'
 *
 * Given an m x n matrix of non-negative integers representing the height of
 * each unit cell in a continent, the "Pacific ocean" touches the left and top
 * edges of the matrix and the "Atlantic ocean" touches the right and bottom
 * edges.
 * 
 * Water can only flow in four directions (up, down, left, or right) from a
 * cell to another one with height equal or lower.
 * 
 * Find the list of grid coordinates where water can flow to both the Pacific
 * and Atlantic ocean.
 * 
 * Note:
 * 
 * 
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Given the following 5x5 matrix:
 * 
 * ⁠ Pacific ~   ~   ~   ~   ~ 
 * ⁠      ~  1   2   2   3  (5) *
 * ⁠      ~  3   2   3  (4) (4) *
 * ⁠      ~  2   4  (5)  3   1  *
 * ⁠      ~ (6) (7)  1   4   5  *
 * ⁠      ~ (5)  1   1   2   4  *
 * ⁠         *   *   *   *   * Atlantic
 * 
 * Return:
 * 
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with
 * parentheses in above matrix).
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
	boolean[][] tl;
    boolean[][] br;
    int[][] mat;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    	mat = matrix;
    	int n = matrix.length;
    	if(n == 0){
    		return new ArrayList<>();
    	}
    	int m = matrix[0].length;
        tl = new boolean[n][m];
        br = new boolean[n][m];
        for(int i = 0; i < n; i++){
        	tl[i][0] = true;
        	br[i][m - 1] = true;
        }
        for(int i = 0; i < m; i++){
        	tl[0][i] = true;
        	br[n - 1][i] = true;
        }

        bfs(tl);
        bfs(br);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(tl[i][j] && br[i][j]){
        			res.add(Arrays.asList(i, j));
        		}
        	}
        }
        return res;
    }

    void bfs(boolean[][] visited){
    	int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    	int n = mat.length;
    	int m = mat[0].length;
    	Queue<int[]> q = new LinkedList<>();
    	for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(visited[i][j]){
        			q.add(new int[]{i, j});
        		}
        	}
        }
        while(!q.isEmpty()){
        	int[] cur = q.poll();
        	for(int[] d : dirs){
        		int[] nxt = {cur[0] + d[0], cur[1] + d[1]};
        		if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m ){
        			if(mat[nxt[0]][nxt[1]] >= mat[cur[0]][cur[1]] && !visited[nxt[0]][nxt[1]]){
        				visited[nxt[0]][nxt[1]] = true;
        				q.offer(nxt);
        			}
        		}
        	}
        }
    }
}
// @lc code=end
