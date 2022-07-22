/*
 * @lc app=leetcode id=2333 lang=java
 *
 * [2333] Minimum Sum of Squared Difference
 *
 * https://leetcode.com/problems/minimum-sum-of-squared-difference/description/
 *
 * algorithms
 * Medium (22.74%)
 * Likes:    253
 * Dislikes: 20
 * Total Accepted:    5.5K
 * Total Submissions: 24K
 * Testcase Example:  '[1,2,3,4]\n[2,10,20,19]\n0\n0'
 *
 * You are given two positive 0-indexed integer arrays nums1 and nums2, both of
 * length n.
 * 
 * The sum of squared difference of arrays nums1 and nums2 is defined as the
 * sum of (nums1[i] - nums2[i])^2 for each 0 <= i < n.
 * 
 * You are also given two positive integers k1 and k2. You can modify any of
 * the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can
 * modify any of the elements of nums2 by +1 or -1 at most k2 times.
 * 
 * Return the minimum sum of squared difference after modifying array nums1 at
 * most k1 times and modifying array nums2 at most k2 times.
 * 
 * Note: You are allowed to modify the array elements to become negative
 * integers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
 * Output: 579
 * Explanation: The elements in nums1 and nums2 cannot be modified because k1 =
 * 0 and k2 = 0. 
 * The sum of square difference will be: (1 - 2)^2 + (2 - 10)^2 + (3 - 20)^2 +
 * (4 - 19)^2 = 579.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
 * Output: 43
 * Explanation: One way to obtain the minimum sum of square difference is: 
 * - Increase nums1[0] once.
 * - Increase nums2[2] once.
 * The minimum of the sum of square difference will be: 
 * (2 - 5)^2 + (4 - 8)^2 + (10 - 7)^2 + (12 - 9)^2 = 43.
 * Note that, there are other ways to obtain the minimum of the sum of square
 * difference, but there is no way to obtain a sum smaller than 43.
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 10^5
 * 0 <= k1, k2 <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.add(0);
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            if (diff > 0)
                pq.add(diff);
        }
        int k = k1 + k2;
        int prev = 0, cnt = 0;
        while (k > 0 && !pq.isEmpty()) {
            int cur = pq.poll();
            long x = (prev - cur) * cnt;
            if (x <= k) {
                prev = cur;
                cnt++;
                k -= x;
            } else {
                int a = k / cnt; 
                int b = k % cnt; 
                int c = prev - a; 
                for (int i = 0; i < cnt - b; i++) 
                    pq.add(c);
                for (int i = 0; i < b; i++) 
                    pq.add(c - 1);
                pq.add(cur);
                k = 0;
            }
        }
        long ans = 0;
        for (long diff : pq)
            ans += (long)diff * diff;
        return ans;
    }
}
// @lc code=end
