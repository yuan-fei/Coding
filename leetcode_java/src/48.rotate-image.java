/*
 * [48] Rotate Image
 *
 * https://leetcode.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (41.05%)
 * Total Accepted:    157.7K
 * Total Submissions: 377.6K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Note:
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 * 
 * 
 * Example 1:
 * 
 * Given input matrix = 
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 * 
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 * 
 * 
 * 
 * Example 2:
 * 
 * Given input matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ], 
 * 
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 * 
 * 
 */

/*
Analysis:
matrix[i][j] -> matrix[j][n - i] => matrix[i][j] -> matrix[j][i] & reverse(matrix[i])

*/
class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverse(matrix);
    }

    private void swap(int[][] matrix, int i_left, int j_left, int i_right, int j_right){
    	int temp = matrix[i_left][j_left];
    	matrix[i_left][j_left] = matrix[i_right][j_right];
    	matrix[i_right][j_right] = temp;
    }

    private void transpose(int[][] matrix){
    	for (int i = 0; i < matrix.length; i++) {
    		for (int j = 0; j < i; j++) {
    			swap(matrix, i, j, j, i);
    		}
    	}
    }

    private void reverse(int[][] matrix){
    	int i = 0;
    	int j = matrix.length - 1;
    	while(i < j){
    		for (int k = 0; k < matrix.length; k++) {
    			swap(matrix, k, i, k, j);
    		}
    		i++;
    		j--;
    	}
    }
}
