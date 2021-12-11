/*
 * @lc app=leetcode id=715 lang=java
 *
 * [715] Range Module
 *
 * https://leetcode.com/problems/range-module/description/
 *
 * algorithms
 * Hard (42.64%)
 * Likes:    829
 * Dislikes: 63
 * Total Accepted:    38.4K
 * Total Submissions: 89.9K
 * Testcase Example:  '["RangeModule","addRange","removeRange","queryRange","queryRange","queryRange"]\n' +
  '[[],[10,20],[14,16],[10,14],[13,15],[16,17]]'
 *
 * A Range Module is a module that tracks ranges of numbers. Design a data
 * structure to track the ranges represented as half-open intervals and query
 * about them.
 * 
 * A half-open interval [left, right) denotes all the real numbers x where left
 * <= x < right.
 * 
 * Implement the RangeModule class:
 * 
 * 
 * RangeModule() Initializes the object of the data structure.
 * void addRange(int left, int right) Adds the half-open interval [left,
 * right), tracking every real number in that interval. Adding an interval that
 * partially overlaps with currently tracked numbers should add any numbers in
 * the interval [left, right) that are not already tracked.
 * boolean queryRange(int left, int right) Returns true if every real number in
 * the interval [left, right) is currently being tracked, and false
 * otherwise.
 * void removeRange(int left, int right) Stops tracking every real number
 * currently being tracked in the half-open interval [left, right).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange",
 * "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 * 
 * Explanation
 * RangeModule rangeModule = new RangeModule();
 * rangeModule.addRange(10, 20);
 * rangeModule.removeRange(14, 16);
 * rangeModule.queryRange(10, 14); // return True,(Every number in [10, 14) is
 * being tracked)
 * rangeModule.queryRange(13, 15); // return False,(Numbers like 14, 14.03,
 * 14.17 in [13, 15) are not being tracked)
 * rangeModule.queryRange(16, 17); // return True, (The number 16 in [16, 17)
 * is still being tracked, despite the remove operation)
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= left < right <= 10^9
 * At most 10^4 calls will be made to addRange, queryRange, and removeRange.
 * 
 * 
 */

// @lc code=start
class RangeModule {
    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (start == null && end == null) {
            map.put(left, right);
        } else if (start != null && map.get(start) >= left) {
            map.put(start, Math.max(map.get(end), Math.max(map.get(start), right)));
        } else {
            map.put(left, Math.max(map.get(end), right));
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, false, right, true);
        Set<Integer> set = new HashSet(subMap.keySet());
        map.keySet().removeAll(set);
    }
    
    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);
        if (start == null) return false;
        return map.get(start) >= right;
    }
    
    public void removeRange(int left, int right) {
        if (right <= left) return;
        Integer start = map.floorKey(left);
        Integer end = map.floorKey(right);
        if (end != null && map.get(end) > right) {
            map.put(right, map.get(end));
        }
        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }
        // clean up intermediate intervals
        Map<Integer, Integer> subMap = map.subMap(left, true, right, false);
        Set<Integer> set = new HashSet(subMap.keySet());
        map.keySet().removeAll(set);
        
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
// @lc code=end
