/*
 * @lc app=leetcode id=1335 lang=java
 *
 * [1335] Minimum Difficulty of a Job Schedule
 *
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/description/
 *
 * algorithms
 * Hard (47.43%)
 * Likes:    23
 * Dislikes: 2
 * Total Accepted:    1.1K
 * Total Submissions: 2.3K
 * Testcase Example:  '[6,5,4,3,2,1]\n2'
 *
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To
 * work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
 * 
 * You have to finish at least one task every day. The difficulty of a job
 * schedule is the sum of difficulties of each day of the d days. The
 * difficulty of a day is the maximum difficulty of a job done in that day.
 * 
 * Given an array of integers jobDifficulty and an integer d. The difficulty of
 * the i-th job is jobDifficulty[i].
 * 
 * Return the minimum difficulty of a job schedule. If you cannot find a
 * schedule for the jobs return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty =
 * 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you
 * cannot find a schedule for the given jobs.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: jobDifficulty = [7,1,7,1,7,1], d = 3
 * Output: 15
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: jobDifficulty = [11,111,22,222,33,333,44,444], d = 6
 * Output: 843
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 * 
 */

// @lc code=start
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
    	int n = jobDifficulty.length;
        if(d > n){
        	return -1;
        }
        int[][] maxDifficulty = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
        	maxDifficulty[i][i] = jobDifficulty[i - 1];
        	for(int j = i + 1; j <= n; j++){
        		maxDifficulty[i][j] = Math.max(maxDifficulty[i][j - 1], jobDifficulty[j - 1]);
        	}
        }
        int MAX = 1000 * 300;
        int[][] dp = new int[n + 1][d + 1];
        for(int i = 0; i <= n; i++){
        	for(int j = 0; j <= d; j++){
        		dp[i][j] = MAX;
        	}
        }
        dp[0][0] = 0;
        for(int i = 1; i <= n; i++){
        	for(int j = 1; j <= d; j++){
        		for(int k = i - 1; k >= j - 1; k--){
        			dp[i][j] = Math.min(dp[i][j], dp[k][j - 1] + maxDifficulty[k + 1][i]);
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(dp));
        return dp[n][d] >= MAX ? -1 : dp[n][d];
    }
}
// @lc code=end
