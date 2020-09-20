/*
 * @lc app=leetcode id=1591 lang=java
 *
 * [1591] Strange Printer II
 *
 * https://leetcode.com/problems/strange-printer-ii/description/
 *
 * algorithms
 * Hard (45.16%)
 * Likes:    27
 * Dislikes: 1
 * Total Accepted:    673
 * Total Submissions: 1.5K
 * Testcase Example:  '[[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]'
 *
 * There is a strange printer with the following two special
 * requirements:
 * 
 * 
 * On each turn, the printer will print a solid rectangular pattern of a single
 * color on the grid. This will cover up the existing colors in the
 * rectangle.
 * Once the printer has used a color for the above operation, the same color
 * cannot be used again.
 * 
 * 
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the
 * color in the position (row, col) of the grid.
 * 
 * Return true if it is possible to print the matrix targetGrid, otherwise,
 * return false.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
 * Output: false
 * Explanation: It is impossible to form targetGrid because it is not allowed
 * to print the same color in different turns.
 * 
 * Example 4:
 * 
 * 
 * Input: targetGrid = [[1,1,1],[3,1,3]]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int[][] colorRange = getRange(targetGrid);
        // System.out.println(Arrays.deepToString(colorRange));
        Set<Integer>[] dependencies = buildAdj(targetGrid, colorRange);
        // System.out.println(Arrays.toString(dependencies));
        return checkOverlap(dependencies);
    }

    int[][] getRange(int[][] targetGrid){
    	int n = targetGrid.length;
    	int m = targetGrid[0].length;
    	int[][] range = new int[61][4];
    	for(int i = 0; i < range.length; i++){
    		range[i][0] = 61;
    		range[i][1] = 61;
    	}
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			range[targetGrid[i][j]][0] = Math.min(range[targetGrid[i][j]][0], i);
    			range[targetGrid[i][j]][1] = Math.min(range[targetGrid[i][j]][1], j);
    			range[targetGrid[i][j]][2] = Math.max(range[targetGrid[i][j]][2], i);
    			range[targetGrid[i][j]][3] = Math.max(range[targetGrid[i][j]][3], j);
    		}
    	}
    	return range;
    }

    Set<Integer>[] buildAdj(int[][] targetGrid, int[][] colorRange){
    	int n = targetGrid.length;
    	int m = targetGrid[0].length;
    	int k = colorRange.length;
    	Set<Integer>[] adj = new Set[k];
    	for(int i = 0; i < k; i++){
    		adj[i] = new HashSet<>();
    	}
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < m; j++){
    			int cur = targetGrid[i][j];
    			for(int c = 1; c < k; c++){
    				if(colorRange[cur][0] == 61 || c == cur){
    					continue;
    				}
    				if(colorRange[c][0] <= i && colorRange[c][1] <= j && colorRange[c][2] >= i && colorRange[c][3] >= j){
    					adj[cur].add(c);
    				}
    			}
    		}
    	}
    	return adj;
    }

    boolean checkOverlap(Set<Integer>[] dependencies){
    	int n = dependencies.length;
    	int[] visited = new int[n];
    	for(int i = 1; i < n; i++){
    		if(visited[i] == 0 && !dfs(dependencies, visited, i)){
    			return false;
    		}
    	}
    	return true;
    }

    boolean dfs(Set<Integer>[] dependencies, int[] visited, int cur){
    	if(visited[cur] == 1){
    		return false;
    	}
    	visited[cur] = 1;
    	for(int nxt : dependencies[cur]){
    		if(visited[nxt] != 2 && !dfs(dependencies, visited, nxt)){
    			return false;
    		}
    	}
    	visited[cur] = 2;
    	return true;
    }
}
// @lc code=end
