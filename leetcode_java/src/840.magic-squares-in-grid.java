/*
 * @lc app=leetcode id=840 lang=java
 *
 * [840] Magic Squares In Grid
 *
 * https://leetcode.com/problems/magic-squares-in-grid/description/
 *
 * algorithms
 * Medium (38.59%)
 * Likes:    281
 * Dislikes: 1529
 * Total Accepted:    34.7K
 * Total Submissions: 89.9K
 * Testcase Example:  '[[4,3,8,4],[9,5,1,9],[2,7,6,2]]'
 *
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to
 * 9 such that each row, column, and both diagonals all have the same sum.
 * 
 * Given a row x col grid of integers, how many 3 x 3 "magic square" subgrids
 * are there?  (Each subgrid is contiguous).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
 * Output: 1
 * Explanation: 
 * The following subgrid is a 3 x 3 magic square:
 * 
 * while this one is not:
 * 
 * In total, there is only one magic square inside the given grid.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[8]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 10
 * 0 <= grid[i][j] <= 15
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int ret = 0;
        for(int i = 0; i < n - 2; i++){
            for(int j = 0; j < m - 2; j++){
                if(check(grid, i, j)){
                    ret++;
                }
            }
        }
        return ret;
    }

    boolean check(int[][] grid, int r, int c){
        Set<Integer> s = new HashSet<>();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(0 < grid[r + i][c + j] && grid[r + i][c + j] < 10){
                    s.add(grid[r + i][c + j]);
                }
                else{
                    return false;
                }
            }
        }
        // System.out.println(1);
        if(s.size() != 9){
            return false;
        }
        // System.out.println(2);
        for(int i = 0; i < 3; i++){
            int sumR = 0;
            int sumC = 0;
            for(int j = 0; j < 3; j++){
                sumR += grid[r + j][c + i];
                sumC += grid[r + i][c + j];
            }
            if(sumR != 15 || sumC != 15){
                return false;
            }
        }
        // System.out.println(3);
        int sumD1 = 0;
        int sumD2 = 0;
        for(int i = 0; i < 3; i++){
            sumD1 += grid[r + i][c + i];
            sumD2 += grid[r + i][c + 2 - i];
        }
        // System.out.println(sumD1 + ", " + sumD2);
        return sumD1 == 15 && sumD2 == 15;
    }
}
// @lc code=end
