/*
 * @lc app=leetcode id=2397 lang=java
 *
 * [2397] Maximum Rows Covered by Columns
 *
 * https://leetcode.com/problems/maximum-rows-covered-by-columns/description/
 *
 * algorithms
 * Medium (46.28%)
 * Likes:    84
 * Dislikes: 207
 * Total Accepted:    6.9K
 * Total Submissions: 14.8K
 * Testcase Example:  '[[0,0,0],[1,0,1],[0,1,1],[0,0,1]]\n2'
 *
 * You are given a 0-indexed m x n binary matrix mat and an integer cols, which
 * denotes the number of columns you must choose.
 * 
 * A row is covered by a set of columns if each cell in the row that has a
 * value of 1 also lies in one of the columns of the chosen set.
 * 
 * Return the maximum number of rows that can be covered by a set of cols
 * columns.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: mat = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], cols = 2
 * Output: 3
 * Explanation:
 * As shown in the diagram above, one possible way of covering 3 rows is by
 * selecting the 0th and 2nd columns.
 * It can be shown that no more than 3 rows can be covered, so we return 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: mat = [[1],[0]], cols = 1
 * Output: 2
 * Explanation:
 * Selecting the only column will result in both rows being covered, since the
 * entire matrix is selected.
 * Therefore, we return 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 12
 * mat[i][j] is either 0 or 1.
 * 1 <= cols <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumRows(int[][] mat, int cols) {
        int m = mat.length;
        int n = mat[0].length;
        int ans = 0;
        int[] matB = new int[m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 1){
                    matB[i] |= (1 << j);
                }
            }
        }
        for(int i = 0; i < (1 << n); i++){
            if(Integer.bitCount(i) == cols){
                ans = Math.max(ans, getMaxCoveredRows(matB, i));
            }
        }
        return ans;
    }

    int getMaxCoveredRows(int[] mat, int mask){
        int m = mat.length;
        int ret = 0;
        for(int i = 0; i < m; i++){
            if((mat[i] | mask) == mask) {
                ret++;
            }
        }
        return ret;
    }
}
// @lc code=end
