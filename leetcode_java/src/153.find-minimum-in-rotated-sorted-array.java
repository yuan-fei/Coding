/*
 * [153] Find Minimum in Rotated Sorted Array
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (40.65%)
 * Total Accepted:    186.4K
 * Total Submissions: 457.6K
 * Testcase Example:  '[1]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 */
class Solution {
    public int findMin(int[] nums) {
    	if(nums ==null || nums.length == 0){
    		return -1;
    	}
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(nums[mid] < nums[high]){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        if(nums[low] <= nums[high]){
        	return nums[low];
        }
        else{
        	return nums[high];
        }
    }
}
