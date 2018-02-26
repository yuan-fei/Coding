/*
 * [123] Best Time to Buy and Sell Stock III
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
 *
 * algorithms
 * Hard (30.18%)
 * Total Accepted:    103.2K
 * Total Submissions: 341.6K
 * Testcase Example:  '[]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most two
 * transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 */
class Solution {
    public int maxProfit(int[] prices) {
        return solve2(prices);
    }

    private int solve1(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        int min = prices[0];
        int[] maxProfit1 = new int[prices.length];
        maxProfit1[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxProfit1[i] = Math.max(maxProfit1[i - 1], prices[i] - min);
        }

        int max = prices[prices.length - 1];
        int[] maxProfit2 = new int[prices.length];
        maxProfit2[prices.length - 1] = 0;
        for (int i = prices.length - 2; i > 0; i--) {
            max = Math.max(max, prices[i]);
            maxProfit2[i] = Math.max(maxProfit2[i + 1], max - prices[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit1[i] + maxProfit2[i], maxProfit);
        }
        return maxProfit;
    }

    private int solve2(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }

        int[][] buyState = new int[prices.length + 1][3];
        int[][] sellState = new int[prices.length + 1][3];

        for (int i = 0; i <= prices.length; i++) {
            buyState[i][0] = 0;
            sellState[i][0] = 0;
        }

        for (int i = 0; i <= 2; i++) {
            buyState[0][i] = -prices[0];
            sellState[0][i] = 0;
        }


        for (int i = 1; i <= prices.length; i++) {
            for(int j = 1; j <= 2; j++){
                buyState[i][j] = Math.max(buyState[i - 1][j], sellState[i - 1][j - 1] - prices[i - 1]);
                sellState[i][j] = Math.max(sellState[i - 1][j], buyState[i - 1][j] + prices[i - 1]);
            }           
        }

        return sellState[prices.length][2];
    }
}
