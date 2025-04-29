/*
 * @lc app=leetcode id=3142 lang=java
 *
 * [3142] Check if Grid Satisfies Conditions
 *
 * https://leetcode.com/problems/check-if-grid-satisfies-conditions/description/
 *
 * algorithms
 * Easy (43.67%)
 * Likes:    85
 * Dislikes: 3
 * Total Accepted:    38K
 * Total Submissions: 87.7K
 * Testcase Example:  '[[1,0,2],[1,0,2]]'
 *
 * You are given a 2D matrix grid of size m x n. You need to check if each cell
 * grid[i][j] is:
 * 
 * 
 * Equal to the cell below it, i.e. grid[i][j] == grid[i + 1][j] (if it
 * exists).
 * Different from the cell to its right, i.e. grid[i][j] != grid[i][j + 1] (if
 * it exists).
 * 
 * 
 * Return true if all the cells satisfy these conditions, otherwise, return
 * false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,2],[1,0,2]]
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * 
 * 
 * All the cells in the grid satisfy the conditions.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,1],[0,0,0]]
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * 
 * 
 * All cells in the first row are equal.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1],[2],[3]]
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * 
 * 
 * Cells in the first column have different values.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, m <= 10
 * 0 <= grid[i][j] <= 9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public boolean satisfiesConditions(int[][] grid) {
        int[] row0 = grid[0];
        for (int i = 1; i < row0.length; i++) {
            if (row0[i] == row0[i - 1]) {
                return false;
            }
        }
        for (int i = 1; i < grid.length; i++) {
            if (!Arrays.equals(row0, grid[i])) {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
