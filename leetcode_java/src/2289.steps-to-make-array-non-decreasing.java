/*
 * @lc app=leetcode id=2289 lang=java
 *
 * [2289] Steps to Make Array Non-decreasing
 *
 * https://leetcode.com/problems/steps-to-make-array-non-decreasing/description/
 *
 * algorithms
 * Medium (17.40%)
 * Likes:    402
 * Dislikes: 76
 * Total Accepted:    5.2K
 * Total Submissions: 29.9K
 * Testcase Example:  '[5,3,4,4,7,3,6,11,8,5,11]'
 *
 * You are given a 0-indexed integer array nums. In one step, remove all
 * elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 * 
 * Return the number of steps performed until nums becomes a non-decreasing
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
 * Output: 3
 * Explanation: The following are the steps performed:
 * - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
 * - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
 * - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
 * [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,5,7,7,13]
 * Output: 0
 * Explanation: nums is already a non-decreasing array. Therefore, we return
 * 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
     public int totalSteps(int[] A) {
        int n = A.length, res = 0, j = -1;
        int dp[] = new int[n], stack[] = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            while (j >= 0 && A[i] > A[stack[j]]) {
                dp[i] = Math.max(++dp[i], dp[stack[j--]]);
                res = Math.max(res, dp[i]);
            }
            stack[++j] = i;
        }
        return res;
    }
}
// @lc code=end
