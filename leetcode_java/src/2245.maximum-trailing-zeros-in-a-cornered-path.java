/*
 * @lc app=leetcode id=2245 lang=java
 *
 * [2245] Maximum Trailing Zeros in a Cornered Path
 *
 * https://leetcode.com/problems/maximum-trailing-zeros-in-a-cornered-path/description/
 *
 * algorithms
 * Medium (32.48%)
 * Likes:    87
 * Dislikes: 265
 * Total Accepted:    4.3K
 * Total Submissions: 13.4K
 * Testcase Example:  '[[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]'
 *
 * You are given a 2D integer array grid of size m x n, where each cell
 * contains a positive integer.
 * 
 * A cornered path is defined as a set of adjacent cells with at most one turn.
 * More specifically, the path should exclusively move either horizontally or
 * vertically up to the turn (if there is one), without returning to a
 * previously visited cell. After the turn, the path will then move exclusively
 * in the alternate direction: move vertically if it moved horizontally, and
 * vice versa, also without returning to a previously visited cell.
 * 
 * The product of a path is defined as the product of all the values in the
 * path.
 * 
 * Return the maximum number of trailing zeros in the product of a cornered
 * path found in grid.
 * 
 * Note:
 * 
 * 
 * Horizontal movement means moving in either the left or right direction.
 * Vertical movement means moving in either the up or down direction.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[23,17,15,3,20],[8,1,20,27,11],[9,4,6,2,21],[40,9,1,10,6],[22,7,4,5,3]]
 * Output: 3
 * Explanation: The grid on the left shows a valid cornered path.
 * It has a product of 15 * 20 * 6 * 1 * 10 = 18000 which has 3 trailing zeros.
 * It can be shown that this is the maximum trailing zeros in the product of a
 * cornered path.
 * 
 * The grid in the middle is not a cornered path as it has more than one turn.
 * The grid on the right is not a cornered path as it requires a return to a
 * previously visited cell.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[4,3,2],[7,6,1],[8,8,8]]
 * Output: 0
 * Explanation: The grid is shown in the figure above.
 * There are no cornered paths in the grid that result in a product with a
 * trailing zero.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxTrailingZeros(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] cnt = new int[n + 1][m + 1][2];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                int[] factors = getFactors(grid[i - 1][j - 1]);
                for(int k = 0; k < 2; k++){
                    cnt[i][j][k] = cnt[i - 1][j][k] + cnt[i][j - 1][k] - cnt[i - 1][j - 1][k] + factors[k];
                }
            }
        }
        // System.out.println(Arrays.deepToString(cnt));
        int max = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                int[][] p = new int[4][2];
                for(int k = 0; k < 2; k++){
                    p[0][k] += cnt[i][j][k] - cnt[i][j - 1][k];
                }

                for(int k = 0; k < 2; k++){
                    p[1][k] += cnt[i][j][k] - cnt[i - 1][j][k];
                }

                for(int k = 0; k < 2; k++){
                    p[2][k] += cnt[i][m][k] - cnt[i][j - 1][k] - (cnt[i - 1][m][k] - cnt[i - 1][j - 1][k]);
                }

                for(int k = 0; k < 2; k++){
                    p[3][k] += cnt[n][j][k] - cnt[i - 1][j][k] - (cnt[n][j - 1][k] - cnt[i - 1][j - 1][k]);
                }
                
                for(int k1 = 0; k1 < 4; k1++){
                    for(int k2 = 0; k2 < 4; k2++){
                        if(k1 != k2){
                            int[] factors = getFactors(grid[i - 1][j - 1]);
                            max = Math.max(max, Math.min(p[k1][0] + p[k2][0] - factors[0], p[k1][1] + p[k2][1] - factors[1]));
                        }
                    }
                }
            }
        }
        return max;
    }

    int[] getFactors(int n){
        int[] factors = new int[2];
        while(n % 2 == 0){
            n /= 2;
            factors[0]++;
        }
        while(n % 5 == 0){
            n /= 5;
            factors[1]++;
        }
        return factors;
    }
}
// @lc code=end
