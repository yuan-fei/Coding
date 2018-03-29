

public class SudokuSolver {

	public static void main(String[] args) {
		char[][] board = new char[][] {
				new char[] {'.','.','9','7','4','8','.','.','.'},
				new char[] {'7','.','.','.','.','.','.','.','.'},
				new char[] {'.','2','.','1','.','9','.','.','.'},
				new char[] {'.','.','7','.','.','.','2','4','.'},
				new char[] {'.','6','4','.','1','.','5','9','.'},
				new char[] {'.','9','8','.','.','.','3','.','.'},
				new char[] {'.','.','.','8','.','3','.','2','.'},
				new char[] {'.','.','.','.','.','.','.','.','6'},
				new char[] {'.','.','.','2','7','5','9','.','.'}
		};
		solveSudoku(board);
		printBoard(board);
	}

	public static void printBoard(char[][] board) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j] + ",");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void solveSudoku(char[][] board) {
		boolean candidateStates[][] = new boolean[9][9];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.') {
					candidateStates[i][board[i][j] - '1'] = true;
				}
			}
		}

		solveSudokuHelper(board, candidateStates, 0, 0);
	}

	private static boolean solveSudokuHelper(char[][] board, boolean[][] candidateStates, int row, int col) {
		if (row == board.length) {
			return true;
		} else if (board[row][col] == '.') {
			boolean[] candidates = candidateStates[row];
			for (int i = 0; i < candidates.length; i++) {

				// System.out.println(""+row+","+col+","+i+"="+candidates[i]);

				if (!candidates[i] && validate(board, row, col, (char) ('1' + i))) {
					candidates[i] = true;
					board[row][col] = (char) ('1' + i);

					// System.out.println("validated: "+row+","+col+","+i+"="+candidates[i]);

					if (solveSudokuHelper(board, candidateStates, row + (col + 1) / 9, (col + 1) % 9)) {
						return true;
					}
					candidates[i] = false;
					board[row][col] = '.';
				}
			}
		} else {
			// System.out.println(""+row+","+col+","+board[row][col]);
			if (solveSudokuHelper(board, candidateStates, row + (col + 1) / 9, (col + 1) % 9)) {
				return true;
			}
		}
		return false;
	}

	private static boolean validate(char[][] board, int row, int col, char c) {
		// System.out.println("validate0: "+c);
		for (int i = 0; i < 9; i++) {
			if (board[i][col] == c) {
				// System.out.println("validate1: "+i+","+col);
				return false;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[row - row % 3 + i][col - col % 3 + j] == c) {
					// System.out.println("validate2: "+(row / 3 + i)+","+(col / 3 + j));
					return false;
				}
			}
		}

		return true;
	}
}
