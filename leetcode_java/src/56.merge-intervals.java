/*
 * [56] Merge Intervals
 *
 * https://leetcode.com/problems/merge-intervals/description/
 *
 * algorithms
 * Medium (31.66%)
 * Total Accepted:    194.2K
 * Total Submissions: 607.1K
 * Testcase Example:  '[[1,3],[2,6],[8,10],[15,18]]'
 *
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
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

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) {
            return intervals;
        }
        intervals.sort(Comparator.comparing(i -> i.start));
        List<Interval> res = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (cur.start > last.end) {
                res.add(last);
                last = cur;
            } else {
                last.end = Math.max(last.end, cur.end);
            }
        }
        res.add(last);
        return res;
    }

	class Point{
		int val;
		boolean isStart;
		Point(int v, boolean s){
			val = v;
			isStart = s;
		}
	}
    public List<Interval> merge2(List<Interval> intervals) {
    	List<Interval> result = new ArrayList<Interval>();
    	List<Point> ps = new ArrayList<Point>();
        for (Interval itvl: intervals) {
        	ps.add(new Point(itvl.start, true));
        	ps.add(new Point(itvl.end, false));
        }
        Collections.sort(ps, new Comparator<Point>(){
        	public int compare(Point p1, Point p2){
        		int v = p1.val - p2.val;
        		if(v == 0){
        			int s1 = p1.isStart?0:1;
        			int s2 = p2.isStart?0:1;
        			v = s1 - s2;
        		}
        		return v;
        	}
        });
        Stack<Integer> s = new Stack<Integer>();
        for (Point p: ps) {
        	if (p.isStart) {
        		s.push(p.val);
        	}
        	else{
        		int start = s.pop();
        		if(s.isEmpty()){
        			result.add(new Interval(start, p.val));
        		}
        	}
        }
        return result;
    }
}
