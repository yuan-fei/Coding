/*
 * @lc app=leetcode id=1986 lang=java
 *
 * [1986] Minimum Number of Work Sessions to Finish the Tasks
 *
 * https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/description/
 *
 * algorithms
 * Medium (29.53%)
 * Likes:    254
 * Dislikes: 16
 * Total Accepted:    6.2K
 * Total Submissions: 21.1K
 * Testcase Example:  '[1,2,3]\n3'
 *
 * There are n tasks assigned to you. The task times are represented as an
 * integer array tasks of length n, where the i^th task takes tasks[i] hours to
 * finish. A work session is when you work for at most sessionTime consecutive
 * hours and then take a break.
 * 
 * You should finish the given tasks in a way that satisfies the following
 * conditions:
 * 
 * 
 * If you start a task in a work session, you must complete it in the same work
 * session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * 
 * 
 * Given tasks and sessionTime, return the minimum number of work sessions
 * needed to finish all the tasks following the conditions above.
 * 
 * The tests are generated such that sessionTime is greater than or equal to
 * the maximum element in tasks[i].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3
 * hours.
 * - Second work session: finish the third task in 3 hours.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3
 * + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int[] dp = new int[1 << n];
        for(int m = 0; m < (1 << n); m++){
            dp[m] = n;
            int total = 0;
            for(int i = 0; i < n; i++){
                if(((m >> i) & 1) != 0){
                    total += tasks[i];    
                }
            }
            if(total <= sessionTime){
                dp[m] = 1;
            }

            int x = m; 
            while(x != 0){
                x = (x-1) & m;
                dp[m] = Math.min(dp[m], dp[x] + dp[m ^ x]);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[(1 << n) - 1];
    }
}
// @lc code=end
