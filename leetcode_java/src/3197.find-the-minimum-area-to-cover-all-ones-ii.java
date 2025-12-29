/*
 * @lc app=leetcode id=3197 lang=java
 *
 * [3197] Find the Minimum Area to Cover All Ones II
 *
 * https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii/description/
 *
 * algorithms
 * Hard (35.85%)
 * Likes:    393
 * Dislikes: 66
 * Total Accepted:    64.1K
 * Total Submissions: 100.6K
 * Testcase Example:  '[[1,0,1],[1,1,1]]'
 *
 * You are given a 2D binary array grid. You need to find 3 non-overlapping
 * rectangles having non-zero areas with horizontal and vertical sides such
 * that all the 1's in grid lie inside these rectangles.
 * 
 * Return the minimum possible sum of the area of these rectangles.
 * 
 * Note that the rectangles are allowed to touch.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,1],[1,1,1]]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * The 1's at (0, 0) and (1, 0) are covered by a rectangle of area 2.
 * The 1's at (0, 2) and (1, 2) are covered by a rectangle of area 2.
 * The 1 at (1, 1) is covered by a rectangle of area 1.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0,1,0],[0,1,0,1]]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * The 1's at (0, 0) and (0, 2) are covered by a rectangle of area 3.
 * The 1 at (1, 1) is covered by a rectangle of area 1.
 * The 1 at (1, 3) is covered by a rectangle of area 1.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length, grid[i].length <= 30
 * grid[i][j] is either 0 or 1.
 * The input is generated such that there are at least three 1's in grid.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 1000;

    // 0 1 0
    // 1 0 1
    // 0 1 0
    public int minimumSum(int[][] grid) {
        // solveCut0 : O(n*m)
        // solveCut1 : O((n + m) * n * m)
        // solveCut2 : O((n + m)) * O(solve2 + solve1) = O((n + m)^2 * n * m)
        // return solveCut2(grid, new int[] { 0, 0 }, new int[] { grid.length - 1,
        // grid[0].length - 1 });
        // only work for cut <= 2:
        // https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-ii/solutions/5355610/python-two-cases-split-verticalhorizonta-awju/
        return solveCut(grid, new int[] { 0, 0 }, new int[] { grid.length - 1, grid[0].length - 1 }, 2);
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

    int solveCut(int[][] grid, int[] topLeft, int[] bottomRight, int remainingCut) {
        if (remainingCut == 0) {
            return solveCut0(grid, topLeft, bottomRight);
        }
        int res = MAX;
        for (int i = topLeft[1]; i < bottomRight[1]; i++) {
            for (int cut = 0; cut < remainingCut; cut++) {
                int area1 = solveCut(grid, new int[] { topLeft[0], topLeft[1] }, new int[] { bottomRight[0], i }, cut);
                int area2 = solveCut(grid, new int[] { topLeft[0], i + 1 },
                        new int[] { bottomRight[0], bottomRight[1] },
                        remainingCut - cut - 1);
                res = Math.min(res, area1 + area2);
            }
        }
        for (int j = topLeft[0]; j < bottomRight[0]; j++) {
            for (int cut = 0; cut < remainingCut; cut++) {
                int area1 = solveCut(grid, new int[] { topLeft[0], topLeft[1] }, new int[] { j, bottomRight[1] }, cut);
                int area2 = solveCut(grid, new int[] { j + 1, topLeft[1] },
                        new int[] { bottomRight[0], bottomRight[1] },
                        remainingCut - 1 - cut);
                res = Math.min(res, area1 + area2);
            }
        }
        return res;
    }

}
// @lc code=end
