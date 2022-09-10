/*
 * @lc app=leetcode id=2400 lang=java
 *
 * [2400] Number of Ways to Reach a Position After Exactly k Steps
 *
 * https://leetcode.com/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps/description/
 *
 * algorithms
 * Medium (20.72%)
 * Likes:    88
 * Dislikes: 35
 * Total Accepted:    6.7K
 * Total Submissions: 27.8K
 * Testcase Example:  '1\n2\n3'
 *
 * You are given two positive integers startPos and endPos. Initially, you are
 * standing at position startPos on an infinite number line. With one step, you
 * can move either one position to the left, or one position to the right.
 * 
 * Given a positive integer k, return the number of different ways to reach the
 * position endPos starting from startPos, such that you perform exactly k
 * steps. Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * Two ways are considered different if the order of the steps made is not
 * exactly the same.
 * 
 * Note that the number line includes negative integers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: startPos = 1, endPos = 2, k = 3
 * Output: 3
 * Explanation: We can reach position 2 from 1 in exactly 3 steps in three
 * ways:
 * - 1 -> 2 -> 3 -> 2.
 * - 1 -> 2 -> 1 -> 2.
 * - 1 -> 0 -> 1 -> 2.
 * It can be proven that no other way is possible, so we return 3.
 * 
 * Example 2:
 * 
 * 
 * Input: startPos = 2, endPos = 5, k = 10
 * Output: 0
 * Explanation: It is impossible to reach position 5 from position 2 in exactly
 * 10 steps.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= startPos, endPos, k <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int numberOfWays(int startPos, int endPos, int k) {
        int d = Math.abs(endPos - startPos);
        if(k < d || (k - d) % 2 != 0){
            return 0;
        }
        return cmb(k, (k - d) / 2);
    }

    int cmb(int n, int k){
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++){
            dp[i][0] = 1;
            for(int j = 1; j <= i; j++){
                dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[n][k];
    }

}
// @lc code=end
