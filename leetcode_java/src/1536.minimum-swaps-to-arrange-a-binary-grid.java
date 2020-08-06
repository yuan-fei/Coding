/*
 * @lc app=leetcode id=1536 lang=java
 *
 * [1536] Minimum Swaps to Arrange a Binary Grid
 *
 * https://leetcode.com/problems/minimum-swaps-to-arrange-a-binary-grid/description/
 *
 * algorithms
 * Medium (41.55%)
 * Likes:    154
 * Dislikes: 39
 * Total Accepted:    5.5K
 * Total Submissions: 13.2K
 * Testcase Example:  '[[0,0,1],[1,1,0],[1,0,0]]'
 *
 * Given an n x n binary grid, in one step you can choose two adjacent rows of
 * the grid and swap them.
 * 
 * A grid is said to be valid if all the cells above the main diagonal are
 * zeros.
 * 
 * Return the minimum number of steps needed to make the grid valid, or -1 if
 * the grid cannot be valid.
 * 
 * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and
 * ends at cell (n, n).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
 * Output: 3
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
 * Output: -1
 * Explanation: All rows are similar, swaps have no effect on the grid.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 200
 * grid[i][j] is 0 or 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSwaps(int[][] grid) {
    	int n = grid.length;
    	int[] zeros = new int[n];
    	for(int i  = 0; i < n; i++){
    		zeros[i] = getTrailingZeros(grid[i]);
    	}
    	int swap = 0;
    	for(int i = 0; i < n; i++){
    		boolean foundFit = false;
    		for(int j = i; j < n; j++){
    			if(zeros[j] >= n - i - 1){
    				move(zeros, i, j);
    				swap += j - i;
    				foundFit = true;
    				break;
    			}
    		}
    		if(!foundFit){
    			return -1;
    		}
    	}
    	return swap;
    }

    int getTrailingZeros(int[] row){
    	int cnt = 0;
    	for(int i = row.length - 1; i >= 0; i--){
    		if(row[i] != 0){
    			return cnt;
    		}
    		cnt++;
    	}
    	return cnt;
    }

    void move(int[] a, int i, int j){
    	int t = a[j];
    	while(j > i){
    		a[j] = a[j - 1];
    		j--;
    	}
    	a[i] = t;
    }
}
// @lc code=end
