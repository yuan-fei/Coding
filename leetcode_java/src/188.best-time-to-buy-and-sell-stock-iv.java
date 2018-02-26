/*
 * [188] Best Time to Buy and Sell Stock IV
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
 *
 * algorithms
 * Hard (24.82%)
 * Total Accepted:    58.4K
 * Total Submissions: 235.1K
 * Testcase Example:  '2\n[]'
 *
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most k
 * transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Credits:Special thanks to @Freezen for adding this problem and creating all
 * test cases.
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
    	if(k > prices.length / 2){
    		return maxProfitWithoutLimit(prices);
    	}
    	
        int[][] buyState = new int[prices.length + 1][k + 1];
        int[][] sellState = new int[prices.length + 1][k + 1];

        for (int i = 0; i <= prices.length; i++) {
        	buyState[i][0] = 0;
        	sellState[i][0] = 0;
        }

        for (int i = 0; i <= k; i++) {
        	buyState[0][i] = -prices[0];
        	sellState[0][i] = 0;
        }

        for(int j = 1; j <= k; j++){
	        for (int i = 1; i <= prices.length; i++) {
	        	buyState[i][j] = Math.max(buyState[i - 1][j], sellState[i - 1][j - 1] - prices[i - 1]);
	        	sellState[i][j] = Math.max(sellState[i - 1][j], buyState[i - 1][j] + prices[i - 1]);
	        }        	
        }

        return sellState[prices.length][k];
    }

    public int maxProfitWithoutLimit(int[] prices) {
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
}
