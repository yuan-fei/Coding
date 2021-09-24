/*
 * @lc app=leetcode id=2013 lang=java
 *
 * [2013] Detect Squares
 *
 * https://leetcode.com/problems/detect-squares/description/
 *
 * algorithms
 * Medium (33.77%)
 * Likes:    87
 * Dislikes: 54
 * Total Accepted:    4.9K
 * Total Submissions: 14.5K
 * Testcase Example:  '["DetectSquares","add","add","add","count","count","add","count"]\n' +
  '[[],[[3,10]],[[11,2]],[[3,2]],[[11,10]],[[14,8]],[[11,2]],[[11,10]]]'
 *
 * You are given a stream of points on the X-Y plane. Design an algorithm
 * that:
 * 
 * 
 * Adds new points from the stream into a data structure. Duplicate points are
 * allowed and should be treated as different points.
 * Given a query point, counts the number of ways to choose three points from
 * the data structure such that the three points and the query point form an
 * axis-aligned square with positive area.
 * 
 * 
 * An axis-aligned square is a square whose edges are all the same length and
 * are either parallel or perpendicular to the x-axis and y-axis.
 * 
 * Implement the DetectSquares class:
 * 
 * 
 * DetectSquares() Initializes the object with an empty data structure.
 * void add(int[] point) Adds a new point point = [x, y] to the data
 * structure.
 * int count(int[] point) Counts the number of ways to form axis-aligned
 * squares with point point = [x, y] as described above.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["DetectSquares", "add", "add", "add", "count", "count", "add", "count"]
 * [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11,
 * 10]]]
 * Output
 * [null, null, null, null, 1, 0, null, 2]
 * 
 * Explanation
 * DetectSquares detectSquares = new DetectSquares();
 * detectSquares.add([3, 10]);
 * detectSquares.add([11, 2]);
 * detectSquares.add([3, 2]);
 * detectSquares.count([11, 10]); // return 1. You can choose:
 * ⁠                              //   - The first, second, and third points
 * detectSquares.count([14, 8]);  // return 0. The query point cannot form a
 * square with any points in the data structure.
 * detectSquares.add([11, 2]);    // Adding duplicate points is allowed.
 * detectSquares.count([11, 10]); // return 2. You can choose:
 * ⁠                              //   - The first, second, and third points
 * ⁠                              //   - The first, third, and fourth
 * points
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * point.length == 2
 * 0 <= x, y <= 1000
 * At most 5000 calls in total will be made to add and count.
 * 
 * 
 */

// @lc code=start
class DetectSquares {
    Map<Integer, Integer>[] cnt = new Map[1001];
    public DetectSquares() {
        for(int i = 0; i < cnt.length; i++){
            cnt[i] = new HashMap<>();
        }
    }
    
    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        cnt[x].put(y, cnt[x].getOrDefault(y, 0) + 1);
    }
    
    public int count(int[] point) {
        // System.out.println(cnt[11]);
        int x = point[0];
        int y = point[1];
        int ret = 0;
        for(int yy : cnt[x].keySet()){
            if(yy == y){
                continue;
            }

            if(x + Math.abs(yy - y) <= 1000){
                ret += cnt[x].get(yy) * cnt[x + Math.abs(yy - y)].getOrDefault(y, 0) * cnt[x + Math.abs(yy - y)].getOrDefault(yy, 0);
            }
            if(x - Math.abs(yy - y) >= 0){
                ret += cnt[x].get(yy) * cnt[x - Math.abs(yy - y)].getOrDefault(y, 0) * cnt[x - Math.abs(yy - y)].getOrDefault(yy, 0);
            }
        }
        return ret;
    }
}

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
// @lc code=end
