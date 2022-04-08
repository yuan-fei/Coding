/*
 * @lc app=leetcode id=2221 lang=java
 *
 * [2221] Find Triangular Sum of an Array
 *
 * https://leetcode.com/problems/find-triangular-sum-of-an-array/description/
 *
 * algorithms
 * Medium (78.35%)
 * Likes:    117
 * Dislikes: 3
 * Total Accepted:    11.4K
 * Total Submissions: 14.5K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * You are given a 0-indexed integer array nums, where nums[i] is a digit
 * between 0 and 9 (inclusive).
 * 
 * The triangular sum of nums is the value of the only element present in nums
 * after the following process terminates:
 * 
 * 
 * Let nums comprise of n elements. If n == 1, end the process. Otherwise,
 * create a new 0-indexed integer array newNums of length n - 1.
 * For each index i, where 0 <= i < n - 1, assign the value of newNums[i] as
 * (nums[i] + nums[i+1]) % 10, where % denotes modulo operator.
 * Replace the array nums with newNums.
 * Repeat the entire process starting from step 1.
 * 
 * 
 * Return the triangular sum of nums.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4,5]
 * Output: 8
 * Explanation:
 * The above diagram depicts the process from which we obtain the triangular
 * sum of the array.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5]
 * Output: 5
 * Explanation:
 * Since there is only one element in nums, the triangular sum is the value of
 * that element itself.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int triangularSum(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < nums.length - i; j++){
                nums[j] = (nums[j] + nums[j + 1]) % 10;
            }
        }   
        return nums[0];
    }
}
// @lc code=end
