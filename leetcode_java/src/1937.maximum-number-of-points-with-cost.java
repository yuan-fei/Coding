/*
 * @lc app=leetcode id=1937 lang=java
 *
 * [1937] Maximum Number of Points with Cost
 *
 * https://leetcode.com/problems/maximum-number-of-points-with-cost/description/
 *
 * algorithms
 * Medium (25.98%)
 * Likes:    405
 * Dislikes: 17
 * Total Accepted:    5.6K
 * Total Submissions: 21.4K
 * Testcase Example:  '[[1,2,3],[1,5,1],[3,1,1]]'
 *
 * You are given an m x n integer matrix points (0-indexed). Starting with 0
 * points, you want to maximize the number of points you can get from the
 * matrix.
 * 
 * To gain points, you must pick one cell in each row. Picking the cell at
 * coordinates (r, c) will add points[r][c] to your score.
 * 
 * However, you will lose points if you pick a cell too far from the cell that
 * you picked in the previous row. For every two adjacent rows r and r + 1
 * (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2)
 * will subtract abs(c1 - c2) from your score.
 * 
 * Return the maximum number of points you can achieve.
 * 
 * abs(x) is defined as:
 * 
 * 
 * x for x >= 0.
 * -x for x < 0.
 * 
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0,
 * 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0,
 * 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == points.length
 * n == points[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 0 <= points[r][c] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maxPoints(int[][] points) {
        int n = points.length;
        int m = points[0].length;
        long[] dp = new long[m];
        for(int j = 0; j < m; j++){
            dp[j] = points[0][j];
        }
        for(int i = 1; i < n; i++){
            // System.out.println(Arrays.toString(dp));
            long[] newDp = new long[m];
            TreeMap<Long, Integer> left = new TreeMap<>();
            TreeMap<Long, Integer> right = new TreeMap<>();
            for(int j = 0; j < m; j++){
                left.put(dp[j] - j, left.getOrDefault(dp[j] - j, 0) + 1);
            }
            for(int j = 0; j < m; j++){
                int cnt = left.get(dp[j] - j) - 1;
                if(cnt > 0){
                    left.put(dp[j] - j, cnt);
                }
                else{
                    left.remove(dp[j] - j);
                }
                right.put(dp[j] + j, right.getOrDefault(dp[j] + j, 0) + 1);
                if(!left.isEmpty()){
                    newDp[j] = Math.max(left.lastKey() + j, newDp[j]);
                }
                if(!right.isEmpty()){
                    newDp[j] = Math.max(right.lastKey() - j, newDp[j]);
                }
                // System.out.println(left + ", " + right);
                newDp[j] += points[i][j];
            }
            dp = newDp;
        }
        long max = 0;
        for(int j = 0; j < m; j++){
            max = Math.max(max, dp[j]);
        }
        return max;
    }
}
// @lc code=end
