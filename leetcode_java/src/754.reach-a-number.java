/*
 * @lc app=leetcode id=754 lang=java
 *
 * [754] Reach a Number
 *
 * https://leetcode.com/problems/reach-a-number/description/
 *
 * algorithms
 * Medium (42.72%)
 * Likes:    1430
 * Dislikes: 716
 * Total Accepted:    44K
 * Total Submissions: 102.9K
 * Testcase Example:  '2'
 *
 * You are standing at position 0 on an infinite number line. There is a
 * destination at position target.
 * 
 * You can make some number of moves numMoves so that:
 * 
 * 
 * On each move, you can either go left or right.
 * During the i^th move (starting from i == 1 to i == numMoves), you take i
 * steps in the chosen direction.
 * 
 * 
 * Given the integer target, return the minimum number of moves required (i.e.,
 * the minimum numMoves) to reach the destination.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: target = 2
 * Output: 3
 * Explanation:
 * On the 1^st move, we step from 0 to 1 (1 step).
 * On the 2^nd move, we step from 1 to -1 (2 steps).
 * On the 3^rd move, we step from -1 to 2 (3 steps).
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: target = 3
 * Output: 2
 * Explanation:
 * On the 1^st move, we step from 0 to 1 (1 step).
 * On the 2^nd move, we step from 1 to 3 (2 steps).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -10^9 <= target <= 10^9
 * target != 0
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target);
        int k = 0;
        while (target > 0)
            target -= ++k;
        return target % 2 == 0 ? k : k + 1 + k%2;
    }
}
// @lc code=end
