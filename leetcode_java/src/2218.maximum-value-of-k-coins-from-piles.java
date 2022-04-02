/*
 * @lc app=leetcode id=2218 lang=java
 *
 * [2218] Maximum Value of K Coins From Piles
 *
 * https://leetcode.com/problems/maximum-value-of-k-coins-from-piles/description/
 *
 * algorithms
 * Hard (40.51%)
 * Likes:    137
 * Dislikes: 2
 * Total Accepted:    2.8K
 * Total Submissions: 7K
 * Testcase Example:  '[[1,100,3],[7,8,9]]\n2'
 *
 * There are n piles of coins on a table. Each pile consists of a positive
 * number of coins of assorted denominations.
 * 
 * In one move, you can choose any coin on top of any pile, remove it, and add
 * it to your wallet.
 * 
 * Given a list piles, where piles[i] is a list of integers denoting the
 * composition of the i^th pile from top to bottom, and a positive integer k,
 * return the maximum total value of coins you can have in your wallet if you
 * choose exactly k coins optimally.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: piles = [[1,100,3],[7,8,9]], k = 2
 * Output: 101
 * Explanation:
 * The above diagram shows the different ways we can choose k coins.
 * The maximum total we can obtain is 101.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: piles = [[100],[100],[100],[100],[100],[100],[1,1,1,1,1,1,700]], k =
 * 7
 * Output: 706
 * Explanation:
 * The maximum total can be obtained if we choose all coins from the last
 * pile.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == piles.length
 * 1 <= n <= 1000
 * 1 <= piles[i][j] <= 10^5
 * 1 <= k <= sum(piles[i].length) <= 2000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= k; j++){
                dp[i][j] = dp[i - 1][j];
                int sum = 0;
                for(int c = 1; c <= Math.min(j, piles.get(i - 1).size()); c++){
                    sum += piles.get(i - 1).get(c - 1);
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - c] + sum);
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[n][k];
    }
}
// @lc code=end
