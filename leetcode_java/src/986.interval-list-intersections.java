/*
 * @lc app=leetcode id=986 lang=java
 *
 * [986] Interval List Intersections
 *
 * https://leetcode.com/problems/interval-list-intersections/description/
 *
 * algorithms
 * Medium (63.75%)
 * Total Accepted:    24.7K
 * Total Submissions: 38.8K
 * Testcase Example:  '[[0,2],[5,10],[13,23],[24,25]]\n[[1,5],[8,12],[15,24],[25,26]]'
 *
 * Given two lists of closed intervals, each list of intervals is pairwise
 * disjoint and in sorted order.
 * 
 * Return the intersection of these two interval lists.
 * 
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real
 * numbers x with a <= x <= b.  The intersection of two closed intervals is a
 * set of real numbers that is either empty, or can be represented as a closed
 * interval.  For example, the intersection of [1, 3] and [2, 4] is [2,
 * 3].)
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B =
 * [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects,
 * and not arrays or lists.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 * 
 * 
 * NOTE: input types have been changed on April 15, 2019. Please reset to
 * default code definition to get new method signature.
 * 
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
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A.length == 0||B.length == 0) {
			return new Interval[0];
		}
		
		int i = 0;
		int j = 0;
		List<Interval> res = new ArrayList<>();
		while (i < A.length && j < B.length) {
			Interval intv = intersect(A[i], B[j]);
			if (intv.start <= intv.end) {
				res.add(intv);
			}
			if (A[i].end > B[j].end) {
				j++;
			} else {
				i++;
			}
		}
		return res.toArray(new Interval[0]);
	}

	private Interval intersect(Interval i, Interval j) {
		return new Interval(Math.max(i.start, j.start), Math.min(i.end, j.end));
	}
}
