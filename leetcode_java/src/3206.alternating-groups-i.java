/*
 * @lc app=leetcode id=3206 lang=java
 *
 * [3206] Alternating Groups I
 *
 * https://leetcode.com/problems/alternating-groups-i/description/
 *
 * algorithms
 * Easy (67.23%)
 * Likes:    161
 * Dislikes: 9
 * Total Accepted:    70.5K
 * Total Submissions: 102.9K
 * Testcase Example:  '[1,1,1]'
 *
 * There is a circle of red and blue tiles. You are given an array of integers
 * colors. The color of tile i is represented by colors[i]:
 * 
 * 
 * colors[i] == 0 means that tile i is red.
 * colors[i] == 1 means that tile i is blue.
 * 
 * 
 * Every 3 contiguous tiles in the circle with alternating colors (the middle
 * tile has a different color from its left and right tiles) is called an
 * alternating group.
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
 * Input: colors = [1,1,1]
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: colors = [0,1,0,0,1]
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
 * 
 * Constraints:
 * 
 * 
 * 3 <= colors.length <= 100
 * 0 <= colors[i] <= 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            boolean good = true;
            for (int j = 1; j < 3; j++) {
                if (colors[(i + j) % n] == colors[(i + j - 1) % n]) {
                    good = false;
                    break;
                }
            }
            if (good) {
                ans++;
            }
        }
        return ans;
    }
}
// @lc code=end
