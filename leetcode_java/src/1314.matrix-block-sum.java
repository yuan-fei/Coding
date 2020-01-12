/*
 * @lc app=leetcode id=1314 lang=java
 *
 * [1314] Matrix Block Sum
 *
 * https://leetcode.com/problems/matrix-block-sum/description/
 *
 * algorithms
 * Medium (68.07%)
 * Likes:    13
 * Dislikes: 0
 * Total Accepted:    2K
 * Total Submissions: 2.9K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]\n1'
 *
 * Given a m * n matrix mat and an integer K, return a matrix answer where each
 * answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j
 * - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n, K <= 100
 * 1 <= mat[i][j] <= 100
 * 
 */

// @lc code=start
class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
    	int n = mat.length;
    	if(n == 0){
    		return mat;
    	}
    	int m = mat[0].length;
    	int[][] pSum = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		pSum[i][j] = pSum[i][j - 1] + pSum[i - 1][j] - pSum[i - 1][j - 1] + mat[i - 1][j - 1];
        	}
        }

        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		int r1 = Math.max(i - K, 0);
        		int c1 = Math.max(j - K, 0);
        		int r2 = Math.min(i + K, n - 1);
        		int c2 = Math.min(j + K, m - 1);
        		mat[i][j] = pSum[r2 + 1][c2 + 1] - pSum[r2 + 1][c1] - pSum[r1][c2 + 1] + pSum[r1][c1];
        	}
        }
        return mat;
    }
}
// @lc code=end
