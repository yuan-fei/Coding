/*
 * @lc app=leetcode id=3179 lang=java
 *
 * [3179] Find the N-th Value After K Seconds
 *
 * https://leetcode.com/problems/find-the-n-th-value-after-k-seconds/description/
 *
 * algorithms
 * Medium (54.00%)
 * Likes:    109
 * Dislikes: 20
 * Total Accepted:    41.9K
 * Total Submissions: 78.1K
 * Testcase Example:  '4\n5'
 *
 * You are given two integers n and k.
 * 
 * Initially, you start with an array a of n integers where a[i] = 1 for all 0
 * <= i <= n - 1. After each second, you simultaneously update each element to
 * be the sum of all its preceding elements plus the element itself. For
 * example, after one second, a[0] remains the same, a[1] becomes a[0] + a[1],
 * a[2] becomes a[0] + a[1] + a[2], and so on.
 * 
 * Return the value of a[n - 1] after k seconds.
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, k = 5
 * 
 * Output: 56
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Second
 * State After
 * 
 * 
 * 0
 * [1,1,1,1]
 * 
 * 
 * 1
 * [1,2,3,4]
 * 
 * 
 * 2
 * [1,3,6,10]
 * 
 * 
 * 3
 * [1,4,10,20]
 * 
 * 
 * 4
 * [1,5,15,35]
 * 
 * 
 * 5
 * [1,6,21,56]
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, k = 3
 * 
 * Output: 35
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Second
 * State After
 * 
 * 
 * 0
 * [1,1,1,1,1]
 * 
 * 
 * 1
 * [1,2,3,4,5]
 * 
 * 
 * 2
 * [1,3,6,10,15]
 * 
 * 
 * 3
 * [1,4,10,20,35]
 * 
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n, k <= 1000
 * 
 * 
 */

// @lc code=start

import java.math.BigInteger;

class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int valueAfterKSeconds(int n, int k) {
        // C(k + n - 1, n - 1) = (k + n - 1) * (k + n -2) *...* (k + 1) / (n - 1) *
        // (n-2)*...*1
        // 1/x % m => x^(m-2) % m
        long ans = 1;
        for (int i = k + 1; i <= k + n - 1; i++) {
            ans *= i;
            ans %= MOD;
        }
        long denominator = 1;
        for (int i = 1; i <= n - 1; i++) {
            denominator *= i;
            denominator %= MOD;
        }
        ans *= BigInteger.valueOf(denominator).modInverse(BigInteger.valueOf(MOD)).longValue();
        ans %= MOD;
        return (int) ans;
    }
}
// @lc code=end
