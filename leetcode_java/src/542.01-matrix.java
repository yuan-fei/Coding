/*
 * @lc app=leetcode id=542 lang=java
 *
 * [542] 01 Matrix
 *
 * https://leetcode.com/problems/01-matrix/description/
 *
 * algorithms
 * Medium (40.10%)
 * Likes:    1738
 * Dislikes: 111
 * Total Accepted:    98.1K
 * Total Submissions: 244.8K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for
 * each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[0,0,0]]
 * 
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * 
 * 
 * Example 2: 
 * 
 * 
 * Input:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,1,1]]
 * 
 * Output:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,2,1]]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
    	int n = matrix.length;
    	int m = matrix[0].length;
        int[][] ans = new int[n][m];
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		ans[i][j] = -1;
        		if(matrix[i][j] == 0){
        			ans[i][j] = 0;
        			q.offer(new int[]{i, j});
        		}
        	}
        }
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int d = 1;
        while(!q.isEmpty()){
        	for(int l = q.size(); l > 0; l--){
	        	int[] cur = q.poll();
	        	for(int[] dir : dirs){
	        		int x = cur[0] + dir[0];
	        		int y = cur[1] + dir[1];
	        		if(x >= 0 && x < n && y >= 0 && y < m && ans[x][y] == -1){
	        			ans[x][y] = d;
	        			q.offer(new int[]{x, y});
	        		}
	        	}	
        	}
        	d++;
        }
        return ans;
    }
}
// @lc code=end
