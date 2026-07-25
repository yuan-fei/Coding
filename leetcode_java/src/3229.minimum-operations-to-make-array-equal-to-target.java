/*
 * @lc app=leetcode id=3229 lang=java
 *
 * [3229] Minimum Operations to Make Array Equal to Target
 *
 * https://leetcode.com/problems/minimum-operations-to-make-array-equal-to-target/description/
 *
 * algorithms
 * Hard (38.26%)
 * Likes:    294
 * Dislikes: 12
 * Total Accepted:    23.8K
 * Total Submissions: 57.5K
 * Testcase Example:  '[3,5,1,2]\n[4,6,2,4]'
 *
 * You are given two positive integer arrays nums and target, of the same
 * length.
 * 
 * In a single operation, you can select any subarray of nums and increment
 * each element within that subarray by 1 or decrement each element within that
 * subarray by 1.
 * 
 * Return the minimum number of operations required to make nums equal to the
 * array target.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,5,1,2], target = [4,6,2,4]
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * We will perform the following operations to make nums equal to target:
 * - Increment nums[0..3] by 1, nums = [4,6,2,3].
 * - Increment nums[3..3] by 1, nums = [4,6,2,4].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,3,2], target = [2,1,4]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * We will perform the following operations to make nums equal to target:
 * - Increment nums[0..0] by 1, nums = [2,3,2].
 * - Decrement nums[1..1] by 1, nums = [2,2,2].
 * - Decrement nums[1..1] by 1, nums = [2,1,2].
 * - Increment nums[2..2] by 1, nums = [2,1,3].
 * - Increment nums[2..2] by 1, nums = [2,1,4].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length == target.length <= 10^5
 * 1 <= nums[i], target[i] <= 10^8
 * 
 * 
 */

// @lc code=start
class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        int[] diff = new int[n + 2];
        for (int i = 0; i < n; i++) {
            diff[i + 1] = nums[i] - target[i];
        }
        long ans = 0;
        for (int i = 1; i < diff.length; i++) {
            ans += Math.max(0, diff[i] - diff[i - 1]);
        }
        return ans;
    }
}
// @lc code=end
