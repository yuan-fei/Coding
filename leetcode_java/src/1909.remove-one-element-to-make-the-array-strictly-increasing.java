/*
 * @lc app=leetcode id=1909 lang=java
 *
 * [1909] Remove One Element to Make the Array Strictly Increasing
 *
 * https://leetcode.com/problems/remove-one-element-to-make-the-array-strictly-increasing/description/
 *
 * algorithms
 * Easy (31.14%)
 * Likes:    111
 * Dislikes: 104
 * Total Accepted:    8K
 * Total Submissions: 25.8K
 * Testcase Example:  '[1,2,10,5,7]'
 *
 * Given a 0-indexed integer array nums, return true if it can be made strictly
 * increasing after removing exactly one element, or false otherwise. If the
 * array is already strictly increasing, return true.
 * 
 * The array nums is strictly increasing if nums[i - 1] < nums[i] for each
 * index (1 <= i < nums.length).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,10,5,7]
 * Output: true
 * Explanation: By removing 10 at index 2 from nums, it becomes [1,2,5,7].
 * [1,2,5,7] is strictly increasing, so return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,3,1,2]
 * Output: false
 * Explanation:
 * [3,1,2] is the result of removing the element at index 0.
 * [2,1,2] is the result of removing the element at index 1.
 * [2,3,2] is the result of removing the element at index 2.
 * [2,3,1] is the result of removing the element at index 3.
 * No resulting array is strictly increasing, so return false.
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,1,1]
 * Output: false
 * Explanation: The result of removing any element is [1,1].
 * [1,1] is not strictly increasing, so return false.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [1,2,3]
 * Output: true
 * Explanation: [1,2,3] is already strictly increasing, so return true.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canBeIncreasing(int[] nums) {
    	boolean seen = false;
        for(int i = 1; i < nums.length; i++){
        	if(nums[i] <= nums[i - 1]){
        		if(seen){
        			return false;
        		}
        		else{
        			if (i>1 && nums[i] <= nums[i-2]){
						nums[i] = nums[i-1];
        			}
        			seen = true;
        		}
        	}
        }
        return true;
    }
}
// @lc code=end
