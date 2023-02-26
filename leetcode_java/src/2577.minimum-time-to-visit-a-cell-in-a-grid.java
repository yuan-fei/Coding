/*
 * @lc app=leetcode id=2577 lang=java
 *
 * [2577] Minimum Time to Visit a Cell In a Grid
 *
 * https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/
 *
 * algorithms
 * Hard (22.89%)
 * Likes:    64
 * Dislikes: 1
 * Total Accepted:    1.5K
 * Total Submissions: 6.9K
 * Testcase Example:  '[[0,1,3,2],[5,1,2,5],[4,3,8,6]]'
 *
 * You are given a m x n matrix grid consisting of non-negative integers where
 * grid[row][col] represents the minimum time required to be able to visit the
 * cell (row, col), which means you can visit the cell (row, col) only when the
 * time you visit it is greater than or equal to grid[row][col].
 * 
 * You are standing in the top-left cell of the matrix in the 0^th second, and
 * you must move to any adjacent cell in the four directions: up, down, left,
 * and right. Each move you make takes 1 second.
 * 
 * Return the minimum time required in which you can visit the bottom-right
 * cell of the matrix. If you cannot visit the bottom-right cell, then return
 * -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
 * Output: 7
 * Explanation: One of the paths that we can take is the following:
 * - at t = 0, we are on the cell (0,0).
 * - at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <=
 * 1.
 * - at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <=
 * 2.
 * - at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <=
 * 3.
 * - at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <=
 * 4.
 * - at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <=
 * 5.
 * - at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <=
 * 6.
 * - at t = 7, we move to the cell (2,3). It is possible because grid[1][3] <=
 * 7.
 * The final time is 7. It can be shown that it is the minimum time possible.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
 * Output: -1
 * Explanation: There is no path from the top left to the bottom-right
 * cell.
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
 * 0 <= grid[i][j] <= 10^5
 * grid[0][0] == 0
 * 
 * 
 * 
 * .spoilerbutton {display:block; border:dashed; padding: 0px 0px; margin:10px
 * 0px; font-size:150%; font-weight: bold; color:#000000;
 * background-color:cyan; outline:0; 
 * }
 * .spoiler {overflow:hidden;}
 * .spoiler > div {-webkit-transition: all 0s ease;-moz-transition: margin 0s
 * ease;-o-transition: all 0s ease;transition: margin 0s ease;}
 * .spoilerbutton[value="Show Message"] + .spoiler > div {margin-top:-500%;}
 * .spoilerbutton[value="Hide Message"] + .spoiler {padding:5px;}
 * 
 * 
 */

// @lc code=start
class Solution {
    int N;
    int M;
    public int minimumTime(int[][] grid) {
        N = grid.length;
        M = grid[0].length;
        int[][] dirs = {{0, 1},{1, 0},{-1, 0},{0, -1}};
        boolean[] processed = new boolean[N * M];
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        if(N > 0 && grid[1][0] <= 1){
            q.offer(new int[]{1, 0, 1});
        }
        if(M > 0 && grid[0][1] <= 1){
            q.offer(new int[]{0, 1, 1});
        }
        if(!q.isEmpty()){
            if(N > 0 && grid[1][0] > 1){
                q.offer(new int[]{1, 0, grid[1][0] + grid[1][0] % 2});
            }
            if(M > 0 && grid[0][1] > 1){
                q.offer(new int[]{0, 1, Math.max(grid[0][1] + (grid[0][1] + 1) % 2, 3)});
            }
        }
        processed[encode(new int[]{0, 0})] = true;
        int targetCode = encode(new int[]{N - 1, M - 1});
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curCode = encode(cur);
            if(curCode == targetCode){
                return cur[2];
            }
            if(!processed[curCode]){
                // System.out.println(Arrays.toString(cur));
                for(int[] dir : dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1], 0};
                    if(0 <= nxt[0] && nxt[0] < N && 0 <= nxt[1] && nxt[1] < M){
                        int offset = (nxt[0] + nxt[1]) % 2;
                        nxt[2] = Math.max(cur[2] + 1, grid[nxt[0]][nxt[1]] + (grid[nxt[0]][nxt[1]] + offset) % 2);
                        q.offer(nxt);
                    }
                }
                processed[curCode] = true;
            }
        }
        return -1;
    }

    int encode(int[] p){
        return p[0] * M + p[1];
    }
}
// @lc code=end
