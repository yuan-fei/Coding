/*
 * [122] Best Time to Buy and Sell Stock II
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
 *
 * algorithms
 * Easy (47.77%)
 * Total Accepted:    188.8K
 * Total Submissions: 395.1K
 * Testcase Example:  '[]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times). However, you may not engage in multiple transactions at the
 * same time (ie, you must sell the stock before you buy again).
 */
class Solution {
    public int maxProfit(int[] prices) {
        return solve2(prices);
    }

    private int solve1(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int increment = prices[i] - prices[i - 1];
            if(increment > 0){
                maxProfit += increment;
            }
        }
        return maxProfit;
    }

    private int solve2(int[] prices){
        if(prices == null || prices.length == 0){
            return 0;
        }

        int[] boughtState = new int[prices.length + 1];
        int[] soldState = new int[prices.length + 1];
        boughtState[0] = -prices[0];
        soldState[0] = 0;
        for (int i = 1; i <= prices.length; i++) {
            boughtState[i] = Math.max(boughtState[i - 1], soldState[i - 1] - prices[i - 1]);
            soldState[i] = Math.max(soldState[i - 1], boughtState[i - 1] + prices[i - 1]);
        }

        return soldState[prices.length];
    }
}
