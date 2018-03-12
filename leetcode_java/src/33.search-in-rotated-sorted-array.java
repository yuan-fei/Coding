/*
 * [33] Search in Rotated Sorted Array
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (32.03%)
 * Total Accepted:    239.3K
 * Total Submissions: 748.7K
 * Testcase Example:  '[]\n5'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 */
class Solution {
    public int search(int[] nums, int target) {
    	if(nums == null || nums.length == 0){
    		return -1;
    	}
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(nums[mid] == target){
        		return mid;
        	}
        	else if(nums[low] < nums[mid]){
        		if(nums[low] <= target && target < nums[mid] ){
        			high = mid;
        		}
        		else{
        			low  = mid;
        		}
        	}
        	else{
        		if(nums[mid] < target && target <= nums[high] ){
        			low = mid;
        		}
        		else{
        			high  = mid;
        		}	
        	}
        }
        if(nums[low] == target){
        	return low;
        }
        else if(nums[high] == target){
        	return high;
        }
        return -1;
    }
}
