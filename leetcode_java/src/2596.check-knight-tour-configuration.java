/*
 * @lc app=leetcode id=2596 lang=java
 *
 * [2596] Check Knight Tour Configuration
 *
 * https://leetcode.com/problems/check-knight-tour-configuration/description/
 *
 * algorithms
 * Medium (60.56%)
 * Likes:    58
 * Dislikes: 9
 * Total Accepted:    9.9K
 * Total Submissions: 16.3K
 * Testcase Example:  '[[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]'
 *
 * There is a knight on an n x n chessboard. In a valid configuration, the
 * knight starts at the top-left cell of the board and visits every cell on the
 * board exactly once.
 * 
 * You are given an n x n integer matrix grid consisting of distinct integers
 * from the range [0, n * n - 1] where grid[row][col] indicates that the cell
 * (row, col) is the grid[row][col]^th cell that the knight visited. The moves
 * are 0-indexed.
 * 
 * Return true if grid represents a valid configuration of the knight's
 * movements or false otherwise.
 * 
 * Note that a valid knight move consists of moving two squares vertically and
 * one square horizontally, or two squares horizontally and one square
 * vertically. The figure below illustrates all the possible eight moves of a
 * knight from some cell.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * Output: true
 * Explanation: The above diagram represents the grid. It can be shown that it
 * is a valid configuration.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,3,6],[5,8,1],[2,7,4]]
 * Output: false
 * Explanation: The above diagram represents the grid. The 8^th move of the
 * knight is not valid considering its position after the 7^th move.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * All integers in grid are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkValidGrid(int[][] grid) {
        int n = grid.length;
        int[][] path = new int[n * n][2];
        for (int i = 0; i< n; i++) {
            for(int j = 0; j < n; j++){
                path[grid[i][j]][0] = i;
                path[grid[i][j]][1] = j;
            }
        }
        if(path[0][0] != 0 || path[0][1] != 0){
            return false;
        }
        for(int i = 1; i < path.length; i++){
            int dr = Math.abs(path[i][0] - path[i - 1][0]);
            int dc = Math.abs(path[i][1] - path[i - 1][1]);
            if(dr == 1 && dc == 2){
                continue;
            }
            if(dc == 1 && dr == 2){
                continue;
            }
            return false;
        }
        return true;
    }
}
// @lc code=end
