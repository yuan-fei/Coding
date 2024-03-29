/*
 * @lc app=leetcode id=2352 lang=java
 *
 * [2352] Equal Row and Column Pairs
 *
 * https://leetcode.com/problems/equal-row-and-column-pairs/description/
 *
 * algorithms
 * Medium (70.47%)
 * Likes:    160
 * Dislikes: 2
 * Total Accepted:    18.4K
 * Total Submissions: 26.1K
 * Testcase Example:  '[[3,2,1],[1,7,6],[2,7,7]]'
 *
 * Given a 0-indexed n x n integer matrix grid, return the number of pairs (Ri,
 * Cj) such that row Ri and column Cj are equal.
 * 
 * A row and column pair is considered equal if they contain the same elements
 * in the same order (i.e. an equal array).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
 * Output: 1
 * Explanation: There is 1 equal row and column pair:
 * - (Row 2, Column 1): [2,7,7]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * Output: 3
 * Explanation: There are 3 equal row and column pairs:
 * - (Row 0, Column 0): [3,1,2,2]
 * - (Row 2, Column 2): [2,4,2,2]
 * - (Row 3, Column 2): [2,4,2,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int[][] tGrid = transpose(grid);
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(Arrays.equals(grid[i], tGrid[j])){
                    ans++;
                }
            }
        }
        return ans;
    }

    int[][] transpose(int[][] g){
        int n = g.length;
        int[][] ret = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ret[i][j] = g[j][i];
            }
        }
        return ret;
    }
}
// @lc code=end
