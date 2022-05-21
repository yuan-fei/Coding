/*
 * @lc app=leetcode id=2271 lang=java
 *
 * [2271] Maximum White Tiles Covered by a Carpet
 *
 * https://leetcode.com/problems/maximum-white-tiles-covered-by-a-carpet/description/
 *
 * algorithms
 * Medium (27.73%)
 * Likes:    309
 * Dislikes: 22
 * Total Accepted:    6.1K
 * Total Submissions: 22.2K
 * Testcase Example:  '[[1,5],[10,11],[12,18],[20,25],[30,32]]\n10'
 *
 * You are given a 2D integer array tiles where tiles[i] = [li, ri] represents
 * that every tile j in the range li <= j <= ri is colored white.
 * 
 * You are also given an integer carpetLen, the length of a single carpet that
 * can be placed anywhere.
 * 
 * Return the maximum number of white tiles that can be covered by the
 * carpet.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * Output: 9
 * Explanation: Place the carpet starting on tile 10. 
 * It covers 9 white tiles, so we return 9.
 * Note that there may be other places where the carpet covers 9 white tiles.
 * It can be shown that the carpet cannot cover more than 9 white tiles.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tiles = [[10,11],[1,1]], carpetLen = 2
 * Output: 2
 * Explanation: Place the carpet starting on tile 10. 
 * It covers 2 white tiles, so we return 2.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= tiles.length <= 5 * 10^4
 * tiles[i].length == 2
 * 1 <= li <= ri <= 10^9
 * 1 <= carpetLen <= 10^9
 * The tiles are non-overlapping.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (a, b) -> Integer.compare(a[0], b[0]));
        int n = tiles.length;
        int rightId = 0;
        int right = 0;
        long ans = 0;
        long sum = 0;
        for(int i = 0; i < n; i++){
            right = Math.max(right, tiles[i][0]);
            while(rightId < n && right <= tiles[i][0] + carpetLen - 1){
                sum += Math.min(tiles[rightId][1], tiles[i][0] + carpetLen - 1) - right + 1;
                if(tiles[rightId][1] <= tiles[i][0] + carpetLen - 1){
                    rightId++;
                    if(rightId < n){
                        right = tiles[rightId][0];
                    }
                }
                else{
                    right = tiles[i][0] + carpetLen;
                }
                // System.out.println(rightId + ", " + right + ", " + (tiles[i][0] + carpetLen - 1) + ", " + sum);
            }
            if(i > 0){
                sum -= Math.min(tiles[i - 1][1] - tiles[i - 1][0] + 1, carpetLen);
            }
            // System.out.println(sum);
            ans = Math.max(sum, ans);
        }
        return (int)ans;
    }
}
// @lc code=end
