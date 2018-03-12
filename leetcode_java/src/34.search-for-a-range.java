/*
 * [34] Search for a Range
 *
 * https://leetcode.com/problems/search-for-a-range/description/
 *
 * algorithms
 * Medium (31.59%)
 * Total Accepted:    180K
 * Total Submissions: 569.7K
 * Testcase Example:  '[5,7,7,8,8,10]\n8'
 *
 * Given an array of integers sorted in ascending order, find the starting and
 * ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
    	int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
    	if(nums == null || nums.length == 0){
    		return result;
    	}

        int low = 0;
        int high = nums.length - 1;
        
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(nums[mid] >= target){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        if(nums[low] == target){
        	result[0] = low;
        }
        else if(nums[high] == target){
        	result[0] = high;
        }

        if(result[0] != -1){
        	low = result[0];
        	high = nums.length - 1;
        	while(low + 1 < high){
	        	int mid = low + (high - low) / 2;
	        	if(nums[mid] > target){
	        		high = mid;
	        	}
	        	else{
	        		low = mid;
	        	}
        	}
	        if(nums[high] == target){
	        	result[1] = high;
	        }
	        else if(nums[low] == target){
	        	result[1] = low;
	        }
        }
        return result;
    }
}
