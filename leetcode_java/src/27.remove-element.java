/*
 * [27] Remove Element
 *
 * https://leetcode.com/problems/remove-element
 *
 * algorithms
 * Easy (39.94%)
 * Total Accepted:    241.2K
 * Total Submissions: 603.6K
 * Testcase Example:  '[3,2,2,3]\n3'
 *
 * Given an array and a value, remove all instances of that value in-place and
 * return the new length.
 * 
 * 
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 * 
 * The order of elements can be changed. It doesn't matter what you leave
 * beyond the new length.
 * 
 * 
 * Example:
 * 
 * Given nums = [3,2,2,3], val = 3,
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 2.
 * 
 * 
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        return removeElement1(nums, val);
    }

    private int removeElement1(int[] nums, int val) {
        int start = 0;
        int end = nums.length;
        while(start < end){
        	if(nums[start] != val){
        		start++;
        		continue;
        	}
        	if(nums[end - 1] == val){
        		end--;
        		continue;
        	}
        	nums[start] = nums[end - 1];
        	end--;
        }
        return start;
    }

    private int removeElement2(int[] nums, int val) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
        	if(nums[i] != target){
        		nums[j] = nums[i];
        		j++;
        	}
        }
        return start;
    }
}
