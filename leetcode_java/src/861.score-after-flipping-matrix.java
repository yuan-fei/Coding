/*
 * @lc app=leetcode id=861 lang=java
 *
 * [861] Score After Flipping Matrix
 *
 * https://leetcode.com/problems/score-after-flipping-matrix/description/
 *
 * algorithms
 * Medium (74.87%)
 * Likes:    1383
 * Dislikes: 171
 * Total Accepted:    41.4K
 * Total Submissions: 55.2K
 * Testcase Example:  '[[0,0,1,1],[1,0,1,0],[1,1,0,0]]'
 *
 * You are given an m x n binary matrix grid.
 * 
 * A move consists of choosing any row or column and toggling each value in
 * that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
 * 
 * Every row of the matrix is interpreted as a binary number, and the score of
 * the matrix is the sum of these numbers.
 * 
 * Return the highest possible score after making any number of moves
 * (including zero moves).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * Output: 39
 * Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid[i][j] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int matrixScore(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for(int i = 0; i < n; i++){
            if(grid[i][0] == 0){
                for(int j = 0; j < m; j++){
                    grid[i][j] = 1 - grid[i][j];
                }    
            }
        }
        // System.out.println(Arrays.deepToString(grid));
        int ans = 0;
        for(int j = 0; j < m; j++){
            int cnt = 0;
            for(int i = 0; i < n; i++){
                cnt += grid[i][j];
            }
            cnt = Math.max(cnt, n - cnt);
            ans <<= 1;
            ans += cnt;
        }
        return ans;
    }
}
// @lc code=end
