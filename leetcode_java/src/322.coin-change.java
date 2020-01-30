/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 *
 * https://leetcode.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (33.28%)
 * Likes:    2867
 * Dislikes: 97
 * Total Accepted:    304.4K
 * Total Submissions: 913.9K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3 
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * 
 * 
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * 
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * 
 */

// @lc code=start
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int MAX = amount + 5;
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for(int c : coins){
        	for(int i = c; i <= amount; i++){
        		dp[i] = Math.min(dp[i], dp[i - c] + 1);
        	}
        }
        return dp[amount] >= MAX ? -1 : dp[amount];
    }
}
// @lc code=end
