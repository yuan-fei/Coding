/*
 * @lc app=leetcode id=1275 lang=java
 *
 * [1275] Find Winner on a Tic Tac Toe Game
 *
 * https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/description/
 *
 * algorithms
 * Easy (55.92%)
 * Likes:    18
 * Dislikes: 7
 * Total Accepted:    3.1K
 * Total Submissions: 5.6K
 * Testcase Example:  '[[0,0],[2,0],[1,1],[2,1],[2,2]]'
 *
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid.
 * 
 * Here are the rules of Tic-Tac-Toe:
 * 
 * 
 * Players take turns placing characters into empty squares (" ").
 * The first player A always places "X" characters, while the second player B
 * always places "O" characters.
 * "X" and "O" characters are always placed into empty squares, never on filled
 * ones.
 * The game ends when there are 3 of the same (non-empty) character filling any
 * row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 * 
 * 
 * Given an array moves where each element is another array of size 2
 * corresponding to the row and column of the grid where they mark their
 * respective character in the order in which A and B play.
 * 
 * Return the winner of the game if it exists (A or B), in case the game ends
 * in a draw return "Draw", if there are still movements to play return
 * "Pending".
 * 
 * You can assume that moves is valid (It follows the rules of Tic-Tac-Toe),
 * the grid is initially empty and A will play first.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: "A" wins, he always plays first.
 * "X  "    "X  "    "X  "    "X  "    "X  "
 * "   " -> "   " -> " X " -> " X " -> " X "
 * "   "    "O  "    "O  "    "OO "    "OOX"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: moves = [[0,0],[1,1],[0,1],[0,2],[1,0],[2,0]]
 * Output: "B"
 * Explanation: "B" wins.
 * "X  "    "X  "    "XX "    "XXO"    "XXO"    "XXO"
 * "   " -> " O " -> " O " -> " O " -> "XO " -> "XO " 
 * "   "    "   "    "   "    "   "    "   "    "O  "
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: moves = [[0,0],[1,1],[2,0],[1,0],[1,2],[2,1],[0,1],[0,2],[2,2]]
 * Output: "Draw"
 * Explanation: The game ends in a draw since there are no moves to make.
 * "XXO"
 * "OOX"
 * "XOX"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: moves = [[0,0],[1,1]]
 * Output: "Pending"
 * Explanation: The game has not finished yet.
 * "X  "
 * " O "
 * "   "
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= moves.length <= 9
 * moves[i].length == 2
 * 0 <= moves[i][j] <= 2
 * There are no repeated elements on moves.
 * moves follow the rules of tic tac toe.
 * 
 */

// @lc code=start
class Solution {
	public String tictactoe(int[][] moves) {
		int N = moves.length;
		int[][] row = new int[2][3];
		int[][] col = new int[2][3];
		int[][] diagonal = new int[2][2];
		for(int i = 0; i < N; i++){
			int r = moves[i][0];
			int c = moves[i][1];
    		row[i % 2][r]++;
    		col[i % 2][c]++;
    		if(r == c){
    			diagonal[i % 2][0]++;
    		}
    		if(r == 2 - c){
    			diagonal[i % 2][1]++;
    		}
    		if(row[i % 2][r] == 3 || col[i % 2][c] == 3 || diagonal[i % 2][0] == 3 || diagonal[i % 2][1] == 3){
    			return i % 2 == 0?"A":"B";
    		}
    	}
    	if(N == 9){
    		return "Draw";
    	}
    	else{
    		return "Pending";
    	}

	}
    public String tictactoe1(int[][] moves) {
    	int N = moves.length;
    	int[][] state = new int[3][3];
    	for(int i = 0; i < N; i++){
    		state[moves[i][0]][moves[i][1]] = 2 - i % 2;
    	}
    	
    	// System.out.println(Arrays.deepToString(state));
    	for(int i = 0; i < 3; i++){
    		boolean win = true;
    		int winner = 0;
    		for(int j = 0; j < 3; j++){
    			winner = state[i][j];
    			if(j + 1 < 3){
    				if(state[i][j] == 0 || state[i][j] != state[i][j + 1]){
    					win = false;
    					break;
    				}
    			}
    		}
    		if(win){
    			return winner == 2 ? "A" : "B";	
    		}
    	}

    	for(int i = 0; i < 3; i++){
    		boolean win = true;
    		int winner = 0;
    		for(int j = 0; j < 3; j++){
    			winner = state[j][i];
    			if(j + 1 < 3){
    				if(state[j][i] == 0 || state[j][i] != state[j + 1][i]){
    					win = false;
    					break;
    				}
    			}
    		}
    		if(win){
    			return winner == 2 ? "A" : "B";	
    		}
    	}

		
		boolean win = true;
    	int winner = 0;
    	for(int j = 0; j < 3; j++){
			winner = state[j][j];
			if(j + 1 < 3){
				if(state[j][j] == 0 || state[j][j] != state[j + 1][j + 1]){
					win = false;
					break;
				}
			}
    	}
    	if(win){
			return winner == 2 ? "A" : "B";	
		}
    	
    	win = true;
    	for(int j = 0; j < 3; j++){
			winner = state[j][2 - j];
			if(j + 1 < 3){
				if(state[j][2 - j] == 0 || state[j][2 - j] != state[j + 1][1 - j]){
					win = false;
					break;
				}
			}
    	}
		if(win){
    		return winner == 2 ? "A" : "B";	
    	}
    	
    	if(moves.length == 9){
    		return "Draw";
    	}
    	else{
    		return "Pending";
    	}
    }

}
// @lc code=end
