/*
 * @lc app=leetcode id=1453 lang=java
 *
 * [1453] Maximum Number of Darts Inside of a Circular Dartboard
 *
 * https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/description/
 *
 * algorithms
 * Hard (17.78%)
 * Likes:    11
 * Dislikes: 24
 * Total Accepted:    563
 * Total Submissions: 3.3K
 * Testcase Example:  '[[-2,0],[2,0],[0,2],[0,-2]]\n2'
 *
 * You have a very large square wall and a circular dartboard placed on the
 * wall. You have been challenged to throw darts into the board blindfolded.
 * Darts thrown at the wall are represented as an array of points on a 2D
 * plane. 
 * 
 * Return the maximum number of points that are within or lie on any circular
 * dartboard of radius r.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: points = [[-2,0],[2,0],[0,2],[0,-2]], r = 2
 * Output: 4
 * Explanation: Circle dartboard with center in (0,0) and radius = 2 contain
 * all points.
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: points = [[-3,0],[3,0],[2,6],[5,4],[0,9],[7,8]], r = 5
 * Output: 5
 * Explanation: Circle dartboard with center in (0,4) and radius = 5 contain
 * all points except the point (7,8).
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: points = [[-2,0],[2,0],[0,2],[0,-2]], r = 1
 * Output: 1
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: points = [[1,2],[3,5],[1,-1],[2,3],[4,1],[1,3]], r = 2
 * Output: 4
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= points.length <= 100
 * points[i].length == 2
 * -10^4 <= points[i][0], points[i][1] <= 10^4
 * 1 <= r <= 5000
 * 
 */

// @lc code=start
class Solution {
    static class Angle implements Comparable<Angle> {
        double a;
        boolean enter;

        Angle(double a, boolean enter) {
            this.a = a;
            this.enter = enter;
        }

        @Override
        public int compareTo(Angle o) {
            //make sure enter is ahead of exit
            return (a > o.a) ? 1 : (a < o.a) ? -1 : enter == o.enter ? 0 : enter ? -1 : 1;
        }
    }

    int getPointsInside(int i, double r, int n, int[][] points, double[][] dis) {
        List<Angle> angles = new ArrayList<>(2 * n);

        for (int j = 0; j < n; j++) {
            if (i != j && dis[i][j] <= 2 * r) {
                // angle between line [i, j] and line [i, center]
                double B = Math.acos(dis[i][j] / (2 * r));
                // angle between line [i, j] and x axis
                double A = Math.atan2(points[j][1] - points[i][1], points[j][0] - points[i][0]);
                double alpha = A - B;
                double beta = A + B;
                angles.add(new Angle(alpha, true));
                angles.add(new Angle(beta, false));
            }
        }

        Collections.sort(angles);

        int count = 1, res = 1;
        for (Angle a : angles) {
            if (a.enter) {
                count++;
            } else {
                count--;
            }

            if (count > res) {
                res = count;
            }
        }

        return res;
    }

    public int numPoints(int[][] points, int r) {
        int n = points.length;
        double[][] dis = new double[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                dis[i][j] = dis[j][i] = Math.sqrt(Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2));
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, getPointsInside(i, r, n, points, dis));
        }

        return ans;
    }
    
}
// @lc code=end
