/*
 * @lc app=leetcode id=1411 lang=java
 *
 * [1411] Number of Ways to Paint N × 3 Grid
 *
 * https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/description/
 *
 * algorithms
 * Hard (61.90%)
 * Likes:    178
 * Dislikes: 9
 * Total Accepted:    6.5K
 * Total Submissions: 10.5K
 * Testcase Example:  '1'
 *
 * You have a grid of size n x 3 and you want to paint each cell of the grid
 * with exactly one of the three colours: Red, Yellow or Green while making
 * sure that no two adjacent cells have the same colour (i.e no two cells that
 * share vertical or horizontal sides have the same colour).
 * 
 * You are given n the number of rows of the grid.
 * 
 * Return the number of ways you can paint this grid. As the answer may grow
 * large, the answer must be computed modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 54
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3
 * Output: 246
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: n = 7
 * Output: 106494
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: n = 5000
 * Output: 30228214
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 * 
 */

// @lc code=start
class Solution {
    public int numOfWays(int n) {
    	int[][] m = generateTransformMatrix();
    	if(n == 1){
    		return m.length;
    	}
    	int[][] r = fastExp(m, n - 1);
    	int ans = 0;
    	for(int i = 0; i < r.length; i++){
    		for(int j = 0; j < r.length; j++){
    			ans = (ans + r[i][j]) % mod;
    		}
    	}
    	return ans;
    }
    int mod = 1000000007;
    int[][] generateTransformMatrix(){
    	List<String> states = new ArrayList<>();
    	for(int i = 0; i < 3; i++){
    		for(int j = 0; j < 3; j++){
    			for(int k = 0; k < 3; k++){
    				if(i != j && j != k){
    					states.add(""+i+j+k);
    				}
    			}
    		}
    	}
    	int n = states.size();
    	int[][] mat = new int[n][n];
    	for(int i = 0; i < n; i++){
    		for(int j = 0; j < n; j++){
    			boolean good = true;
    			for(int k = 0; k < 3; k++){
    				if(states.get(i).charAt(k) == states.get(j).charAt(k)){
    					good = false;
    					break;
    				}
    			}
    			if(good){
    				mat[i][j] = 1;
    			}
    		}
    	}
    	return mat;
    }

    int[][] fastExp(int[][] m, int exp){
    	if(exp == 1){
    		return m;
    	}
    	else{
    		if(exp % 2 == 1){
    			return matrixMul(m, fastExp(m, exp - 1));
    		}
    		else{
    			int[][] t = fastExp(m, exp / 2);
    			return matrixMul(t, t);	
    		}
    	}
    }

    int[][] matrixMul(int[][] m1, int[][] m2){
    	int[][] ret = new int[m1.length][m2[0].length];
    	for(int i = 0; i < ret.length; i++){
    		for(int j = 0; j < ret[i].length; j++){
    			for(int k = 0; k < m1[i].length; k++){
    				long t = 1L * m1[i][k] * m2[k][j];
    				ret[i][j] = ret[i][j] + (int)(t % mod);
    				ret[i][j] %= mod;
    			}
    		}
    	}
    	return ret;
    }
}
// @lc code=end
