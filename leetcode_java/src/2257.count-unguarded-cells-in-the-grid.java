/*
 * @lc app=leetcode id=2257 lang=java
 *
 * [2257] Count Unguarded Cells in the Grid
 *
 * https://leetcode.com/problems/count-unguarded-cells-in-the-grid/description/
 *
 * algorithms
 * Medium (46.57%)
 * Likes:    119
 * Dislikes: 16
 * Total Accepted:    5.5K
 * Total Submissions: 11.9K
 * Testcase Example:  '4\n6\n[[0,0],[1,1],[2,3]]\n[[0,1],[2,2],[1,4]]'
 *
 * You are given two integers m and n representing a 0-indexed m x n grid. You
 * are also given two 2D integer arrays guards and walls where guards[i] =
 * [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the i^th
 * guard and j^th wall respectively.
 * 
 * A guard can see every cell in the four cardinal directions (north, east,
 * south, or west) starting from their position unless obstructed by a wall or
 * another guard. A cell is guarded if there is at least one guard that can see
 * it.
 * 
 * Return the number of unoccupied cells that are not guarded.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls =
 * [[0,1],[2,2],[1,4]]
 * Output: 7
 * Explanation: The guarded and unguarded cells are shown in red and green
 * respectively in the above diagram.
 * There are a total of 7 unguarded cells, so we return 7.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
 * Output: 4
 * Explanation: The unguarded cells are shown in green in the above diagram.
 * There are a total of 4 unguarded cells, so we return 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * 1 <= guards.length, walls.length <= 5 * 10^4
 * 2 <= guards.length + walls.length <= m * n
 * guards[i].length == walls[j].length == 2
 * 0 <= rowi, rowj < m
 * 0 <= coli, colj < n
 * All the positions in guards and walls are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        TreeMap<Integer, Boolean>[] rowIndex = new TreeMap[m];
        TreeMap<Integer, Boolean>[] colIndex = new TreeMap[n];
        for(int i  = 0; i < m; i++){
            rowIndex[i] = new TreeMap<>();
        }
        for(int i  = 0; i < n; i++){
            colIndex[i] = new TreeMap<>();
        }
        for(int[] g : guards){
            rowIndex[g[0]].put(g[1], true);
        }
        for(int[] g : walls){
            rowIndex[g[0]].put(g[1], false);
        }
        for(int[] g : guards){
            colIndex[g[1]].put(g[0], true);
        }
        for(int[] g : walls){
            colIndex[g[1]].put(g[0], false);
        }
        int ans = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                boolean guarded = false;
                for(java.util.Map.Entry<Integer, Boolean> e : new java.util.Map.Entry[]{
                    rowIndex[i].ceilingEntry(j), 
                    rowIndex[i].floorEntry(j)
                }){
                    // System.out.println(i + "," + j + "=" + e);
                    if(e != null){
                        if(e.getKey() == j){
                            guarded = true;
                            break;
                        }
                        guarded |= e.getValue();
                    }
                }
                for(java.util.Map.Entry<Integer, Boolean> e : new java.util.Map.Entry[]{
                    colIndex[j].ceilingEntry(i), 
                    colIndex[j].floorEntry(i)
                }){
                    // System.out.println(i + "," + j + "=" + e);
                    if(e != null){
                        if(e.getKey() == i){
                            guarded = true;
                            break;
                        }
                        guarded |= e.getValue();
                    }
                }
                if(!guarded){
                    // System.out.println(i + "," + j);
                    ans++;
                }
            }
        }
        return ans;
    }
}
// @lc code=end
