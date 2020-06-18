/*
 * @lc app=leetcode id=1480 lang=java
 *
 * [1480] Running Sum of 1d Array
 *
 * https://leetcode.com/problems/running-sum-of-1d-array/description/
 *
 * algorithms
 * Easy (95.24%)
 * Likes:    71
 * Dislikes: 17
 * Total Accepted:    15.1K
 * Total Submissions: 15.8K
 * Testcase Example:  '[1,2,3,4]'
 *
 * Given an array nums. We define a running sum of an array as runningSum[i] =
 * sum(nums[0]…nums[i]).
 * 
 * Return the running sum of nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: [1,3,6,10]
 * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,1,1]
 * Output: [1,2,3,4,5]
 * Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1,
 * 1+1+1+1+1].
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,1,2,10,1]
 * Output: [3,4,6,16,17]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * -10^6 <= nums[i] <= 10^6
 * 
 */

// @lc code=start
class Solution {
    public int[] runningSum(int[] nums) {
        int[] ans = new int[nums.length];
        ans[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
        	ans[i] += ans[i - 1] + nums[i];
        }
        return ans;
    }
}
// @lc code=end