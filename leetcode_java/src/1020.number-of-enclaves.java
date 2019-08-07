/*
 * @lc app=leetcode id=1020 lang=java
 *
 * [1020] Number of Enclaves
 *
 * https://leetcode.com/problems/number-of-enclaves/description/
 *
 * algorithms
 * Medium (54.68%)
 * Total Accepted:    8.5K
 * Total Submissions: 15.5K
 * Testcase Example:  '[[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]'
 *
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing
 * land)
 * 
 * A move consists of walking from one land square 4-directionally to another
 * land square, or off the boundary of the grid.
 * 
 * Return the number of land squares in the grid for which we cannot walk off
 * the boundary of the grid in any number of moves.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 3
 * Explanation: 
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed
 * because its on the boundary.
 * 
 * Example 2:
 * 
 * 
 * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * Output: 0
 * Explanation: 
 * All 1s are either on the boundary or can reach the boundary.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length <= 500
 * 1 <= A[i].length <= 500
 * 0 <= A[i][j] <= 1
 * All rows have the same size.
 * 
 */
class Solution {
    int[] dx = new int[]{-1, 0};
    int[] dy = new int[]{0, -1};
    public int numEnclaves(int[][] A) {
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[i].length; j++){
                if(A[i][j] == 1){
                    if(i==0 || j==0 || i == A.length - 1 || j == A[i].length -1){
                        makeSet(i * 500 + j, true);
                    }
                    else{
                        makeSet(i * 500 + j, false);
                    }
                    for(int k = 0; k < 2; k++){
                        int x = i + dx[k];
                        int y = j + dy[k];
                        if(x>=0 && y>=0 && A[x][y] == 1){
                            union(x * 500 + y, i * 500 + j);
                        }
                    }
                }
            }
        }
        int sum = 0;
        for(int id: sets.keySet()){
            if(!sets.get(id)){
                sum += cnts.get(id);
            }
        }
        return sum;
    }
    
    Map<Integer, Integer> parent = new HashMap<>();
    Map<Integer, Boolean> sets = new HashMap<>();
    Map<Integer, Integer> cnts = new HashMap<>();
    void makeSet(int x, boolean isBound){
        parent.put(x, x);
        sets.put(x, isBound);
        cnts.put(x, 1);
    }
    
    void union(int x, int y){
        int xp = findSet(x);
        int yp = findSet(y);
        if(xp != yp){
            parent.put(xp, yp);
            sets.put(yp, sets.get(yp)||sets.get(xp));
            cnts.put(yp, cnts.get(yp)+cnts.get(xp));
            sets.remove(xp);
            cnts.remove(xp);
        }
    }
    
    int findSet(int x){
        int p = parent.get(x);
        if(p != x){
            p = findSet(p);
            parent.put(x, p);
        }
        return p;
    }
}
