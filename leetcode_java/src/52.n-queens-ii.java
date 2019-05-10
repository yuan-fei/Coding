/*
 * [52] N-Queens II
 *
 * https://leetcode.com/problems/n-queens-ii/description/
 *
 * algorithms
 * Hard (47.36%)
 * Total Accepted:    95.6K
 * Total Submissions: 186.5K
 * Testcase Example:  '4'
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return the number of distinct solutions to the n-queens
 * puzzle.
 * 
 * Example:
 * 
 * 
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as
 * shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * 
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 
 * 
 */
class Solution {
    public int totalNQueens(int n) {
    	boolean[] c = new boolean[n]; // direction '/'
        boolean[] d1 = new boolean[2*n]; // diagonal '/'
        boolean[] d2 = new boolean[2*n]; // diagonal '\'
        return totalNQueensHelper(n, c, d1, d2, 0);
    }

    int totalNQueensHelper(int n, boolean[] c, boolean[] d1, boolean[] d2, int r){
    	if(r == n){
    		return 1;
    	}
    	else{
    		int total = 0;
    		for(int i = 0; i < n; i++){
    			//map -n~n to 0~2n
    			int id1 = n+i-r;
    			int id2 = i+r;
    			if(!c[i] && !d1[id1] && !d2[id2]){
    				c[i] = d1[id1] = d2[id2] = true;
    				total += totalNQueensHelper(n, c, d1, d2, r+1);
    				c[i] = d1[id1] = d2[id2] = false;
    			}
    		}
    		return total;
    	}
    	
    }
}
