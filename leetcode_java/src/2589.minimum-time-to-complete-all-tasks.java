/*
 * @lc app=leetcode id=2589 lang=java
 *
 * [2589] Minimum Time to Complete All Tasks
 *
 * https://leetcode.com/problems/minimum-time-to-complete-all-tasks/description/
 *
 * algorithms
 * Hard (22.80%)
 * Likes:    72
 * Dislikes: 5
 * Total Accepted:    1.7K
 * Total Submissions: 7.5K
 * Testcase Example:  '[[2,3,1],[4,5,1],[1,5,2]]'
 *
 * There is a computer that can run an unlimited number of tasks at the same
 * time. You are given a 2D integer array tasks where tasks[i] = [starti, endi,
 * durationi] indicates that the i^th task should run for a total of durationi
 * seconds (not necessarily continuous) within the inclusive time range
 * [starti, endi].
 * 
 * You may turn on the computer only when it needs to run a task. You can also
 * turn it off if it is idle.
 * 
 * Return the minimum time during which the computer should be turned on to
 * complete all tasks.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tasks = [[2,3,1],[4,5,1],[1,5,2]]
 * Output: 2
 * Explanation: 
 * - The first task can be run in the inclusive time range [2, 2].
 * - The second task can be run in the inclusive time range [5, 5].
 * - The third task can be run in the two inclusive time ranges [2, 2] and [5,
 * 5].
 * The computer will be on for a total of 2 seconds.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tasks = [[1,3,2],[2,5,3],[5,6,2]]
 * Output: 4
 * Explanation: 
 * - The first task can be run in the inclusive time range [2, 3].
 * - The second task can be run in the inclusive time ranges [2, 3] and [5, 5].
 * - The third task can be run in the two inclusive time range [5, 6].
 * The computer will be on for a total of 4 seconds.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= tasks.length <= 2000
 * tasks[i].length == 3
 * 1 <= starti, endi <= 2000
 * 1 <= durationi <= endi - starti + 1 
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 2000;
    public int findMinimumTime(int[][] tasks) {
        int n = tasks.length;
        int[] pSum = new int[MAX + 2];
        int[] picked = new int[MAX + 1];
        Arrays.sort(tasks, Comparator.comparing(e -> e[1]));
        int lastEnd = -1;
        for(int[] t : tasks){
            for(int j = lastEnd + 1; j <= t[1]; j++){
                pSum[j + 1] = pSum[j];
            }
            if(pSum[t[1] + 1] - pSum[t[0]] < t[2]){
                int diff =  t[2] - (pSum[t[1] + 1] - pSum[t[0]]);
                int i = t[1];
                for(; i >= t[0]; i--){
                    if(picked[i] == 0){
                        picked[i] = 1;
                        diff--;
                        if(diff == 0){
                            break;
                        }
                    }
                }
                while(i <= t[1]){
                    pSum[i + 1] = pSum[i] + picked[i];
                    i++;
                }
            }
            // System.out.println(Arrays.toString(picked));
            lastEnd = t[1];
        }
        return pSum[lastEnd + 1];
    }
}
// @lc code=end
