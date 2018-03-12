/*
 * [162] Find Peak Element
 *
 * https://leetcode.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (38.61%)
 * Total Accepted:    145.4K
 * Total Submissions: 374.9K
 * Testcase Example:  '[1,2,3,1]'
 *
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 * 
 * click to show spoilers.
 * 
 * Note:
 * Your solution should be in logarithmic complexity.
 * 
 * 
 * Credits:Special thanks to @ts for adding this problem and creating all test
 * cases.
 */
class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while(low + 1 < high){
        	int mid = low + (high - low) / 2;
        	if(nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]){
        		return mid;
        	}
        	else if(nums[mid] < nums[mid - 1]){
        		high = mid;
        	}
        	else{
        		low = mid;
        	}
        }
        if(nums[low] > nums[high]){
        	return low;
        }
        else{
        	return high;
        }
    }
}
