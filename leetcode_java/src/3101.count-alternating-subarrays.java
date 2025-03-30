/*
 * @lc app=leetcode id=3101 lang=java
 *
 * [3101] Count Alternating Subarrays
 *
 * https://leetcode.com/problems/count-alternating-subarrays/description/
 *
 * algorithms
 * Medium (57.17%)
 * Likes:    220
 * Dislikes: 8
 * Total Accepted:    39.2K
 * Total Submissions: 68.5K
 * Testcase Example:  '[0,1,1,1]'
 *
 * You are given a binary array nums.
 * 
 * We call a subarray alternating if no two adjacent elements in the subarray
 * have the same value.
 * 
 * Return the number of alternating subarrays in nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [0,1,1,1]
 * 
 * Output: 5
 * 
 * Explanation:
 * 
 * The following subarrays are alternating: [0], [1], [1], [1], and [0,1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,0,1,0]
 * 
 * Output: 10
 * 
 * Explanation:
 * 
 * Every subarray of the array is alternating. There are 10 possible subarrays
 * that we can choose.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        int l = 0;
        for(int r = 1; r < nums.length; r++){
            if(nums[r] == nums[r - 1]){
                ans += 1L * (r - l) * (r - l + 1) / 2;
                l = r;
            }
        }
        ans += 1L * (nums.length - l) * (nums.length - l + 1) / 2;
        return ans;
    }
}
// @lc code=end
