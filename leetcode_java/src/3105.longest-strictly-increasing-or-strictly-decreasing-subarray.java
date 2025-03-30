/*
 * @lc app=leetcode id=3105 lang=java
 *
 * [3105] Longest Strictly Increasing or Strictly Decreasing Subarray
 *
 * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/description/
 *
 * algorithms
 * Easy (65.42%)
 * Likes:    626
 * Dislikes: 28
 * Total Accepted:    200.8K
 * Total Submissions: 307.3K
 * Testcase Example:  '[1,4,3,3,2]'
 *
 * You are given an array of integers nums. Return the length of the longest
 * subarray of nums which is either strictly increasing or strictly
 * decreasing.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,4,3,3,2]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and
 * [1,4].
 * 
 * The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4],
 * [3,2], and [4,3].
 * 
 * Hence, we return 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,3,3,3]
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The strictly increasing subarrays of nums are [3], [3], [3], and [3].
 * 
 * The strictly decreasing subarrays of nums are [3], [3], [3], and [3].
 * 
 * Hence, we return 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,2,1]
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * The strictly increasing subarrays of nums are [3], [2], and [1].
 * 
 * The strictly decreasing subarrays of nums are [3], [2], [1], [3,2], [2,1],
 * and [3,2,1].
 * 
 * Hence, we return 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 50
 * 1 <= nums[i] <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        int n = nums.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] > nums[j - 1]) {
                    ans = Math.max(ans, j - i + 1);
                } else {
                    break;
                }
            }
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[j - 1]) {
                    ans = Math.max(ans, j - i + 1);
                } else {
                    break;
                }
            }
        }
        return ans;
    }
}
// @lc code=end
