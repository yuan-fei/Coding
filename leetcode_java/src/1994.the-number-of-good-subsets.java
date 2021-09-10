/*
 * @lc app=leetcode id=1994 lang=java
 *
 * [1994] The Number of Good Subsets
 *
 * https://leetcode.com/problems/the-number-of-good-subsets/description/
 *
 * algorithms
 * Hard (24.15%)
 * Likes:    68
 * Dislikes: 1
 * Total Accepted:    942
 * Total Submissions: 3.9K
 * Testcase Example:  '[1,2,3,4]'
 *
 * You are given an integer array nums. We call a subset of nums good if its
 * product can be represented as a product of one or more distinct prime
 * numbers.
 * 
 * 
 * For example, if nums = [1, 2, 3, 4]:
 * 
 * 
 * [2, 3], [1, 2, 3], and [1, 3] are good subsets with products 6 = 2*3, 6 =
 * 2*3, and 3 = 3 respectively.
 * [1, 4] and [4] are not good subsets with products 4 = 2*2 and 4 = 2*2
 * respectively.
 * 
 * 
 * 
 * 
 * Return the number of different good subsets in nums modulo 10^9 + 7.
 * 
 * A subset of nums is any array that can be obtained by deleting some
 * (possibly none or all) elements from nums. Two subsets are different if and
 * only if the chosen indices to delete are different.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: 6
 * Explanation: The good subsets are:
 * - [1,2]: product is 2, which is the product of distinct prime 2.
 * - [1,2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [1,3]: product is 3, which is the product of distinct prime 3.
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,2,3,15]
 * Output: 5
 * Explanation: The good subsets are:
 * - [2]: product is 2, which is the product of distinct prime 2.
 * - [2,3]: product is 6, which is the product of distinct primes 2 and 3.
 * - [2,15]: product is 30, which is the product of distinct primes 2, 3, and
 * 5.
 * - [3]: product is 3, which is the product of distinct prime 3.
 * - [15]: product is 15, which is the product of distinct primes 3 and 5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 30
 * 
 * 
 */

// @lc code=start
class Solution {
    static int mod = (int) 1e9 + 7;
    static int[] map = new int[31];
    static {
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        for (int i = 2; i <= 30; ++i) {
            if (0 == i % 4 || 0 == i % 9 || 25 == i) continue;
            int mask = 0;
            for (int j = 0; j < 10; ++j) {
                if (0 == i % prime[j]) mask |= 1 << j;
            }
            map[i] = mask;
        }
    }
    public int numberOfGoodSubsets(int[] nums) {
        int n = nums.length, one = 0;
        int[] dp = new int[1024], cnt = new int[31];
        dp[0] = 1;
        for (int i : nums) {
            if (1 == i) one++;
            else if (0 != map[i]) cnt[i]++;
        }
        for (int i = 0; i < 31; ++i) {
            if (0 == cnt[i]) continue;
            for (int j = 0; j < 1024; ++j) {
                if (0 != (j & map[i])) continue;
                dp[j | map[i]] = (int) ((dp[j | map[i]] + dp[j] * (long) cnt[i]) % mod);
            }
        }
        long res = 0;
        for (int i : dp) res = (res + i) % mod;
        res--;
        if (0 != one) res = res * pow(one) % mod;
        return (int) res;
    }
    private long pow(int n) {
        long res = 1, m = 2;
        while (0 != n) {
            if (1 == (n & 1)) res = (res * m) % mod;
            m = m * m % mod;
            n >>= 1;
        }
        return res;
    }
}
// @lc code=end
