/*
 * @lc app=leetcode id=498 lang=java
 *
 * [498] Diagonal Traverse
 *
 * https://leetcode.com/problems/diagonal-traverse/description/
 *
 * algorithms
 * Medium (48.17%)
 * Likes:    788
 * Dislikes: 343
 * Total Accepted:    86.3K
 * Total Submissions: 179.1K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a matrix of M x N elements (M rows, N columns), return all elements of
 * the matrix in diagonal order as shown in the below image.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 4, 5, 6 ],
 * ⁠[ 7, 8, 9 ]
 * ]
 * 
 * Output:  [1,2,4,7,5,3,6,8,9]
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The total number of elements of the given matrix will not exceed 10,000.
 * 
 */

// @lc code=start
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
    	int n = matrix.length;
    	if(n == 0){
    		return new int[0];
    	}
    	int m = matrix[0].length;
    	int[] ret = new int[n * m];
    	int k = 0;
        for(int sum = 0; sum < n + m - 1; sum++){
        	if(sum % 2 == 1){
	        	int r = Math.max(sum - m + 1, 0);
	        	int c = Math.min(sum, m - 1);
	        	while(r < n && c >= 0){
	        		ret[k++] = matrix[r][c];
	        		r += 1;
	        		c -= 1;
	        	}	
        	}
        	else{
        		int r = Math.min(sum, n - 1);
	        	int c = Math.max(sum - n + 1, 0);
	        	while(r >= 0 && c < m){
	        		ret[k++] = matrix[r][c];
	        		r -= 1;
	        		c += 1;
	        	}
        	}
        }
        return ret;
    }
}
// @lc code=end
