/*
 * @lc app=leetcode id=1895 lang=java
 *
 * [1895] Largest Magic Square
 *
 * https://leetcode.com/problems/largest-magic-square/description/
 *
 * algorithms
 * Medium (47.98%)
 * Likes:    106
 * Dislikes: 140
 * Total Accepted:    4.5K
 * Total Submissions: 9.4K
 * Testcase Example:  '[[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]'
 *
 * A k x k magic square is a k x k grid filled with integers such that every
 * row sum, every column sum, and both diagonal sums are all equal. The
 * integers in the magic square do not have to be distinct. Every 1 x 1 grid is
 * trivially a magic square.
 * 
 * Given an m x n integer grid, return the size (i.e., the side length k) of
 * the largest magic square that can be found within this grid.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * Output: 3
 * Explanation: The largest magic square has a size of 3.
 * Every row sum, column sum, and diagonal sum of this magic square is equal to
 * 12.
 * - Row sums: 5+1+6 = 5+4+3 = 2+7+3 = 12
 * - Column sums: 5+5+2 = 1+4+7 = 6+3+3 = 12
 * - Diagonal sums: 5+4+3 = 6+4+2 = 12
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[5,1,3,1],[9,3,3,1],[1,3,3,8]]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
	long[][] pSumRow;
    long[][] pSumCol;
    int[][] g;
    public int largestMagicSquare(int[][] grid) {
    	g = grid;
    	int n = grid.length;
    	int m = grid[0].length;
        pSumRow = new long[n][m + 1];
        pSumCol = new long[n + 1][m];
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		pSumRow[i][j + 1] = pSumRow[i][j] + grid[i][j];
        		pSumCol[i + 1][j] = pSumCol[i][j] + grid[i][j];
        	}
        }
        int ans = 1;
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		for(int l = ans; i + l - 1 < n && j + l - 1 < m; l++){
        			if(check(new int[]{i, j}, l)){
        				ans = l;
        			}
        		}
        	}
        }
        // check(new int[]{1, 1}, 3);
        return ans;
    }

    boolean check(int[] topleft, int l){
    	System.out.println(l);
    	long sum = 0;
    	int[] diagonals = new int[2];
    	for(int i = 0; i < l; i++){
    		if(sum == 0){
    			sum = pSumRow[topleft[0] + i][topleft[1] + l] - pSumRow[topleft[0] + i][topleft[1]];
    		}
    		else{
	    		if(pSumRow[topleft[0] + i][topleft[1] + l] - pSumRow[topleft[0] + i][topleft[1]] != sum){
	    			// System.out.println(Arrays.asList(i, pSumRow[topleft[0] + i][topleft[1] + l], pSumRow[topleft[0] + i][topleft[1]]));
	    			return false;
	    		}
	    		if(pSumCol[topleft[0] + l][topleft[1] + i] - pSumCol[topleft[0]][topleft[1] + i] != sum){
	    			// System.out.println(Arrays.asList(i, pSumCol[topleft[0] + l][topleft[1] + i], pSumCol[topleft[0]][topleft[1] + i]));
	    			return false;
	    		}	
    		}
    		diagonals[0] += g[topleft[0] + i][topleft[1] + i];
    		diagonals[1] += g[topleft[0] + i][topleft[1] + l - 1 - i];
    	}
    	// System.out.println(Arrays.toString(diagonals));
    	return (diagonals[0] == sum && diagonals[1] == sum);
    }
}
// @lc code=end
