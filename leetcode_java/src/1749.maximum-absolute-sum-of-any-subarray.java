/*
 * @lc app=leetcode id=1749 lang=java
 *
 * [1749] Maximum Absolute Sum of Any Subarray
 *
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/
 *
 * algorithms
 * Medium (49.45%)
 * Likes:    156
 * Dislikes: 2
 * Total Accepted:    5.7K
 * Total Submissions: 11.4K
 * Testcase Example:  '[1,-3,2,3,-4]'
 *
 * You are given an integer array nums. The absolute sum of a subarray [numsl,
 * numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 +
 * numsr).
 * 
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 * 
 * Note that abs(x) is defined as follows:
 * 
 * 
 * If x is a negative integer, then abs(x) = -x.
 * If x is a non-negative integer, then abs(x) = x.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,-3,2,3,-4]
 * Output: 5
 * Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,-5,1,-4,3,-2]
 * Output: 8
 * Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8)
 * = 8.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxAbsoluteSum(int[] nums) {
    	int n = nums.length;
        int[] pSum = new int[n + 1];
        for(int i = 1; i <= n; i++){
        	pSum[i] = pSum[i - 1] + nums[i - 1];
        }
        int minPSum = Integer.MAX_VALUE;
        int maxPSum = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i <= n; i++){
        	minPSum = Math.min(minPSum, pSum[i]);
        	maxPSum = Math.max(maxPSum, pSum[i]);
        	max = Math.max(pSum[i] - minPSum, max);
        	min = Math.min(pSum[i] - maxPSum, min);
        }
        return Math.max(Math.abs(max), Math.abs(min));
    }
}
// @lc code=end
