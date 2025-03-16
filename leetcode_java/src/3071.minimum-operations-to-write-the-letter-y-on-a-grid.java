/*
 * @lc app=leetcode id=3071 lang=java
 *
 * [3071] Minimum Operations to Write the Letter Y on a Grid
 *
 * https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/description/
 *
 * algorithms
 * Medium (62.30%)
 * Likes:    110
 * Dislikes: 25
 * Total Accepted:    21.9K
 * Total Submissions: 35.2K
 * Testcase Example:  '[[1,2,2],[1,1,0],[0,1,0]]'
 *
 * You are given a 0-indexed n x n grid where n is odd, and grid[r][c] is 0, 1,
 * or 2.
 * 
 * We say that a cell belongs to the Letter Y if it belongs to one of the
 * following:
 * 
 * 
 * The diagonal starting at the top-left cell and ending at the center cell of
 * the grid.
 * The diagonal starting at the top-right cell and ending at the center cell of
 * the grid.
 * The vertical line starting at the center cell and ending at the bottom
 * border of the grid.
 * 
 * 
 * The Letter Y is written on the grid if and only if:
 * 
 * 
 * All values at cells belonging to the Y are equal.
 * All values at cells not belonging to the Y are equal.
 * The values at cells belonging to the Y are different from the values at
 * cells not belonging to the Y.
 * 
 * 
 * Return the minimum number of operations needed to write the letter Y on the
 * grid given that in one operation you can change the value at any cell to 0,
 * 1, or 2.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,2,2],[1,1,0],[0,1,0]]
 * Output: 3
 * Explanation: We can write Y on the grid by applying the changes highlighted
 * in blue in the image above. After the operations, all cells that belong to
 * Y, denoted in bold, have the same value of 1 while those that do not belong
 * to Y are equal to 0.
 * It can be shown that 3 is the minimum number of operations needed to write Y
 * on the grid.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
 * Output: 12
 * Explanation: We can write Y on the grid by applying the changes highlighted
 * in blue in the image above. After the operations, all cells that belong to
 * Y, denoted in bold, have the same value of 0 while those that do not belong
 * to Y are equal to 2. 
 * It can be shown that 12 is the minimum number of operations needed to write
 * Y on the grid.
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n <= 49 
 * n == grid.length == grid[i].length
 * 0 <= grid[i][j] <= 2
 * n is odd.
 * 
 * 
 */

// @lc code=start

import java.util.stream.IntStream;

class Solution {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        return IntStream.rangeClosed(0, 2)
                    .flatMap(i -> IntStream.rangeClosed(0, 2).filter(j -> i != j).map(j -> countChanges(grid, i, j)))
                    .min().getAsInt();
    }

    int countChanges(int[][] grid, int expectedY, int expectedNonY){
        int ret = (int) IntStream.range(0, grid.length)
                        .flatMap(i -> IntStream.range(0, grid.length)
                                                .filter(j -> mismatch(i, j, grid, expectedY, expectedNonY)))
                        .count();
        // System.out.println(Arrays.asList(expectedY, expectedNonY, ret));
        return ret;
    }

    boolean mismatch(int r, int c, int[][] grid, int expectedY, int expectedNonY){
        return (isOnY(r, c, grid.length) && expectedY != grid[r][c]) || (!isOnY(r, c, grid.length) && expectedNonY != grid[r][c]);
    }

    boolean isOnY(int r, int c, int n){
        return (r == c && r <= n / 2) || (r + c == n - 1 && r <= n / 2) || (c == n / 2 && r >= n / 2);
    }
}
// @lc code=end
