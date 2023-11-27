/*
 * @lc app=leetcode id=994 lang=java
 *
 * [994] Rotting Oranges
 *
 * https://leetcode.com/problems/rotting-oranges/description/
 *
 * algorithms
 * Medium (53.58%)
 * Likes:    11859
 * Dislikes: 370
 * Total Accepted:    708.9K
 * Total Submissions: 1.3M
 * Testcase Example:  '[[2,1,1],[1,1,0],[0,1,1]]'
 *
 * You are given an m x n grid where each cell can have one of three
 * values:
 * 
 * 
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * 
 * 
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten
 * orange becomes rotten.
 * 
 * Return the minimum number of minutes that must elapse until no cell has a
 * fresh orange. If this is impossible, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never
 * rotten, because rotting only happens 4-directionally.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the
 * answer is just 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2){
                    q.offer(new int[]{i, j});
                }
            }
        }
        
        int step = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] cur = q.poll();
                for(int[] dir : dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && grid[nxt[0]][nxt[1]] == 1){
                        q.offer(nxt);
                        grid[nxt[0]][nxt[1]] = 2;
                    }
                }
            }
            step++;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    return -1;
                }
            }
        }
        return Math.max(step - 1, 0);
    }
}
// @lc code=end
