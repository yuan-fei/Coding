/*
 * @lc app=leetcode id=2240 lang=java
 *
 * [2240] Number of Ways to Buy Pens and Pencils
 *
 * https://leetcode.com/problems/number-of-ways-to-buy-pens-and-pencils/description/
 *
 * algorithms
 * Medium (53.96%)
 * Likes:    139
 * Dislikes: 6
 * Total Accepted:    11.1K
 * Total Submissions: 20.5K
 * Testcase Example:  '20\n10\n5'
 *
 * You are given an integer total indicating the amount of money you have. You
 * are also given two integers cost1 and cost2 indicating the price of a pen
 * and pencil respectively. You can spend part or all of your money to buy
 * multiple quantities (or none) of each kind of writing utensil.
 * 
 * Return the number of distinct ways you can buy some number of pens and
 * pencils.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: total = 20, cost1 = 10, cost2 = 5
 * Output: 9
 * Explanation: The price of a pen is 10 and the price of a pencil is 5.
 * - If you buy 0 pens, you can buy 0, 1, 2, 3, or 4 pencils.
 * - If you buy 1 pen, you can buy 0, 1, or 2 pencils.
 * - If you buy 2 pens, you cannot buy any pencils.
 * The total number of ways to buy pens and pencils is 5 + 3 + 1 = 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: total = 5, cost1 = 10, cost2 = 10
 * Output: 1
 * Explanation: The price of both pens and pencils are 10, which cost more than
 * total, so you cannot buy any writing utensils. Therefore, there is only 1
 * way: buy 0 pens and 0 pencils.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= total, cost1, cost2 <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for(int i = 0; i <= total / cost1; i++){
            ans += 1 + (total - i * cost1) / cost2;
        }
        return ans;
    }
}
// @lc code=end
