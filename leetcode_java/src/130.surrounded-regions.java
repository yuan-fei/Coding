/*
 * @lc app=leetcode id=130 lang=java
 *
 * [130] Surrounded Regions
 *
 * https://leetcode.com/problems/surrounded-regions/description/
 *
 * algorithms
 * Medium (24.02%)
 * Total Accepted:    163.9K
 * Total Submissions: 680.5K
 * Testcase Example:  '[["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]'
 *
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * Example:
 * 
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * 
 * After running your function, the board should be:
 * 
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * Explanation:
 * 
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on
 * the border of the board are not flipped to 'X'. Any 'O' that is not on the
 * border and it is not connected to an 'O' on the border will be flipped to
 * 'X'. Two cells are connected if they are adjacent cells connected
 * horizontally or vertically.
 * 
 */
class Solution {
    public void solve(char[][] board) {
    	if(board.length == 0){
    		return;
    	}
    	int M = board.length;
    	int N = board[0].length;
    	boolean[][] visited = new boolean[M][N];
    	for(int i = 0; i < M; i++){
    		for (int j = 0; j < N; j++) {
    			if(i == 0 || i == M - 1 || j == 0 || j == N - 1){
    				if(board[i][j] == 'O' && !visited[i][j]){
    					dfs(board, visited, i, j, false);	
    				}
    				
    			}
    		}
    	}
    	// System.out.println(Arrays.deepToString(visited));
    	for(int i = 0; i < M; i++){
    		for (int j = 0; j < N; j++) {
				if(board[i][j] == 'O' && !visited[i][j]){
					dfs(board, visited, i, j, true);	
				}
    		}
    	}
    }

    int[][] dir = {{0,1}, {1,0}, {-1,0}, {0,-1}};
    void dfs(char[][] board, boolean[][] visited, int x, int y, boolean mark){
    	int M = board.length;
    	int N = board[0].length;
    	visited[x][y] = true;
    	if(mark){
    		board[x][y] = 'X';
    	}

    	for (int[] d : dir) {
    		int xx = x + d[0];
    		int yy = y + d[1];
    		if(xx >= 0 && xx < M && yy >= 0 && yy < N && board[xx][yy] == 'O' && !visited[xx][yy]){
    			dfs(board, visited, xx, yy, mark);
    		}
    	}
    }
}
