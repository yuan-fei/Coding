/*
 * @lc app=leetcode id=1723 lang=java
 *
 * [1723] Find Minimum Time to Finish All Jobs
 *
 * https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/description/
 *
 * algorithms
 * Hard (43.65%)
 * Likes:    123
 * Dislikes: 6
 * Total Accepted:    4.9K
 * Total Submissions: 11.3K
 * Testcase Example:  '[3,2,3]\n3'
 *
 * You are given an integer array jobs, where jobs[i] is the amount of time it
 * takes to complete the i^th job.
 * 
 * There are k workers that you can assign jobs to. Each job should be assigned
 * to exactly one worker. The working time of a worker is the sum of the time
 * it takes to complete all jobs assigned to them. Your goal is to devise an
 * optimal assignment such that the maximum working time of any worker is
 * minimized.
 * 
 * Return the minimum possible maximum working time of any assignment. 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumTimeRequired(int[] jobs, int k) {
    	int n = jobs.length;
    	int[][] dp = new int[k + 1][1 << n];
    	int MAX = 12 * 10000000 + 5;
    	
    	for(int mask = 1; mask < 1 << n; mask++){
    		for(int i = 0; i < n; i++){
    			if(((mask >> i) & 1) != 0){
    				dp[1][mask] = dp[1][mask ^ (1 << i)] + jobs[i];
    			}
    		}
    	}
    	dp[1][0] = MAX;

    	for(int i = 2; i <= k; i++){
    		for(int mask = 0; mask < 1 << n; mask++){
    			dp[i][mask] = dp[i - 1][mask];
    			int submask = mask;
    			while(submask != 0){
    				dp[i][mask] = Math.min(dp[i][mask], Math.max(dp[i - 1][submask], dp[1][mask & ~submask]));
    				submask = (submask - 1) & mask;
    			}
        	}
    	}
    	// System.out.println(Arrays.deepToString(dp));
        return dp[k][(1 << n) - 1];
    }
}
// @lc code=end
