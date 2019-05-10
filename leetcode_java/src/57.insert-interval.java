/*
 * [57] Insert Interval
 *
 * https://leetcode.com/problems/insert-interval/description/
 *
 * algorithms
 * Hard (29.24%)
 * Total Accepted:    170.5K
 * Total Submissions: 550K
 * Testcase Example:  '[[1,3],[6,9]]\n[2,5]'
 *
 * Given a set of non-overlapping intervals, insert a new interval into the
 * intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their
 * start times.
 * 
 * Example 1:
 * 
 * 
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with
 * [3,5],[6,7],[8,10].
 * 
 */
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<>(); 
        for(int i = 0; i < intervals.size(); i++){
        	Interval cur = intervals.get(i);
        	if(newInterval.end < cur.start){
        		ret.add(newInterval);
        		for(int j = i; j < intervals.size(); j++){
        			ret.add(intervals.get(j));
        		}
        		return ret;	
        	}
        	else if(newInterval.start > cur.end){
        		ret.add(cur);
        	}
        	else{
        		newInterval.start = Math.min(newInterval.start, cur.start);
        		newInterval.end = Math.max(newInterval.end, cur.end);
        	}
        }
        ret.add(newInterval);
        return ret;

    }
}
