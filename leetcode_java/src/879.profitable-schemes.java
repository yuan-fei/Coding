/*
 * @lc app=leetcode id=879 lang=java
 *
 * [879] Profitable Schemes
 *
 * https://leetcode.com/problems/profitable-schemes/description/
 *
 * algorithms
 * Hard (49.69%)
 * Likes:    1642
 * Dislikes: 113
 * Total Accepted:    55.8K
 * Total Submissions: 112.7K
 * Testcase Example:  '5\n3\n[2,2]\n[2,3]'
 *
 * There is a group of n members, and a list of various crimes they could
 * commit. The i^th crime generates a profit[i] and requires group[i] members
 * to participate in it. If a member participates in one crime, that member
 * can't participate in another crime.
 * 
 * Let's call a profitable scheme any subset of these crimes that generates at
 * least minProfit profit, and the total number of members participating in
 * that subset of crimes is at most n.
 * 
 * Return the number of schemes that can be chosen. Since the answer may be
 * very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
 * Output: 2
 * Explanation: To make a profit of at least 3, the group could either commit
 * crimes 0 and 1, or just crime 1.
 * In total, there are 2 schemes.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
 * Output: 7
 * Explanation: To make a profit of at least 5, the group could commit any
 * crimes, as long as they commit one.
 * There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and
 * (0,1,2).
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 100
 * 0 <= minProfit <= 100
 * 1 <= group.length <= 100
 * 1 <= group[i] <= 100
 * profit.length == group.length
 * 0 <= profit[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int MOD = (int)1e9 + 7;
        int nG = group.length;
        int[][][] dp = new int[nG + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for(int i = 1; i <= nG; i++){
            for(int p = 0; p <= minProfit; p++){
                for(int m = 0; m <= n; m++){
                    dp[i][m][p] += dp[i - 1][m][p];
                    dp[i][m][p] %= MOD;
                    if(m + group[i - 1] <= n){
                        dp[i][m + group[i - 1]][Math.min(minProfit, p + profit[i - 1])] += dp[i - 1][m][p];
                        dp[i][m + group[i - 1]][Math.min(minProfit, p + profit[i - 1])] %= MOD;
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i <= n; i++){
            ans += dp[nG][i][minProfit];
            ans %= MOD;
        }
        return ans;
    }
}
// @lc code=end
