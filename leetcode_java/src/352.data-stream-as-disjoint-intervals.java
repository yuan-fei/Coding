/*
 * @lc app=leetcode id=352 lang=java
 *
 * [352] Data Stream as Disjoint Intervals
 *
 * https://leetcode.com/problems/data-stream-as-disjoint-intervals/description/
 *
 * algorithms
 * Hard (44.95%)
 * Likes:    254
 * Dislikes: 70
 * Total Accepted:    29.2K
 * Total Submissions: 64.9K
 * Testcase Example:  '["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]\n[[],[1],[],[3],[],[7],[],[2],[],[6],[]]'
 *
 * Given a data stream input of non-negative integers a1, a2, ..., an, ...,
 * summarize the numbers seen so far as a list of disjoint intervals.
 * 
 * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6,
 * ..., then the summary will be:
 * 
 * 
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * 
 * 
 * 
 * 
 * Follow up:
 * 
 * What if there are lots of merges and the number of disjoint intervals are
 * small compared to the data stream's size?
 * 
 */

// @lc code=start
class SummaryRanges {

	TreeSet<int[]> ts;
    /** Initialize your data structure here. */
    public SummaryRanges() {
        ts = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]));
    }
    
    public void addNum(int val) {
        int[] cur = {val, val};
        int[] ceiling = ts.ceiling(cur);
        if(ceiling != null){
        	if(ceiling[0] == cur[0] + 1 || ceiling[0] == cur[0]){
        		ts.remove(ceiling);
        		cur[1] = ceiling[1];
        	}
        }
        int[] floor = ts.floor(cur);
        if(floor != null){
        	if(floor[1] + 1 == cur[0]){
        		ts.remove(floor);
        		cur[0] = floor[0];
        	}
        	else if(floor[1] >= cur[0]){
        		ts.remove(floor);
        		cur[0] = floor[0];
        		cur[1] = floor[1];
        	}
        }
    	ts.add(cur);
    }
    
    public int[][] getIntervals() {
    	int n = ts.size();
        int[][] ret = new int[n][];
        int i = 0;
        for (int[] itv : ts) {
        	ret[i++] = itv;
        }
        return ret;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
// @lc code=end
