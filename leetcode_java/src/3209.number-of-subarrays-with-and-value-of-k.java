/*
 * @lc app=leetcode id=3209 lang=java
 *
 * [3209] Number of Subarrays With AND Value of K
 *
 * https://leetcode.com/problems/number-of-subarrays-with-and-value-of-k/description/
 *
 * algorithms
 * Hard (33.90%)
 * Likes:    167
 * Dislikes: 7
 * Total Accepted:    13.5K
 * Total Submissions: 38.8K
 * Testcase Example:  '[1,1,1]\n1'
 *
 * Given an array of integers nums and an integer k, return the number of
 * subarrays of nums where the bitwise AND of the elements of the subarray
 * equals k.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1], k = 1
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * All subarrays contain only 1's.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,2], k = 1
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * Subarrays having an AND value of 1 are: [1,1,2], [1,1,2], [1,1,2].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2,3], k = 2
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * Subarrays having an AND value of 2 are: [1,2,3], [1,2,3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i], k <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    // k = 1
    // 1 0 1 0 1
    // 1 1 1 1 1

    // target = 1, cur = 1, left = cur index, right = first 0 - 1
    // target = 1, cur = 0, left = NA, right = NA
    // target = 0, cur = 1, left = first 0, right = inf
    // target = 0, cur = 0, left = cur index, right = inf
    static final int MAX_BIT = 30;

    public long countSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[][] firstZeroOccur = new int[n][MAX_BIT];
        Arrays.fill(firstZeroOccur[n - 1], n);
        for (int i = n - 1; i >= 0; i--) {
            for (int b = 0; b < MAX_BIT; b++) {
                if (i < n - 1) {
                    firstZeroOccur[i][b] = firstZeroOccur[i + 1][b];
                }
                if (((nums[i] >> b) & 1) == 0) {
                    firstZeroOccur[i][b] = i;
                }
            }
        }
        int inf = n - 1;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int left = -1;
            int right = n;
            for (int b = 0; b < MAX_BIT; b++) {
                int cur = ((nums[i] >> b) & 1);
                int target = ((k >> b) & 1);
                int curLeft, curRight;
                if (target == 1) {
                    if (cur == 1) {
                        curLeft = i;
                        curRight = firstZeroOccur[i][b] - 1;
                    } else {
                        curLeft = -1;
                        curRight = -2;
                    }
                } else {
                    if (cur == 1) {
                        curLeft = firstZeroOccur[i][b];
                        curRight = inf;
                    } else {
                        curLeft = i;
                        curRight = inf;
                    }
                }
                left = Math.max(left, curLeft);
                right = Math.min(right, curRight);
            }

            ans += Math.max(0, right - left + 1);
        }
        return ans;
    }
}
// @lc code=end
