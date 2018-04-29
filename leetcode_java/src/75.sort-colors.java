/*
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (38.76%)
 * Total Accepted:    218K
 * Total Submissions: 560.2K
 * Testcase Example:  '[0]'
 *
 * 
 * Given an array with n objects colored red, white or blue, sort them so that
 * objects of the same color are adjacent, with the colors in the order red,
 * white and blue.
 * 
 * 
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * 
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * 
 * click to show follow up.
 * 
 * 
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with an one-pass algorithm using only constant space?
 * 
 * 
 */
class Solution {
    public void sortColors(int[] nums) {
    	int p0 = -1;
    	int p1 = -1;

        for (int p2 = 0; p2 < nums.length; p2++) {
        	if(nums[p2] == 0){
        		swap(nums, p0+1, p2);
        		p0++;
        	}
        	p1 = Math.max(p0, p1);
        	if(nums[p2] == 1){
        		swap(nums, p1+1, p2);
        		p1++;
        	}
        }
    }

    private void swap(int[] a, int i, int j){
		int tmp = a[i];
    	a[i] = a[j];
    	a[j] = tmp;
    }
}
