/*
 * @lc app=leetcode id=1041 lang=java
 *
 * [1041] Robot Bounded In Circle
 *
 * https://leetcode.com/problems/robot-bounded-in-circle/description/
 *
 * algorithms
 * Medium (55.46%)
 * Likes:    3637
 * Dislikes: 685
 * Total Accepted:    223.1K
 * Total Submissions: 401.6K
 * Testcase Example:  '"GGLLGG"'
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.
 * Note that:
 * 
 * 
 * The north direction is the positive direction of the y-axis.
 * The south direction is the negative direction of the y-axis.
 * The east direction is the positive direction of the x-axis.
 * The west direction is the negative direction of the x-axis.
 * 
 * 
 * The robot can receive one of three instructions:
 * 
 * 
 * "G": go straight 1 unit.
 * "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
 * "R": turn 90 degrees to the right (i.e., clockwise direction).
 * 
 * 
 * The robot performs the instructions given in order, and repeats them
 * forever.
 * 
 * Return true if and only if there exists a circle in the plane such that the
 * robot never leaves the circle.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
 * "G": move one step. Position: (0, 1). Direction: South.
 * "G": move one step. Position: (0, 0). Direction: South.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1)
 * --> (0, 2) --> (0, 1) --> (0, 0).
 * Based on that, we return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "G": move one step. Position: (0, 2). Direction: North.
 * Repeating the instructions, keeps advancing in the north direction and does
 * not go into cycles.
 * Based on that, we return false.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot is initially at (0, 0) facing the north direction.
 * "G": move one step. Position: (0, 1). Direction: North.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
 * "G": move one step. Position: (-1, 1). Direction: West.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
 * "G": move one step. Position: (-1, 0). Direction: South.
 * "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
 * "G": move one step. Position: (0, 0). Direction: East.
 * "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
 * Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1)
 * --> (-1, 1) --> (-1, 0) --> (0, 0).
 * Based on that, we return true.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= instructions.length <= 100
 * instructions[i] is 'G', 'L' or, 'R'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isRobotBounded(String instructions) {
        int[] pos = {0, 0};
        int[][] dirs = {{0, 1},{1, 0},{0, -1},{-1, 0}};
        int direction = 0;
        for(char c : instructions.toCharArray()){
            if(c == 'G'){
                pos[0] += dirs[direction][0];
                pos[1] += dirs[direction][1]; 
            }
            else if(c == 'L'){
                direction += 3;
                direction %= 4;
            }
            else{
                direction += 1;
                direction %= 4;
            }
        }
        return direction != 0 || (pos[0] == 0 && pos[1] == 0);
    }
}
// @lc code=end
