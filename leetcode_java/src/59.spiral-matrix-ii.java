/*
 * [59] Spiral Matrix II
 *
 * https://leetcode.com/problems/spiral-matrix-ii/description/
 *
 * algorithms
 * Medium (41.66%)
 * Total Accepted:    131.7K
 * Total Submissions: 286.7K
 * Testcase Example:  '3'
 *
 * Given a positive integer n, generate a square matrix filled with elements
 * from 1 to n2 in spiral order.
 * 
 * Example:
 * 
 * 
 * Input: 3
 * Output:
 * [
 * ⁠[ 1, 2, 3 ],
 * ⁠[ 8, 9, 4 ],
 * ⁠[ 7, 6, 5 ]
 * ]
 * 
 * 
 */
class Solution {
    public int[][] generateMatrix(int n) {

    	int[] dx = new int[]{0,1,0,-1};
    	int[] dy = new int[]{1,0,-1,0};
    	int[][] m = new int[n][n];
    	int i = 0;
    	int x=0,y=-1;
    	int direction = 0;
        while(i < n*n){
        	// if the next position is not available, then change direction
        	while(available(n, m, x + dx[direction], y + dy[direction])){
        		x += dx[direction];
        		y += dy[direction];
        		m[x][y] = ++i;
        	}
        	direction++;
        	direction%=4;
        }
        
        return m;
    }

	private boolean available(int n, int[][] m, int i, int j){
		return (i>=0&&i<n&&j>=0&&j<n&&m[i][j]==0);
	}
}
