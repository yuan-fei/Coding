/*
 * @lc app=leetcode id=795 lang=java
 *
 * [795] Number of Subarrays with Bounded Maximum
 *
 * https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/description/
 *
 * algorithms
 * Medium (52.76%)
 * Likes:    1933
 * Dislikes: 105
 * Total Accepted:    58.9K
 * Total Submissions: 111.7K
 * Testcase Example:  '[2,1,4,3]\n2\n3'
 *
 * Given an integer array nums and two integers left and right, return the
 * number of contiguous non-empty subarrays such that the value of the maximum
 * array element in that subarray is in the range [left, right].
 * 
 * The test cases are generated so that the answer will fit in a 32-bit
 * integer.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,4,3], left = 2, right = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2,
 * 1], [3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,9,2,5,6], left = 2, right = 8
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= left <= right <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return (int)(solve(nums, right) - solve(nums, left - 1));
    }

    long solve(int[] nums, int max){
        int i = -1;
        long ans = 0;
        for(int j = 0; j < nums.length; j++){
            if(nums[j] > max){
                if(i != -1){
                    ans += 1L * (1 + j - i) * (j - i) / 2;
                }
                i = -1;
            }
            else if(i == -1){
                i = j;
            }
        }
        if(i != -1){
            ans += 1L * (1 + nums.length - i) * (nums.length - i) / 2;
        }
        return ans;
    }
}
// @lc code=end
