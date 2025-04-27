/*
 * @lc app=leetcode id=3127 lang=java
 *
 * [3127] Make a Square with the Same Color
 *
 * https://leetcode.com/problems/make-a-square-with-the-same-color/description/
 *
 * algorithms
 * Easy (51.96%)
 * Likes:    79
 * Dislikes: 8
 * Total Accepted:    30K
 * Total Submissions: 58.1K
 * Testcase Example:  '[["B","W","B"],["B","W","W"],["B","W","B"]]'
 *
 * You are given a 2D matrix grid of size 3 x 3 consisting only of characters
 * 'B' and 'W'. Character 'W' represents the white color, and character 'B'
 * represents the black color.
 * 
 * Your task is to change the color of at most one cell so that the matrix has
 * a 2 x 2 square where all cells are of the same color.
 * 
 * Return true if it is possible to create a 2 x 2 square of the same color,
 * otherwise, return false.
 * 
 * 
 * .grid-container {
 * ⁠ display: grid;
 * ⁠ grid-template-columns: 30px 30px 30px;
 * ⁠ padding: 10px;
 * }
 * .grid-item {
 * ⁠ background-color: black;
 * ⁠ border: 1px solid gray;
 * ⁠ height: 30px;
 * ⁠ font-size: 30px;
 * ⁠ text-align: center;
 * }
 * .grid-item-white {
 * ⁠ background-color: white;
 * }
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [["B","W","B"],["B","W","W"],["B","W","B"]]
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * It can be done by changing the color of the grid[0][2].
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [["B","W","B"],["W","B","W"],["B","W","B"]]
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * It cannot be done by changing at most one cell.
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [["B","W","B"],["B","W","W"],["B","W","W"]]
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * The grid already contains a 2 x 2 square of the same color.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * grid.length == 3
 * grid[i].length == 3
 * grid[i][j] is either 'W' or 'B'.
 * 
 * 
 */

// @lc code=start

import java.util.Map;

class Solution {
    public boolean canMakeSquare(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Map<Character, Integer> mapping = Map.of('W', 1, 'B', -1);
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m - 1; j++) {
                int v = mapping.get(grid[i][j]) + mapping.get(grid[i][j + 1]) + mapping.get(grid[i + 1][j])
                        + mapping.get(grid[i + 1][j + 1]);
                if (Math.abs(v) >= 2) {
                    return true;
                }
            }
        }
        return false;
    }

}
// @lc code=end
