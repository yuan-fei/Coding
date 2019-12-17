/*
 * @lc app=leetcode id=1288 lang=java
 *
 * [1288] Remove Covered Intervals
 *
 * https://leetcode.com/problems/remove-covered-intervals/description/
 *
 * algorithms
 * Medium (52.63%)
 * Likes:    17
 * Dislikes: 0
 * Total Accepted:    1.6K
 * Total Submissions: 3K
 * Testcase Example:  '[[1,4],[3,6],[2,8]]'
 *
 * Given a list of intervals, remove all intervals that are covered by another
 * interval in the list. Interval [a,b) is covered by interval [c,d) if and
 * only if c <= a and b <= d.
 * 
 * After doing so, return the number of remaining intervals.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is
 * removed.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 1000
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * intervals[i] != intervals[j] for all i != j
 * 
 * 
 */

// @lc code=start
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int maxRight = -1;
        int cnt = 0;
        for(int[] intv: intervals){
    		if(intv[1] > maxRight){
    			cnt++;
    			maxRight = intv[1];
    		}
        }
        return cnt;
    }
}
// @lc code=end
