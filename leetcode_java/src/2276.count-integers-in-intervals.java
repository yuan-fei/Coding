/*
 * @lc app=leetcode id=2276 lang=java
 *
 * [2276] Count Integers in Intervals
 *
 * https://leetcode.com/problems/count-integers-in-intervals/description/
 *
 * algorithms
 * Hard (28.56%)
 * Likes:    192
 * Dislikes: 35
 * Total Accepted:    5.9K
 * Total Submissions: 20.6K
 * Testcase Example:  '["CountIntervals","add","add","count","add","count"]\n' +
  '[[],[2,3],[7,10],[],[5,8],[]]'
 *
 * Given an empty set of intervals, implement a data structure that can:
 * 
 * 
 * Add an interval to the set of intervals.
 * Count the number of integers that are present in at least one interval.
 * 
 * 
 * Implement the CountIntervals class:
 * 
 * 
 * CountIntervals() Initializes the object with an empty set of intervals.
 * void add(int left, int right) Adds the interval [left, right] to the set of
 * intervals.
 * int count() Returns the number of integers that are present in at least one
 * interval.
 * 
 * 
 * Note that an interval [left, right] denotes all the integers x where left <=
 * x <= right.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["CountIntervals", "add", "add", "count", "add", "count"]
 * [[], [2, 3], [7, 10], [], [5, 8], []]
 * Output
 * [null, null, null, 6, null, 8]
 * 
 * Explanation
 * CountIntervals countIntervals = new CountIntervals(); // initialize the
 * object with an empty set of intervals. 
 * countIntervals.add(2, 3);  // add [2, 3] to the set of intervals.
 * countIntervals.add(7, 10); // add [7, 10] to the set of intervals.
 * countIntervals.count();    // return 6
 * ⁠                          // the integers 2 and 3 are present in the
 * interval [2, 3].
 * ⁠                          // the integers 7, 8, 9, and 10 are present in
 * the interval [7, 10].
 * countIntervals.add(5, 8);  // add [5, 8] to the set of intervals.
 * countIntervals.count();    // return 8
 * ⁠                          // the integers 2 and 3 are present in the
 * interval [2, 3].
 * ⁠                          // the integers 5 and 6 are present in the
 * interval [5, 8].
 * ⁠                          // the integers 7 and 8 are present in the
 * intervals [5, 8] and [7, 10].
 * ⁠                          // the integers 9 and 10 are present in the
 * interval [7, 10].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= left <= right <= 10^9
 * At most 10^5 calls in total will be made to add and count.
 * At least one call will be made to count.
 * 
 * 
 */

// @lc code=start
class CountIntervals {
    TreeSet<int[]> ts = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]));
    int cnt = 0;
    public CountIntervals() {
        
    }
    
    public void add(int left, int right) {
        int[] floor = ts.floor(new int[]{left, 0});
        if(floor != null){
            if(floor[1] >= left){
                ts.remove(floor);
                cnt -= floor[1] - floor[0] + 1;
                left = floor[0];
                right = Math.max(right, floor[1]);
            }
        }
        int[] ceiling = ts.ceiling(new int[]{left, 0});
        while(ceiling != null && ceiling[0] <= right){
            ts.remove(ceiling);
            cnt -= ceiling[1] - ceiling[0] + 1;
            right = Math.max(right, ceiling[1]);
            ceiling = ts.ceiling(new int[]{left, 0});
        }
        ts.add(new int[]{left, right});
        cnt += right - left + 1;
    }
    
    public int count() {
        return cnt;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * CountIntervals obj = new CountIntervals();
 * obj.add(left,right);
 * int param_2 = obj.count();
 */
// @lc code=end
