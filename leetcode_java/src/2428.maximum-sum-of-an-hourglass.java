/*
 * @lc app=leetcode id=2428 lang=java
 *
 * [2428] Maximum Sum of an Hourglass
 *
 * https://leetcode.com/problems/maximum-sum-of-an-hourglass/description/
 *
 * algorithms
 * Medium (73.04%)
 * Likes:    122
 * Dislikes: 12
 * Total Accepted:    16.1K
 * Total Submissions: 22K
 * Testcase Example:  '[[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]'
 *
 * You are given an m x n integer matrix grid.
 * 
 * We define an hourglass as a part of the matrix with the following form:
 * 
 * Return the maximum sum of the elements of an hourglass.
 * 
 * Note that an hourglass cannot be rotated and must be entirely contained
 * within the matrix.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[6,2,1,3],[4,2,1,5],[9,2,8,7],[4,1,2,9]]
 * Output: 30
 * Explanation: The cells shown above represent the hourglass with the maximum
 * sum: 6 + 2 + 1 + 2 + 9 + 2 + 8 = 30.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 35
 * Explanation: There is only one hourglass in the matrix, with the sum: 1 + 2
 * + 3 + 5 + 7 + 8 + 9 = 35.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 3 <= m, n <= 150
 * 0 <= grid[i][j] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i + 2 < n && j + 2 < m){
                    int sum = 0;
                    for(int k : new int[]{i, i + 2}){
                        for(int l = j; l <= j + 2; l++){
                            sum += grid[k][l];
                        }
                    }
                    sum += grid[i + 1][j + 1];
                    ans = Math.max(ans, sum);
                }
            }
        }
        return ans;
    }
}
// @lc code=end
