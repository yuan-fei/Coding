/*
 * [152] Maximum Product Subarray
 *
 * https://leetcode.com/problems/maximum-product-subarray/description/
 *
 * algorithms
 * Medium (26.56%)
 * Total Accepted:    131.2K
 * Total Submissions: 493.1K
 * Testcase Example:  '[-2]'
 *
 * 
 * Find the contiguous subarray within an array (containing at least one
 * number) which has the largest product.
 * 
 * 
 * 
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * 
 */
class Solution {
    public int maxProduct(int[] nums) {
     	int[] maxState = new int[nums.length];
     	int[] minState = new int[nums.length];
     	minState[0] = nums[0];
     	maxState[0] = nums[0];
     	int max = maxState[0];
     	for (int i = 1; i < nums.length; i++) {
     		if(nums[i] > 0){
     			maxState[i] = Math.max(maxState[i - 1] * nums[i], nums[i]);
     			minState[i] = Math.min(minState[i - 1] * nums[i], nums[i]);
     		}
     		else{
     			maxState[i] = Math.max(minState[i - 1] * nums[i], nums[i]);
     			minState[i] = Math.min(maxState[i - 1] * nums[i], nums[i]);
     		}
     		max = Math.max(max, maxState[i]);
     	}
     	return max;
    }
}
