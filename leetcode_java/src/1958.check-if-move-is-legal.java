/*
 * @lc app=leetcode id=1958 lang=java
 *
 * [1958] Check if Move is Legal
 *
 * https://leetcode.com/problems/check-if-move-is-legal/description/
 *
 * algorithms
 * Medium (39.92%)
 * Likes:    59
 * Dislikes: 121
 * Total Accepted:    4.5K
 * Total Submissions: 11.3K
 * Testcase Example:  '[[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]]\n' +
  '4\n3\n"B"'
 *
 * You are given a 0-indexed 8 x 8 grid board, where board[r][c] represents the
 * cell (r, c) on a game board. On the board, free cells are represented by
 * '.', white cells are represented by 'W', and black cells are represented by
 * 'B'.
 * 
 * Each move in this game consists of choosing a free cell and changing it to
 * the color you are playing as (either white or black). However, a move is
 * only legal if, after changing it, the cell becomes the endpoint of a good
 * line (horizontal, vertical, or diagonal).
 * 
 * A good line is a line of three or more cells (including the endpoints) where
 * the endpoints of the line are one color, and the remaining cells in the
 * middle are the opposite color (no cells in the line are free). You can find
 * examples for good lines in the figure below:
 * 
 * Given two integers rMove and cMove and a character color representing the
 * color you are playing as (white or black), return true if changing cell
 * (rMove, cMove) to color color is a legal move, or false if it is not
 * legal.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board =
 * [[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],[".",".",".","W",".",".",".","."],["W","B","B",".","W","W","W","B"],[".",".",".","B",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","W",".",".",".","."]],
 * rMove = 4, cMove = 3, color = "B"
 * Output: true
 * Explanation: '.', 'W', and 'B' are represented by the colors blue, white,
 * and black respectively, and cell (rMove, cMove) is marked with an 'X'.
 * The two good lines with the chosen cell as an endpoint are annotated above
 * with the red rectangles.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board =
 * [[".",".",".",".",".",".",".","."],[".","B",".",".","W",".",".","."],[".",".","W",".",".",".",".","."],[".",".",".","W","B",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".","B","W",".","."],[".",".",".",".",".",".","W","."],[".",".",".",".",".",".",".","B"]],
 * rMove = 4, cMove = 4, color = "W"
 * Output: false
 * Explanation: While there are good lines with the chosen cell as a middle
 * cell, there are no good lines with the chosen cell as an endpoint.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * board.length == board[r].length == 8
 * 0 <= rMove, cMove < 8
 * board[rMove][cMove] == '.'
 * color is either 'B' or 'W'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        int oppositeColor = color == 'B' ? 'W' : 'B';
        for(int offsetR : Arrays.asList(-1, 0, 1)){
            for(int offsetC : Arrays.asList(-1, 0, 1)){
                if(offsetC != 0 || offsetR != 0){
                    int newR = rMove + offsetR;
                    int newC = cMove + offsetC;
                    int move = 0;
                    while(newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && board[newR][newC] != '.'){
                        move++;
                        if(board[newR][newC] == color){
                            break;
                        }
                        newR += offsetR;
                        newC += offsetC;
                    }
                    // System.out.println(Arrays.asList(offsetR, offsetC, move));
                    if(move > 1 && newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && board[newR][newC] != '.'){
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
// @lc code=end
