/*
 * @lc app=leetcode id=1559 lang=java
 *
 * [1559] Detect Cycles in 2D Grid
 *
 * https://leetcode.com/problems/detect-cycles-in-2d-grid/description/
 *
 * algorithms
 * Hard (39.48%)
 * Likes:    51
 * Dislikes: 2
 * Total Accepted:    2.7K
 * Total Submissions: 7K
 * Testcase Example:  '[["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]'
 *
 * Given a 2D array of characters grid of size m x n, you need to find if there
 * exists any cycle consisting of the same value in grid.
 * 
 * A cycle is a path of length 4 or more in the grid that starts and ends at
 * the same cell. From a given cell, you can move to one of the cells adjacent
 * to it - in one of the four directions (up, down, left, or right), if it has
 * the same value of the current cell.
 * 
 * Also, you cannot move to the cell that you visited in your last move. For
 * example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2)
 * we visited (1, 1) which was the last visited cell.
 * 
 * Return true if any cycle of the same value exists in grid, otherwise, return
 * false.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid =
 * [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the
 * image below:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: grid =
 * [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image
 * below:
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 500
 * 1 <= n <= 500
 * grid consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
	char[][] grid;
	int[][] dirs = {{0,1} , {1, 0}, {0, -1}, {-1, 0}};
    public boolean containsCycle(char[][] grid) {
        this.grid = grid;
        for(int i = 0; i < grid.length; i++){
    		for(int j = 0; j < grid[i].length; j++){
    			// System.out.println(i +", "+ j + " outer");
    			if(grid[i][j] >= 'a' && dfs(grid[i][j], i, j, -1, -1)){
    				return true;
    			}
    		}
    	}
    	// System.out.println(Arrays.deepToString(grid));
    	return false;
    }

    
    boolean dfs(char c, int i, int j, int prei, int prej){
    	if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length){
    		// System.out.println(i +", "+ j + "false");
    		return false;
    	}
    	if(grid[i][j] == c - 27){
    		return true;
    	}
    	if(grid[i][j] == c){
    		// System.out.println(i +", "+ j);
    		grid[i][j] -= 27;
    		for(int[] d : dirs){
    			int nxti = i + d[0];
    			int nxtj = j + d[1];
    			if((prei != nxti || prej != nxtj) && dfs(c, nxti, nxtj, i, j)){
    				return true;
    			}
    		}
    	}
    	return false;
    	

    }
}
// @lc code=end
