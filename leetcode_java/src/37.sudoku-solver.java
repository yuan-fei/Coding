/*
 * [37] Sudoku Solver
 *
 * https://leetcode.com/problems/sudoku-solver/description/
 *
 * algorithms
 * Hard (31.72%)
 * Total Accepted:    89.7K
 * Total Submissions: 280.6K
 * Testcase Example:  '[[".",".","9","7","4","8",".",".","."],["7",".",".",".",".",".",".",".","."],[".","2",".","1",".","9",".",".","."],[".",".","7",".",".",".","2","4","."],[".","6","4",".","1",".","5","9","."],[".","9","8",".",".",".","3",".","."],[".",".",".","8",".","3",".","2","."],[".",".",".",".",".",".",".",".","6"],[".",".",".","2","7","5","9",".","."]]'
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 * 
 * 
 * 
 * A sudoku puzzle...
 * 
 * 
 * 
 * 
 * ...and its solution numbers marked in red.
 * 
 */
class Solution {
    public void solveSudoku(char[][] board) {
    	boolean candidateStates[][] = new boolean[9][9];
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {
        		if(board[i][j] != '.'){
        			candidateStates[i][board[i][j] - '1'] = true;	
        		}
        	}
        }
        //print(candidateStates);
        solveSudokuHelper(board, candidateStates, 0, 0);
    }

	private void print(boolean[][] candidateStates){
		for (int i = 0; i < candidateStates.length; i++) {
			for (int j = 0; j < candidateStates.length; j++) {
				if(!candidateStates[i][j]){
					System.out.println(""+i+", "+j);
				}
			}
		}
	}

    private boolean solveSudokuHelper(char[][] board, boolean[][] candidateStates, int row, int col){
    	if(row == board.length){
    		return true;
    	}
    	else if(board[row][col] == '.'){
    		boolean[] candidates = candidateStates[row];
    		for (int i = 0; i < candidates.length; i++) {
    			
    			//System.out.println(""+row+","+col+","+i+"="+candidates[i]);
    			
    			if(!candidates[i] && validate(board, row, col, (char)('1' + i))){
    				candidates[i] = true;
    				board[row][col] = (char)('1' + i);
    				
    				//System.out.println("validated: "+row+","+col+","+i+"="+candidates[i]);

    				if(solveSudokuHelper(board, candidateStates, row + (col + 1) / 9, (col + 1) % 9)) {
    					return true;
    				}
    				candidates[i] = false;
    				board[row][col] = '.';
    			}
    		}
    	}
    	else{
    		//System.out.println(""+row+","+col+","+board[row][col]);
		    if(solveSudokuHelper(board, candidateStates, row + (col + 1) / 9, (col + 1) % 9)) {
    			return true;
    		}
    	}
    	return false;
    }


    private boolean validate(char[][] board, int row, int col, char c){
    	//System.out.println("validate0: "+c);
    	for(int i = 0; i < 9; i++){
    		if(board[i][col] == c){
    			//System.out.println("validate1: "+i+","+col);
    			return false;
    		}
    	}
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if(board[row - row % 3 + i][col - col % 3 + j] == c){
    				//System.out.println("validate2: "+(row / 3 + i)+","+(col / 3 + j));
    				return false;
    			}
    		}
    	}

    	return true;
    }
}
