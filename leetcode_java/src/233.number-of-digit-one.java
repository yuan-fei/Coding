/*
 * @lc app=leetcode id=233 lang=java
 *
 * [233] Number of Digit One
 *
 * https://leetcode.com/problems/number-of-digit-one/description/
 *
 * algorithms
 * Hard (30.87%)
 * Likes:    217
 * Dislikes: 523
 * Total Accepted:    44.8K
 * Total Submissions: 145.2K
 * Testcase Example:  '13'
 *
 * Given an integer n, count the total number of digit 1 appearing in all
 * non-negative integers less than or equal to n.
 * 
 * Example:
 * 
 * 
 * Input: 13
 * Output: 6 
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countDigitOne(int n) {
    	String s = n + "";
    	int l = s.length();
		dp = new int[l][2];
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < 2; j++){
				dp[i][j] = -1;
			}
		}
		
		int cnt = countDigitOne(s, 0, true);
		// System.out.println(Arrays.deepToString(dp));
		return cnt;
    }
    int[][] dp;
    int countDigitOne(String n, int b, boolean isTight){
    	int max = 0;
    	if(!isTight){
    		max = 9;
    	}
    	else{
    		max = n.charAt(b) - '0';
    	}
    	if(b == n.length() - 1){
    		if(max >= 1){
    			return 1;	
    		}
    		else{
    			return 0;
    		}
    	}
    	if(dp[b][isTight ? 1 : 0] == -1){
    		int cnt = 0;
			for(int i = 0; i <= max; i++){
	    		boolean isTightNext = (isTight && (i == max));
	    		cnt += countDigitOne(n, b + 1, isTightNext);
	    		if(i == 1){
	    			if(isTightNext){
	    				cnt += Integer.parseInt(n.substring(b + 1)) + 1;
	    			}
	    			else{
	    				cnt += (int)Math.pow(10, n.length() - b - 1);
	    			}
				}
	    	}
	    	dp[b][isTight ? 1 : 0] = cnt;
    	}
    	
    	return dp[b][isTight ? 1 : 0];
    }
}
// @lc code=end
