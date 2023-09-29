/*
 * @lc app=leetcode id=920 lang=java
 *
 * [920] Number of Music Playlists
 *
 * https://leetcode.com/problems/number-of-music-playlists/description/
 *
 * algorithms
 * Hard (61.08%)
 * Likes:    2239
 * Dislikes: 190
 * Total Accepted:    65.6K
 * Total Submissions: 107.5K
 * Testcase Example:  '3\n3\n1'
 *
 * Your music player contains n different songs. You want to listen to goal
 * songs (not necessarily different) during your trip. To avoid boredom, you
 * will create a playlist so that:
 * 
 * 
 * Every song is played at least once.
 * A song can only be played again only if k other songs have been played.
 * 
 * 
 * Given n, goal, and k, return the number of possible playlists that you can
 * create. Since the answer can be very large, return it modulo 10^9 + 7.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, goal = 3, k = 1
 * Output: 6
 * Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1,
 * 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 2, goal = 3, k = 0
 * Output: 6
 * Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1,
 * 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, goal = 3, k = 1
 * Output: 2
 * Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= k < n <= goal <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numMusicPlaylists(int n, int goal, int k) {
        long MOD = (long)1e9 + 7;
        long[][] dp = new long[goal + 1][n + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= goal; i++){
            for(int used = 1; used <= n; used++){
                // use existing songs
                dp[i][used] += dp[i - 1][used] * Math.max(used - Math.min(i + 1, k), 0);
                dp[i][used] %= MOD;
                // add a new song
                dp[i][used] += dp[i - 1][used - 1] * (n - used + 1);
                dp[i][used] %= MOD;
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        return (int)dp[goal][n];
    }
}
// @lc code=end
