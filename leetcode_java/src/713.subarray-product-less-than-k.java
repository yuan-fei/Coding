/*
 * @lc app=leetcode id=713 lang=java
 *
 * [713] Subarray Product Less Than K
 *
 * https://leetcode.com/problems/subarray-product-less-than-k/description/
 *
 * algorithms
 * Medium (36.64%)
 * Total Accepted:    33.2K
 * Total Submissions: 89.3K
 * Testcase Example:  '[10,5,2,6]\n100'
 *
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays where the product of
 * all the elements in the subarray is less than k.
 * 
 * Example 1:
 * 
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5],
 * [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly
 * less than k.
 * 
 * 
 * 
 * Note:
 * 0 < nums.length .
 * 0 < nums[i] < 1000.
 * 0 .
 * 
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
    	int n = nums.length;
    	int left = 0;
    	int total = 0;
    	int mul = 1;
        for(int i = 0 ; i < n; i++){
        	if(nums[i] < k){
        		mul *= nums[i];
        		while(mul >= k){
        			mul/=nums[left++];
        		}
        		total += i - left + 1;
        	}
        	else{
        		mul = 1;
        		left = i+1;
        	}
        }
        return total;
    }
}
