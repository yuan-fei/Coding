/*
 * @lc app=leetcode id=963 lang=java
 *
 * [963] Minimum Area Rectangle II
 *
 * https://leetcode.com/problems/minimum-area-rectangle-ii/description/
 *
 * algorithms
 * Medium (54.63%)
 * Likes:    354
 * Dislikes: 441
 * Total Accepted:    25.9K
 * Total Submissions: 47.3K
 * Testcase Example:  '[[1,2],[2,1],[1,0],[0,1]]'
 *
 * You are given an array of points in the X-Y plane points where points[i] =
 * [xi, yi].
 * 
 * Return the minimum area of any rectangle formed from these points, with
 * sides not necessarily parallel to the X and Y axes. If there is not any such
 * rectangle, return 0.
 * 
 * Answers within 10^-5 of the actual answer will be accepted.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,2],[2,1],[1,0],[0,1]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1],
 * with an area of 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[0,1],[2,1],[1,1],[1,0],[2,0]]
 * Output: 1.00000
 * Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0],
 * with an area of 1.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: points = [[0,3],[1,2],[3,1],[1,3],[2,1]]
 * Output: 0
 * Explanation: There is no possible rectangle to form from these points.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= points.length <= 50
 * points[i].length == 2
 * 0 <= xi, yi <= 4 * 10^4
 * All the given points are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public double minAreaFreeRect(int[][] points) {
        int n = points.length;
        Set<List<Integer>> pointSet = new HashSet<>();
        for(int i = 0; i < n; i++){
            pointSet.add(Arrays.asList(points[i][0], points[i][1]));
        }
        double ans = Double.MAX_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    if(i != j && j != k && i != k){
                        List<Integer> p = getLast(points, i, j, k);
                        if(p != null && pointSet.contains(p)){
                            // System.out.println(Arrays.asList(i, j, k));
                            ans = Math.min(ans, getArea(points, i, j, k));
                        }    
                    }
                }
            }
        }
        return ans == Double.MAX_VALUE ? 0 : ans;
    }

    double getArea(int[][] points, int i, int j, int k){
        return norm(getVector(points[i], points[j])) * norm(getVector(points[i], points[k]));
    }

    List<Integer> getLast(int[][] points, int i, int j, int k){
        if(isRightAngle(getVector(points[i], points[j]), getVector(points[i], points[k]))){
            return Arrays.asList(points[j][0] + points[k][0] - points[i][0], points[j][1] + points[k][1] - points[i][1]);
        }
        return null;
    }

    int[] getVector(int[] v1, int[] v2){
        return new int[]{v2[0] - v1[0], v2[1] - v1[1]};
    }

    long dotProduct(int[] v1, int[] v2){
        return 1L * v1[0] * v2[0] + 1L * v1[1] * v2[1];
    }

    double norm(int[] v1){
        return Math.sqrt(1L * v1[0] * v1[0] + 1L * v1[1] * v1[1]);
    }

    double getAngleCosine(int[] v1, int[] v2){
        return 1.0d * Math.abs(dotProduct(v1, v2)) / norm(v1) / norm(v2);
    }

    boolean isRightAngle(int[] v1, int[] v2){
        double cosine = getAngleCosine(v1, v2);
        // System.out.println(Arrays.toString(v1) + ", " + Arrays.toString(v2) + " = " + cosine);
        return cosine == 0;
    }
}
// @lc code=end
