/*
 * @lc app=leetcode id=1751 lang=java
 *
 * [1751] Maximum Number of Events That Can Be Attended II
 *
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/description/
 *
 * algorithms
 * Hard (47.06%)
 * Likes:    128
 * Dislikes: 3
 * Total Accepted:    3.2K
 * Total Submissions: 6.8K
 * Testcase Example:  '[[1,2,4],[3,4,3],[2,3,1]]\n2'
 *
 * You are given an array of events where events[i] = [startDayi, endDayi,
 * valuei]. The i^th event starts at startDayi and ends at endDayi, and if you
 * attend this event, you will receive a value of valuei. You are also given an
 * integer k which represents the maximum number of events you can attend.
 * 
 * You can only attend one event at a time. If you choose to attend an event,
 * you must attend the entire event. Note that the end day is inclusive: that
 * is, you cannot attend two events where one of them starts and the other ends
 * on the same day.
 * 
 * Return the maximum sum of values that you can receive by attending
 * events.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
 * Output: 7
 * Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value
 * of 4 + 3 = 7.
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
 * Output: 10
 * Explanation: Choose event 2 for a total value of 10.
 * Notice that you cannot attend any other event as they overlap, and that you
 * do not have to attend k events.
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
 * Output: 9
 * Explanation: Although the events do not overlap, you can only attend 3
 * events. Pick the highest valued three.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= events.length
 * 1 <= k * events.length <= 10^6
 * 1 <= startDayi <= endDayi <= 10^9
 * 1 <= valuei <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxValue(int[][] events, int k) {
    	int n = events.length;
        int[][] dp = new int[k + 1][n + 1];
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        for(int i = 1; i <= k; i++){
        	PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(events[a][1], events[b][1]));
        	int max = 0;
        	for(int j = 1; j <= n; j++){
        		int start = events[j - 1][0];
        		int end = events[j - 1][1];
        		while(!q.isEmpty() && events[q.peek()][1] < start){
        			int eid = q.poll();
        			max = Math.max(max, dp[i - 1][eid + 1]);
        		}
        		// System.out.println(i +", " +j +", "+ q);
        		dp[i][j] = max + events[j - 1][2];
        		q.offer(j - 1);
        	}
        }
        int ans = 0;
        for(int v : dp[k]){
        	ans = Math.max(v, ans);
        }
        // System.out.println(Arrays.deepToString(dp));
        return ans;
    }
}
// @lc code=end
