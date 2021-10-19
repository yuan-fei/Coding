/*
 * @lc app=leetcode id=2040 lang=java
 *
 * [2040] Kth Smallest Product of Two Sorted Arrays
 *
 * https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (19.82%)
 * Likes:    88
 * Dislikes: 3
 * Total Accepted:    1K
 * Total Submissions: 5.3K
 * Testcase Example:  '[2,5]\n[3,4]\n2'
 *
 * Given two sorted 0-indexed integer arrays nums1 and nums2 as well as an
 * integer k, return the k^th (1-based) smallest product of nums1[i] * nums2[j]
 * where 0 <= i < nums1.length and 0 <= j < nums2.length.
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [2,5], nums2 = [3,4], k = 2
 * Output: 8
 * Explanation: The 2 smallest products are:
 * - nums1[0] * nums2[0] = 2 * 3 = 6
 * - nums1[0] * nums2[1] = 2 * 4 = 8
 * The 2^nd smallest product is 8.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [-4,-2,0,3], nums2 = [2,4], k = 6
 * Output: 0
 * Explanation: The 6 smallest products are:
 * - nums1[0] * nums2[1] = (-4) * 4 = -16
 * - nums1[0] * nums2[0] = (-4) * 2 = -8
 * - nums1[1] * nums2[1] = (-2) * 4 = -8
 * - nums1[1] * nums2[0] = (-2) * 2 = -4
 * - nums1[2] * nums2[0] = 0 * 2 = 0
 * - nums1[2] * nums2[1] = 0 * 4 = 0
 * The 6^th smallest product is 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums1 = [-2,-1,0,1,2], nums2 = [-3,-1,2,4,5], k = 3
 * Output: -6
 * Explanation: The 3 smallest products are:
 * - nums1[0] * nums2[4] = (-2) * 5 = -10
 * - nums1[0] * nums2[3] = (-2) * 4 = -8
 * - nums1[4] * nums2[0] = 2 * (-3) = -6
 * The 3^rd smallest product is -6.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 5 * 10^4
 * -10^5 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 and nums2 are sorted.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long lo = (long) -1e10;
        long hi = (long) 1e10;
        while (lo < hi) {
            final long mid = lo + hi + 1 >> 1;
            if (f(nums1, nums2, mid) < k) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    private static long f(int[] nums1, int[] nums2, long mid) {
        long count = 0;
        for (int num : nums1) {
            int lo = 0;
            int hi = nums2.length;
            if (num < 0) {
                while (lo < hi) {
                    final int m = lo + hi >>> 1;
                    if ((long) nums2[m] * num >= mid) {
                        lo = m + 1;
                    } else {
                        hi = m;
                    }
                }
                count += nums2.length - lo;
            } else {
                while (lo < hi) {
                    final int m = lo + hi >>> 1;
                    if ((long) nums2[m] * num < mid) {
                        lo = m + 1;
                    } else {
                        hi = m;
                    }
                }
                count += lo;
            }
        }
        return count;
    }
}
// @lc code=end
