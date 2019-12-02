/*
 * @lc app=leetcode id=1277 lang=java
 *
 * [1277] Count Square Submatrices with All Ones
 *
 * https://leetcode.com/problems/count-square-submatrices-with-all-ones/description/
 *
 * algorithms
 * Medium (62.08%)
 * Likes:    50
 * Dislikes: 1
 * Total Accepted:    2.4K
 * Total Submissions: 3.9K
 * Testcase Example:  '[[0,1,1,1],[1,1,1,1],[0,1,1,1]]'
 *
 * Given a m * n matrix of ones and zeros, return how many square submatrices
 * have all ones.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation: 
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = 
 * [
 * ⁠ [1,0,1],
 * ⁠ [1,1,0],
 * ⁠ [1,1,0]
 * ]
 * Output: 7
 * Explanation: 
 * There are 6 squares of side 1.  
 * There is 1 square of side 2. 
 * Total number of squares = 6 + 1 = 7.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {

	// O(m*n)
	public int countSquares(int[][] matrix) {

    	int n = matrix.length;
    	int m = matrix[0].length;
    	int[][] dp = new int[n + 1][m + 1];
    	int cnt = 0;
        for (int i = 1; i <= n; i++) {
        	for(int j = 1; j <= m; j++){
        		if(matrix[i - 1][j - 1] == 1){
        			dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1],dp[i - 1][j])) + 1;	
        		}
        		cnt += dp[i][j];
        	}
        }
        return cnt;
    }

    // O(n*m*m)
    public int countSquares1(int[][] matrix) {

    	int n = matrix.length;
    	int m = matrix[0].length;
    	int[][] maxRight = new int[n][m];
        for (int i = 0; i < n; i++) {
        	for(int j = m - 1; j >= 0; j--){
        		maxRight[i][j] = -1;
        		if(matrix[i][j] == 1){
        			maxRight[i][j] = j;
        			if(j < m - 1){
        				maxRight[i][j] = Math.max(maxRight[i][j], maxRight[i][j + 1]);	
        			}
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(maxRight));
        int cnt = 0;
        for(int i = 0; i < m; i++){
        	for(int j = i; j < m; j++){
        		int l = j - i + 1;
        		int lst = -1;
        		for(int k = 0; k < n; k++){
        			if(maxRight[k][i] >= j){
        				if(lst == -1){
        					lst = k;
        				}
        				if(k - lst + 1 >= l){
        					cnt++;
        				}
        			}
        			else{
        				lst = -1;
        			}
        		}
        	}
        }
        return cnt;
    }
}
// @lc code=end
