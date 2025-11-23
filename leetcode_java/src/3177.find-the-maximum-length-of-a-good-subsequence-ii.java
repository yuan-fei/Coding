/*
 * @lc app=leetcode id=3177 lang=java
 *
 * [3177] Find the Maximum Length of a Good Subsequence II
 *
 * https://leetcode.com/problems/find-the-maximum-length-of-a-good-subsequence-ii/description/
 *
 * algorithms
 * Hard (24.13%)
 * Likes:    126
 * Dislikes: 10
 * Total Accepted:    8.2K
 * Total Submissions: 34.4K
 * Testcase Example:  '[1,2,1,1,3]\n2'
 *
 * You are given an integer array nums and a non-negative integer k. A sequence
 * of integers seq is called good if there are at most k indices i in the range
 * [0, seq.length - 2] such that seq[i] != seq[i + 1].
 * 
 * Return the maximum possible length of a good subsequence of nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,1,1,3], k = 2
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * The maximum length subsequence is [1,2,1,1,3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4,5,1], k = 0
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The maximum length subsequence is [1,2,3,4,5,1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^3
 * 1 <= nums[i] <= 10^9
 * 0 <= k <= min(50, nums.length)
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maximumLength(int[] nums, int k) {
        // dp[i][x] = max(globalMax[x - 1], valueMax[nums[i]][x]) + 1
        // globalMax[x] = max(globalMax[x], dp[i][x])
        // valueMax[x][nums[i]] = max(valueMax[x][nums[i]], dp[i][x])
        int[] globalMaxLengths = new int[k + 1];
        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] maxLengthByValue = new Map[k + 1];
        for (int i = 0; i <= k; i++) {
            maxLengthByValue[i] = new HashMap<>();
        }
        int ans = 0;
        for (int v : nums) {
            for (int x = k; x >= 0; x--) {
                int t = Math.max((x > 0 ? globalMaxLengths[x - 1] : 0), maxLengthByValue[x].getOrDefault(v, 0))
                        + 1;
                globalMaxLengths[x] = Math.max(globalMaxLengths[x], t);
                maxLengthByValue[x].put(v, Math.max(maxLengthByValue[x].getOrDefault(v, 0), t));
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }
}
// @lc code=end
