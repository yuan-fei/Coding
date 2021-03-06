/*
 * @lc app=leetcode id=1567 lang=java
 *
 * [1567] Maximum Length of Subarray With Positive Product
 *
 * https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/description/
 *
 * algorithms
 * Medium (29.58%)
 * Likes:    95
 * Dislikes: 2
 * Total Accepted:    4.4K
 * Total Submissions: 14.9K
 * Testcase Example:  '[1,-2,-3,4]'
 *
 * Given an array of integers nums, find the maximum length of a subarray where
 * the product of all its elements is positive.
 * 
 * A subarray of an array is a consecutive sequence of zero or more values
 * taken out of that array.
 * 
 * Return the maximum length of a subarray with positive product.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,-2,-3,4]
 * Output: 4
 * Explanation: The array nums already has a positive product of 24.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,1,-2,-3,-4]
 * Output: 3
 * Explanation: The longest subarray with positive product is [1,-2,-3] which
 * has a product of 6.
 * Notice that we cannot include 0 in the subarray since that'll make the
 * product 0 which is not positive.
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [-1,-2,-3,0,1]
 * Output: 2
 * Explanation: The longest subarray with positive product is [-1,-2] or
 * [-2,-3].
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [-1,2]
 * Output: 1
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: nums = [1,2,3,5,-6,4,0,10]
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int getMaxLen(int[] nums) {
    	int n = nums.length;
    	int firstPos = -1;
    	int firstNeg = -1;
    	int cur = 1;
    	int max = 0;
        for(int i = 0; i < n; i++){
        	if(nums[i] == 0){
        		firstPos = i;
    			firstNeg = -1;
    			cur = 1;
        	}
        	else {
        		if(nums[i] < 0){
	        		cur *= -1;
	        	}
	        	if(cur > 0){
		    		max = Math.max(max, i - firstPos);	
	        	}
	    		else{
	    			if(firstNeg == -1){
		    			firstNeg = i;
		    		}
		    		max = Math.max(max, i - firstNeg);
	    		}
        	}
        	// System.out.println(max);
        }
        return max;
    }
}
// @lc code=end
