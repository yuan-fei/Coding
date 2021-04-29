/*
 * @lc app=leetcode id=665 lang=java
 *
 * [665] Non-decreasing Array
 *
 * https://leetcode.com/problems/non-decreasing-array/description/
 *
 * algorithms
 * Medium (19.92%)
 * Likes:    2620
 * Dislikes: 606
 * Total Accepted:    127.8K
 * Total Submissions: 641.1K
 * Testcase Example:  '[4,2,3]'
 *
 * Given an array nums with n integers, your task is to check if it could
 * become non-decreasing by modifying at most one element.
 * 
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for
 * every i (0-based) such that (0 <= i <= n - 2).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing
 * array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one
 * element.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPossibility(int[] nums) {
    	int n = nums.length;
    	int inverse = 0;
    	int pos = -1;
        for(int i = 1; i < n; i++){
        	if(nums[i] < nums[i - 1]){
        		inverse++;
        		pos = i;
        	}
        }
        if(inverse > 1){
        	return false;
        }
        else if(inverse == 0){
        	return true;
        }
        if(pos < 2 || nums[pos] >= nums[pos - 2]){
        	return true;
        }
        if(pos >= n - 1 || nums[pos + 1] >= nums[pos - 1]){
        	return true;
        }
        return false;
    }
}
// @lc code=end
