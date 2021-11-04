/*
 * @lc app=leetcode id=2054 lang=java
 *
 * [2054] Two Best Non-Overlapping Events
 *
 * https://leetcode.com/problems/two-best-non-overlapping-events/description/
 *
 * algorithms
 * Medium (30.47%)
 * Likes:    86
 * Dislikes: 5
 * Total Accepted:    2.2K
 * Total Submissions: 7.3K
 * Testcase Example:  '[[1,3,2],[4,5,2],[2,4,3]]'
 *
 * You are given a 0-indexed 2D integer array of events where events[i] =
 * [startTimei, endTimei, valuei]. The i^th event starts at startTimei and ends
 * at endTimei, and if you attend this event, you will receive a value of
 * valuei. You can choose at most two non-overlapping events to attend such
 * that the sum of their values is maximized.
 * 
 * Return this maximum sum.
 * 
 * Note that the start time and end time is inclusive: that is, you cannot
 * attend two events where one of them starts and the other ends at the same
 * time. More specifically, if you attend an event with end time t, the next
 * event must start at or after t + 1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= events.length <= 10^5
 * events[i].length == 3
 * 1 <= startTimei <= endTimei <= 10^9
 * 1 <= valuei <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxTwoEvents(int[][] events) {
        int n = events.length;
        int[][] points = new int[2 * n][];
        for(int i = 0; i < n; i++){
            points[i] = new int[]{events[i][0], events[i][2], 0};
            points[n + i] = new int[]{events[i][1], events[i][2], 1};
        }
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[2], b[2]));
        // System.out.println(Arrays.deepToString(points));
        int max = 0;
        int ret = 0;
        for(int[] e : points){
            if(e[2] == 0){
                ret = Math.max(ret, e[1] + max);
            }
            else{
                max = Math.max(max, e[1]);
            }
        }
        return ret;
    }
}
// @lc code=end
