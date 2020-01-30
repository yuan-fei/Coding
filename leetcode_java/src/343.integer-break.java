/*
 * @lc app=leetcode id=343 lang=java
 *
 * [343] Integer Break
 *
 * https://leetcode.com/problems/integer-break/description/
 *
 * algorithms
 * Medium (49.18%)
 * Likes:    780
 * Dislikes: 199
 * Total Accepted:    94.9K
 * Total Submissions: 192.9K
 * Testcase Example:  '2'
 *
 * Given a positive integer n, break it into the sum of at least two positive
 * integers and maximize the product of those integers. Return the maximum
 * product you can get.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 * 
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * 
 * 
 */

// @lc code=start
class Solution {

	public int integerBreak(int n) {
		if(n == 2){
			return 1;
		}
		if(n == 3){
			return 2;
		}
		if(n % 3 == 0){
			return (int)Math.pow(3, n / 3);
		}
		else if(n % 3 == 1){
			return 2 * 2 * (int)Math.pow(3, (n - 4) / 3);
		}
		else{
			return 2 * (int)Math.pow(3, (n - 2) / 3);	
		}
	}

	int[] dp = new int[60];
    public int integerBreakDP(int n) {
    	if(n == 1){
    		return 1;
    	}
    	if(dp[n] == 0){
	    	for(int i = 1; i < n; i++){
	    		dp[n] = Math.max(dp[n], i * (n - i));
	    		dp[n] = Math.max(dp[n], integerBreakDP(i) * (n - i));
	        }
	        // System.out.println(n + ", " + dp[n]);	
    	}
        
        return dp[n];
    }
}
// @lc code=end
