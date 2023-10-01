/*
 * @lc app=leetcode id=934 lang=java
 *
 * [934] Shortest Bridge
 *
 * https://leetcode.com/problems/shortest-bridge/description/
 *
 * algorithms
 * Medium (57.75%)
 * Likes:    5086
 * Dislikes: 187
 * Total Accepted:    184.2K
 * Total Submissions: 319K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0
 * represents water.
 * 
 * An island is a 4-directionally connected group of 1's not connected to any
 * other 1's. There are exactly two islands in grid.
 * 
 * You may change 0's to 1's to connect the two islands to form one island.
 * 
 * Return the smallest number of 0's you must flip to connect the two
 * islands.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] is either 0 or 1.
 * There are exactly two islands in grid.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] seen = new boolean[n][m];
        for(int i = 0; i < n; i++){
            boolean found = false;
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    seen[i][j] = true;
                    dfs(new int[]{i, j}, grid, seen);
                    found = true;
                    break;
                }
            }
            if(found){
                break;
            }
        }
        // System.out.println(Arrays.deepToString(seen));
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(seen[i][j]){
                    q.offer(new int[]{i, j});
                }
            }
        }
        int steps= 0;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] pos = q.poll();
                for (int[] dir : dirs) {
                    int[] nxt = {pos[0] + dir[0], pos[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && !seen[nxt[0]][nxt[1]]){
                        if(grid[nxt[0]][nxt[1]] == 1){
                            return steps;
                        }
                        q.offer(nxt);
                        seen[nxt[0]][nxt[1]] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    void dfs(int[] pos, int[][] grid, boolean[][] seen){
        int n = grid.length;
        int m = grid[0].length;
        for (int[] dir : dirs) {
            int[] nxt = {pos[0] + dir[0], pos[1] + dir[1]};
            if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && grid[nxt[0]][nxt[1]] == 1 && !seen[nxt[0]][nxt[1]]){
                seen[nxt[0]][nxt[1]] = true;
                dfs(nxt, grid, seen);
            }
        }
    }
}
// @lc code=end
