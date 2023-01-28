/*
 * @lc app=leetcode id=812 lang=java
 *
 * [812] Largest Triangle Area
 *
 * https://leetcode.com/problems/largest-triangle-area/description/
 *
 * algorithms
 * Easy (60.02%)
 * Likes:    451
 * Dislikes: 1480
 * Total Accepted:    39.3K
 * Total Submissions: 65.5K
 * Testcase Example:  '[[0,0],[0,1],[1,0],[0,2],[2,0]]'
 *
 * Given an array of points on the X-Y plane points where points[i] = [xi, yi],
 * return the area of the largest triangle that can be formed by any three
 * different points. Answers within 10^-5 of the actual answer will be
 * accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2.00000
 * Explanation: The five points are shown in the above figure. The red triangle
 * is the largest.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[1,0],[0,0],[0,1]]
 * Output: 0.50000
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= points.length <= 50
 * -50 <= xi, yi <= 50
 * All the given points are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public double largestTriangleArea(int[][] points) {
        int n = points.length;
        double ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    ans = Math.max(ans, crossProduct(new int[] {points[i][0] - points[j][0], points[i][1] - points[j][1]}, new int[]{points[k][0] - points[i][0], points[k][1] - points[i][1]}));
                }
            }
        }
        return ans;
    }

    double crossProduct(int[] v1, int[] v2){
        return Math.abs(v1[0]*v2[1] - v2[0]*v1[1]) / 2.0d;
    }
}
// @lc code=end
