/*
 * @lc app=leetcode id=2209 lang=java
 *
 * [2209] Minimum White Tiles After Covering With Carpets
 *
 * https://leetcode.com/problems/minimum-white-tiles-after-covering-with-carpets/description/
 *
 * algorithms
 * Hard (31.29%)
 * Likes:    138
 * Dislikes: 9
 * Total Accepted:    3.5K
 * Total Submissions: 11.2K
 * Testcase Example:  '"10110101"\n2\n2'
 *
 * You are given a 0-indexed binary string floor, which represents the colors
 * of tiles on a floor:
 * 
 * 
 * floor[i] = '0' denotes that the i^th tile of the floor is colored black.
 * On the other hand, floor[i] = '1' denotes that the i^th tile of the floor is
 * colored white.
 * 
 * 
 * You are also given numCarpets and carpetLen. You have numCarpets black
 * carpets, each of length carpetLen tiles. Cover the tiles with the given
 * carpets such that the number of white tiles still visible is minimum.
 * Carpets may overlap one another.
 * 
 * Return the minimum number of white tiles still visible.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: floor = "10110101", numCarpets = 2, carpetLen = 2
 * Output: 2
 * Explanation: 
 * The figure above shows one way of covering the tiles with the carpets such
 * that only 2 white tiles are visible.
 * No other way of covering the tiles with the carpets can leave less than 2
 * white tiles visible.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: floor = "11111", numCarpets = 2, carpetLen = 3
 * Output: 0
 * Explanation: 
 * The figure above shows one way of covering the tiles with the carpets such
 * that no white tiles are visible.
 * Note that the carpets are able to overlap one another.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= carpetLen <= floor.length <= 1000
 * floor[i] is either '0' or '1'.
 * 1 <= numCarpets <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int[] pSum = new int[floor.length() + 1];
        for(int i = 1; i < pSum.length; i++){
            pSum[i] = pSum[i - 1] + floor.charAt(i - 1) - '0';
        }
        int[][] dp = new int[floor.length() + 1][numCarpets + 1];
        for(int i = 1; i <= floor.length(); i++){
            for(int j = numCarpets; j >= 0; j--){
                if(j > 0){
                    int prev = Math.max(0, i - carpetLen);
                    dp[i][j] = Math.max(dp[i - 1][j], dp[prev][j - 1] + pSum[i] - pSum[prev]);
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return pSum[floor.length()] - dp[floor.length()][numCarpets];
    }
}
// @lc code=end
