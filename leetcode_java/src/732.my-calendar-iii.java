/*
 * @lc app=leetcode id=732 lang=java
 *
 * [732] My Calendar III
 *
 * https://leetcode.com/problems/my-calendar-iii/description/
 *
 * algorithms
 * Hard (71.63%)
 * Likes:    1745
 * Dislikes: 247
 * Total Accepted:    80.6K
 * Total Submissions: 112.6K
 * Testcase Example:  '["MyCalendarThree","book","book","book","book","book","book"]\n' +
  '[[],[10,20],[50,60],[10,40],[5,15],[5,10],[25,55]]'
 *
 * A k-booking happens when k events have some non-empty intersection (i.e.,
 * there is some time that is common to all k events.)
 * 
 * You are given some events [startTime, endTime), after each given event,
 * return an integer k representing the maximum k-booking between all the
 * previous events.
 * 
 * Implement the MyCalendarThree class:
 * 
 * 
 * MyCalendarThree() Initializes the object.
 * int book(int startTime, int endTime) Returns an integer k representing the
 * largest integer such that there exists a k-booking in the calendar.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * Output
 * [null, 1, 1, 2, 3, 3, 3]
 * 
 * Explanation
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // return 1
 * myCalendarThree.book(50, 60); // return 1
 * myCalendarThree.book(10, 40); // return 2
 * myCalendarThree.book(5, 15); // return 3
 * myCalendarThree.book(5, 10); // return 3
 * myCalendarThree.book(25, 55); // return 3
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= startTime < endTime <= 10^9
 * At most 400 calls will be made to book.
 * 
 * 
 */

// @lc code=start
class MyCalendarThree {
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    
    public MyCalendarThree() {
        
    }
    
    public int book(int start, int end) {
        tm.put(start, tm.getOrDefault(start, 0) + 1);
        tm.put(end, tm.getOrDefault(end, 0) - 1);
        int ans = 0;
        int curCnt = 0;
        for(int delta : tm.values()){
            curCnt += delta;
            ans = Math.max(curCnt, ans);   
        }
        return ans;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(startTime,endTime);
 */
// @lc code=end
