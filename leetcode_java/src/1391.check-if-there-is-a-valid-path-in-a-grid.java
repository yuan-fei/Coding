/*
 * @lc app=leetcode id=1391 lang=java
 *
 * [1391] Check if There is a Valid Path in a Grid
 *
 * https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/description/
 *
 * algorithms
 * Medium (37.52%)
 * Likes:    17
 * Dislikes: 37
 * Total Accepted:    2.6K
 * Total Submissions: 7K
 * Testcase Example:  '[[2,4,3],[6,5,2]]'
 *
 * Given a m x n grid. Each cell of the grid represents a street. The street of
 * grid[i][j] can be:
 * 
 * 1 which means a street connecting the left cell and the right cell.
 * 2 which means a street connecting the upper cell and the lower cell.
 * 3 which means a street connecting the left cell and the lower cell.
 * 4 which means a street connecting the right cell and the lower cell.
 * 5 which means a street connecting the left cell and the upper cell.
 * 6 which means a street connecting the right cell and the upper cell.
 * 
 * 
 * 
 * 
 * You will initially start at the street of the upper-left cell (0,0). A valid
 * path in the grid is a path which starts from the upper left cell (0,0) and
 * ends at the bottom-right cell (m - 1, n - 1). The path should only follow
 * the streets.
 * 
 * Notice that you are not allowed to change any street.
 * 
 * Return true if there is a valid path in the grid or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[2,4,3],[6,5,2]]
 * Output: true
 * Explanation: As shown you can start at cell (0, 0) and visit all the cells
 * of the grid to reach (m - 1, n - 1).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,2,1],[1,2,1]]
 * Output: false
 * Explanation: As shown you the street at cell (0, 0) is not connected with
 * any street of any other cell and you will get stuck at cell (0, 0)
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1,1,2]]
 * Output: false
 * Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0,
 * 2).
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [[1,1,1,1,1,1,3]]
 * Output: true
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * 1 <= grid[i][j] <= 6
 * 
 */

// @lc code=start
class Solution {
    public boolean hasValidPath(int[][] grid) {
    	int l = 1;
    	int r = 2;
    	int u = 3;
    	int d = 4;
    	return hasValidPath(grid, l) || hasValidPath(grid, r) || hasValidPath(grid, u) || hasValidPath(grid, d);
    }

    public boolean hasValidPath(int[][] grid, int from) {
    	int l = 1;
    	int r = 2;
    	int u = 3;
    	int d = 4;
        int[][] dirs = {{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int[][] dirMap = new int[5][7];
        dirMap[l][1] = r;
        dirMap[r][1] = l;
        dirMap[u][2] = d;
        dirMap[d][2] = u;
        dirMap[l][3] = d;
        dirMap[d][3] = l;
        dirMap[r][4] = d;
        dirMap[d][4] = r;
        dirMap[l][5] = u;
        dirMap[u][5] = l;
        dirMap[r][6] = u;
        dirMap[u][6] = r;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] cur = {0, 0};
        
        
        while(true){
        	// System.out.println(Arrays.toString(cur));
        	// System.out.println(from);
        	if(cur[0] >= 0 && cur[0] < n && cur[1] >= 0 && cur[1] < m && !visited[cur[0]][cur[1]]){
        		visited[cur[0]][cur[1]] = true;
        		int to = dirMap[from][grid[cur[0]][cur[1]]];
	        	if(to == 0){
	        		return false;
	        	}
	        	if(cur[0] == n - 1 && cur[1] == m - 1){
        			return true;
        		}
	        	from = ((to - 1) ^ 1) + 1;
	        	cur[0] += dirs[to][0];
	        	cur[1] += dirs[to][1];
        	}
        	else{
        		return false;
        	}	
        }
    }
}
// @lc code=end
