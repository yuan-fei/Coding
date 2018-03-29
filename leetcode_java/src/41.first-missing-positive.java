/*
 * [41] First Missing Positive
 *
 * https://leetcode.com/problems/first-missing-positive/description/
 *
 * algorithms
 * Hard (25.81%)
 * Total Accepted:    127.6K
 * Total Submissions: 494.2K
 * Testcase Example:  '[1,2,0]'
 *
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * 
 * 
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * 
 * 
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 */
/*
Analysis: the missing number must be in the range [1, nums.length + 1]
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
    	int i = 0;
        while (i < nums.length) {
        	if(nums[i] > 0 && nums[i] < nums.length + 1 && nums[i] != i + 1){
    			if(nums[nums[i] - 1] != nums[i]){
    				swap(nums, nums[i] - 1, i);	
    			}
    			else{
    				i++;
    			}
        	}
        	else{
        		i++;
        	}
        }
        //System.out.println(Arrays.toString(nums));
        for (i = 0; i < nums.length; i++) {
        	if(nums[i] != i + 1){
        		return i + 1;
        	}
        }
        return nums.length + 1;
    }

    public void swap(int[] nums, int i , int j){
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
}
