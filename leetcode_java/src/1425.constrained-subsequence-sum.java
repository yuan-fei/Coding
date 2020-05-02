/*
 * @lc app=leetcode id=1425 lang=java
 *
 * [1425] Constrained Subsequence Sum
 *
 * https://leetcode.com/problems/constrained-subsequence-sum/description/
 *
 * algorithms
 * Hard (41.27%)
 * Likes:    165
 * Dislikes: 9
 * Total Accepted:    4.5K
 * Total Submissions: 10.8K
 * Testcase Example:  '[10,2,-10,5,20]\n2'
 *
 * Given an integer array nums and an integer k, return the maximum sum of a
 * non-empty subsequence of that array such that for every two consecutive
 * integers in the subsequence, nums[i] and nums[j], where i < j, the condition
 * j - i <= k is satisfied.
 * 
 * A subsequence of an array is obtained by deleting some number of elements
 * (can be zero) from the array, leaving the remaining elements in their
 * original order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest
 * number.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
    	int n = nums.length;
    	int[] dp = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        for(int i = 0; i < n; i++){
        	while(!dq.isEmpty() && i - dq.peekFirst() > k){
        		dq.pollFirst();
        	}
        	int max = 0;
        	if(!dq.isEmpty()){
        		max = Math.max(max, dp[dq.peekFirst()]);
        	}
        	dp[i] = max + nums[i];
        	while(!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]){
        		dq.pollLast();
        	}
        	dq.offer(i);
        }
        // System.out.println(Arrays.toString(dp));
        int max = Integer.MIN_VALUE;
        for(int x : dp){
        	max = Math.max(max, x);
        }
        return max;
    }
}
// @lc code=end
