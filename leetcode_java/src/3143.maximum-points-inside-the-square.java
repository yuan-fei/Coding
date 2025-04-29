/*
 * @lc app=leetcode id=3143 lang=java
 *
 * [3143] Maximum Points Inside the Square
 *
 * https://leetcode.com/problems/maximum-points-inside-the-square/description/
 *
 * algorithms
 * Medium (38.80%)
 * Likes:    158
 * Dislikes: 22
 * Total Accepted:    19.1K
 * Total Submissions: 50.4K
 * Testcase Example:  '[[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]]\n"abdca"'
 *
 * You are given a 2D array points and a string s where, points[i] represents
 * the coordinates of point i, and s[i] represents the tag of point i.
 * 
 * A valid square is a square centered at the origin (0, 0), has edges parallel
 * to the axes, and does not contain two points with the same tag.
 * 
 * Return the maximum number of points contained in a valid square.
 * 
 * Note:
 * 
 * 
 * A point is considered to be inside the square if it lies on or within the
 * square's boundaries.
 * The side length of the square can be zero.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: points = [[2,2],[-1,-2],[-4,4],[-3,1],[3,-3]], s = "abdca"
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * The square of side length 4 covers two points points[0] and points[1].
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: points = [[1,1],[-2,-2],[-2,2]], s = "abb"
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The square of side length 2 covers one point, which is points[0].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: points = [[1,1],[-1,-1],[2,-2]], s = "ccd"
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * It's impossible to make any valid squares centered at the origin such that
 * it covers only one point among points[0] and points[1].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, points.length <= 10^5
 * points[i].length == 2
 * -10^9 <= points[i][0], points[i][1] <= 10^9
 * s.length == points.length
 * points consists of distinct coordinates.
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int low = 0;
        int high = (int) 1e9 + 7;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            int cnt = countValidPoints(points, s, mid);
            if (cnt == -1) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int cnt = countValidPoints(points, s, high);
        if (cnt >= 0) {
            return cnt;
        } else {
            return countValidPoints(points, s, low);
        }
    }

    int countValidPoints(int[][] points, String s, int side) {
        List<Character> tags = IntStream.range(0, points.length)
                .filter(i -> Math.abs(points[i][0]) <= side && Math.abs(points[i][1]) <= side).mapToObj(s::charAt)
                .sorted().toList();
        // System.out.println(Arrays.asList(side, tags));
        if (new HashSet<>(tags).size() == tags.size()) {
            return tags.size();
        }
        return -1;
    }
}
// @lc code=end
