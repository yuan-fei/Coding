/*
 * @lc app=leetcode id=213 lang=java
 *
 * [213] House Robber II
 *
 * https://leetcode.com/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (35.77%)
 * Likes:    1218
 * Dislikes: 40
 * Total Accepted:    142.1K
 * Total Submissions: 397.1K
 * Testcase Example:  '[2,3,2]'
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed. All houses at this place are
 * arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on
 * the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money
 * = 2),
 * because they are adjacent houses.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money =
 * 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 */

// @lc code=start
class Solution {
    public int rob(int[] nums) {

    	int n = nums.length;
    	if(n == 0){
    		return 0;
    	}
    	if(n == 1){
    		return nums[0];
    	}
        int[][] dp = new int[n + 1][2];
        dp[1][0] = 0;
        dp[1][1] = nums[0];
        for(int i = 2; i < n; i++){
        	dp[i][0] = Math.max(nums[i - 1] + dp[i - 2][0], dp[i - 1][0]);
        	dp[i][1] = Math.max(nums[i - 1] + dp[i - 2][1], dp[i - 1][1]);
        }
        dp[n][0] = Math.max(nums[n - 1] + dp[n - 2][0], dp[n - 1][0]);
        dp[n][1] = dp[n - 1][1];
        // System.out.println(Arrays.deepToString(dp));
        return Math.max(dp[n][0], dp[n][1]);
    }
}
// @lc code=end
