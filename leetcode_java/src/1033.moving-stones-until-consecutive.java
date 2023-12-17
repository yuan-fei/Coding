/*
 * @lc app=leetcode id=1033 lang=java
 *
 * [1033] Moving Stones Until Consecutive
 *
 * https://leetcode.com/problems/moving-stones-until-consecutive/description/
 *
 * algorithms
 * Medium (47.11%)
 * Likes:    207
 * Dislikes: 641
 * Total Accepted:    23.7K
 * Total Submissions: 50.1K
 * Testcase Example:  '1\n2\n5'
 *
 * There are three stones in different positions on the X-axis. You are given
 * three integers a, b, and c, the positions of the stones.
 * 
 * In one move, you pick up a stone at an endpoint (i.e., either the lowest or
 * highest position stone), and move it to an unoccupied position between those
 * endpoints. Formally, let's say the stones are currently at positions x, y,
 * and z with x < y < z. You pick up the stone at either position x or position
 * z, and move that stone to an integer position k, with x < k < z and k != y.
 * 
 * The game ends when you cannot make any more moves (i.e., the stones are in
 * three consecutive positions).
 * 
 * Return an integer array answer of length 2 where:
 * 
 * 
 * answer[0] is the minimum number of moves you can play, and
 * answer[1] is the maximum number of moves you can play.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = 1, b = 2, c = 5
 * Output: [1,2]
 * Explanation: Move the stone from 5 to 3, or move the stone from 5 to 4 to
 * 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = 4, b = 3, c = 2
 * Output: [0,0]
 * Explanation: We cannot make any moves.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: a = 3, b = 5, c = 1
 * Output: [1,2]
 * Explanation: Move the stone from 1 to 4; or move the stone from 1 to 2 to
 * 4.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= a, b, c <= 100
 * a, b, and c have different values.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] positions = {a, b, c};
        Arrays.sort(positions);
        a = positions[0];
        b = positions[1];
        c = positions[2];
        int minGap = Math.min(b - a - 1, c - b - 1);
        int maxGap = Math.max(b - a - 1, c - b - 1);
        int min = maxGap == 0 ? 0 : (minGap > 1 ? 2 : 1);
        int max = c - a - 2;
        return new int[]{min, max};
    }
}
// @lc code=end
