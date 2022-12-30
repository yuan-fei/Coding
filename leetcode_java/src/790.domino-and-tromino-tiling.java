/*
 * @lc app=leetcode id=790 lang=java
 *
 * [790] Domino and Tromino Tiling
 *
 * https://leetcode.com/problems/domino-and-tromino-tiling/description/
 *
 * algorithms
 * Medium (53.03%)
 * Likes:    2682
 * Dislikes: 833
 * Total Accepted:    89.5K
 * Total Submissions: 168.8K
 * Testcase Example:  '3'
 *
 * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You
 * may rotate these shapes.
 * 
 * Given an integer n, return the number of ways to tile an 2 x n board. Since
 * the answer may be very large, return it modulo 10^9 + 7.
 * 
 * In a tiling, every square must be covered by a tile. Two tilings are
 * different if and only if there are two 4-directionally adjacent cells on the
 * board such that exactly one of the tilings has both squares occupied by a
 * tile.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3
 * Output: 5
 * Explanation: The five different ways are show above.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1
 * Output: 1
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numTilings(int n) {
        int MOD = (int)1e9 + 7;
        int[][] dp  =new int[n + 1][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for(int i = 2; i <= n; i++){
            // *
            // *
            dp[i][0] += dp[i - 1][0];
            dp[i][0] %= MOD;
            // **
            // **
            dp[i][0] += dp[i - 2][0];
            dp[i][0] %= MOD;
            //  *
            // **
            dp[i][0] += dp[i - 1][1];
            dp[i][0] %= MOD;
            // **
            //  *
            dp[i][0] += dp[i - 1][2];
            dp[i][0] %= MOD;
            // **
            // *
            dp[i][1] += dp[i - 2][0];
            dp[i][1] %= MOD;
            // **
            // 
            dp[i][1] += dp[i - 1][2];
            dp[i][1] %= MOD;
            // *
            // **
            dp[i][2] += dp[i - 2][0];
            dp[i][2] %= MOD;
            // 
            // **
            dp[i][2] += dp[i - 1][1];
            dp[i][2] %= MOD;
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[n][0];
    }
}
// @lc code=end
