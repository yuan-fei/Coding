/*
 * @lc app=leetcode id=999 lang=java
 *
 * [999] Available Captures for Rook
 *
 * https://leetcode.com/problems/available-captures-for-rook/description/
 *
 * algorithms
 * Easy (68.52%)
 * Likes:    626
 * Dislikes: 625
 * Total Accepted:    61.8K
 * Total Submissions: 90.2K
 * Testcase Example:  '[[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]'
 *
 * On an 8 x 8 chessboard, there is exactly one white rook 'R' and some number
 * of white bishops 'B', black pawns 'p', and empty squares '.'.
 * 
 * When the rook moves, it chooses one of four cardinal directions (north,
 * east, south, or west), then moves in that direction until it chooses to
 * stop, reaches the edge of the board, captures a black pawn, or is blocked by
 * a white bishop. A rook is considered attacking a pawn if the rook can
 * capture the pawn on the rook's turn. The number of available captures for
 * the white rook is the number of pawns that the rook is attacking.
 * 
 * Return the number of available captures for the white rook.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: board =
 * [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation: In this example, the rook is attacking all the pawns.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: board =
 * [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 0
 * Explanation: The bishops are blocking the rook from attacking any of the
 * pawns.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: board =
 * [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
 * Output: 3
 * Explanation: The rook is attacking the pawns at positions b5, d6, and
 * f5.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * board.length == 8
 * board[i].length == 8
 * board[i][j] is either 'R', '.', 'B', or 'p'
 * There is exactly one cell with board[i][j] == 'R'
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numRookCaptures(char[][] board) {
        int[] posRook = findRook(board);
        int n = board.length;
        int m = board[0].length;
        int[][] dirs = {{0, 1},{0, -1},{1, 0},{-1, 0}};
        int ans = 0;
        for(int[] dir: dirs){
            int[] curPos = new int[]{posRook[0], posRook[1]};
            while(true){
                curPos[0] += dir[0];
                curPos[1] += dir[1];
                if(0 <= curPos[0] && curPos[0] < n && 0 <= curPos[1] && curPos[1] < m){
                    if(board[curPos[0]][curPos[1]] == 'p'){
                        ans++;
                    }
                    if(board[curPos[0]][curPos[1]] != '.'){
                        break;
                    }
                }
                else{
                    break;
                }
            }
        }
        return ans;
    }

    int[] findRook(char[][] board){
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == 'R'){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
// @lc code=end
