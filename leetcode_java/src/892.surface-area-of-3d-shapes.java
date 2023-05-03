/*
 * @lc app=leetcode id=892 lang=java
 *
 * [892] Surface Area of 3D Shapes
 *
 * https://leetcode.com/problems/surface-area-of-3d-shapes/description/
 *
 * algorithms
 * Easy (64.15%)
 * Likes:    502
 * Dislikes: 678
 * Total Accepted:    34.2K
 * Total Submissions: 53.4K
 * Testcase Example:  '[[1,2],[3,4]]'
 *
 * You are given an n x n grid where you have placed some 1 x 1 x 1 cubes. Each
 * value v = grid[i][j] represents a tower of v cubes placed on top of cell (i,
 * j).
 * 
 * After placing these cubes, you have decided to glue any directly adjacent
 * cubes to each other, forming several irregular 3D shapes.
 * 
 * Return the total surface area of the resulting shapes.
 * 
 * Note: The bottom face of each shape counts toward its surface area.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,2],[3,4]]
 * Output: 34
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 32
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 46
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] g = new int[n + 2][m + 2];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                g[i][j] = grid[i - 1][j - 1];
            }
        }
        int ans = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(g[i][j] > 0){
                    ans += Math.max(g[i][j] - g[i - 1][j], 0);
                    ans += Math.max(g[i][j] - g[i + 1][j], 0);
                    ans += Math.max(g[i][j] - g[i][j + 1], 0);
                    ans += Math.max(g[i][j] - g[i][j - 1], 0);
                    ans += 2;    
                }
            }
        }
        return ans;
    }
}
// @lc code=end
