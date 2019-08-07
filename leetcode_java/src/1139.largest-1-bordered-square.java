/*
 * @lc app=leetcode id=1139 lang=java
 *
 * [1139] Largest 1-Bordered Square
 *
 * https://leetcode.com/problems/largest-1-bordered-square/description/
 *
 * algorithms
 * Medium (42.50%)
 * Total Accepted:    3.6K
 * Total Submissions: 8.4K
 * Testcase Example:  '[[1,1,1],[1,0,1],[1,1,1]]'
 *
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest
 * square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't
 * exist in the grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 9
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,1,0,0]]
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] is 0 or 1
 * 
 */
class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] maxRight = new int[n][m];
        int[][] maxDown = new int[n][m];
        //populate maxRight/maxDown
        for(int i = 0; i < n; i++){
            int curMR = -1;
            for(int j = m-1; j>=0; j--){
                if(grid[i][j]==1){
                    if(curMR==-1){
                        curMR = j;
                    }
                }
                else{
                    curMR = -1;
                }
                maxRight[i][j] = curMR;
            }
        }
        
        for(int i = 0; i < m; i++){
            int curMD = -1;
            for(int j = n-1; j>=0; j--){
                if(grid[j][i]==1){
                    if(curMD==-1){
                        curMD = j;
                    }
                }
                else{
                    curMD = -1;
                }
                maxDown[j][i] = curMD;
            }
        }
        // System.out.println(Arrays.deepToString(maxRight));
        // System.out.println(Arrays.deepToString(maxDown));
        
        int MAX = Math.min(n,m);
        
        for(int l = MAX; l >= 1; l--){
            for(int i = 0; i < n-l+1; i++){
                if(checkPossible(i, l, grid, maxRight, maxDown)){
                    return l*l;   
                }
            }
        }
        return 0;
    }
    
    boolean checkPossible(int i, int l, int[][] grid, int[][] maxRight, int[][] maxDown){
        int n = grid.length;
        int m = grid[0].length;
        for(int j = 0; j <= m-l; j++){
            // System.out.println(i+", "+j+","+l);
            if(maxRight[i][j]>=j+l-1 && maxDown[i][j]>=i+l-1 && maxRight[i+l-1][j]>=j+l-1 && maxDown[i][j+l-1]>=i+l-1){
                return true;
            }
        }
        return false;
    }
}
