/*
 * [309] Best Time to Buy and Sell Stock with Cooldown
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * algorithms
 * Medium (41.77%)
 * Total Accepted:    56.8K
 * Total Submissions: 135.9K
 * Testcase Example:  '[1,2,3,0,2]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the
 * following restrictions:
 * 
 * 
 * ⁠   You may not engage in multiple transactions at the same time (ie, you
 * must sell the stock before you buy again).
 * ⁠   After you sell your stock, you cannot buy stock on next day. (ie,
 * cooldown 1 day)
 * 
 * 
 * Example:
 * 
 * prices = [1, 2, 3, 0, 2]
 * maxProfit = 3
 * transactions = [buy, sell, cooldown, buy, sell]
 * 
 * 
 * Credits:Special thanks to @dietpepsi for adding this problem and creating
 * all test cases.
 */

/*
Analysis:
state machine: bought: hold->bought; sell->sold. sold: hold->cooldown. cooldown: hold->cooldown; buy->bought
*/
class Solution {
    public int maxProfit(int[] prices) {
    	if(prices == null || prices.length == 0){
    		return 0;
    	}
        int[] boughtState = new int[prices.length + 1];
		int[] soldState = new int[prices.length + 1];
		int[] cooldownState = new int[prices.length + 1];

		
		boughtState[0] = -prices[0];
		soldState[0] = 0;
		cooldownState[0] = 0;
		
		for (int i = 1; i <= prices.length; i++) {
			boughtState[i] = Math.max(boughtState[i - 1], cooldownState[i - 1] - prices[i - 1]);
			soldState[i] = boughtState[i - 1] + prices[i - 1];
			cooldownState[i] = Math.max(cooldownState[i - 1], soldState[i - 1]);
		}
		return Math.max(soldState[prices.length], cooldownState[prices.length]);
    }
}
