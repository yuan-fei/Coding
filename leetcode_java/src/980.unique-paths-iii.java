/*
 * @lc app=leetcode id=980 lang=java
 *
 * [980] Unique Paths III
 *
 * https://leetcode.com/problems/unique-paths-iii/description/
 *
 * algorithms
 * Hard (81.70%)
 * Likes:    4907
 * Dislikes: 182
 * Total Accepted:    185.6K
 * Total Submissions: 227.1K
 * Testcase Example:  '[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]'
 *
 * You are given an m x n integer array grid where grid[i][j] could be:
 * 
 * 
 * 1 representing the starting square. There is exactly one starting
 * square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * 
 * 
 * Return the number of 4-directional walks from the starting square to the
 * ending square, that walk over every non-obstacle square exactly once.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths: 
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths: 
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly
 * once.
 * Note that the starting and ending square can be anywhere in the grid.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * 1 <= m * n <= 20
 * -1 <= grid[i][j] <= 2
 * There is exactly one starting cell and one ending cell.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] grid;
    int n;
    int m;
    int endState;
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    int[][] cache;
    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;
        cache = new int[1 << (n * m)][n * m];
        for(int i = 0; i < 1 << (n * m); i++){
            for(int j = 0; j < n * m; j++){
                cache[i][j] = -1;
            }
        }
        int[] start = new int[0];
        endState = (1 << (n * m)) - 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    start = new int[]{i, j};
                }
                else if(grid[i][j] == -1){
                    endState ^= 1 << encode(new int[]{i, j});
                }
            }
        }
        return dfs(grid, start, 0);
    }
    
    int dfs(int[][] grid, int[] pos, int state){
        int curCode = encode(pos);
        state |= 1 << curCode;
        if(grid[pos[0]][pos[1]] == 2){
            if(state == endState){
                return 1;    
            }
            return 0;
        }
        if(cache[state][curCode] == -1){
            int ret = 0;
            for(int[] dir: dirs){
                int[] nxt = new int[]{pos[0] + dir[0], pos[1] + dir[1]};
                if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && grid[nxt[0]][nxt[1]] != -1 && !isTraveled(state, nxt)){
                    ret += dfs(grid, nxt, state);
                }
            }
            cache[state][curCode] = ret;
            // System.out.println(state + "," + Arrays.toString(pos) + "=" + ret);
        }
        return cache[state][curCode];
    }

    int encode(int[] pos){
        return pos[0] * m + pos[1];
    }

    boolean isTraveled(int state, int[] pos){
        int cur = encode(pos);
        boolean ret = ((state >> cur) & 1) == 1;
        // System.out.println(state + "," + Arrays.toString(pos) + "=" + ret);
        return ret;
    }
}
// @lc code=end
