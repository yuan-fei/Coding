/*
 * @lc app=leetcode id=1034 lang=java
 *
 * [1034] Coloring A Border
 *
 * https://leetcode.com/problems/coloring-a-border/description/
 *
 * algorithms
 * Medium (49.07%)
 * Likes:    680
 * Dislikes: 855
 * Total Accepted:    32.5K
 * Total Submissions: 66.3K
 * Testcase Example:  '[[1,1],[1,2]]\n0\n0\n3'
 *
 * You are given an m x n integer matrix grid, and three integers row, col, and
 * color. Each value in the grid represents the color of the grid square at
 * that location.
 * 
 * Two squares are called adjacent if they are next to each other in any of the
 * 4 directions.
 * 
 * Two squares belong to the same connected component if they have the same
 * color and they are adjacent.
 * 
 * The border of a connected component is all the squares in the connected
 * component that are either adjacent to (at least) a square not in the
 * component, or on the boundary of the grid (the first or last row or
 * column).
 * 
 * You should color the border of the connected component that contains the
 * square grid[row][col] with color.
 * 
 * Return the final grid.
 * 
 * 
 * Example 1:
 * Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * Output: [[3,3],[3,2]]
 * Example 2:
 * Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * Output: [[1,3,3],[2,3,3]]
 * Example 3:
 * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * Output: [[2,2,2],[2,1,2],[2,2,2]]
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 * 
 * 
 */

// @lc code=start
class Solution {
    boolean[][] seen;
    int[][] grid;
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    List<int[]> boundaries = new ArrayList<>();
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int n = grid.length;
        int m = grid[0].length;
        this.grid = grid;
        seen = new boolean[n][m];
        rec(new int[]{row, col});
        for(int[] pos : boundaries){
            grid[pos[0]][pos[1]] = color;
        }
        return grid;
    }

    void rec(int[] cur){
        int n = grid.length;
        int m = grid[0].length;
        seen[cur[0]][cur[1]] = true;
        boolean isBoundary = false;
        for(int[] dir : dirs){
            int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
            if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && grid[nxt[0]][nxt[1]] == grid[cur[0]][cur[1]]){
                if(!seen[nxt[0]][nxt[1]]){
                    rec(nxt);    
                }
            }
            else{
                isBoundary = true;
            }
        }
        if(isBoundary){
            boundaries.add(cur);
        }
    }
}
// @lc code=end
