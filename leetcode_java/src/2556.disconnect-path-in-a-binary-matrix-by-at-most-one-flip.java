/*
 * @lc app=leetcode id=2556 lang=java
 *
 * [2556] Disconnect Path in a Binary Matrix by at Most One Flip
 *
 * https://leetcode.com/problems/disconnect-path-in-a-binary-matrix-by-at-most-one-flip/description/
 *
 * algorithms
 * Medium (13.96%)
 * Likes:    33
 * Dislikes: 2
 * Total Accepted:    1.7K
 * Total Submissions: 12.4K
 * Testcase Example:  '[[1,1,1],[1,0,0],[1,1,1]]'
 *
 * You are given a 0-indexed m x n binary matrix grid. You can move from a cell
 * (row, col) to any of the cells (row + 1, col) or (row, col + 1) that has the
 * value 1. The matrix is disconnected if there is no path from (0, 0) to (m -
 * 1, n - 1).
 * 
 * You can flip the value of at most one (possibly none) cell. You cannot flip
 * the cells (0, 0) and (m - 1, n - 1).
 * 
 * Return true if it is possible to make the matrix disconnect or false
 * otherwise.
 * 
 * Note that flipping a cell changes its value from 0 to 1 or from 1 to 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,1,1],[1,0,0],[1,1,1]]
 * Output: true
 * Explanation: We can change the cell shown in the diagram above. There is no
 * path from (0, 0) to (2, 2) in the resulting grid.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: false
 * Explanation: It is not possible to change at most one cell such that there
 * is not path from (0, 0) to (2, 2).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 1
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] grid;
    int n;
    int m;
    public boolean isPossibleToCutPath(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        int[][] dirs1 = {{0, 1}, {1, 0}};
        int[][] dirs2 = {{0, -1}, {-1, 0}};
        return !dfs(new int[]{0, 0}, new int[]{n - 1, m - 1}, dirs1, seen) || !dfs(new int[]{n - 1, m - 1}, new int[]{0, 0}, dirs2, seen);
    }

    boolean dfs(int[] cur, int[] target, int[][] dirs, boolean[][] seen){
        if(Arrays.equals(cur, target)){
            seen[cur[0]][cur[1]] = false;
            return true;
        }
        
        boolean ret = false;
        for(int[] dir : dirs){
            int[] next = {cur[0] + dir[0], cur[1] + dir[1]};
            if(0 <= next[0] && next[0] < n && 0 <= next[1] && next[1] < m && !seen[next[0]][next[1]] && grid[next[0]][next[1]] == 1){
                seen[next[0]][next[1]] = true;
                if(dfs(next, target, dirs, seen)){
                    ret = true;
                    break;
                }
            }
        }
        // System.out.println(Arrays.toString(cur) + "->" + Arrays.toString(target) + "=" + ret);
        return ret;
    }
}
// @lc code=end
