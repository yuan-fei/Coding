/*
 * @lc app=leetcode id=3225 lang=java
 *
 * [3225] Maximum Score From Grid Operations
 *
 * https://leetcode.com/problems/maximum-score-from-grid-operations/description/
 *
 * algorithms
 * Hard (24.35%)
 * Likes:    83
 * Dislikes: 11
 * Total Accepted:    3.3K
 * Total Submissions: 12.3K
 * Testcase Example:  '[[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]'
 *
 * You are given a 2D matrix grid of size n x n. Initially, all cells of the
 * grid are colored white. In one operation, you can select any cell of indices
 * (i, j), and color black all the cells of the j^th column starting from the
 * top row down to the i^th row.
 * 
 * The grid score is the sum of all grid[i][j] such that cell (i, j) is white
 * and it has a horizontally adjacent black cell.
 * 
 * Return the maximum score that can be achieved after some number of
 * operations.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]
 * 
 * Output: 11
 * 
 * Explanation:
 * 
 * In the first operation, we color all cells in column 1 down to row 3, and in
 * the second operation, we color all cells in column 4 down to the last row.
 * The score of the resulting grid is grid[3][0] + grid[1][2] + grid[3][3]
 * which is equal to 11.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid =
 * [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]
 * 
 * Output: 94
 * 
 * Explanation:
 * 
 * We perform operations on 1, 2, and 3 down to rows 1, 4, and 0, respectively.
 * The score of the resulting grid is grid[0][0] + grid[1][0] + grid[2][1] +
 * grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4]
 * which is equal to 94.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == grid.length <= 100
 * n == grid[i].length
 * 0 <= grid[i][j] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        long[][] prefixSum = new long[n][n + 1];
        for (int c = 0; c < n; c++) {
            for (int r = 1; r <= n; r++) {
                prefixSum[c][r] = prefixSum[c][r - 1] + grid[r - 1][c];
            }
        }
        long[][][] dp = new long[n][n + 1][n + 1];
        long max = 0;
        for (int c = 1; c < n; c++) {
            for (int curBlack = 0; curBlack <= n; curBlack++) {
                for (int curWhite = 0; curWhite + curBlack <= n; curWhite++) {
                    if (curWhite == 0) {
                        for (int prevBlack = 0; prevBlack <= curBlack; prevBlack++) {
                            for (int prevWhite = 0; prevWhite + prevBlack <= n; prevWhite++) {
                                long prevTotalScore = dp[c - 1][prevBlack][prevWhite];
                                if (curBlack >= prevBlack + prevWhite) {
                                    prevTotalScore += prefixSum[c - 1][curBlack]
                                            - prefixSum[c - 1][prevBlack + prevWhite];
                                }
                                dp[c][curBlack][curWhite] = Math.max(dp[c][curBlack][curWhite], prevTotalScore);
                            }
                        }
                    } else {
                        int prevBlack = curBlack + curWhite;
                        for (int prevWhite = 0; prevWhite + prevBlack <= n; prevWhite++) {
                            long prevTotalScore = dp[c - 1][prevBlack][prevWhite];
                            dp[c][curBlack][curWhite] = Math.max(dp[c][curBlack][curWhite], prevTotalScore);
                        }
                    }
                    long curWhiteScore = prefixSum[c][curBlack + curWhite] - prefixSum[c][curBlack];
                    dp[c][curBlack][curWhite] += curWhiteScore;
                    max = Math.max(max, dp[c][curBlack][curWhite]);
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return max;
    }
}
// @lc code=end
