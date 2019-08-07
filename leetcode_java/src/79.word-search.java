/*
 * @lc app=leetcode id=79 lang=java
 *
 * [79] Word Search
 *
 * https://leetcode.com/problems/word-search/description/
 *
 * algorithms
 * Medium (31.24%)
 * Total Accepted:    306.6K
 * Total Submissions: 961.4K
 * Testcase Example:  '[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]\n"ABCCED"'
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * Example:
 * 
 * 
 * board =
 * [
 * ⁠ ['A','B','C','E'],
 * ⁠ ['S','F','C','S'],
 * ⁠ ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 * 
 * 
 */
class Solution {
    public boolean exist(char[][] board, String word) {
    	int n = board.length;
    	int m = board[0].length;
        for(int i = 0; i < n; i++){
        	for (int j = 0; j < m; j++ ) {
        		if(board[i][j] == word.charAt(0) && dfs(board, word, i, j, 0, new boolean[n][m])){
        			return true;
        		}
        	}
        }
        return false;
    }

	int[] dx = new int[]{0,0,-1,1};
	int[] dy = new int[]{-1,1,0,0};
    boolean dfs(char[][] board, String word, int i, int j, int pos, boolean[][] visited){
    	int n = board.length;
    	int m = board[0].length;
    	visited[i][j] = true;
    	if(board[i][j] == word.charAt(pos)){
    		
    		if(pos + 1 == word.length()){
    			return true;
    		}
    		for(int k = 0; k < 4; k++){
	    		int x = dx[k] + i;
	    		int y = dy[k] + j;
	    		if(x>=0&&x<n&&y>=0&&y<m&&!visited[x][y]&&dfs(board, word, x, y, pos+1, visited)){
	    			return true;
	    		}
    		}
    	}
    	visited[i][j] = false;
    	return false;
    }
}
