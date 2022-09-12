/*
 * @lc app=leetcode id=2406 lang=java
 *
 * [2406] Divide Intervals Into Minimum Number of Groups
 *
 * https://leetcode.com/problems/divide-intervals-into-minimum-number-of-groups/description/
 *
 * algorithms
 * Medium (39.80%)
 * Likes:    234
 * Dislikes: 3
 * Total Accepted:    9.8K
 * Total Submissions: 24.6K
 * Testcase Example:  '[[5,10],[6,8],[1,5],[2,3],[1,10]]'
 *
 * You are given a 2D integer array intervals where intervals[i] = [lefti,
 * righti] represents the inclusive interval [lefti, righti].
 * 
 * You have to divide the intervals into one or more groups such that each
 * interval is in exactly one group, and no two intervals that are in the same
 * group intersect each other.
 * 
 * Return the minimum number of groups you need to make.
 * 
 * Two intervals intersect if there is at least one common number between them.
 * For example, the intervals [1, 5] and [5, 8] intersect.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * Output: 3
 * Explanation: We can divide the intervals into the following groups:
 * - Group 1: [1, 5], [6, 8].
 * - Group 2: [2, 3], [5, 10].
 * - Group 3: [1, 10].
 * It can be proven that it is not possible to divide the intervals into fewer
 * than 3 groups.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
 * Output: 1
 * Explanation: None of the intervals overlap, so we can put all of them in one
 * group.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minGroups(int[][] intervals) {
        int n = intervals.length;
        int[][] events = new int[2 * n][];
        for(int i = 0; i < n; i++){
            events[i] = new int[]{intervals[i][0], 1};
            events[n + i] = new int[]{intervals[i][1] + 1, -1};
        }
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int ans = 0;
        int t = -1;
        int cur = 0;
        for(int[] e : events){
            if(e[0] != t){
                ans = Math.max(cur, ans);
                t = e[0];
            }
            cur += e[1];
            // System.out.println(Arrays.toString(e) + ", " + cur);
        }
        ans = Math.max(cur, ans);
        return ans;
    }
}
// @lc code=end
