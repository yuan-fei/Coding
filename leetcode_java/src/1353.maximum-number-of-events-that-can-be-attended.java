/*
 * @lc app=leetcode id=1353 lang=java
 *
 * [1353] Maximum Number of Events That Can Be Attended
 *
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
 *
 * algorithms
 * Medium (22.18%)
 * Likes:    73
 * Dislikes: 16
 * Total Accepted:    2.5K
 * Total Submissions: 10.9K
 * Testcase Example:  '[[1,2],[2,3],[3,4]]'
 *
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event
 * i starts at startDayi and ends at endDayi.
 * 
 * You can attend an event i at any day d where startTimei <= d <= endTimei.
 * Notice that you can only attend one event at any time d.
 * 
 * Return the maximum number of events you can attend.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
 * Output: 4
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: events = [[1,100000]]
 * Output: 1
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= events[i][0] <= events[i][1] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        // System.out.println(Arrays.deepToString(events));
        int cnt = 0;
        int n = events.length;
        int i = 0;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int d = 0; d < 100005; d++){
        	while(i < n && events[i][0] == d){
        		q.offer(events[i][1]);
        		i++;
        	}
        	while(!q.isEmpty() && q.peek() < d){
        		q.poll();
        	}
        	if(!q.isEmpty()){
        		q.poll();
        		cnt++;
        	}
        }
        
        return cnt;
    }
}
// @lc code=end
