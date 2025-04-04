/*
 * @lc app=leetcode id=3116 lang=java
 *
 * [3116] Kth Smallest Amount With Single Denomination Combination
 *
 * https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/description/
 *
 * algorithms
 * Hard (17.46%)
 * Likes:    237
 * Dislikes: 19
 * Total Accepted:    9.5K
 * Total Submissions: 54K
 * Testcase Example:  '[3,6,9]\n3'
 *
 * You are given an integer array coins representing coins of different
 * denominations and an integer k.
 * 
 * You have an infinite number of coins of each denomination. However, you are
 * not allowed to combine coins of different denominations.
 * 
 * Return the k^th smallest amount that can be made using these coins.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: coins = [3,6,9], k = 3
 * 
 * Output:  9
 * 
 * Explanation: The given coins can make the following amounts:
 * Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
 * Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
 * Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
 * All of the coins combined produce: 3, 6, 9, 12, 15, etc.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: coins = [5,2], k = 7
 * 
 * Output: 12 
 * 
 * Explanation: The given coins can make the following amounts:
 * Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
 * Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
 * All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12, 14, 15, etc.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= coins.length <= 15
 * 1 <= coins[i] <= 25
 * 1 <= k <= 2 * 10^9
 * coins contains pairwise distinct integers.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long low = 0;
        long high = 25 * 2 * (long) 1e9;
        while (low + 1 < high) {
            long mid = low + (high - low) / 2;
            long cnt = countLessEqualThan(coins, mid);
            if (cnt < k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (countLessEqualThan(coins, low) == k) {
            return low;
        }
        return high;
    }

    long countLessEqualThan(int[] coins, long x) {
        int n = coins.length;
        long cnt = 0;
        for (int i = 1; i < (1 << n); i++) {
            long gcm = 1;
            int sign = -1;
            for (int b = 0; b < n; b++) {
                if (((i >> b) & 1) == 1) {
                    gcm = gcm * coins[b] / gcd(gcm, coins[b]);
                    sign *= -1;
                }
            }
            cnt += sign * (x / gcm);
        }
        // System.out.println(Arrays.asList(x, cnt));
        return cnt;
    }

    long gcd(long x, long y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x % y);
        }
    }
}
// @lc code=end
