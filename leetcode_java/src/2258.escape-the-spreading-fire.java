/*
 * @lc app=leetcode id=2258 lang=java
 *
 * [2258] Escape the Spreading Fire
 *
 * https://leetcode.com/problems/escape-the-spreading-fire/description/
 *
 * algorithms
 * Hard (29.81%)
 * Likes:    129
 * Dislikes: 9
 * Total Accepted:    1.7K
 * Total Submissions: 5.8K
 * Testcase Example:  '[[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]'
 *
 * You are given a 0-indexed 2D integer array grid of size m x n which
 * represents a field. Each cell has one of three values:
 * 
 * 
 * 0 represents grass,
 * 1 represents fire,
 * 2 represents a wall that you and fire cannot pass through.
 * 
 * 
 * You are situated in the top-left cell, (0, 0), and you want to travel to the
 * safehouse at the bottom-right cell, (m - 1, n - 1). Every minute, you may
 * move to an adjacent grass cell. After your move, every fire cell will spread
 * to all adjacent cells that are not walls.
 * 
 * Return the maximum number of minutes that you can stay in your initial
 * position before moving while still safely reaching the safehouse. If this is
 * impossible, return -1. If you can always reach the safehouse regardless of
 * the minutes stayed, return 10^9.
 * 
 * Note that even if the fire spreads to the safehouse immediately after you
 * have reached it, it will be counted as safely reaching the safehouse.
 * 
 * A cell is adjacent to another cell if the former is directly north, east,
 * south, or west of the latter (i.e., their sides are touching).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
 * Output: 3
 * Explanation: The figure above shows the scenario where you stay in the
 * initial position for 3 minutes.
 * You will still be able to safely reach the safehouse.
 * Staying for more than 3 minutes will not allow you to safely reach the
 * safehouse.
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
 * Output: -1
 * Explanation: The figure above shows the scenario where you immediately move
 * towards the safehouse.
 * Fire will spread to any cell you move towards and it is impossible to safely
 * reach the safehouse.
 * Thus, -1 is returned.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
 * Output: 1000000000
 * Explanation: The figure above shows the initial grid.
 * Notice that the fire is contained by walls and you will always be able to
 * safely reach the safehouse.
 * Thus, 10^9 is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 300
 * 4 <= m * n <= 2 * 10^4
 * grid[i][j] is either 0, 1, or 2.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] dirs = {{0, 1},{1, 0},{-1, 0}, {0, -1}};
    public int maximumMinutes(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        bfs1(grid);
        // System.out.println(Arrays.deepToString(grid));
        int low = 0;
        int high = (int)1e9;
        while(low + 1 < high){
            int mid = low + (high - low) / 2;
            // System.out.println(low + ", " + mid + ", " + high);
            if(bfs2(grid, mid)){
                low = mid;
            }
            else{
                high = mid;
            }
        }
        if(bfs2(grid, low)){
            if(bfs2(grid, high)){
                return high;
            }
            else{
                return low;
            }
        }
        else{
            return -1;
        }
    }

    void bfs1(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    q.offer(new int[]{i, j});
                }
            }
        }
        int t = 1;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] cur = q.poll();
                for(int[] dir : dirs){
                    int r = cur[0] + dir[0];
                    int c = cur[1] + dir[1];
                    if(r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 0){
                        grid[r][c] = -t;
                        q.offer(new int[]{r, c});
                    }
                }
            }
            t++;
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 || grid[i][j] == 2){
                    grid[i][j] = 0;
                }
                else if(grid[i][j] == 0){
                    grid[i][j] = (int) 1e9 + n * m + 7 ;
                }
                else{
                    grid[i][j] = -grid[i][j];
                }
            }
        }
    }

    boolean bfs2(int[][] grid, int t){
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        if(t == grid[0][0]){
            return false;
        }
        q.offer(new int[]{0, 0});
        t++;
        boolean[][] seen = new boolean[n][m];
        seen[0][0] = true;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] cur = q.poll();
                if(Arrays.equals(cur, new int[]{n - 1, m - 1})){
                    return true;
                }
                for(int[] dir : dirs){
                    int r = cur[0] + dir[0];
                    int c = cur[1] + dir[1];
                    if(r >= 0 && c >= 0 && r < n && c < m && !seen[r][c] && (t < grid[r][c]) || (r == n - 1 && c == m - 1 && t == grid[r][c]) ){
                        q.offer(new int[]{r, c});
                        seen[r][c] = true;
                    }
                }
            }
            t++;
        }
        return false;
    }
}
// @lc code=end
