/*
 * @lc app=leetcode id=1444 lang=java
 *
 * [1444] Number of Ways of Cutting a Pizza
 *
 * https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/description/
 *
 * algorithms
 * Hard (45.23%)
 * Likes:    38
 * Dislikes: 3
 * Total Accepted:    1.6K
 * Total Submissions: 3.6K
 * Testcase Example:  '["A..","AAA","..."]\n3'
 *
 * Given a rectangular pizza represented as a rows x cols matrix containing the
 * following characters: 'A' (an apple) and '.' (empty cell) and given the
 * integer k. You have to cut the pizza into k pieces using k-1 cuts. 
 * 
 * For each cut you choose the direction: vertical or horizontal, then you
 * choose a cut position at the cell boundary and cut the pizza into two
 * pieces. If you cut the pizza vertically, give the left part of the pizza to
 * a person. If you cut the pizza horizontally, give the upper part of the
 * pizza to a person. Give the last piece of pizza to the last person.
 * 
 * Return the number of ways of cutting the pizza such that each piece contains
 * at least one apple. Since the answer can be a huge number, return this
 * modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: pizza = ["A..","AAA","..."], k = 3
 * Output: 3 
 * Explanation: The figure above shows the three ways to cut the pizza. Note
 * that pieces must contain at least one apple.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: pizza = ["A..","AA.","..."], k = 3
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: pizza = ["A..","A..","..."], k = 1
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza consists of characters 'A' and '.' only.
 * 
 */

// @lc code=start
class Solution {
    public int ways(String[] pizza, int k) {
    	int n = pizza.length;
    	int m = pizza[0].length();
		prefSum = new int[n + 1][m + 1];
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= m; j++){
        		prefSum[i][j] = prefSum[i - 1][j] + prefSum[i][j - 1] - prefSum[i - 1][j - 1] + (pizza[i - 1].charAt(j - 1) =='A'? 1 : 0);
        	}
        }
        cache = new long[n][m][k + 1];
        for (int i = 0; i < cache.length; i++) {
        	for(int j = 0; j < cache[i].length; j++){
        		Arrays.fill(cache[i][j], -1);
        	}
        }
        return (int)dfs(pizza, 0, 0, k - 1);
    }
    int[][] prefSum;
    long mod = 1000000007;
    long[][][] cache;
    long dfs(String[] pizza, int x, int y, int cutLeft){
    	int n = pizza.length;
    	int m = pizza[0].length();
    	if(cache[x][y][cutLeft] == -1){
    		if(cutLeft == 0){
	    		if(prefSum[n][m] - prefSum[n][y] - prefSum[x][m] + prefSum[x][y] > 0){
	    			// System.out.println("" + x + ", " + y + ", "+ cutLeft +"=" + 1);
	    			cache[x][y][cutLeft] = 1;
	    		}
	    		else{
	    			// System.out.println("" + x + ", " + y + ", "+ cutLeft +"=" + 0);
	    			cache[x][y][cutLeft] = 0;
	    		}
	    	}
	    	else{
	    		long ret = 0;
		    	for(int i = x; i < n - 1; i++){
		    		if(prefSum[i + 1][m] - prefSum[i + 1][y] - prefSum[x][m] + prefSum[x][y] > 0){
		    			long sub = dfs(pizza, i + 1, y, cutLeft - 1);
		    			if(sub > 0){
		    				ret = (ret + sub) % mod;
		    			}
		    		}
		    	}
		    	for(int j = y; j < m - 1; j++){
		    		if(prefSum[n][j + 1] - prefSum[x][j + 1] - prefSum[n][y] + prefSum[x][y] > 0){
		    			long sub = dfs(pizza, x, j + 1, cutLeft - 1);
		    			if(sub > 0){
		    				ret = (ret + sub) % mod;
		    			}
		    		}
		    	}
		    	cache[x][y][cutLeft] = ret;;
	    	}
    	}
    	
    	// System.out.println("" + x + ", " + y + ", "+ cutLeft +"=" + ret);
    	return cache[x][y][cutLeft];
    }
}
// @lc code=end
