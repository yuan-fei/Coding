/*
 * @lc app=leetcode id=1594 lang=java
 *
 * [1594] Maximum Non Negative Product in a Matrix
 *
 * https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/description/
 *
 * algorithms
 * Medium (28.43%)
 * Likes:    72
 * Dislikes: 2
 * Total Accepted:    3.6K
 * Total Submissions: 12.7K
 * Testcase Example:  '[[-1,-2,-3],[-2,-3,-3],[-3,-3,-2]]'
 *
 * You are given a rows x cols matrix grid. Initially, you are located at the
 * top-left corner (0, 0), and in each step, you can only move right or down in
 * the matrix.
 * 
 * Among all possible paths starting from the top-left corner (0, 0) and ending
 * in the bottom-right corner (rows - 1, cols - 1), find the path with the
 * maximum non-negative product. The product of a path is the product of all
 * integers in the grid cells visited along the path.
 * 
 * Return the maximum non-negative product modulo 10^9 + 7. If the maximum
 * product is negative return -1.
 * 
 * Notice that the modulo is performed after getting the maximum product.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid = [[-1,-2,-3],
 * [-2,-3,-3],
 * [-3,-3,-2]]
 * Output: -1
 * Explanation: It's not possible to get non-negative product in the path from
 * (0, 0) to (2, 2), so return -1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[1,-2,1],
 * [1,-2,1],
 * [3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 =
 * 8).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: grid = [[1, 3],
 * [0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: grid = [[ 1, 4,4,0],
 * [-2, 0,0,1],
 * [ 1,-1,1,1]]
 * Output: 2
 * Explanation: Maximum non-negative product is in bold (1 * -2 * 1 * -1 * 1 *
 * 1 = 2).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rows, cols <= 15
 * -4 <= grid[i][j] <= 4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxProductPath(int[][] grid) {
    	int n = grid.length;
    	int m = grid[0].length;
    	long[][] minNeg = new long[n][m];
    	long[][] maxPos = new long[n][m];
    	boolean hasZero = false;
    	if(grid[0][0] == 0){
    		return 0;
    	}
    	else{
    		minNeg[0][0] = grid[0][0];	
    		maxPos[0][0] = grid[0][0];	
    	}
    	
    	for(int i = 1; i < n; i++){
    		if(grid[i][0] == 0){
    			hasZero = true;
    		}
    		else if(grid[i][0] < 0){
    			maxPos[i][0] = minNeg[i - 1][0] * grid[i][0];
    			minNeg[i][0] = maxPos[i - 1][0] * grid[i][0];
    		}
    		else{
    			minNeg[i][0] = minNeg[i - 1][0] * grid[i][0];	
    			maxPos[i][0] = maxPos[i - 1][0] * grid[i][0];	
    		}
    	}
    	for(int i = 1; i < m; i++){
    		if(grid[0][i] == 0){
    			hasZero = true;
    		}
    		else if(grid[0][i] < 0){
    			maxPos[0][i] = minNeg[0][i - 1] * grid[0][i];
    			minNeg[0][i] = maxPos[0][i - 1] * grid[0][i];
    		}
    		else{
    			minNeg[0][i] = minNeg[0][i - 1] * grid[0][i];	
    			maxPos[0][i] = maxPos[0][i - 1] * grid[0][i];
    		}
    	}
    	
        for(int i = 1; i < n; i++){
        	for(int j = 1; j < m; j++){
        		if(grid[i][j] == 0){
        			hasZero = true;
        		}
        		else{
	        		long min = Math.min(minNeg[i - 1][j], minNeg[i][j - 1]);
	        		long max = Math.max(maxPos[i - 1][j], maxPos[i][j - 1]);
	        		if(grid[i][j] < 0){
	        			minNeg[i][j] = 	max * grid[i][j];
	        			maxPos[i][j] = 	min * grid[i][j];
	        		}
	        		else{
	        			minNeg[i][j] = 	min * grid[i][j];
	        			maxPos[i][j] = 	max * grid[i][j];
	        		}	
        		}
        	}
        }
        System.out.println(Arrays.deepToString(minNeg));
        System.out.println(Arrays.deepToString(maxPos));
        if(maxPos[n - 1][m - 1] < 0){
        	return hasZero ? 0 : -1;
        }
        else{
        	return (int)(maxPos[n - 1][m - 1] % 1000000007);
        }
    }
}
// @lc code=end
