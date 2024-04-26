/*
 * @lc app=leetcode id=2812 lang=java
 *
 * [2812] Find the Safest Path in a Grid
 *
 * https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/
 *
 * algorithms
 * Medium (30.89%)
 * Likes:    765
 * Dislikes: 90
 * Total Accepted:    18.4K
 * Total Submissions: 59.2K
 * Testcase Example:  '[[1,0,0],[0,0,0],[0,0,1]]'
 *
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c)
 * represents:
 * 
 * 
 * A cell containing a thief if grid[r][c] = 1
 * An empty cell if grid[r][c] = 0
 * 
 * 
 * You are initially positioned at cell (0, 0). In one move, you can move to
 * any adjacent cell in the grid, including cells containing thieves.
 * 
 * The safeness factor of a path on the grid is defined as the minimum
 * manhattan distance from any cell in the path to any thief in the grid.
 * 
 * Return the maximum safeness factor of all paths leading to cell (n - 1, n -
 * 1).
 * 
 * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1),
 * (r + 1, c) and (r - 1, c) if it exists.
 * 
 * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a -
 * x| + |b - y|, where |val| denotes the absolute value of val.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
 * Output: 0
 * Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves
 * in cells (0, 0) and (n - 1, n - 1).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of
 * 2 since:
 * - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0).
 * The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness
 * factor.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of
 * 2 since:
 * - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2).
 * The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
 * - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2).
 * The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness
 * factor.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length == n <= 400
 * grid[i].length == n
 * grid[i][j] is either 0 or 1.
 * There is at least one thief in the grid.
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 400;
    int[] parent = new int[MAX * MAX];
    int[][] dirs = {{0, 1},{1, 0},{-1, 0},{0, -1}};
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dist = computeDistance(grid);
        List<int[]>[] distToPositions = new List[MAX * 2 + 1];
        for(int i = 0; i < distToPositions.length; i++){
            distToPositions[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                distToPositions[dist[i][j]].add((new int[]{i, j}));
            }
        }
        for(int i = 0; i < parent.length; i++){
            parent[i] = i;
        }
        int[] start = {0, 0};
        int[] end = {n - 1, m - 1};
        // System.out.println(Arrays.deepToString(dist));
        for(int d = distToPositions.length - 1; d >= 0; d--){
            for(int[] cur : distToPositions[d]){
                for(int[] dir: dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && dist[nxt[0]][nxt[1]] >= d){
                        union(encode(nxt), encode(cur));
                        // System.out.println(Arrays.toString(nxt) + "<->" + Arrays.toString(cur));
                        if(find(encode(start)) == find(encode(end))){
                            return d;
                        }
                    }
                }
            }
            
        }
        return 0;
    }

    int encode(int[] pos){
        return pos[0] * MAX + pos[1];
    }

    int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx != ry){
            parent[rx] = ry;
        }
    }

    int[][] computeDistance(List<List<Integer>> grid){
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] mat = new int[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
            Arrays.fill(mat[i], -1);
            for(int j = 0; j < m; j++){
                if(grid.get(i).get(j) == 1){
                    q.offer(new int[]{i, j});
                    mat[i][j] = 0;
                }
            }
        }
        
        int step = 0;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] cur = q.poll();
                
                for(int[] dir: dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < n && 0 <= nxt[1] && nxt[1] < m && mat[nxt[0]][nxt[1]] == -1){
                        q.offer(nxt);
                        mat[nxt[0]][nxt[1]] = step + 1;
                    }
                }
            }
            step++;
        }
        return mat;
    }
}
// @lc code=end
