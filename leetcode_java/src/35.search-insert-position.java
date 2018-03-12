/*
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (40.02%)
 * Total Accepted:    239.2K
 * Total Submissions: 597.5K
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Example 1:
 * 
 * Input: [1,3,5,6], 5
 * Output: 2
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1,3,5,6], 2
 * Output: 1
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: [1,3,5,6], 7
 * Output: 4
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [1,3,5,6], 0
 * Output: 0
 * 
 * 
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
    	if(nums == null){
    		return -1;
    	}
    	if(nums.length == 0){
    		return 0;
    	}
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(nums[mid] <= target){
        		low = mid;
        	}
        	else{
        		high = mid;
        	}
        }
        
        if(nums[low] >= target){
        	return low;
        }
        else if(nums[high] >= target){
        	return high;
        }
        else {
        	return high + 1;
        }
    }
}
