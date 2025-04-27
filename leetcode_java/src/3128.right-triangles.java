/*
 * @lc app=leetcode id=3128 lang=java
 *
 * [3128] Right Triangles
 *
 * https://leetcode.com/problems/right-triangles/description/
 *
 * algorithms
 * Medium (46.97%)
 * Likes:    118
 * Dislikes: 20
 * Total Accepted:    20.7K
 * Total Submissions: 44.5K
 * Testcase Example:  '[[0,1,0],[0,1,1],[0,1,0]]'
 *
 * You are given a 2D boolean matrix grid.
 * 
 * A collection of 3 elements of grid is a right triangle if one of its
 * elements is in the same row with another element and in the same column with
 * the third element. The 3 elements may not be next to each other.
 * 
 * Return an integer that is the number of right triangles that can be made
 * with 3 elements of grid such that all of them have a value of 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 0
 * 1
 * 1
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 0
 * 1
 * 1
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 0
 * 1
 * 1
 * 
 * 
 * 0
 * 1
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [[0,1,0],[0,1,1],[0,1,0]]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * There are two right triangles with elements of the value 1. Notice that the
 * blue ones do not form a right triangle because the 3 elements are in the
 * same column.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 0
 * 0
 * 
 * 
 * 0
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * There are no right triangles with elements of the value 1.  Notice that the
 * blue ones do not form a right triangle.
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 1
 * 0
 * 1
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 1
 * 0
 * 0
 * 
 * 
 * 
 * 
 * 
 * 
 * Input: grid = [[1,0,1],[1,0,0],[1,0,0]]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * There are two right triangles with elements of the value 1.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length <= 1000
 * 1 <= grid[i].length <= 1000
 * 0 <= grid[i][j] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public long numberOfRightTriangles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] cntRow = new int[n];
        int[] cntCol = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cntRow[i] += grid[i][j];
                cntCol[j] += grid[i][j];
            }
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans += 1L * (cntRow[i] - 1) * (cntCol[j] - 1);
                }
            }
        }
        return ans;
    }
}
// @lc code=end
