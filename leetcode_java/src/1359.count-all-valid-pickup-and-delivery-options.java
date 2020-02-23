/*
 * @lc app=leetcode id=1359 lang=java
 *
 * [1359] Count All Valid Pickup and Delivery Options
 *
 * https://leetcode.com/problems/count-all-valid-pickup-and-delivery-options/description/
 *
 * algorithms
 * Hard (55.97%)
 * Likes:    31
 * Dislikes: 1
 * Total Accepted:    1.5K
 * Total Submissions: 2.7K
 * Testcase Example:  '1'
 *
 * Given n orders, each order consist in pickup and delivery services. 
 * 
 * Count all valid pickup/delivery possible sequences such that delivery(i) is
 * always after of pickup(i). 
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 1
 * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup
 * 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 6
 * Explanation: All possible orders: 
 * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1)
 * and (P2,D2,P1,D1).
 * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery
 * 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3
 * Output: 90
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 500
 * 
 */

// @lc code=start
class Solution {
    public int countOrders(int n) {
    	long mod = 1000000007;
    	long[][] dp = new long[n + 1][n + 1];
    	dp[0][0] = 1;
        for(int i = 1; i <= n; i++){
        	dp[i][0] = (dp[i - 1][0] * (n - i + 1)) % mod;
        	for(int j = 1; j <= i; j++){
        		dp[i][j] = (dp[i][j - 1] * (i - j + 1)) % mod;
        		if(i != j){
        			dp[i][j] += (dp[i - 1][j] * (n - i + 1)) % mod;
        		}
        	}
        }
        return (int)dp[n][n];
    }
}
// @lc code=end
