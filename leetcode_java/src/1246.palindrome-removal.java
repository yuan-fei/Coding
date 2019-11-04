/*
 * @lc app=leetcode id=1246 lang=java
 *
 * [1246] Palindrome Removal
 *
 * https://leetcode.com/problems/palindrome-removal/description/
 *
 * algorithms
 * Hard (39.79%)
 * Likes:    43
 * Dislikes: 0
 * Total Accepted:    807
 * Total Submissions: 2K
 * Testcase Example:  '[1,2]'
 *
 * Given an integer array arr, in one move you can select a palindromic
 * subarray arr[i], arr[i+1], ..., arr[j] where i <= j, and remove that
 * subarray from the given array. Note that after removing a subarray, the
 * elements on the left and on the right of that subarray move to fill the gap
 * left by the removal.
 * 
 * Return the minimum number of moves needed to remove all numbers from the
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [1,2]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,3,4,1,5]
 * Output: 3
 * Explanation: Remove [4] then remove [1,3,1] then remove [5].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 100
 * 1 <= arr[i] <= 20
 * 
 * 
 */

// @lc code=start
class Solution {

	int[][] dp;
	public int minimumMoves(int[] arr) {
		int n = arr.length;
    	dp = new int[n + 1][n + 1];
    	return minimumMovesTopDown(arr, 1 , arr.length);
    }

    int minimumMovesTopDown(int[] arr, int i, int j) {
    	if(dp[i][j] == 0){
			if(i == j){
				dp[i][j] = 1;
	    	}
	    	else if(i + 1 == j){
	    		dp[i][j] = (arr[i - 1] == arr[j - 1]) ? 1 : 2;
	    	}
	    	else{
	    		dp[i][j] = 1000;
	    		if(arr[i - 1] == arr[j - 1]){
	    			dp[i][j] = minimumMovesTopDown(arr, i + 1, j - 1);
	    		}
	    		else{
		    		for(int k = i; k < j; k++){
						dp[i][j] = Math.min(dp[i][j], minimumMovesTopDown(arr, i, k) + minimumMovesTopDown(arr, k + 1, j));		
					}			
	    		}
	    	}
    	}
    	return dp[i][j];
    	
    }

    public int minimumMovesBottomUpDp(int[] arr) {
    	int n = arr.length;
    	int[][] dp = new int[n + 1][n + 1];
    	for(int i = 1; i <= n; i++){
    		dp[i][i] = 1;
    		if(i > 1){
    			dp[i - 1][i] = (arr[i - 2] == arr[i - 1]) ? 1 : 2;	
    		}
    		
    	}
        for (int l = 3; l <= n; l++) {
			for(int i = 1; i + l - 1 <= n; i++){
				dp[i][i + l - 1] = 1000;
				if(arr[i - 1] == arr[i + l - 2]){
					dp[i][i + l - 1] = dp[i + 1][i + l - 2];
				}
				else{
					for(int j = i; j < i + l - 1; j++){
						dp[i][i + l - 1] = Math.min(dp[i][i + l - 1], dp[i][j] + dp[j + 1][i + l - 1]);		
					}
				}
			}        	
        }
        return dp[1][n];
    }
}
// @lc code=end
