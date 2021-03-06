/*
 * [26] Remove Duplicates from Sorted Array
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array
 *
 * algorithms
 * Easy (35.61%)
 * Total Accepted:    289.8K
 * Total Submissions: 813.5K
 * Testcase Example:  '[1,1,2]'
 *
 * 
 * Given a sorted array, remove the duplicates in-place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 * 
 * 
 * Example:
 * 
 * Given nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 * 
 * 
 */
class Solution {
    public int removeDuplicates(int[] nums) {
    	if(nums.length == 0){
    		return 0;
    	}
        int lastUnique = 1;
        for (int i = 1; i < nums.length; i++) {
        	if(nums[i] != nums[i - 1]){
        		nums[lastUnique] = nums[i];
        		lastUnique++;
        	}
        }
        return lastUnique;
    }
}
