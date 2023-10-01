/*
 * @lc app=leetcode id=935 lang=java
 *
 * [935] Knight Dialer
 *
 * https://leetcode.com/problems/knight-dialer/description/
 *
 * algorithms
 * Medium (51.11%)
 * Likes:    2120
 * Dislikes: 393
 * Total Accepted:    105.3K
 * Total Submissions: 205.4K
 * Testcase Example:  '1'
 *
 * The chess knight has a unique movement, it may move two squares vertically
 * and one square horizontally, or two squares horizontally and one square
 * vertically (with both forming the shape of an L). The possible movements of
 * chess knight are shown in this diagaram:
 * 
 * A chess knight can move as indicated in the chess diagram below:
 * 
 * We have a chess knight and a phone pad as shown below, the knight can only
 * stand on a numeric cell (i.e. blue cell).
 * 
 * Given an integer n, return how many distinct phone numbers of length n we
 * can dial.
 * 
 * You are allowed to place the knight on any numeric cell initially and then
 * you should perform n - 1 jumps to dial a number of length n. All jumps
 * should be valid knight jumps.
 * 
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 1
 * Output: 10
 * Explanation: We need to dial a number of length 1, so placing the knight
 * over any numeric cell of the 10 cells is sufficient.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2
 * Output: 20
 * Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29,
 * 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 3131
 * Output: 136006598
 * Explanation: Please take care of the mod.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 5000
 * 
 * 
 */

// @lc code=start
class Solution {
    int MOD = (int)1e9 + 7;
    public int knightDialer(int n) {
        if(n == 1){
            return 10;
        }
        int[][] transition = {
            {0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 0, 0, 0, 0, 0}
        };
        int[][] finalMatrix = pow(transition, n - 1);
        int ans = 0;
        for(int i = 0; i < finalMatrix.length; i++){
            for(int j = 0; j < finalMatrix.length; j++){
                ans += finalMatrix[i][j];
                ans %= MOD;
            }
        }
        return ans;
    }

    int[][] pow(int[][] m, int p){
        if(p == 1){
            return m;
        }
        if(p % 2 == 1){
            return mul(m, pow(m, p - 1));
        }
        else{
            int[][] t = pow(m, p / 2);
            return mul(t, t);
        }
    }

    int[][] mul(int[][] m1, int[][] m2){
        int n = m1.length;
        int[][] ret = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < n; k++){
                    ret[i][j] += (int)((1L * m1[i][k] * m2[k][j]) % MOD);
                    ret[i][j] %= MOD;
                }
            }
        }
        return ret;
    }
}
// @lc code=end
