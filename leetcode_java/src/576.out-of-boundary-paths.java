/*
 * @lc app=leetcode id=576 lang=java
 *
 * [576] Out of Boundary Paths
 *
 * https://leetcode.com/problems/out-of-boundary-paths/description/
 *
 * algorithms
 * Medium (35.37%)
 * Likes:    611
 * Dislikes: 139
 * Total Accepted:    30.8K
 * Total Submissions: 87K
 * Testcase Example:  '2\n2\n2\n0\n0'
 *
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the
 * ball, you can move the ball to adjacent cell or cross the grid boundary in
 * four directions (up, down, left, right). However, you can at most move N
 * times. Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * Explanation:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findPaths(int m, int n, int N, int x, int y) {
    	int[][] mat = new int[m + 2][n + 2];
    	mat[x + 1][y + 1] = 1;
    	int total = 0;
    	for(int t = 0; t < N; t++){
    		for(int i = 1; i <= m; i++){
    			total = add(total, mat[i][1]);
    			total = add(total, mat[i][n]);
    		}
    		for(int j = 1; j <= n; j++){
    			total = add(total, mat[1][j]);
    			total = add(total, mat[m][j]);		
    		}
    		int[][] newMat = new int[m + 2][n + 2];
    		for(int i = 1; i <= m; i++){
    			for(int j = 1; j <= n; j++){
    				newMat[i][j] = add(newMat[i][j], mat[i - 1][j]);
    				newMat[i][j] = add(newMat[i][j], mat[i + 1][j]);
    				newMat[i][j] = add(newMat[i][j], mat[i][j - 1]);
    				newMat[i][j] = add(newMat[i][j], mat[i][j + 1]);
    			}
    		}
    		mat = newMat;
    	}
    	return total;
    }

    int add(int a, int b){
    	return (a + b) % 1000000007;
    }
}
// @lc code=end
