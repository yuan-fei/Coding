/*
 * @lc app=leetcode id=2328 lang=java
 *
 * [2328] Number of Increasing Paths in a Grid
 *
 * https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/description/
 *
 * algorithms
 * Hard (41.52%)
 * Likes:    154
 * Dislikes: 5
 * Total Accepted:    4.4K
 * Total Submissions: 10.7K
 * Testcase Example:  '[[1,1],[3,4]]'
 *
 * You are given an m x n integer matrix grid, where you can move from a cell
 * to any adjacent cell in all 4 directions.
 * 
 * Return the number of strictly increasing paths in the grid such that you can
 * start from any cell and end at any cell. Since the answer may be very large,
 * return it modulo 10^9 + 7.
 * 
 * Two paths are considered different if they do not have exactly the same
 * sequence of visited cells.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,1],[3,4]]
 * Output: 8
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [1], [3], [4].
 * - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
 * - Paths with length 3: [1 -> 3 -> 4].
 * The total number of paths is 4 + 3 + 1 = 8.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1],[2]]
 * Output: 3
 * Explanation: The strictly increasing paths are:
 * - Paths with length 1: [1], [2].
 * - Paths with length 2: [1 -> 2].
 * The total number of paths is 2 + 1 = 3.
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
 * 1 <= grid[i][j] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int MOD = (int)1e9 + 7;
        int[][] dp = new int[n + 2][m + 2];
        TreeMap<Integer, List<int[]>> map = new TreeMap<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!map.containsKey(grid[i][j])){
                    map.put(grid[i][j], new ArrayList<>());
                }
                map.get(grid[i][j]).add(new int[]{i + 1, j + 1});
            }
        }
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int ans = 0;
        for(List<int[]> e : map.values()){
            List<Integer> tmp = new ArrayList<>();
            for(int[] pos : e){
                int x = 0;
                for(int[] d : dirs){
                    x += dp[pos[0] + d[0]][pos[1] + d[1]];
                    x %= MOD;
                }
                x += 1;
                x %= MOD;
                tmp.add(x);
            }
            for(int i = 0; i < e.size(); i++){
                dp[e.get(i)[0]][e.get(i)[1]] = tmp.get(i);
                ans += tmp.get(i);
                ans %= MOD;
            }
        }
        return ans;
    }
}
// @lc code=end
