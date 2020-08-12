/*
 * @lc app=leetcode id=1546 lang=java
 *
 * [1546] Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
 *
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/description/
 *
 * algorithms
 * Medium (40.93%)
 * Likes:    222
 * Dislikes: 5
 * Total Accepted:    6.7K
 * Total Submissions: 16.5K
 * Testcase Example:  '[1,1,1,1,1]\n2'
 *
 * Given an array nums and an integer target.
 * 
 * Return the maximum number of non-empty non-overlapping subarrays such that
 * the sum of values in each subarray is equal to target.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,1,1,1], target = 2
 * Output: 2
 * Explanation: There are 2 non-overlapping subarrays [1,1,1,1,1] with sum
 * equals to target(2).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-1,3,5,1,4,2,-9], target = 6
 * Output: 2
 * Explanation: There are 3 subarrays with sum equal to 6.
 * ([5,1], [4,2], [3,5,1,4,2,-9]) but only the first 2 are non-overlapping.
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [-2,6,6,3,5,4,1,2,8], target = 10
 * Output: 3
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [0,0,0], target = 0
 * Output: 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 0 <= target <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, 0);
        int sum = 0;
        

        for(int i = 0; i < nums.length; i++){
        	sum += nums[i];
        	dp[i + 1] = dp[i];
        	if(m.containsKey(sum - target)){
        		int idx = m.get(sum - target);
        		dp[i + 1] = Math.max(dp[i + 1], dp[idx] + 1);
        	}
        	m.put(sum, i + 1);
        }
        return dp[n];
    }
}
// @lc code=end
