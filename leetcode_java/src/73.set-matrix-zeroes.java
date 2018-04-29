/*
 * [73] Set Matrix Zeroes
 *
 * https://leetcode.com/problems/set-matrix-zeroes/description/
 *
 * algorithms
 * Medium (36.42%)
 * Total Accepted:    136.9K
 * Total Submissions: 375K
 * Testcase Example:  '[[0]]'
 *
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to
 * 0. Do it in place.
 * 
 * 
 * click to show follow up.
 * 
 * Follow up:
 * 
 * 
 * Did you use extra space?
 * A straight forward solution using O(mn) space is probably a bad idea.
 * A simple improvement uses O(m + n) space, but still not the best solution.
 * Could you devise a constant space solution?
 * 
 * 
 */
class Solution {
    public void setZeroes(int[][] matrix) {
    	int mode = -1;
        for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix[i].length; j++) {
        		if(matrix[i][j] == 0){
        			matrix[i][0] = 0;
        			if(i == 0){
        				switch(mode){
        					case -1: mode = 1;
        							break;
        					case 2: mode += 1;
        							break;
        				}
        			}
        			matrix[0][j] = 0;
        			if(j == 0){
        				switch(mode){
        					case -1: mode = 2;
        							break;
        					case 1: mode += 2;
        							break;
        				}
        			}
        		}
        	}
        }
        for (int i = matrix.length - 1; i >= 0; i--) {
        	for (int j = matrix[i].length - 1; j >= 0; j--) {
        		if(matrix[i][0] == 0 || matrix[0][j] == 0){
        			if(i == 0 && (mode == 2 || mode == -1)){
        				continue;
        			}
        			if(j == 0 && (mode == 1 || mode == -1)){
        				continue;
        			}
        			matrix[i][j] = 0;
        		}
        	}
        }
    }
}
