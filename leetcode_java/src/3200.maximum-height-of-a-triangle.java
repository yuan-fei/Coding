/*
 * @lc app=leetcode id=3200 lang=java
 *
 * [3200] Maximum Height of a Triangle
 *
 * https://leetcode.com/problems/maximum-height-of-a-triangle/description/
 *
 * algorithms
 * Easy (43.37%)
 * Likes:    161
 * Dislikes: 29
 * Total Accepted:    43.1K
 * Total Submissions: 98.1K
 * Testcase Example:  '2\n4'
 *
 * You are given two integers red and blue representing the count of red and
 * blue colored balls. You have to arrange these balls to form a triangle such
 * that the 1^st row will have 1 ball, the 2^nd row will have 2 balls, the 3^rd
 * row will have 3 balls, and so on.
 * 
 * All the balls in a particular row should be the same color, and adjacent
 * rows should have different colors.
 * 
 * Return the maximum height of the triangle that can be achieved.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: red = 2, blue = 4
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * 
 * 
 * The only possible arrangement is shown above.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: red = 2, blue = 1
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * The only possible arrangement is shown above.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: red = 1, blue = 1
 * 
 * Output: 1
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: red = 10, blue = 1
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * The only possible arrangement is shown above.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= red, blue <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(place(new int[] { red, blue }), place(new int[] { blue, red }));
    }

    int place(int[] balls) {
        for (int i = 0;; i++) {
            int ballId = i % 2;
            balls[ballId] -= i + 1;
            if (balls[ballId] < 0) {
                return i;
            }
        }
    }
}
// @lc code=end
