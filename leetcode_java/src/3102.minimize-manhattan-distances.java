/*
 * @lc app=leetcode id=3102 lang=java
 *
 * [3102] Minimize Manhattan Distances
 *
 * https://leetcode.com/problems/minimize-manhattan-distances/description/
 *
 * algorithms
 * Hard (31.44%)
 * Likes:    174
 * Dislikes: 15
 * Total Accepted:    9.4K
 * Total Submissions: 29.6K
 * Testcase Example:  '[[3,10],[5,15],[10,2],[4,4]]'
 *
 * You are given an array points representing integer coordinates of some
 * points on a 2D plane, where points[i] = [xi, yi].
 * 
 * The distance between two points is defined as their Manhattan distance.
 * 
 * Return the minimum possible value for maximum distance between any two
 * points by removing exactly one point.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[3,10],[5,15],[10,2],[4,4]]
 * 
 * Output: 12
 * 
 * Explanation:
 * 
 * The maximum distance after removing each point is the following:
 * 
 * 
 * After removing the 0^th point the maximum distance is between points (5, 15)
 * and (10, 2), which is |5 - 10| + |15 - 2| = 18.
 * After removing the 1^st point the maximum distance is between points (3, 10)
 * and (10, 2), which is |3 - 10| + |10 - 2| = 15.
 * After removing the 2^nd point the maximum distance is between points (5, 15)
 * and (4, 4), which is |5 - 4| + |15 - 4| = 12.
 * After removing the 3^rd point the maximum distance is between points (5, 15)
 * and (10, 2), which is |5 - 10| + |15 - 2| = 18.
 * 
 * 
 * 12 is the minimum possible maximum distance between any two points after
 * removing exactly one point.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[1,1],[1,1],[1,1]]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * Removing any of the points results in the maximum distance between any two
 * points of 0.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= points.length <= 10^5
 * points[i].length == 2
 * 1 <= points[i][0], points[i][1] <= 10^8
 * 
 * 
 */

// @lc code=start

import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {

    public int minimumDistance(int[][] points) {
        int n = points.length;
        // S = x + y, D = x - y
        int[] s = IntStream.range(0, n).boxed().sorted(Comparator.comparing(i -> sumValue(points[i])))
                .mapToInt(x -> x)
                .toArray();
        int[] d = IntStream.range(0, n).boxed().sorted(Comparator.comparing(i -> diffValue(points[i])))
                .mapToInt(x -> x)
                .toArray();
        int[] maxDistArray;
        if (sumValue(points[s[n - 1]]) - sumValue(points[s[0]]) > diffValue(points[d[n - 1]])
                - diffValue(points[d[0]])) {
            maxDistArray = s;
        } else {
            maxDistArray = d;
        }
        // System.out.println(Arrays.toString(s) + Arrays.toString(d));
        int minDist = Integer.MAX_VALUE;
        for (int excludedPoint : new int[] { maxDistArray[0], maxDistArray[n - 1] }) {
            int maxDist = 0;
            int min = (s[0] == excludedPoint) ? s[1] : s[0];
            int max = (s[n - 1] == excludedPoint) ? s[n - 2] : s[n - 1];
            maxDist = Math.max(maxDist, sumValue(points[max]) - sumValue(points[min]));

            min = (d[0] == excludedPoint) ? d[1] : d[0];
            max = (d[n - 1] == excludedPoint) ? d[n - 2] : d[n - 1];
            maxDist = Math.max(maxDist, diffValue(points[max]) - diffValue(points[min]));
            minDist = Math.min(minDist, maxDist);
        }
        return minDist;
    }

    int sumValue(int[] p) {
        return p[0] + p[1];
    }

    int diffValue(int[] p) {
        return p[0] - p[1];
    }
}
// @lc code=end
