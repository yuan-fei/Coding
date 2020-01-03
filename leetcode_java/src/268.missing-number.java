/*
 * @lc app=leetcode id=268 lang=java
 *
 * [268] Missing Number
 *
 * https://leetcode.com/problems/missing-number/description/
 *
 * algorithms
 * Easy (49.81%)
 * Likes:    1266
 * Dislikes: 1664
 * Total Accepted:    358.1K
 * Total Submissions: 718.3K
 * Testcase Example:  '[3,0,1]'
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,0,1]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * 
 * 
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement
 * it using only constant extra space complexity?
 */

// @lc code=start
class Solution {
    public int missingNumber(int[] nums) {
    	int n = nums.length;
    	int xor = 0;
        for(int i = 0; i <= n; i++){
        	xor ^= i;
        }
        for(int i = 0; i < n; i++){
        	xor ^= nums[i];
        }
        return xor;
    }
}
// @lc code=end
