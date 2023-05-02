/*
 * @lc app=leetcode id=2658 lang=java
 *
 * [2658] Maximum Number of Fish in a Grid
 *
 * https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/
 *
 * algorithms
 * Medium (56.24%)
 * Likes:    128
 * Dislikes: 12
 * Total Accepted:    9.4K
 * Total Submissions: 16.5K
 * Testcase Example:  '[[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]'
 *
 * You are given a 0-indexed 2D matrix grid of size m x n, where (r, c)
 * represents:
 * 
 * 
 * A land cell if grid[r][c] = 0, or
 * A water cell containing grid[r][c] fish, if grid[r][c] > 0.
 * 
 * 
 * A fisher can start at any water cell (r, c) and can do the following
 * operations any number of times:
 * 
 * 
 * Catch all the fish at cell (r, c), or
 * Move to any adjacent water cell.
 * 
 * 
 * Return the maximum number of fish the fisher can catch if he chooses his
 * starting cell optimally, or 0 if no water cell exists.
 * 
 * An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c -
 * 1), (r + 1, c) or (r - 1, c) if it exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,2,1,0],[4,0,0,3],[1,0,0,4],[0,3,2,0]]
 * Output: 7
 * Explanation: The fisher can start at cell (1,3) and collect 3 fish, then
 * move to cell (2,3) and collect 4 fish.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,0],[0,0,0,1]]
 * Output: 1
 * Explanation: The fisher can start at cells (0,0) or (3,3) and collect a
 * single fish. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * 0 <= grid[i][j] <= 10
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {-1,0}, {1, 0}};
    public int findMaxFish(int[][] grid) {
        int ans = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] > 0){
                    ans = Math.max(dfs(grid, new int[]{i, j}), ans);
                }
            }
        }
        return ans;
    }

    int dfs(int[][] grid, int[] pos){
        int ret = grid[pos[0]][pos[1]];
        grid[pos[0]][pos[1]] = 0;
        for(int[] dir : dirs){
            int[] nxt = new int[]{dir[0] + pos[0], dir[1] + pos[1]};
            if(0 <= nxt[0] && nxt[0] < grid.length && 0 <= nxt[1] && nxt[1] < grid[0].length && grid[nxt[0]][nxt[1]] > 0){
                ret += dfs(grid, nxt);
            }
        }
        return ret;
    }
}
// @lc code=end
