/*
 * @lc app=leetcode id=1272 lang=java
 *
 * [1272] Remove Interval
 *
 * https://leetcode.com/problems/remove-interval/description/
 *
 * algorithms
 * Medium (51.08%)
 * Likes:    22
 * Dislikes: 4
 * Total Accepted:    1.7K
 * Total Submissions: 3.3K
 * Testcase Example:  '[[0,2],[3,4],[5,7]]\n[1,6]'
 *
 * Given a sorted list of disjoint intervals, each interval intervals[i] = [a,
 * b] represents the set of real numbers x such that a <= x < b.
 * 
 * We remove the intersections between any interval in intervals and the
 * interval toBeRemoved.
 * 
 * Return a sorted list of intervals after all such removals.
 * 
 * 
 * Example 1:
 * Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
 * Output: [[0,1],[6,7]]
 * Example 2:
 * Input: intervals = [[0,5]], toBeRemoved = [2,3]
 * Output: [[0,2],[3,5]]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= intervals.length <= 10^4
 * -10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> ans = new ArrayList<>();
        int MIN = -1000000000;
        int MAX = 1000000000;
        int[] lower = {MIN, toBeRemoved[0]};
        int[] upper = {toBeRemoved[1], MAX};
        for (int[] intv : intervals) {
        	int[] new0 = {Math.max(intv[0], lower[0]), Math.min(intv[1], lower[1])};
        	int[] new1 = {Math.max(intv[0], upper[0]), Math.min(intv[1], upper[1])};
        	if(new0[0] < new0[1]){
        		ans.add(Arrays.stream(new0).boxed().collect(Collectors.toList()));
        	}
        	if(new1[0] < new1[1]){
        		ans.add(Arrays.stream(new1).boxed().collect(Collectors.toList()));
        	}
        }
        return ans;
    }
}
// @lc code=end
