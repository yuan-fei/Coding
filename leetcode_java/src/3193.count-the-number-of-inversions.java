/*
 * @lc app=leetcode id=3193 lang=java
 *
 * [3193] Count the Number of Inversions
 *
 * https://leetcode.com/problems/count-the-number-of-inversions/description/
 *
 * algorithms
 * Hard (33.09%)
 * Likes:    168
 * Dislikes: 31
 * Total Accepted:    9.3K
 * Total Submissions: 32K
 * Testcase Example:  '3\n[[2,2],[0,0]]'
 *
 * You are given an integer n and a 2D array requirements, where
 * requirements[i] = [endi, cnti] represents the end index and the inversion
 * count of each requirement.
 * 
 * A pair of indices (i, j) from an integer array nums is called an inversion
 * if:
 * 
 * 
 * i < j and nums[i] > nums[j]
 * 
 * 
 * Return the number of permutations perm of [0, 1, 2, ..., n - 1] such that
 * for all requirements[i], perm[0..endi] has exactly cnti inversions.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, requirements = [[2,2],[0,0]]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The two permutations are:
 * 
 * 
 * [2, 0, 1]
 * 
 * 
 * Prefix [2, 0, 1] has inversions (0, 1) and (0, 2).
 * Prefix [2] has 0 inversions.
 * 
 * 
 * [1, 2, 0]
 * 
 * Prefix [1, 2, 0] has inversions (0, 2) and (1, 2).
 * Prefix [1] has 0 inversions.
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 3, requirements = [[2,2],[1,1],[0,0]]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The only satisfying permutation is [2, 0, 1]:
 * 
 * 
 * Prefix [2, 0, 1] has inversions (0, 1) and (0, 2).
 * Prefix [2, 0] has an inversion (0, 1).
 * Prefix [2] has 0 inversions.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, requirements = [[0,0],[1,0]]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The only satisfying permutation is [0, 1]:
 * 
 * 
 * Prefix [0] has 0 inversions.
 * Prefix [0, 1] has an inversion (0, 1).
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 300
 * 1 <= requirements.length <= n
 * requirements[i] = [endi, cnti]
 * 0 <= endi <= n - 1
 * 0 <= cnti <= 400
 * The input is generated such that there is at least one i such that endi == n
 * - 1.
 * The input is generated such that all endi are unique.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int MAX_INVERSION = 400;
    int MOD = (int) 1e9 + 7;

    public int numberOfPermutations(int n, int[][] requirements) {
        // n : length, m : inversion count
        // dp(n, m) = dp(n - 1, m) + dp(n - 1, m - 1) +...+ dp(n - 1, m - (n - 1))

        // dp(n, m) = 0 when(n, m) is not in req, but 'n' is in req
        // dp(n, m) = sum(dp(n - 1, m), ... dp(n -1, max(m - (n - 1), 0)) otherwise
        // dp(0,0) = 1
        int[][] dp = new int[n + 1][MAX_INVERSION + 1];
        dp[0][0] = 1;
        int[] invCountsRequirements = new int[n];
        Arrays.fill(invCountsRequirements, -1);
        for (int[] req : requirements) {
            invCountsRequirements[req[0]] = req[1];
        }
        for (int len = 1; len <= n; len++) {
            for (int cnt = 0; cnt <= MAX_INVERSION; cnt++) {
                if (invCountsRequirements[len - 1] == -1 || invCountsRequirements[len - 1] == cnt) {
                    for (int preCnt = cnt; preCnt >= Math.max(0, cnt - (len - 1)); preCnt--) {
                        dp[len][cnt] += dp[len - 1][preCnt];
                        dp[len][cnt] %= MOD;
                    }
                }
            }
        }
        return dp[n][invCountsRequirements[n - 1]];
    }
}
// @lc code=end
