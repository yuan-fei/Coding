/*
 * @lc app=leetcode id=2133 lang=java
 *
 * [2133] Check if Every Row and Column Contains All Numbers
 *
 * https://leetcode.com/problems/check-if-every-row-and-column-contains-all-numbers/description/
 *
 * algorithms
 * Easy (51.81%)
 * Likes:    253
 * Dislikes: 13
 * Total Accepted:    21.9K
 * Total Submissions: 42.3K
 * Testcase Example:  '[[1,2,3],[3,1,2],[2,3,1]]'
 *
 * An n x n matrix is valid if every row and every column contains all the
 * integers from 1 to n (inclusive).
 * 
 * Given an n x n integer matrix matrix, return true if the matrix is valid.
 * Otherwise, return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the
 * numbers 1, 2, and 3.
 * Hence, we return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
 * Output: false
 * Explanation: In this case, n = 3, but the first row and the first column do
 * not contain the numbers 2 or 3.
 * Hence, we return false.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * 1 <= matrix[i][j] <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkValid(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n; i++){
            Set<Integer> sRow = new HashSet<>();
            Set<Integer> sCol = new HashSet<>();
            for(int j = 0; j < n; j++){
                if(1 > matrix[i][j] || matrix[i][j] > n){
                    return false;
                }
                if(1 > matrix[j][i] || matrix[j][i] > n){
                    return false;
                }
                sRow.add(matrix[i][j]);
                sCol.add(matrix[j][i]);
            }
            if(sRow.size() != n || sCol.size() != n){
                return false;
            }
        }   
        return true;
    }
}
// @lc code=end
