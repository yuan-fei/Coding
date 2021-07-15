/*
 * @lc app=leetcode id=1926 lang=java
 *
 * [1926] Nearest Exit from Entrance in Maze
 *
 * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/description/
 *
 * algorithms
 * Medium (34.99%)
 * Likes:    151
 * Dislikes: 12
 * Total Accepted:    6K
 * Total Submissions: 17.1K
 * Testcase Example:  '[["+","+",".","+"],[".",".",".","+"],["+","+","+","."]]\n[1,2]'
 *
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented
 * as '.') and walls (represented as '+'). You are also given the entrance of
 * the maze, where entrance = [entrancerow, entrancecol] denotes the row and
 * column of the cell you are initially standing at.
 * 
 * In one step, you can move one cell up, down, left, or right. You cannot step
 * into a cell with a wall, and you cannot step outside the maze. Your goal is
 * to find the nearest exit from the entrance. An exit is defined as an empty
 * cell that is at the border of the maze. The entrance does not count as an
 * exit.
 * 
 * Return the number of steps in the shortest path from the entrance to the
 * nearest exit, or -1 if no such path exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]],
 * entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * maze.length == m
 * maze[i].length == n
 * 1 <= m, n <= 100
 * maze[i][j] is either '.' or '+'.
 * entrance.length == 2
 * 0 <= entrancerow < m
 * 0 <= entrancecol < n
 * entrance will always be an empty cell.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(entrance);
        boolean[][] state = new boolean[maze.length][maze[0].length];
        state[entrance[0]][entrance[1]] = true;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        int steps = 0;
        while(!q.isEmpty()){
            for(int x = q.size(); x > 0; x--){
                int[] cur = q.poll();
                if(cur[0] == 0 || cur[0] == maze.length - 1 || cur[1] == 0 || cur[1] == maze[0].length - 1){
                    if(steps > 0){
                        return steps;    
                    }
                }
                for(int[] dir : dirs){
                    int[] nxt = {cur[0] + dir[0], cur[1] + dir[1]};
                    if(0 <= nxt[0] && nxt[0] < maze.length && 0 <= nxt[1] && nxt[1] < maze[0].length && maze[nxt[0]][nxt[1]] != '+' && !state[nxt[0]][nxt[1]]){
                        q.offer(nxt);
                        state[nxt[0]][nxt[1]] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
// @lc code=end
