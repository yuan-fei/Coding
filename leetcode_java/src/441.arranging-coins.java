/*
 * @lc app=leetcode id=441 lang=java
 *
 * [441] Arranging Coins
 *
 * https://leetcode.com/problems/arranging-coins/description/
 *
 * algorithms
 * Easy (39.68%)
 * Likes:    305
 * Dislikes: 597
 * Total Accepted:    95.3K
 * Total Submissions: 240K
 * Testcase Example:  '5'
 *
 * You have a total of n coins that you want to form in a staircase shape,
 * where every k-th row must have exactly k coins.
 * ⁠
 * Given n, find the total number of full staircase rows that can be formed.
 * 
 * n is a non-negative integer and fits within the range of a 32-bit signed
 * integer.
 * 
 * Example 1:
 * 
 * n = 5
 * 
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * 
 * Because the 3rd row is incomplete, we return 2.
 * 
 * 
 * 
 * Example 2:
 * 
 * n = 8
 * 
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * 
 * Because the 4th row is incomplete, we return 3.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int arrangeCoins(int n) {
    	// x(x+1) = 2n => x = sqrt(2n + 1/4) - 1/2 = (sqrt(8n + 1) - 1)/2
    	return (int)((Math.sqrt(8L * n + 1) - 1) / 2);
    }
}
// @lc code=end
