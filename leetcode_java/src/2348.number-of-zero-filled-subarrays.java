/*
 * @lc app=leetcode id=2348 lang=java
 *
 * [2348] Number of Zero-Filled Subarrays
 *
 * https://leetcode.com/problems/number-of-zero-filled-subarrays/description/
 *
 * algorithms
 * Medium (56.24%)
 * Likes:    175
 * Dislikes: 5
 * Total Accepted:    16.4K
 * Total Submissions: 29.2K
 * Testcase Example:  '[1,3,0,0,2,0,0,4]'
 *
 * Given an integer array nums, return the number of subarrays filled with 0.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,0,0,2,0,0,4]
 * Output: 6
 * Explanation: 
 * There are 4 occurrences of [0] as a subarray.
 * There are 2 occurrences of [0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 2 filled with 0.
 * Therefore, we return 6.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,0,0,2,0,0]
 * Output: 9
 * Explanation:
 * There are 5 occurrences of [0] as a subarray.
 * There are 3 occurrences of [0,0] as a subarray.
 * There is 1 occurrence of [0,0,0] as a subarray.
 * There is no occurrence of a subarray with a size more than 3 filled with 0.
 * Therefore, we return 9.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,10,2019]
 * Output: 0
 * Explanation: There is no subarray filled with 0. Therefore, we return 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public long zeroFilledSubarray(int[] nums) {
        int[] cnt = new int[100005];
        int left = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                if(left != -1){
                    cnt[i - left]++;
                }
                left = -1;
            }
            else{
                if(left == -1){
                    left = i;
                }
            }
        }
        if(left != -1){
            cnt[nums.length - left]++;
        }
        long ans = 0;
        for(int i = 0; i < cnt.length; i++){
            ans += 1L * i * (i + 1) / 2 * cnt[i];
        }
        return ans;
    }
}
// @lc code=end
