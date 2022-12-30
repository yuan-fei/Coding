/*
 * @lc app=leetcode id=794 lang=java
 *
 * [794] Valid Tic-Tac-Toe State
 *
 * https://leetcode.com/problems/valid-tic-tac-toe-state/description/
 *
 * algorithms
 * Medium (35.13%)
 * Likes:    476
 * Dislikes: 1069
 * Total Accepted:    52.6K
 * Total Submissions: 149.8K
 * Testcase Example:  '["O  ","   ","   "]'
 *
 * Given a Tic-Tac-Toe board as a string array board, return true if and only
 * if it is possible to reach this board position during the course of a valid
 * tic-tac-toe game.
 * 
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'.
 * The ' ' character represents an empty square.
 * 
 * Here are the rules of Tic-Tac-Toe:
 * 
 * 
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player
 * always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled
 * ones.
 * The game ends when there are three of the same (non-empty) character filling
 * any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board = ["O  ","   ","   "]
 * Output: false
 * Explanation: The first player always plays "X".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board = ["XOX"," X ","   "]
 * Output: false
 * Explanation: Players take turns making moves.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: board = ["XOX","O O","XOX"]
 * Output: true
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * board.length == 3
 * board[i].length == 3
 * board[i][j] is either 'X', 'O', or ' '.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean validTicTacToe(String[] board) {
        int cntX = 0;
        int cntO = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i].charAt(j) == 'X'){
                    cntX++;
                }
                else if(board[i].charAt(j) == 'O'){
                    cntO++;
                }
            }
        }
        if(0 > cntX - cntO || cntX - cntO > 1){
            return false;
        }
        int rowX = 0;
        int rowO = 0;
        for(int i = 0; i < 3; i++){
            boolean same = true;
            for(int j = 1; j < 3; j++){
                if(board[i].charAt(j) != board[i].charAt(j - 1)){
                    same = false;
                    break;
                }
            }
            if(same){
                if(board[i].charAt(0) == 'X'){
                    rowX++;
                }
                else if(board[i].charAt(0) == 'O'){
                    rowO++;
                }
            }
            same = true;
            for(int j = 1; j < 3; j++){
                if(board[j].charAt(i) != board[j - 1].charAt(i)){
                    same = false;
                    break;
                }
            }
            if(same){
                if(board[0].charAt(i) == 'X'){
                    rowX++;
                }
                else if(board[0].charAt(i) == 'O'){
                    rowO++;
                }
            }
        }
        boolean same = true;
        for(int i = 1; i < 3; i++){
            if(board[i].charAt(i) != board[i - 1].charAt(i - 1)){
                same = false;
                break;
            }
        }
        if(same){
            if(board[0].charAt(0) == 'X'){
                rowX++;
            }
            else if(board[0].charAt(0) == 'O'){
                rowO++;
            }
        }
        same = true;
        for(int i = 1; i < 3; i++){
            if(board[2 - i].charAt(i) != board[2 - (i - 1)].charAt(i - 1)){
                same = false;
                break;
            }
        }
        if(same){
            if(board[2].charAt(0) == 'X'){
                rowX++;
            }
            else if(board[2].charAt(0) == 'O'){
                rowO++;
            }
        }
        if(rowX > 2 || rowO > 1){
            return false;
        }
        if(rowX >= 1 && rowO == 1){
            return false;
        }
        if(rowX > 0 && cntO == cntX){
            return false;
        }
        if(rowO > 0 && cntO != cntX){
            return false;
        }
        return true;
    }
}
// @lc code=end
