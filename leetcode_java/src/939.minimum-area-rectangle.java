/*
 * @lc app=leetcode id=939 lang=java
 *
 * [939] Minimum Area Rectangle
 *
 * https://leetcode.com/problems/minimum-area-rectangle/description/
 *
 * algorithms
 * Medium (53.04%)
 * Likes:    1829
 * Dislikes: 269
 * Total Accepted:    125.9K
 * Total Submissions: 237K
 * Testcase Example:  '[[1,1],[1,3],[3,1],[3,3],[2,2]]'
 *
 * You are given an array of points in the X-Y plane points where points[i] =
 * [xi, yi].
 * 
 * Return the minimum area of a rectangle formed from these points, with sides
 * parallel to the X and Y axes. If there is not any such rectangle, return
 * 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= points.length <= 500
 * points[i].length == 2
 * 0 <= xi, yi <= 4 * 10^4
 * All the given points are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minAreaRect(int[][] points) {
        Set<List<Integer>> pointSet = new HashSet<>();
        for(int[] p : points){
            pointSet.add(Arrays.asList(p[0], p[1]));
        }
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]) != 0 ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int ans = Integer.MAX_VALUE;
        // System.out.println(Arrays.deepToString(points));
        for(int i = 0; i < points.length; i++){
            for(int j = i + 1; j < points.length; j++){
                if(points[i][0] != points[j][0] && points[i][1] != points[j][1]){
                    // System.out.println(Arrays.asList(i, j));
                    if(pointSet.contains(Arrays.asList(points[i][0], points[j][1])) 
                        && pointSet.contains(Arrays.asList(points[j][0], points[i][1]))){
                        // System.out.println("hit: " + Arrays.asList(i, j));
                        ans = Math.min(ans, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
// @lc code=end
