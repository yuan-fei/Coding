/*
 * @lc app=leetcode id=2543 lang=java
 *
 * [2543] Check if Point Is Reachable
 *
 * https://leetcode.com/problems/check-if-point-is-reachable/description/
 *
 * algorithms
 * Hard (33.95%)
 * Likes:    78
 * Dislikes: 19
 * Total Accepted:    2.3K
 * Total Submissions: 6.9K
 * Testcase Example:  '6\n9'
 *
 * There exists an infinitely large grid. You are currently at point (1, 1),
 * and you need to reach the point (targetX, targetY) using a finite number of
 * steps.
 * 
 * In one step, you can move from point (x, y) to any one of the following
 * points:
 * 
 * 
 * (x, y - x)
 * (x - y, y)
 * (2 * x, y)
 * (x, 2 * y)
 * 
 * 
 * Given two integers targetX and targetY representing the X-coordinate and
 * Y-coordinate of your final position, return true if you can reach the point
 * from (1, 1) using some number of steps, and false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: targetX = 6, targetY = 9
 * Output: false
 * Explanation: It is impossible to reach (6,9) from (1,1) using any sequence
 * of moves, so false is returned.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: targetX = 4, targetY = 7
 * Output: true
 * Explanation: You can follow the path (1,1) -> (1,2) -> (1,4) -> (1,8) ->
 * (1,7) -> (2,7) -> (4,7).
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= targetX, targetY <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isReachable(int targetX, int targetY) {
        while(targetX % 2 == 0){
            targetX /= 2;
        }
        while(targetY % 2 == 0){
            targetY /= 2;
        }
        if(targetX == targetY){
            return targetX == 1;
        }
        if(targetX > targetY){
            return isReachable(targetY, targetX);
        }
        return isReachable(targetX, targetX + targetY);
    }
}
// @lc code=end
