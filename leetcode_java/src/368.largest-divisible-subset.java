/*
 * @lc app=leetcode id=368 lang=java
 *
 * [368] Largest Divisible Subset
 *
 * https://leetcode.com/problems/largest-divisible-subset/description/
 *
 * algorithms
 * Medium (35.81%)
 * Likes:    729
 * Dislikes: 38
 * Total Accepted:    54.9K
 * Total Submissions: 153.1K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a set of distinct positive integers, find the largest subset such that
 * every pair (Si, Sj) of elements in this subset satisfies:
 * 
 * Si % Sj = 0 or Sj % Si = 0.
 * 
 * If there are multiple solutions, return any subset is fine.
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        int[] pre = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(pre, -1);
        int max = 0;
        int maxLast = -1;
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < i; j++){
        		if(nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1){
        			dp[i] = dp[j] + 1;
        			pre[i] = j;
        		}
        	}
        	if(max < dp[i]){
        		max = dp[i];
        		maxLast = i;
        	}
        }
        List<Integer> ans = new ArrayList<>();
        while(maxLast != -1){
        	ans.add(nums[maxLast]);
        	maxLast = pre[maxLast];
        }
        return ans;
    }
}
// @lc code=end
