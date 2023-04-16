/*
 * @lc app=leetcode id=2643 lang=java
 *
 * [2643] Row With Maximum Ones
 *
 * https://leetcode.com/problems/row-with-maximum-ones/description/
 *
 * algorithms
 * Easy (74.72%)
 * Likes:    30
 * Dislikes: 0
 * Total Accepted:    12.6K
 * Total Submissions: 16.9K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * Given a m x n binary matrix mat, find the 0-indexed position of the row that
 * contains the maximum count of ones, and the number of ones in that row.
 * 
 * In case there are multiple rows that have the maximum count of ones, the row
 * with the smallest row number should be selected.
 * 
 * Return an array containing the index of the row, and the number of ones in
 * it.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[0,1],[1,0]]
 * Output: [0,1]
 * Explanation: Both rows have the same number of 1's. So we return the index
 * of the smaller row, 0, and the maximum count of ones (1). So, the answer is
 * [0,1]. 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[0,0,0],[0,1,1]]
 * Output: [1,2]
 * Explanation: The row indexed 1 has the maximum count of ones (2). So we
 * return its index, 1, and the count. So, the answer is [1,2].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[0,0],[1,1],[0,0]]
 * Output: [1,2]
 * Explanation: The row indexed 1 has the maximum count of ones (2). So the
 * answer is [1,2].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length 
 * n == mat[i].length 
 * 1 <= m, n <= 100 
 * mat[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] rowAndMaximumOnes(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] ans = {0, 0};
        for(int i = 0; i < n; i++){
            int cnt = 0;
            for(int j = 0; j < m; j++){
                if(mat[i][j] == 1){
                    cnt++;
                }
            }
            if(ans[1] < cnt){
                ans = new int[]{i, cnt};
            }
        }
        return ans;
    }
}
// @lc code=end
