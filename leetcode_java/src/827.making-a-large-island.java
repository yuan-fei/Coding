/*
 * @lc app=leetcode id=827 lang=java
 *
 * [827] Making A Large Island
 *
 * https://leetcode.com/problems/making-a-large-island/description/
 *
 * algorithms
 * Hard (44.81%)
 * Likes:    2936
 * Dislikes: 58
 * Total Accepted:    125.9K
 * Total Submissions: 280.8K
 * Testcase Example:  '[[1,0],[0,1]]'
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most
 * one 0 to be 1.
 * 
 * Return the size of the largest island in grid after applying this
 * operation.
 * 
 * An island is a 4-directionally connected group of 1s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island
 * with area = 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island
 * with area = 4.
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 * 
 */

// @lc code=start
class Solution {
    int[][] grid;
    int n;
    int m;
    int[] group;
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    public int largestIsland(int[][] grid) {
        this.grid = grid;
        n = grid.length;
        m = grid[0].length;    
        List<Integer> gSize = new ArrayList<>();
        group = new int[n * m];
        Arrays.fill(group, -1);
        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && group[code(new int[]{i, j})] == -1){
                    gSize.add(dfs(new int[]{i, j}, gSize.size()));
                    ans = Math.max(ans, gSize.get(gSize.size() - 1));
                }
            }
        }
        // System.out.println(Arrays.toString(group) + ", " + gSize);
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0){
                    int[] p = new int[]{i, j};
                    int size = 1;
                    Set<Integer> adjGroups = new HashSet<>();
                    for(int[] dir : dirs){
                        int[] next = new int[]{dir[0] + p[0], dir[1] + p[1]};
                        int cNext = code(next);
                        if(0 <= next[0] && next[0] < n && 0 <= next[1] && next[1] < m && grid[next[0]][next[1]] == 1){
                            if(!adjGroups.contains(group[cNext])){
                                size += gSize.get(group[cNext]);    
                            }
                            adjGroups.add(group[cNext]);
                        }
                    }
                    ans = Math.max(ans, size);      
                }
                
            }
        }
        return ans;
    }


    int dfs(int[] p, int g){
        int c = code(p);
        group[c] = g;
        int rt = 1;
        for(int[] dir : dirs){
            int[] next = new int[]{dir[0] + p[0], dir[1] + p[1]};
            int cNext = code(next);
            if(0 <= next[0] && next[0] < n && 0 <= next[1] && next[1] < m && group[cNext] == -1 && grid[next[0]][next[1]] == 1){
                rt += dfs(next, g);
            }
        }    
        return rt;
    }

    int code(int[] p){
        return p[0] * m + p[1];
    }
}
// @lc code=end
