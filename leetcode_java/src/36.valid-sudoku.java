/*
 * [36] Valid Sudoku
 *
 * https://leetcode.com/problems/valid-sudoku/description/
 *
 * algorithms
 * Medium (37.18%)
 * Total Accepted:    148.1K
 * Total Submissions: 396.2K
 * Testcase Example:  '[[".","8","7","6","5","4","3","2","1"],["2",".",".",".",".",".",".",".","."],["3",".",".",".",".",".",".",".","."],["4",".",".",".",".",".",".",".","."],["5",".",".",".",".",".",".",".","."],["6",".",".",".",".",".",".",".","."],["7",".",".",".",".",".",".",".","."],["8",".",".",".",".",".",".",".","."],["9",".",".",".",".",".",".",".","."]]'
 *
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * 
 * The Sudoku board could be partially filled, where empty cells are filled
 * with the character '.'.
 * 
 * 
 * 
 * A partially filled sudoku which is valid.
 * 
 * 
 * Note:
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only
 * the filled cells need to be validated.
 * 
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
        	if(!isNoDuplication(board, 0, i, 9, i + 1)){
        		return false;
        	}
        	if(!isNoDuplication(board, i, 0, i + 1, 9)){
        		return false;
        	}
        }
        for (int i = 0; i <= 2; i++) {
        	for (int j = 0; j <= 2; j++) {
        		if(!isNoDuplication(board, i * 3, j * 3, i * 3 + 3, j * 3 + 3)){
        			return false;
        		}
        	}	
        }
        return true;
    }

    private boolean isNoDuplication(char[][] board, int left, int top, int right, int bottom){
    	boolean[] nums = new boolean[10];
    	for (int i = top; i < bottom; i++) {
    	 	for (int j = left; j < right; j++) {
    	 		if(board[i][j] != '.'){
    	 			int v = board[i][j] - '0';
    	 			if(nums[v]){
    	 				return false;
    	 			}
    	 			else{
    	 				nums[v] = true;
    	 			}
    	 		}
    	 	}
    	}
    	return true; 
    }
}
