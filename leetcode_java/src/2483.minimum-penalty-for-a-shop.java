/*
 * @lc app=leetcode id=2483 lang=java
 *
 * [2483] Minimum Penalty for a Shop
 *
 * https://leetcode.com/problems/minimum-penalty-for-a-shop/description/
 *
 * algorithms
 * Medium (51.12%)
 * Likes:    104
 * Dislikes: 1
 * Total Accepted:    7.2K
 * Total Submissions: 14.1K
 * Testcase Example:  '"YYNY"'
 *
 * You are given the customer visit log of a shop represented by a 0-indexed
 * string customers consisting only of characters 'N' and 'Y':
 * 
 * 
 * if the i^th character is 'Y', it means that customers come at the i^th
 * hour
 * whereas 'N' indicates that no customers come at the i^th hour.
 * 
 * 
 * If the shop closes at the j^th hour (0 <= j <= n), the penalty is calculated
 * as follows:
 * 
 * 
 * For every hour when the shop is open and no customers come, the penalty
 * increases by 1.
 * For every hour when the shop is closed and customers come, the penalty
 * increases by 1.
 * 
 * 
 * Return the earliest hour at which the shop must be closed to incur a minimum
 * penalty.
 * 
 * Note that if a shop closes at the j^th hour, it means the shop is closed at
 * the hour j.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: customers = "YYNY"
 * Output: 2
 * Explanation: 
 * - Closing the shop at the 0^th hour incurs in 1+1+0+1 = 3 penalty.
 * - Closing the shop at the 1^st hour incurs in 0+1+0+1 = 2 penalty.
 * - Closing the shop at the 2^nd hour incurs in 0+0+0+1 = 1 penalty.
 * - Closing the shop at the 3^rd hour incurs in 0+0+1+1 = 2 penalty.
 * - Closing the shop at the 4^th hour incurs in 0+0+1+0 = 1 penalty.
 * Closing the shop at 2^nd or 4^th hour gives a minimum penalty. Since 2 is
 * earlier, the optimal closing time is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: customers = "NNNNN"
 * Output: 0
 * Explanation: It is best to close the shop at the 0^th hour as no customers
 * arrive.
 * 
 * Example 3:
 * 
 * 
 * Input: customers = "YYYY"
 * Output: 4
 * Explanation: It is best to close the shop at the 4^th hour as customers
 * arrive at each hour.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= customers.length <= 10^5
 * customers consists only of characters 'Y' and 'N'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int bestClosingTime(String customers) {
        int minCost = Integer.MAX_VALUE;
        int ans = 0;
        int cost = 0;
        for(int i = 0; i < customers.length(); i++){
            if(customers.charAt(i) == 'Y'){
                cost++;
            }
        }
        minCost = Math.min(minCost, cost);
        for(int i = 0; i < customers.length(); i++){
            if(customers.charAt(i) == 'Y'){
                cost--;
            }
            else{
                cost++;
            }
            if(cost < minCost){
                minCost = cost;
                ans = i + 1;
            }
        }
        return ans;
    }
}
// @lc code=end
