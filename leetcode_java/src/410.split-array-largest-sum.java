/*
 * @lc app=leetcode id=410 lang=java
 *
 * [410] Split Array Largest Sum
 *
 * https://leetcode.com/problems/split-array-largest-sum/description/
 *
 * algorithms
 * Hard (44.03%)
 * Likes:    1543
 * Dislikes: 79
 * Total Accepted:    83K
 * Total Submissions: 188.4K
 * Testcase Example:  '[7,2,5,10,8]\n2'
 *
 * Given an array which consists of non-negative integers and an integer m, you
 * can split the array into m non-empty continuous subarrays. Write an
 * algorithm to minimize the largest sum among these m subarrays.
 * 
 * 
 * Note:
 * If n is the length of array, assume the following constraints are
 * satisfied:
 * 
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * 
 * 
 * 
 * Examples: 
 * 
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * 
 * Output:
 * 18
 * 
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * 
 * 
 */

// @lc code=start
class Solution {
	long MAX = 5L + Integer.MAX_VALUE;
    public int splitArray(int[] nums, int m) {
    	int n = nums.length;
    	long[][] dp = new long[n + 1][m + 1];
    	for(int i = 0; i <= n; i++){
    		dp[i][0] = MAX;
    	}
    	dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= Math.min(m, i); j++){
        		dp[i][j] = MAX;
        		long sum = 0;
        		for(int k = i; k >= j; k--){
        			sum += nums[k - 1];
        			dp[i][j] = Math.min(dp[i][j], Math.max(sum, dp[k - 1][j - 1]));
        		}
        	}
        }
        return (int)dp[n][m];
    }
}
// @lc code=end
