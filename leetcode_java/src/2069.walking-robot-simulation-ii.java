/*
 * @lc app=leetcode id=2069 lang=java
 *
 * [2069] Walking Robot Simulation II
 *
 * https://leetcode.com/problems/walking-robot-simulation-ii/description/
 *
 * algorithms
 * Medium (16.51%)
 * Likes:    57
 * Dislikes: 147
 * Total Accepted:    2.5K
 * Total Submissions: 14.9K
 * Testcase Example:  '["Robot","move","move","getPos","getDir","move","move","move","getPos","getDir"]\n' +
  '[[6,3],[2],[2],[],[],[2],[1],[4],[],[]]'
 *
 * A width x height grid is on an XY-plane with the bottom-left cell at (0, 0)
 * and the top-right cell at (width - 1, height - 1). The grid is aligned with
 * the four cardinal directions ("North", "East", "South", and "West"). A robot
 * is initially at cell (0, 0) facing direction "East".
 * 
 * The robot can be instructed to move for a specific number of steps. For each
 * step, it does the following.
 * 
 * 
 * Attempts to move forward one cell in the direction it is facing.
 * If the cell the robot is moving to is out of bounds, the robot instead turns
 * 90 degrees counterclockwise and retries the step.
 * 
 * 
 * After the robot finishes moving the number of steps required, it stops and
 * awaits the next instruction.
 * 
 * Implement the Robot class:
 * 
 * 
 * Robot(int width, int height) Initializes the width x height grid with the
 * robot at (0, 0) facing "East".
 * void move(int num) Instructs the robot to move forward num steps.
 * int[] getPos() Returns the current cell the robot is at, as an array of
 * length 2, [x, y].
 * String getDir() Returns the current direction of the robot, "North", "East",
 * "South", or "West".
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["Robot", "move", "move", "getPos", "getDir", "move", "move", "move",
 * "getPos", "getDir"]
 * [[6, 3], [2], [2], [], [], [2], [1], [4], [], []]
 * Output
 * [null, null, null, [4, 0], "East", null, null, null, [1, 2], "West"]
 * 
 * Explanation
 * Robot robot = new Robot(6, 3); // Initialize the grid and the robot at (0,
 * 0) facing East.
 * robot.move(2);  // It moves two steps East to (2, 0), and faces East.
 * robot.move(2);  // It moves two steps East to (4, 0), and faces East.
 * robot.getPos(); // return [4, 0]
 * robot.getDir(); // return "East"
 * robot.move(2);  // It moves one step East to (5, 0), and faces East.
 * ⁠               // Moving the next step East would be out of bounds, so it
 * turns and faces North.
 * ⁠               // Then, it moves one step North to (5, 1), and faces North.
 * robot.move(1);  // It moves one step North to (5, 2), and faces North (not
 * West).
 * robot.move(4);  // Moving the next step North would be out of bounds, so it
 * turns and faces West.
 * ⁠               // Then, it moves four steps West to (1, 2), and faces West.
 * robot.getPos(); // return [1, 2]
 * robot.getDir(); // return "West"
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= width, height <= 100
 * 1 <= num <= 10^5
 * At most 10^4 calls in total will be made to move, getPos, and getDir.
 * 
 * 
 */

// @lc code=start
class Robot {
    String[] faces = {"East", "North", "West", "South"};
    int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int curFace = 0;
    int[] cur = {0, 0};
    int w;
    int h;
    public Robot(int width, int height) {
        w = width;
        h = height;
    }
    
    public void move(int num) {
        num %= (w + h) * 2 - 4;
        num += (w + h) * 2 - 4;
        while(num != 0){
            int d = 0;
            switch(curFace){
                case 0: 
                    d = Math.min(w - 1 - cur[0], num);
                    cur[0] += d;
                    break;
                case 1: 
                    d = Math.min(h - 1 - cur[1], num);
                    cur[1] += d;
                    break;
                case 2: 
                    d = Math.min(cur[0], num);
                    cur[0] -= d;
                    break;
                case 3: 
                    d = Math.min(cur[1], num);
                    cur[1] -= d;
                    break;
            }
            num -= d;
            if(num > 0){
                curFace++;
                curFace %= 4;
            }
        }
    }
    
    public int[] getPos() {
        return cur;
    }
    
    public String getDir() {
        return faces[curFace];
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.move(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
// @lc code=end
