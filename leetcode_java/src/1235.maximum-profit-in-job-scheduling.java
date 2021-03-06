/*
 * @lc app=leetcode id=1235 lang=java
 *
 * [1235] Maximum Profit in Job Scheduling
 *
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
 *
 * algorithms
 * Hard (41.00%)
 * Total Accepted:    2.1K
 * Total Submissions: 5.2K
 * Testcase Example:  '[1,2,3,3]\n[3,4,5,6]\n[50,10,40,70]'
 *
 * We have n jobs, where every job is scheduled to be done from startTime[i] to
 * endTime[i], obtaining a profit of profit[i].
 * 
 * You're given the startTime , endTime and profit arrays, you need to output
 * the maximum profit you can take such that there are no 2 jobs in the subset
 * with overlapping time range.
 * 
 * If you choose a job that ends at time X you will be able to start another
 * job that starts at time X.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job. 
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * 
 * 
 * Example 2:
 * 
 * ⁠
 * 
 * 
 * 
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit =
 * [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job. 
 * Profit obtained 150 = 20 + 70 + 60.
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 * 
 * 
 */
class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
		int n = startTime.length;
		Event[] events = new Event[2 * n];
		for (int i = 0; i < n; i++) {
			events[i] = new Event(i, startTime[i], 1);
			events[n + i] = new Event(i, endTime[i], 0);
		}
		Arrays.sort(events);
		int[] start = new int[n];
		int[] end = new int[n];
		for (int i = 0; i < 2 * n; i++) {
			if (events[i].type == 0) {
				end[events[i].id] = i;
			} else {
				start[events[i].id] = i;
			}
		}
		int[] dp = new int[2 * n + 1];
		for (int i = 1; i <= 2 * n; i++) {
			if (events[i - 1].type == 0) {
				int eid = events[i - 1].id;
				dp[i] = Math.max(dp[i - 1], dp[start[eid]] + profit[eid]);
			} else {
				dp[i] = Math.max(dp[i - 1], dp[i]);
			}
		}
		return dp[2 * n];
	}

	static class Event implements Comparable<Event> {
		int id;
		int time;
		int type;

		Event(int i, int tm, int tp) {
			id = i;
			time = tm;
			type = tp;
		}

		public int compareTo(Event e) {
			int r = Integer.compare(time, e.time);
			if (r == 0) {
				r = Integer.compare(type, e.type);
			}
			return r;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return String.format("%d-%d-%d", id, time, type);
		}
	}
}
