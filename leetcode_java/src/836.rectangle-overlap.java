/*
 * @lc app=leetcode id=836 lang=java
 *
 * [836] Rectangle Overlap
 *
 * https://leetcode.com/problems/rectangle-overlap/description/
 *
 * algorithms
 * Easy (43.73%)
 * Likes:    1725
 * Dislikes: 428
 * Total Accepted:    119.2K
 * Total Submissions: 272.6K
 * Testcase Example:  '[0,0,2,2]\n[1,1,3,3]'
 *
 * An axis-aligned rectangle is represented as a list [x1, y1, x2, y2], where
 * (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the
 * coordinate of its top-right corner. Its top and bottom edges are parallel to
 * the X-axis, and its left and right edges are parallel to the Y-axis.
 * 
 * Two rectangles overlap if the area of their intersection is positive. To be
 * clear, two rectangles that only touch at the corner or edges do not
 * overlap.
 * 
 * Given two axis-aligned rectangles rec1 and rec2, return true if they
 * overlap, otherwise return false.
 * 
 * 
 * Example 1:
 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
 * Output: true
 * Example 2:
 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
 * Output: false
 * Example 3:
 * Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * rec1.length == 4
 * rec2.length == 4
 * -10^9 <= rec1[i], rec2[i] <= 10^9
 * rec1 and rec2 represent a valid rectangle with a non-zero area.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int a = Math.max(rec1[0], rec2[0]);
        int b = Math.max(rec1[1], rec2[1]);
        int c = Math.min(rec1[2], rec2[2]);
        int d = Math.min(rec1[3], rec2[3]);
        return a < c && b < d;
    }
}
// @lc code=end
