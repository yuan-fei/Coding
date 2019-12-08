/*
 * @lc app=leetcode id=1284 lang=java
 *
 * [1284] Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
 *
 * https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/description/
 *
 * algorithms
 * Hard (68.19%)
 * Likes:    7
 * Dislikes: 4
 * Total Accepted:    1.2K
 * Total Submissions: 1.7K
 * Testcase Example:  '[[0,0],[0,1]]\r'
 *
 * Given a m x n binary matrix mat. In one step, you can choose one cell and
 * flip it and all the four neighbours of it if they exist (Flip is changing 1
 * to 0 and 0 to 1). A pair of cells are called neighboors if they share one
 * edge.
 * 
 * Return the minimum number of steps required to convert mat to a zero matrix
 * or -1 if you cannot.
 * 
 * Binary matrix is a matrix with all cells equal to 0 or 1 only.
 * 
 * Zero matrix is a matrix with all cells equal to 0.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally
 * (1, 1) as shown.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We don't need to change it.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: mat = [[1,1,1],[1,0,1],[0,0,0]]
 * Output: 6
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix can't be a zero matrix
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == mat.length
 * n == mat[0].length
 * 1 <= m <= 3
 * 1 <= n <= 3
 * mat[i][j] is 0 or 1.
 * 
 * 
 */

// @lc code=start
class Solution {
	static int[][] dir = {{0, 1},{1, 0},{-1, 0},{0, -1}};
    public int minFlips(int[][] mat) {
        Queue<State> q = new LinkedList<>();
        Set<Integer> s = new HashSet<>();
        State zero = new State(mat);
        q.offer(zero);
        s.add(zero.getEncoding());
        int cnt = 0;
        while(!q.isEmpty()){
        	for(int c = q.size(); c > 0; c--){
        		State cur = q.poll();
        		if(cur.getEncoding() == 0){
        			return cnt;
        		}
        		for(int i = 0; i < mat.length; i++){
	    			for(int j = 0; j < mat[0].length; j++){
	    				State next = cur.getNextState(i, j);
	    				int code = next.getEncoding();
	    				if(!s.contains(code)){
	    					q.offer(next);
	    					s.add(code);
	    				}
	    			}
	    		}
        	}
        	cnt++;
        }
        return -1;
    }

    static class State {
    	int[][] m;
    	State(int[][] mat){
    		m = mat;
    	}

    	int getEncoding(){
    		int c = 0;
    		for(int i = 0; i < m.length; i++){
    			c = c * 10 + getEncoding(m[i]);
    		}
    		return c;
    	}

    	int getEncoding(int[] nums){
    		int c = 0;
    		for(int n : nums){
    			c = (c << 1) + n;
    		}
    		return c;
    	}
    	int[][] dir = {{0, 1},{1, 0},{-1, 0},{0, -1}};
    	State getNextState(int r, int c){
    		int[][] nm = new int[m.length][m[0].length];
    		for(int i = 0; i < m.length; i++){
    			for(int j = 0; j < m[0].length; j++){
    				nm[i][j] = m[i][j];
    			}
    		}
    		nm[r][c] = 1 - nm[r][c];
    		for(int i = 0; i < 4; i++){
    			int nr = r + dir[i][0];
    			int nc = c + dir[i][1];
    			if(nr >= 0 && nr < nm.length && nc >= 0 && nc < nm[0].length){
    				nm[nr][nc] = 1 - nm[nr][nc];
    			}
    		}
    		return new State(nm);
    	}
    }


}
// @lc code=end
