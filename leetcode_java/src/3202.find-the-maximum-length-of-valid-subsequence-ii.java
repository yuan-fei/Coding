/*
 * @lc app=leetcode id=3202 lang=java
 *
 * [3202] Find the Maximum Length of Valid Subsequence II
 *
 * https://leetcode.com/problems/find-the-maximum-length-of-valid-subsequence-ii/description/
 *
 * algorithms
 * Medium (39.66%)
 * Likes:    635
 * Dislikes: 54
 * Total Accepted:    100.2K
 * Total Submissions: 175K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * You are given an integer array nums and a positive integer k.
 * A subsequence sub of nums with length x is called valid if it
 * satisfies:
 * 
 * 
 * (sub[0] + sub[1]) % k == (sub[1] + sub[2]) % k == ... == (sub[x - 2] + sub[x
 * - 1]) % k.
 * 
 * Return the length of the longest valid subsequence of nums.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4,5], k = 2
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * The longest valid subsequence is [1, 2, 3, 4, 5].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,4,2,3,1,4], k = 3
 * 
 * Output: 4
 * 
 * Explanation:
 * 
 * The longest valid subsequence is [1, 4, 1, 4].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^3
 * 1 <= nums[i] <= 10^7
 * 1 <= k <= 10^3
 * 
 * 
 */

// @lc code=start
class Solution {

    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        for (int r = 0; r < k; r++) {
            int[] dp = new int[k];
            for (int v : nums) {
                dp[v % k] = dp[Math.floorMod(r - v, k)] + 1;
                ans = Math.max(ans, dp[v % k]);
            }
        }
        return ans;
    }
}
// @lc code=end
