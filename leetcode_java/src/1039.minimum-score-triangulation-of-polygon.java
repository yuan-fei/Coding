/*
 * @lc app=leetcode id=1039 lang=java
 *
 * [1039] Minimum Score Triangulation of Polygon
 *
 * https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/
 *
 * algorithms
 * Medium (57.98%)
 * Likes:    1753
 * Dislikes: 163
 * Total Accepted:    44.1K
 * Total Submissions: 75.9K
 * Testcase Example:  '[1,2,3]'
 *
 * You have a convex n-sided polygon where each vertex has an integer value.
 * You are given an integer array values where values[i] is the value of the
 * i^th vertex (i.e., clockwise order).
 * 
 * You will triangulate the polygon into n - 2 triangles. For each triangle,
 * the value of that triangle is the product of the values of its vertices, and
 * the total score of the triangulation is the sum of these values over all n -
 * 2 triangles in the triangulation.
 * 
 * Return the smallest possible total score that you can achieve with some
 * triangulation of the polygon.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: values = [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only
 * triangle is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: values = [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 +
 * 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
 * The minimum score is 144.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: values = [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5
 * + 1*1*1 = 13.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == values.length
 * 3 <= n <= 50
 * 1 <= values[i] <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    int[][] dp;
    int[] values;
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        this.values = values;
        dp = new int[n][n];
        return rec(0, n - 1);
    }

    int rec(int start, int end){
        if(end - start < 2){
            return 0;
        }
        if(dp[start][end] == 0){
            dp[start][end] = Integer.MAX_VALUE;
            for(int mid = start + 1; mid < end; mid++){
                dp[start][end] = Math.min(dp[start][end], rec(start, mid) + rec(mid, end) + values[start] * values[mid] * values[end]);
            }    
        }
        // System.out.println(Arrays.asList(start, end, dp[start][end]));
        return dp[start][end];
    }
}
// @lc code=end
