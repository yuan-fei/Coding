/*
 * @lc app=leetcode id=2962 lang=java
 *
 * [2962] Count Subarrays Where Max Element Appears at Least K Times
 *
 * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/
 *
 * algorithms
 * Medium (44.70%)
 * Likes:    1243
 * Dislikes: 61
 * Total Accepted:    136.5K
 * Total Submissions: 232.7K
 * Testcase Example:  '[1,3,2,3,3]\n2'
 *
 * You are given an integer array nums and a positive integer k.
 * 
 * Return the number of subarrays where the maximum element of nums appears at
 * least k times in that subarray.
 * 
 * A subarray is a contiguous sequence of elements within an array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,3,2,3,3], k = 2
 * Output: 6
 * Explanation: The subarrays that contain the element 3 at least 2 times are:
 * [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,4,2,1], k = 3
 * Output: 0
 * Explanation: No subarray contains the element 4 at least 3 times.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
        int j = 0;
        int cnt = 0;
        long ans = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == max){
                cnt++;
            }
            while(cnt >= k){
                if(nums[j] == max){
                    cnt--;
                }
                j++;
            }
            ans += j;
        }
        return ans;

    }
}
// @lc code=end
