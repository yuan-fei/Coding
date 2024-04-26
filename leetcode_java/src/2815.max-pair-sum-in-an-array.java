/*
 * @lc app=leetcode id=2815 lang=java
 *
 * [2815] Max Pair Sum in an Array
 *
 * https://leetcode.com/problems/max-pair-sum-in-an-array/description/
 *
 * algorithms
 * Easy (57.57%)
 * Likes:    314
 * Dislikes: 115
 * Total Accepted:    42.8K
 * Total Submissions: 74.3K
 * Testcase Example:  '[51,71,17,24,42]'
 *
 * You are given a 0-indexed integer array nums. You have to find the maximum
 * sum of a pair of numbers from nums such that the maximum digit in both
 * numbers are equal.
 * 
 * Return the maximum sum or -1 if no such pair exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [51,71,17,24,42]
 * Output: 88
 * Explanation: 
 * For i = 1 and j = 2, nums[i] and nums[j] have equal maximum digits with a
 * pair sum of 71 + 17 = 88. 
 * For i = 3 and j = 4, nums[i] and nums[j] have equal maximum digits with a
 * pair sum of 24 + 42 = 66.
 * It can be shown that there are no other pairs with equal maximum digits, so
 * the answer is 88.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,3,4]
 * Output: -1
 * Explanation: No pair exists in nums with equal maximum digits.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxSum(int[] nums) {
        int ans = -1;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(getMaxDigit(nums[i]) == getMaxDigit(nums[j])){
                    ans = Math.max(ans, nums[i] + nums[j]);
                }
            }
        }
        return ans;
    }

    int getMaxDigit(int x){
        int ans = 0;
        while(x != 0){
            ans = Math.max(ans, x % 10);
            x /= 10;
        }
        return ans;
    }
}
// @lc code=end
