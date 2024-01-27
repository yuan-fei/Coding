/*
 * @lc app=leetcode id=1072 lang=java
 *
 * [1072] Flip Columns For Maximum Number of Equal Rows
 *
 * https://leetcode.com/problems/flip-columns-for-maximum-number-of-equal-rows/description/
 *
 * algorithms
 * Medium (63.81%)
 * Likes:    724
 * Dislikes: 50
 * Total Accepted:    21.9K
 * Total Submissions: 34.3K
 * Testcase Example:  '[[0,1],[1,1]]'
 *
 * You are given an m x n binary matrix matrix.
 * 
 * You can choose any number of columns in the matrix and flip every cell in
 * that column (i.e., Change the value of the cell from 0 to 1 or vice versa).
 * 
 * Return the maximum number of rows that have all values equal after some
 * number of flips.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[0,1],[1,1]]
 * Output: 1
 * Explanation: After flipping no values, 1 row has all values equal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[0,1],[1,0]]
 * Output: 2
 * Explanation: After flipping values in the first column, both rows have equal
 * values.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: matrix = [[0,0,0],[0,0,1],[1,1,0]]
 * Output: 2
 * Explanation: After flipping values in the first two columns, the last two
 * rows have equal values.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int ans = 0;
        for(int r1 = 0; r1 < n; r1++){
            int c = 1;
            for(int r2 = r1 + 1; r2 < n; r2++){
                if(Arrays.equals(matrix[r1], matrix[r2])){
                    c++;
                }
            }
            for(int i = 0; i < m; i++){
                matrix[r1][i] = 1 - matrix[r1][i];
            }
            for(int r2 = r1 + 1; r2 < n; r2++){
                if(Arrays.equals(matrix[r1], matrix[r2])){
                    c++;
                }
            }
            ans = Math.max(c, ans);
        }
        return ans;
    }
}
// @lc code=end
