/*
 * @lc app=leetcode id=850 lang=java
 *
 * [850] Rectangle Area II
 *
 * https://leetcode.com/problems/rectangle-area-ii/description/
 *
 * algorithms
 * Hard (46.08%)
 * Likes:    225
 * Dislikes: 24
 * Total Accepted:    8.6K
 * Total Submissions: 18.7K
 * Testcase Example:  '[[0,0,2,2],[1,0,2,3],[1,0,3,1]]'
 *
 * We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1,
 * y1, x2, y2] , where (x1, y1) are the coordinates of the bottom-left corner,
 * and (x2, y2) are the coordinates of the top-right corner of the ith
 * rectangle.
 * 
 * Find the total area covered by all rectangles in the plane.  Since the
 * answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
 * Output: 6
 * Explanation: As illustrated in the picture.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[0,0,1000000000,1000000000]]
 * Output: 49
 * Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 =
 * (-7)^2 = 49.
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= rectangles.length <= 200
 * rectanges[i].length = 4
 * 0 <= rectangles[i][j] <= 10^9
 * The total area covered by all rectangles will never exceed 2^63 - 1 and thus
 * will fit in a 64-bit signed integer.
 * 
 */

// @lc code=start
class Solution {
	long mod = 1000000007;
    public int rectangleArea(int[][] rectangles) {
    	int n = rectangles.length;
    	Event[] events = new Event[n * 2];
    	for (int i = 0; i < n; i++) {
    		int[] rect = rectangles[i];
    		events[i] = new Event(rect[0], rect[1], rect[3], i, -1);
    		events[n + i] = new Event(rect[2], rect[1], rect[3], i, 1);
    	}
    	// System.out.println(Arrays.toString(events));
    	Arrays.sort(events, (a,b) -> Long.compare(a.x, b.x) != 0 ? Long.compare(a.x, b.x) : Integer.compare(a.type, b.type));
    	TreeSet<Integer> yEvents = new TreeSet<>((a,b)->Long.compare(events[a].low, events[b].low) != 0 ? Long.compare(events[a].low, events[b].low) : Integer.compare(events[a].id, events[b].id));
    	int left = 0;
    	long curX = events[0].x;
    	long totalArea = 0;
    	for (int i = 0; i < events.length; i++) {
    		if(events[i].x != events[left].x){
    			long curH = getTotalHeight(events, yEvents);
    			long area = (events[i].x - curX) % mod;
    			area = (area * curH) % mod;
    			// System.out.println(curH + ", " + area);
    			totalArea = (totalArea + area) % mod;
    			curX = events[i].x;
    			left = i;
    		}
    		if(events[i].type < 0){
    			yEvents.add(i);
    		}
    		else{
    			yEvents.remove(i);
    		}
    	}
    	return (int)totalArea;
    }

    long getTotalHeight(Event[] events, TreeSet<Integer> ts){
    	long total = 0;
    	long low = 0;
    	long high = 0;
    	for (int i : ts) {
    		if(events[i].low <= high){
    			low = Math.min(low, events[i].low);
    			high = Math.max(high, events[i].high);
    		}
    		else{
    			total = (total + (high - low) % mod) % mod;
    			low = events[i].low;
    			high = events[i].high;
    		}
    	}
    	total = (total + (high - low) % mod) % mod;
    	return total;
    }

    static class Event{
    	long x;
    	long low;
    	long high;
    	int id;
    	int type;

    	Event(long xx, long l, long h, int i, int t){
    		x = xx;
    		low = l;
    		high = h;
    		id = i;
    		type = t;
    	}

    	public String toString(){
    		return "(" + x + ","+ low + "," + high + "," + id + "," + type + ")";
    	}
    }

}
// @lc code=end
