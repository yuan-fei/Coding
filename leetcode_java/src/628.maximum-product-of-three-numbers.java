/*
 * @lc app=leetcode id=628 lang=java
 *
 * [628] Maximum Product of Three Numbers
 *
 * https://leetcode.com/problems/maximum-product-of-three-numbers/description/
 *
 * algorithms
 * Easy (46.97%)
 * Likes:    1386
 * Dislikes: 415
 * Total Accepted:    136.2K
 * Total Submissions: 290K
 * Testcase Example:  '[1,2,3]'
 *
 * Given an integer array nums, find three numbers whose product is maximum and
 * return the maximum product.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: 6
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: 24
 * Example 3:
 * Input: nums = [-1,-2,-3]
 * Output: -6
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int max = -1000000000;
        for(int i = 0; i < n; i++){
        	for(int j = i + 1; j < n; j++){
        		if(i > 0){
        			max = Math.max(max, nums[i] * nums[j] * nums[0]);	
        		}
        		if(j < n - 1){
        			max = Math.max(max, nums[i] * nums[j] * nums[n - 1]);	
        		}
        	}
        }
        return max;
    }
}
// @lc code=end
