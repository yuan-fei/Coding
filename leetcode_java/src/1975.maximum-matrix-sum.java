/*
 * @lc app=leetcode id=1975 lang=java
 *
 * [1975] Maximum Matrix Sum
 *
 * https://leetcode.com/problems/maximum-matrix-sum/description/
 *
 * algorithms
 * Medium (42.04%)
 * Likes:    134
 * Dislikes: 5
 * Total Accepted:    5.6K
 * Total Submissions: 13.4K
 * Testcase Example:  '[[1,-1],[-1,1]]'
 *
 * You are given an n x n integer matrix. You can do the following operation
 * any number of times:
 * 
 * 
 * Choose any two adjacent elements of matrix and multiply each of them by
 * -1.
 * 
 * 
 * Two elements are considered adjacent if and only if they share a border.
 * 
 * Your goal is to maximize the summation of the matrix's elements. Return the
 * maximum sum of the matrix's elements using the operation mentioned above.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,-1],[-1,1]]
 * Output: 4
 * Explanation: We can follow the following steps to reach sum equals 4:
 * - Multiply the 2 elements in the first row by -1.
 * - Multiply the 2 elements in the first column by -1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,2,3],[-1,-2,-3],[1,2,3]]
 * Output: 16
 * Explanation: We can follow the following step to reach sum equals 16:
 * - Multiply the 2 last elements in the second row by -1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == matrix.length == matrix[i].length
 * 2 <= n <= 250
 * -10^5 <= matrix[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int min = 100000;
        long sum = 0;
        int negCount = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                min = Math.min(Math.abs(matrix[i][j]), min);
                if(matrix[i][j] < 0){
                    negCount++;
                }
                sum += Math.abs(matrix[i][j]);
            }
        }
        return (negCount % 2 == 0) ? sum : sum - 2 * min;
    }
}
// @lc code=end
