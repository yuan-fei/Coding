/*
 * @lc app=leetcode id=3208 lang=java
 *
 * [3208] Alternating Groups II
 *
 * https://leetcode.com/problems/alternating-groups-ii/description/
 *
 * algorithms
 * Medium (41.45%)
 * Likes:    756
 * Dislikes: 73
 * Total Accepted:    157.6K
 * Total Submissions: 263.3K
 * Testcase Example:  '[0,1,0,1,0]\n3'
 *
 * There is a circle of red and blue tiles. You are given an array of integers
 * colors and an integer k. The color of tile i is represented by
 * colors[i]:
 * 
 * 
 * colors[i] == 0 means that tile i is red.
 * colors[i] == 1 means that tile i is blue.
 * 
 * 
 * An alternating group is every k contiguous tiles in the circle with
 * alternating colors (each tile in the group except the first and last one has
 * a different color from its left and right tiles).
 * 
 * Return the number of alternating groups.
 * 
 * Note that since colors represents a circle, the first and the last tiles are
 * considered to be next to each other.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: colors = [0,1,0,1,0], k = 3
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * 
 * 
 * Alternating groups:
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: colors = [0,1,0,0,1,0,1], k = 6
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * 
 * 
 * Alternating groups:
 * 
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: colors = [1,1,0,1], k = 4
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= colors.length <= 10^5
 * 0 <= colors[i] <= 1
 * 3 <= k <= colors.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int[] repeatColors = new int[2 * n];
        System.arraycopy(colors, 0, repeatColors, 0, n);
        System.arraycopy(colors, 0, repeatColors, n, n);
        int[] pSum = new int[2 * n];
        for (int i = 1; i < 2 * n; i++) {
            pSum[i] = ((repeatColors[i] != repeatColors[i - 1]) ? 1 : 0);
        }
        for (int i = 1; i < 2 * n; i++) {
            pSum[i] += pSum[i - 1];
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (pSum[i + k - 1] - pSum[i] == k - 1) {
                ans++;
            }
        }
        return ans;
    }
}
// @lc code=end
