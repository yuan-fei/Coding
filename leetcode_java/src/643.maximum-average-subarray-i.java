/*
 * @lc app=leetcode id=643 lang=java
 *
 * [643] Maximum Average Subarray I
 *
 * https://leetcode.com/problems/maximum-average-subarray-i/description/
 *
 * algorithms
 * Easy (41.91%)
 * Likes:    868
 * Dislikes: 126
 * Total Accepted:    90.6K
 * Total Submissions: 216K
 * Testcase Example:  '[1,12,-5,-6,50,3]\n4'
 *
 * Given an array consisting of n integers, find the contiguous subarray of
 * given length k that has the maximum average value. And you need to output
 * the maximum average value.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double max = Double.NEGATIVE_INFINITY;
        double sum = 0;
        for(int i = 0; i < nums.length; i++){
        	sum += nums[i];
        	if(i == k - 1){
        		max = Math.max(max, sum / k);
        		// System.out.println(sum + ", " + max);
        	}
        	if(i >= k){
        		sum -= nums[i - k];
        		max = Math.max(max, sum / k);	
        	}

        }
        return max;
    }
}
// @lc code=end
