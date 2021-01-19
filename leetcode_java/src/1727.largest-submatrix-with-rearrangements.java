/*
 * @lc app=leetcode id=1727 lang=java
 *
 * [1727] Largest Submatrix With Rearrangements
 *
 * https://leetcode.com/problems/largest-submatrix-with-rearrangements/description/
 *
 * algorithms
 * Medium (56.37%)
 * Likes:    212
 * Dislikes: 7
 * Total Accepted:    4.7K
 * Total Submissions: 8.3K
 * Testcase Example:  '[[0,0,1],[1,1,1],[1,0,1]]'
 *
 * You are given a binary matrix matrix of size m x n, and you are allowed to
 * rearrange the columns of the matrix in any order.
 * 
 * Return the area of the largest submatrix within matrix where every element
 * of the submatrix is 1 after reordering the columns optimally.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
 * Output: 4
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: matrix = [[1,0,1,0,1]]
 * Output: 3
 * Explanation: You can rearrange the columns as shown above.
 * The largest submatrix of 1s, in bold, has an area of 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: matrix = [[1,1,0],[1,0,1]]
 * Output: 2
 * Explanation: Notice that you must rearrange entire columns, and there is no
 * way to make a submatrix of 1s larger than an area of 2.
 * 
 * Example 4:
 * 
 * 
 * Input: matrix = [[0,0],[0,0]]
 * Output: 0
 * Explanation: As there are no 1s, no submatrix of 1s can be formed and the
 * area is 0.
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m * n <= 10^5
 * matrix[i][j] is 0 or 1.
 * 
 */

// @lc code=start
class Solution {
    public int largestSubmatrix(int[][] matrix) {
    	int n = matrix.length;
    	int m = matrix[0].length;
    	int max = 0;
    	for(int j = n - 1; j >= 0; j--){
    		PriorityQueue<Integer> q = new PriorityQueue<>();
	    	for(int i = 0; i < m; i++){	
	    		if(j < n - 1 && matrix[j][i] == 1){
	    			matrix[j][i] += matrix[j + 1][i];	
	    		}
	    		if(matrix[j][i] > 0){
	    			q.offer(matrix[j][i]);	
	    		}
	        }	
	        // System.out.println(q);
	        for(int l = q.size(); l > 0; l--){
	        	max = Math.max(max, l * q.poll());
	        }
    	}
        return max;
    }
}
// @lc code=end
