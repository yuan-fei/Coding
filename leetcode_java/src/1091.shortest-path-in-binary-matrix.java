/*
 * @lc app=leetcode id=1091 lang=java
 *
 * [1091] Shortest Path in Binary Matrix
 *
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
 *
 * algorithms
 * Medium (36.44%)
 * Total Accepted:    6.3K
 * Total Submissions: 17.3K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * 
 * A clear path from top-left to bottom-right has length k if and only if it is
 * composed of cells C_1, C_2, ..., C_k such that:
 * 
 * 
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are
 * different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] ==
 * 0).
 * 
 * 
 * Return the length of the shortest such clear path from top-left to
 * bottom-right.  If such a path does not exist, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,1],[1,0]]
 * 
 * 
 * Output: 2
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * 
 * 
 * Output: 4
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= grid.length == grid[0].length <= 100
 * grid[r][c] is 0 or 1
 * 
 * 
 */
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0]==1||grid[n-1][n-1]==1){
            return -1;
        }
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(encode(0,0));
        visited.add(encode(0,0));
        int k = 0;
        int last = encode(n-1, n-1);
        int[] dx = new int[]{0,0,1,-1,1,-1,1,-1};
        int[] dy = new int[]{1,-1,0,0,1,-1,-1,1};
        while(!q.isEmpty()){
            k++;
            int m = q.size();
            for(int i = 0; i<m;i++){
                int code = q.poll();
                int[] pos = decode(code);
                if(code==last){
                    return k;
                }
                else{
                    for(int j=0; j<8; j++){
                        int x = dx[j]+pos[0];
                        int y = dy[j]+pos[1];
                        int newCode = encode(x, y);
                        if(0<=x&&x<n&&0<=y&&y<n&&grid[x][y]==0&&!visited.contains(newCode)){
                            q.offer(newCode);
                            visited.add(newCode);
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    public int encode(int r, int c){
        return r*101+c;
    }
    
    public int[] decode(int x){
        return new int[]{x/101,x%101};
    }
}
