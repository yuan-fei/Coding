/*
 * @lc app=leetcode id=2110 lang=java
 *
 * [2110] Number of Smooth Descent Periods of a Stock
 *
 * https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/description/
 *
 * algorithms
 * Medium (52.11%)
 * Likes:    155
 * Dislikes: 5
 * Total Accepted:    9.2K
 * Total Submissions: 17.7K
 * Testcase Example:  '[3,2,1,4]'
 *
 * You are given an integer array prices representing the daily price history
 * of a stock, where prices[i] is the stock price on the i^th day.
 * 
 * A smooth descent period of a stock consists of one or more contiguous days
 * such that the price on each day is lower than the price on the preceding day
 * by exactly 1. The first day of the period is exempted from this rule.
 * 
 * Return the number of smooth descent periods.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: prices = [3,2,1,4]
 * Output: 7
 * Explanation: There are 7 smooth descent periods:
 * [3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
 * Note that a period with one day is a smooth descent period by the
 * definition.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: prices = [8,6,7,7]
 * Output: 4
 * Explanation: There are 4 smooth descent periods: [8], [6], [7], and [7]
 * Note that [8,6] is not a smooth descent period as 8 - 6 ≠ 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: prices = [1]
 * Output: 1
 * Explanation: There is 1 smooth descent period: [1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= prices.length <= 10^5
 * 1 <= prices[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long getDescentPeriods(int[] prices) {
        long cnt = 0;
        int l = 0;
        for(int r = 1; r < prices.length; r++){
            if(prices[r - 1] - prices[r] != 1){
                long t = r - l;
                cnt += (t + 1) * t / 2;
                l = r;
            }
            // System.out.println(cnt);
        }
        long t = prices.length - l;
        cnt += (t + 1) * t / 2;
        return cnt;
    }
}
// @lc code=end
