/*
 * @lc app=leetcode id=2290 lang=java
 *
 * [2290] Minimum Obstacle Removal to Reach Corner
 *
 * https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description/
 *
 * algorithms
 * Hard (45.23%)
 * Likes:    266
 * Dislikes: 7
 * Total Accepted:    6.1K
 * Total Submissions: 13.6K
 * Testcase Example:  '[[0,1,1],[1,1,0],[1,1,0]]'
 *
 * You are given a 0-indexed 2D integer array grid of size m x n. Each cell has
 * one of two values:
 * 
 * 
 * 0 represents an empty cell,
 * 1 represents an obstacle that may be removed.
 * 
 * 
 * You can move up, down, left, or right from and to an empty cell.
 * 
 * Return the minimum number of obstacles to remove so you can move from the
 * upper left corner (0, 0) to the lower right corner (m - 1, n - 1).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,1,1],[1,1,0],[1,1,0]]
 * Output: 2
 * Explanation: We can remove the obstacles at (0, 1) and (0, 2) to create a
 * path from (0, 0) to (2, 2).
 * It can be shown that we need to remove at least 2 obstacles, so we return 2.
 * Note that there may be other ways to remove 2 obstacles to create a path.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * Output: 0
 * Explanation: We can move from (0, 0) to (2, 4) without removing any
 * obstacles, so we return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * grid[i][j] is either 0 or 1.
 * grid[0][0] == grid[m - 1][n - 1] == 0
 * 
 * 
 */

// @lc code=start
class Solution {
    private static final int[] d = {0, 1, 0, -1, 0};
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] di : dist) {
            Arrays.fill(di, Integer.MAX_VALUE);
        }
        dist[0][0] = grid[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{dist[0][0], 0,  0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int o = cur[0], r = cur[1], c = cur[2];
            if (r == m - 1 && c == n - 1) {
                return o;
            }
            for (int k = 0; k < 4; ++k) {
                int i = r + d[k], j = c + d[k + 1];
                if (0 <= i && i < m && 0 <= j && j < n && o + grid[i][j] < dist[i][j]) {
                    dist[i][j] = o + grid[i][j];
                    pq.offer(new int[]{dist[i][j], i, j});
                }
            }
        }
        return dist[m - 1][n - 1];
    }
}
// @lc code=end
