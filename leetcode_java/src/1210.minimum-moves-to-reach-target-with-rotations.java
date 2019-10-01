/*
 * @lc app=leetcode id=1210 lang=java
 *
 * [1210] Minimum Moves to Reach Target with Rotations
 *
 * https://leetcode.com/problems/minimum-moves-to-reach-target-with-rotations/description/
 *
 * algorithms
 * Hard (39.60%)
 * Total Accepted:    1.8K
 * Total Submissions: 4.5K
 * Testcase Example:  '[[0,0,0,0,0,1],[1,1,0,0,1,0],[0,0,0,0,1,1],[0,0,1,0,1,0],[0,1,1,0,0,0],[0,1,1,0,0,0]]\r'
 *
 * In an n*n grid, there is a snake that spans 2 cells and starts moving from
 * the top left corner at (0, 0) and (0, 1). The grid has empty cells
 * represented by zeros and blocked cells represented by ones. The snake wants
 * to reach the lower right corner at (n-1, n-2) and (n-1, n-1).
 * 
 * In one move the snake can:
 * 
 * 
 * Move one cell to the right if there are no blocked cells there. This move
 * keeps the horizontal/vertical position of the snake as it is.
 * Move down one cell if there are no blocked cells there. This move keeps the
 * horizontal/vertical position of the snake as it is.
 * Rotate clockwise if it's in a horizontal position and the two cells under it
 * are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r,
 * c) and (r+1, c).
 * 
 * Rotate counterclockwise if it's in a vertical position and the two cells to
 * its right are both empty. In that case the snake moves from (r, c) and (r+1,
 * c) to (r, c) and (r, c+1).
 * 
 * 
 * 
 * Return the minimum number of moves to reach the target.
 * 
 * If there is no way to reach the target, return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: grid = [[0,0,0,0,0,1],
 * ⁠              [1,1,0,0,1,0],
 * [0,0,0,0,1,1],
 * [0,0,1,0,1,0],
 * [0,1,1,0,0,0],
 * [0,1,1,0,0,0]]
 * Output: 11
 * Explanation:
 * One possible solution is [right, right, rotate clockwise, right, down, down,
 * down, down, rotate counterclockwise, right, down].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: grid = [[0,0,1,1,1,1],
 * [0,0,0,0,1,1],
 * [1,1,0,0,0,1],
 * [1,1,1,0,0,1],
 * [1,1,1,0,0,1],
 * [1,1,1,0,0,0]]
 * Output: 9
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * It is guaranteed that the snake starts at empty cells.
 * 
 * 
 */
class Solution {
    public int minimumMoves(int[][] grid) {
    	int N = grid.length;
    	int[][][] state = new int[N][N][2];
    	int[][] shiftMoves = {{0,1},{1,0}};
    	int[][] rotateMoves = {{1,0},{0,1}};
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[]{0, 0, 0});
    	state[0][0][0] = 0;
    	int moves = 0;
    	while(!q.isEmpty()){
    		moves++;
			// printQueue(q);
    		for(int i = q.size(); i > 0; i--){
    			int[] cur = q.poll();
    			if(cur[0] == N - 1 && cur[1] == N - 2 && cur[2] == 0){
    				return state[cur[0]][cur[1]][cur[2]];
    			}
    			int[] htOffset = {1, 0};
    			if(cur[2] == 0){
    				htOffset = new int[]{0, 1};
    			}
    			for (int[] m : shiftMoves) {
    				int xt = cur[0] + m[0];
    				int yt = cur[1] + m[1];
    				int xh = cur[0] + htOffset[0] + m[0];
    				int yh = cur[1] + htOffset[1] + m[1];
    				if(xt >= 0 && xt < N && yt >= 0 && yt < N && xh >= 0 && xh < N && yh >= 0 && yh < N && grid[xt][yt] == 0 && grid[xh][yh] == 0 && state[xt][yt][cur[2]] == 0){
    					q.offer(new int[]{xt, yt, cur[2]});
    					state[xt][yt][cur[2]] = moves;
    				}
    			}

    			// rotate
				int xt = cur[0];
				int yt = cur[1];
				int xh = cur[0] + rotateMoves[cur[2]][0];
				int yh = cur[1] + rotateMoves[cur[2]][1];
				if(xt >= 0 && xt < N && yt >= 0 && yt < N && xh >= 0 && xh < N && yh >= 0 && yh < N && grid[xh][yh] == 0 && grid[xt + 1][yt + 1] == 0 && state[xt][yt][1 - cur[2]] == 0){
					q.offer(new int[]{xt, yt, 1 - cur[2]});
					state[xt][yt][1 - cur[2]] = moves;
				}
    		}
    	}
    	return -1;
    }

    void printQueue(Queue<int[]> q){
    	String s = "";
    	for (int[] e : q) {
    		s += Arrays.toString(e);
    	}
    	System.out.println(s);
    }

}
