/*
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (27.01%)
 * Total Accepted:    140K
 * Total Submissions: 513.1K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * 
 * 
 * For example,
 * Given the following matrix:
 * 
 * 
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * 
 * 
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> result = new ArrayList<Integer>();
    	if(matrix != null && matrix.length > 0){
    		spiralOrder(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1, result);	
    	}
        return result;
    }

    private void spiralOrder(int[][] matrix, int top, int left, int bottom, int right, List<Integer> result){
    	if( top > bottom || left > right){
    		return;
    	}
    	else{
    		for (int i = left; i <= right; i++) {
    			result.add(matrix[top][i]);
    		}
    		for (int i = top + 1; i <= bottom; i++) {
    			result.add(matrix[i][right]);	
    		}
    		if(top != bottom){
	    		for (int i = right - 1; i >= left; i--) {
	    			result.add(matrix[bottom][i]);
	    		}	
    		}
    		if(left != right){
    			for (int i = bottom - 1; i >= top + 1; i--) {
	    			result.add(matrix[i][left]);	
	    		}
    		}
    		
    	}
    	spiralOrder(matrix, top + 1, left + 1, bottom - 1, right - 1, result);
    }
}
