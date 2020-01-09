/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 *
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (55.91%)
 * Likes:    2735
 * Dislikes: 97
 * Total Accepted:    586.4K
 * Total Submissions: 1M
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * Example:
 * 
 * 
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Note:
 * 
 * 
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * 
 */

// @lc code=start
class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0;
        for(int i = 0; i < nums.length; i++){
        	if(nums[i] != 0){
        		swap(nums, left, i);
        		left++;
        	}
        }
    }

    void swap(int[] nums, int i, int j){
    	int t = nums[i];
    	nums[i] = nums[j];
    	nums[j] = t;
    }
}
// @lc code=end
