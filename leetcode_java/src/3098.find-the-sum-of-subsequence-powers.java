/*
 * @lc app=leetcode id=3098 lang=java
 *
 * [3098] Find the Sum of Subsequence Powers
 *
 * https://leetcode.com/problems/find-the-sum-of-subsequence-powers/description/
 *
 * algorithms
 * Hard (24.38%)
 * Likes:    135
 * Dislikes: 6
 * Total Accepted:    5.6K
 * Total Submissions: 22.9K
 * Testcase Example:  '[1,2,3,4]\n3'
 *
 * You are given an integer array nums of length n, and a positive integer k.
 * 
 * The power of a subsequence is defined as the minimum absolute difference
 * between any two elements in the subsequence.
 * 
 * Return the sum of powers of all subsequences of nums which have length equal
 * to k.
 * 
 * Since the answer may be large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4], k = 3
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * There are 4 subsequences in nums which have length 3: [1,2,3], [1,3,4],
 * [1,2,4], and [2,3,4]. The sum of powers is |2 - 3| + |3 - 4| + |2 - 1| + |3
 * - 4| = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,2], k = 2
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * The only subsequence in nums which has length 2 is [2,2]. The sum of powers
 * is |2 - 2| = 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,3,-1], k = 2
 * 
 * Output: 10
 * 
 * Explanation:
 * 
 * There are 3 subsequences in nums which have length 2: [4,3], [4,-1], and
 * [3,-1]. The sum of powers is |4 - 3| + |4 - (-1)| + |3 - (-1)| = 10.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n == nums.length <= 50
 * -10^8 <= nums[i] <= 10^8 
 * 2 <= k <= n
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    long MOD = (long) 1e9 + 7;

    public int sumOfPowers(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        @SuppressWarnings("unchecked")
        Map<Integer, Long>[][] dp = new Map[n + 1][k + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = new HashMap<>();
            }
            dp[i][1].put(Integer.MAX_VALUE, 1L);
        }

        for (int i = 1; i <= n; i++) {
            for (int k1 = 2; k1 <= k; k1++) {
                for (int j = 1; j < i; j++) {
                    for (int d : dp[j][k1 - 1].keySet()) {
                        int key = Math.min(d, nums[i - 1] - nums[j - 1]);
                        dp[i][k1].put(key, (dp[i][k1].getOrDefault(key, 0L) + dp[j][k1 - 1].get(d)) % MOD);
                    }
                }
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        long ans = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int key : dp[i][k].keySet()) {
                ans += (1L * key * dp[i][k].get(key)) % MOD;
                ans %= MOD;
            }
        }
        return (int) ans;
    }
}
// @lc code=end
