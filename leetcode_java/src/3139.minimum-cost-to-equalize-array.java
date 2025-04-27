/*
 * @lc app=leetcode id=3139 lang=java
 *
 * [3139] Minimum Cost to Equalize Array
 *
 * https://leetcode.com/problems/minimum-cost-to-equalize-array/description/
 *
 * algorithms
 * Hard (17.65%)
 * Likes:    134
 * Dislikes: 24
 * Total Accepted:    5.4K
 * Total Submissions: 30.5K
 * Testcase Example:  '[4,1]\n5\n2'
 *
 * You are given an integer array nums and two integers cost1 and cost2. You
 * are allowed to perform either of the following operations any number of
 * times:
 * 
 * 
 * Choose an index i from nums and increase nums[i] by 1 for a cost of
 * cost1.
 * Choose two different indices i, j, from nums and increase nums[i] and
 * nums[j] by 1 for a cost of cost2.
 * 
 * 
 * Return the minimum cost required to make all elements in the array equal. 
 * 
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,1], cost1 = 5, cost2 = 2
 * 
 * Output: 15
 * 
 * Explanation: 
 * 
 * The following operations can be performed to make the values equal:
 * 
 * 
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,2].
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,3].
 * Increase nums[1] by 1 for a cost of 5. nums becomes [4,4].
 * 
 * 
 * The total cost is 15.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,3,3,5], cost1 = 2, cost2 = 1
 * 
 * Output: 6
 * 
 * Explanation: 
 * 
 * The following operations can be performed to make the values equal:
 * 
 * 
 * Increase nums[0] and nums[1] by 1 for a cost of 1. nums becomes
 * [3,4,3,3,5].
 * Increase nums[0] and nums[2] by 1 for a cost of 1. nums becomes
 * [4,4,4,3,5].
 * Increase nums[0] and nums[3] by 1 for a cost of 1. nums becomes
 * [5,4,4,4,5].
 * Increase nums[1] and nums[2] by 1 for a cost of 1. nums becomes
 * [5,5,5,4,5].
 * Increase nums[3] by 1 for a cost of 2. nums becomes [5,5,5,5,5].
 * 
 * 
 * The total cost is 6.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,5,3], cost1 = 1, cost2 = 3
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * The following operations can be performed to make the values equal:
 * 
 * 
 * Increase nums[0] by 1 for a cost of 1. nums becomes [4,5,3].
 * Increase nums[0] by 1 for a cost of 1. nums becomes [5,5,3].
 * Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,4].
 * Increase nums[2] by 1 for a cost of 1. nums becomes [5,5,5].
 * 
 * 
 * The total cost is 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= cost1 <= 10^6
 * 1 <= cost2 <= 10^6
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {

    long MOD = (long) 1e9 + 7;

    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int n = nums.length;
        int max = Arrays.stream(nums).max().getAsInt();
        long[] diffs = Arrays.stream(nums).asLongStream().map(x -> max - x).sorted().toArray();
        long sum = Arrays.stream(diffs).sum();
        long maxDiff = diffs[n - 1];
        long prev = Long.MAX_VALUE;
        for (long delta = 0;; delta++) {
            long cur = minCostToEqualizeArray(sum + n * delta, maxDiff + delta, cost1, cost2);
            if (cur <= prev) {
                prev = cur;
            } else {
                break;
            }
        }
        return (int) (prev % MOD);
    }

    long minCostToEqualizeArray(long sum, long maxDiff, int cost1, int cost2) {
        if (cost1 * 2 <= cost2) {
            // solo only
            return sum * cost1;
        }
        if (2 * maxDiff >= sum) {
            // pairing first, then solo
            return (sum - maxDiff) * cost2 + (2 * maxDiff - sum) * cost1;
        }
        // pairing only
        return sum / 2 * cost2 + (sum % 2) * cost1;
    }
}
// @lc code=end
