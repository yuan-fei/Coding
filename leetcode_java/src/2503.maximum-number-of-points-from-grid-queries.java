/*
 * @lc app=leetcode id=2503 lang=java
 *
 * [2503] Maximum Number of Points From Grid Queries
 *
 * https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/
 *
 * algorithms
 * Hard (26.52%)
 * Likes:    94
 * Dislikes: 3
 * Total Accepted:    1.9K
 * Total Submissions: 7.1K
 * Testcase Example:  '[[1,2,3],[2,5,7],[3,5,1]]\n[5,6,2]'
 *
 * You are given an m x n integer matrix grid and an array queries of size k.
 * 
 * Find an array answer of size k such that for each integer queres[i] you
 * start in the top left cell of the matrix and repeat the following
 * process:
 * 
 * 
 * If queries[i] is strictly greater than the value of the current cell that
 * you are in, then you get one point if it is your first time visiting this
 * cell, and you can move to any adjacent cell in all 4 directions: up, down,
 * left, and right.
 * Otherwise, you do not get any points, and you end this process.
 * 
 * 
 * After the process, answer[i] is the maximum number of points you can get.
 * Note that for each query you are allowed to visit the same cell multiple
 * times.
 * 
 * Return the resulting array answer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
 * Output: [5,8,1]
 * Explanation: The diagrams above show which cells we visit to get points for
 * each query.
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[5,2,1],[1,1,2]], queries = [3]
 * Output: [0]
 * Explanation: We can not get any points because the value of the top left
 * cell is already greater than or equal to 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * k == queries.length
 * 1 <= k <= 10^4
 * 1 <= grid[i][j], queries[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int n = queries.length;
        int[][] queriesWithIndex = new int[n][2];
        for(int i = 0; i < n; i++){
            queriesWithIndex[i][0] = queries[i];
            queriesWithIndex[i][1] = i;
        }
        Arrays.sort(queriesWithIndex, Comparator.comparing(e -> e[0]));
        int[] ans = new int[n];
        int p = grid.length;
        int q = grid[0].length;
        boolean[][] seen = new boolean[p][q];
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(e -> grid[e[0]][e[1]]));
        pq.offer(new int[]{0, 0});
        seen[0][0] = true;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for(int i = 0; i < n; i++){
            while(!pq.isEmpty()){
                int[] cur = pq.peek();
                if(grid[cur[0]][cur[1]] < queriesWithIndex[i][0]){
                    pq.poll();
                    ans[queriesWithIndex[i][1]]++;
                    for(int[] dir : dirs){
                        int[] next = {dir[0] + cur[0], dir[1] + cur[1]};
                        if(0 <= next[0] && next[0] < p && 0 <= next[1] && next[1] < q && !seen[next[0]][next[1]]){
                            pq.offer(next);
                            seen[next[0]][next[1]] = true;
                        }
                    }
                }
                else{
                    break;
                }
            }    
        }
        // System.out.println(Arrays.toString(ans));
        for(int i = 1; i < n; i++){
            ans[queriesWithIndex[i][1]] += ans[queriesWithIndex[i - 1][1]];
        }
        return ans;
    }
}
// @lc code=end
