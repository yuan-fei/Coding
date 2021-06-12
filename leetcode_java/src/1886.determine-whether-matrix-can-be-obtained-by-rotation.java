/*
 * @lc app=leetcode id=1886 lang=java
 *
 * [1886] Determine Whether Matrix Can Be Obtained By Rotation
 *
 * https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/description/
 *
 * algorithms
 * Easy (54.11%)
 * Likes:    95
 * Dislikes: 19
 * Total Accepted:    9.3K
 * Total Submissions: 17.2K
 * Testcase Example:  '[[0,1],[1,0]]\n[[1,0],[0,1]]'
 *
 * Given two n x n binary matrices mat and target, return true if it is
 * possible to make mat equal to target by rotating mat in 90-degree
 * increments, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise to make mat equal
 * target.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
 * Output: false
 * Explanation: It is impossible to make mat equal to target by rotating mat.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise two times to make mat
 * equal target.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == mat.length == target.length
 * n == mat[i].length == target[i].length
 * 1 <= n <= 10
 * mat[i][j] and target[i][j] are either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
    	for(int i = 0; i < 4; i++){
    		if(check(mat, target)){
    			return true;
    		}
    		mat = rotate(mat);
    	}
    	return false;
    }

    int[][] rotate(int[][] mat){
    	int n = mat.length;
    	int[][] ret = new int[n][n];
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			ret[i][j] = mat[j][n - i - 1];
    		}
    	}
    	return ret;
    }

    boolean check(int[][] mat1, int[][] mat2){
    	int n = mat1.length;
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			if(mat1[i][j] != mat2[i][j]){
    				return false;
    			}
    		}
    	}
    	return true;
    }
}
// @lc code=end
