/*
 * @lc app=leetcode id=3195 lang=java
 *
 * [3195] Find the Minimum Area to Cover All Ones I
 *
 * https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/description/
 *
 * algorithms
 * Medium (73.05%)
 * Likes:    478
 * Dislikes: 32
 * Total Accepted:    164K
 * Total Submissions: 210K
 * Testcase Example:  '[[0,1,0],[1,0,1]]'
 *
 * You are given a 2D binary array grid. Find a rectangle with horizontal and
 * vertical sides with the smallest area, such that all the 1's in grid lie
 * inside this rectangle.
 * 
 * Return the minimum possible area of the rectangle.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,1,0],[1,0,1]]
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * 
 * 
 * The smallest rectangle has a height of 2 and a width of 3, so it has an area
 * of 2 * 3 = 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0],[0,0]]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * 
 * 
 * The smallest rectangle has both height and width 1, so its area is 1 * 1 =
 * 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 1000
 * grid[i][j] is either 0 or 1.
 * The input is generated such that there is at least one 1 in grid.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumArea(int[][] grid) {
        return solveCut0(grid, new int[] { 0, 0 }, new int[] { grid.length - 1, grid[0].length - 1 });
    }

    int solveCut0(int[][] grid, int[] topLeft, int[] bottomRight) {
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MIN_VALUE;
        for (int i = topLeft[0]; i <= bottomRight[0]; i++) {
            for (int j = topLeft[1]; j <= bottomRight[1]; j++) {
                if (grid[i][j] == 1) {
                    left = Math.min(left, i);
                    right = Math.max(right, i);
                    top = Math.min(top, j);
                    bottom = Math.max(bottom, j);
                }
            }
        }
        if (left == Integer.MAX_VALUE) {
            return 1;
        }
        return (right - left + 1) * (bottom - top + 1);
    }
}
// @lc code=end
