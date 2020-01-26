/*
 * @lc app=leetcode id=329 lang=java
 *
 * [329] Longest Increasing Path in a Matrix
 *
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/description/
 *
 * algorithms
 * Hard (41.83%)
 * Likes:    1415
 * Dislikes: 23
 * Total Accepted:    116.9K
 * Total Submissions: 279.3K
 * Testcase Example:  '[[9,9,4],[6,6,8],[2,1,1]]'
 *
 * Given an integer matrix, find the length of the longest increasing path.
 * 
 * From each cell, you can either move to four directions: left, right, up or
 * down. You may NOT move diagonally or move outside of the boundary (i.e.
 * wrap-around is not allowed).
 * 
 * Example 1:
 * 
 * 
 * Input: nums = 
 * [
 * ⁠ [9,9,4],
 * ⁠ [6,6,8],
 * ⁠ [2,1,1]
 * ] 
 * Output: 4 
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = 
 * [
 * ⁠ [3,4,5],
 * ⁠ [3,2,6],
 * ⁠ [2,2,1]
 * ] 
 * Output: 4 
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally
 * is not allowed.
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][] mat;
	int[][] maxDist;
	int n;
    int m;
    public int longestIncreasingPath(int[][] matrix) {
    	mat = matrix;
    	n = matrix.length;
    	if(n == 0){
    		return 0;
    	}
    	m = matrix[0].length;
        maxDist = new int[n][m];
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		maxDist[i][j] = -1;
        	}
        }
        int max = 0;
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(maxDist[i][j] == -1){
        			dfs(i, j);	
        		}
        		max = Math.max(maxDist[i][j], max);
        	}
        }
        // System.out.println(Arrays.deepToString(maxDist));
        return max;
    }
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    void dfs(int r, int c){
    	int max = 0;
    	for(int[] dir : dirs){
    		int x = dir[0] + r;
    		int y = dir[1] + c;
    		if(x >= 0 && x < n && y >= 0 && y < m && mat[x][y] > mat[r][c]){
    			if(maxDist[x][y] == -1){
    				dfs(x, y);	
    			}
    			max = Math.max(max, maxDist[x][y]);
    		}
    	}
    	maxDist[r][c] = max + 1;
    }
}
// @lc code=end
