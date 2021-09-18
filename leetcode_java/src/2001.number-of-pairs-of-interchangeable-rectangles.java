/*
 * @lc app=leetcode id=2001 lang=java
 *
 * [2001] Number of Pairs of Interchangeable Rectangles
 *
 * https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/description/
 *
 * algorithms
 * Medium (40.22%)
 * Likes:    124
 * Dislikes: 12
 * Total Accepted:    9.5K
 * Total Submissions: 23.7K
 * Testcase Example:  '[[4,8],[3,6],[10,20],[15,30]]'
 *
 * You are given n rectangles represented by a 0-indexed 2D integer array
 * rectangles, where rectangles[i] = [widthi, heighti] denotes the width and
 * height of the i^th rectangle.
 * 
 * Two rectangles i and j (i < j) are considered interchangeable if they have
 * the same width-to-height ratio. More formally, two rectangles are
 * interchangeable if widthi/heighti == widthj/heightj (using decimal division,
 * not integer division).
 * 
 * Return the number of pairs of interchangeable rectangles in rectangles.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * Output: 6
 * Explanation: The following are the interchangeable pairs of rectangles by
 * index (0-indexed):
 * - Rectangle 0 with rectangle 1: 4/8 == 3/6.
 * - Rectangle 0 with rectangle 2: 4/8 == 10/20.
 * - Rectangle 0 with rectangle 3: 4/8 == 15/30.
 * - Rectangle 1 with rectangle 2: 3/6 == 10/20.
 * - Rectangle 1 with rectangle 3: 3/6 == 15/30.
 * - Rectangle 2 with rectangle 3: 10/20 == 15/30.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: rectangles = [[4,5],[7,8]]
 * Output: 0
 * Explanation: There are no interchangeable pairs of rectangles.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == rectangles.length
 * 1 <= n <= 10^5
 * rectangles[i].length == 2
 * 1 <= widthi, heighti <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Integer> m = new HashMap<>();
        long ret = 0;
        for (int[] r : rectangles) {
            int d = gcd(r[0], r[1]);
            String k = (r[0]/d) + "," + (r[1]/d);
            ret += m.getOrDefault(k, 0);
            m.put(k, m.getOrDefault(k, 0) + 1);
        }
        return ret;
    }

    int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        else{
            return gcd(b, a % b);
        }
    }
}
// @lc code=end
