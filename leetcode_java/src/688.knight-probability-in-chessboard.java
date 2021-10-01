/*
 * @lc app=leetcode id=688 lang=java
 *
 * [688] Knight Probability in Chessboard
 *
 * https://leetcode.com/problems/knight-probability-in-chessboard/description/
 *
 * algorithms
 * Medium (51.02%)
 * Likes:    1598
 * Dislikes: 259
 * Total Accepted:    69.4K
 * Total Submissions: 136K
 * Testcase Example:  '3\n2\n0\n0'
 *
 * On an n x n chessboard, a knight starts at the cell (row, column) and
 * attempts to make exactly k moves. The rows and columns are 0-indexed, so the
 * top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 * 
 * A chess knight has eight possible moves it can make, as illustrated below.
 * Each move is two cells in a cardinal direction, then one cell in an
 * orthogonal direction.
 * 
 * Each time the knight is to move, it chooses one of eight possible moves
 * uniformly at random (even if the piece would go off the chessboard) and
 * moves there.
 * 
 * The knight continues moving until it has made exactly k moves or has moved
 * off the chessboard.
 * 
 * Return the probability that the knight remains on the board after it has
 * stopped moving.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 2, row = 0, column = 0
 * Output: 0.06250
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight
 * on the board.
 * From each of those positions, there are also two moves that will keep the
 * knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 1, k = 0, row = 0, column = 0
 * Output: 1.00000
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 * 
 * 
 */

// @lc code=start
class Solution {
    public double knightProbability(int n, int k, int row, int column) {
        int[] start = {2,2};
        int[][] dirs = {{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{2,-1},{-2,1},{-2,-1}};
        double[][] dp = new double[n + 4][n + 4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dp[start[0] + i][start[1] + j] = 1.0d;
            }
        }
        while(k > 0){
            double[][] newDp = new double[n + 4][n + 4];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    for(int[] dir: dirs){
                        newDp[start[0] + i][start[1] + j] += dp[start[0] + i + dir[0]][start[1] + j + dir[1]] / 8;
                    }
                }
            }
            dp = newDp;
            k--;    
        }
        return dp[start[0] + row][start[1] + column];
    }
}
// @lc code=end
