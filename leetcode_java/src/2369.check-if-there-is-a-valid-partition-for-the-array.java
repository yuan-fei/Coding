/*
 * @lc app=leetcode id=2369 lang=java
 *
 * [2369] Check if There is a Valid Partition For The Array
 *
 * https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/description/
 *
 * algorithms
 * Medium (33.28%)
 * Likes:    151
 * Dislikes: 73
 * Total Accepted:    7.3K
 * Total Submissions: 21.9K
 * Testcase Example:  '[4,4,4,5,6]'
 *
 * You are given a 0-indexed integer array nums. You have to partition the
 * array into one or more contiguous subarrays.
 * 
 * We call a partition of the array valid if each of the obtained subarrays
 * satisfies one of the following conditions:
 * 
 * 
 * The subarray consists of exactly 2 equal elements. For example, the subarray
 * [2,2] is good.
 * The subarray consists of exactly 3 equal elements. For example, the subarray
 * [4,4,4] is good.
 * The subarray consists of exactly 3 consecutive increasing elements, that is,
 * the difference between adjacent elements is 1. For example, the subarray
 * [3,4,5] is good, but the subarray [1,3,5] is not.
 * 
 * 
 * Return true if the array has at least one valid partition. Otherwise, return
 * false.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,4,4,5,6]
 * Output: true
 * Explanation: The array can be partitioned into the subarrays [4,4] and
 * [4,5,6].
 * This partition is valid, so we return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,1,1,2]
 * Output: false
 * Explanation: There is no valid partition for this array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validPartition(int[] nums) {
        int n = nums.length;
        boolean[] valid = new boolean[n + 1];
        valid[0] = true;
        for(int i = 2; i <= n; i++){
            if(nums[i - 1] == nums[i - 2] && valid[i - 2]){
                valid[i] = true;
            }
            else if(i >= 3 && nums[i - 1] == nums[i - 2] && nums[i - 2] == nums[i - 3] && valid[i - 3]){
                valid[i] = true;   
            }
            else if(i >= 3 && nums[i - 1] == nums[i - 2] + 1 && nums[i - 2] == nums[i - 3] + 1 && valid[i - 3]){
                valid[i] = true;   
            }
        }
        return valid[n];
    }
}
// @lc code=end
