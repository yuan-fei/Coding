/*
 * @lc app=leetcode id=1162 lang=java
 *
 * [1162] As Far from Land as Possible
 *
 * https://leetcode.com/problems/as-far-from-land-as-possible/description/
 *
 * algorithms
 * Medium (34.65%)
 * Total Accepted:    3K
 * Total Submissions: 8.5K
 * Testcase Example:  '[[1,0,1],[0,0,0],[1,0,1]]'
 *
 * Given an N x N grid containing only values 0 and 1, where 0 represents water
 * and 1 represents land, find a water cell such that its distance to the
 * nearest land cell is maximized and return the distance.
 * 
 * The distance used in this problem is the Manhattan distance: the distance
 * between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 * 
 * If no land or water exists in the grid, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: 
 * The cell (1, 1) is as far as possible from all the land with distance 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: 
 * The cell (2, 2) is as far as possible from all the land with distance
 * 4.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= grid.length == grid[0].length <= 100
 * grid[i][j] is 0 or 1
 * 
 * 
 */
class Solution {
    public int maxDistance(int[][] grid) {
        int N = grid.length;
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < N;i++){
            for(int j = 0; j < N; j++){
                if(grid[i][j]==1){
                    q.offer(new int[]{i,j});
                    visited[i][j] = true;
                }
            }
        }
        int[] dx = new int[]{0,0,1,-1};
        int[] dy = new int[]{1,-1,0,0};
        int d = 0;
        while(!q.isEmpty()){
            int n = q.size();
            for(int m =0 ; m < n; m++){
                int[] cur = q.poll();
                for(int i = 0; i < 4; i++){
                    int x=cur[0]+dx[i];
                    int y=cur[1]+dy[i];
                    if(x>=0 && y>=0 && x<N && y<N && !visited[x][y]){
                        q.offer(new int[]{x,y}); 
                        visited[x][y]=true;
                    }
                }    
            }
            if(!q.isEmpty()){
                d++;
            }
        }
        return d==0?-1:d;
    }
    
}
