/*
 * @lc app=leetcode id=1301 lang=java
 *
 * [1301] Number of Paths with Max Score
 *
 * https://leetcode.com/problems/number-of-paths-with-max-score/description/
 *
 * algorithms
 * Hard (31.27%)
 * Likes:    25
 * Dislikes: 1
 * Total Accepted:    1K
 * Total Submissions: 3.3K
 * Testcase Example:  '["E23","2X2","12S"]\r'
 *
 * You are given a square board of characters. You can move on the board
 * starting at the bottom right square marked with the character 'S'.
 * 
 * You need to reach the top left square marked with the character 'E'. The
 * rest of the squares are labeled either with a numeric character 1, 2, ..., 9
 * or with an obstacle 'X'. In one move you can go up, left or up-left
 * (diagonally) only if there is no obstacle there.
 * 
 * Return a list of two integers: the first integer is the maximum sum of
 * numeric characters you can collect, and the second is the number of such
 * paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
 * 
 * In case there is no path, return [0, 0].
 * 
 * 
 * Example 1:
 * Input: board = ["E23","2X2","12S"]
 * Output: [7,1]
 * Example 2:
 * Input: board = ["E12","1X1","21S"]
 * Output: [4,2]
 * Example 3:
 * Input: board = ["E11","XXX","11S"]
 * Output: [0,0]
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= board.length == board[i].length <= 100
 * 
 */

// @lc code=start
class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
    	int n = board.size();
    	int mod = 1000000007;
        int[][][] dp = new int[n][n][2];
        int[][] dir = {{0, 1}, {1, 0}, {1, 1}};
        dp[0][0] = new int[]{ 0, 1 };
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < n; j++){
        		if(board.get(i).charAt(j) != 'X'){
	        		for(int[] d: dir){
	        			int x = i + d[0];
	        			int y = j + d[1];
	        			if(x >= 0 && x < n && y >= 0 && y < n && board.get(x).charAt(y) != 'X'){
	        				int v = 0;
	        				if(board.get(x).charAt(y) != 'S'){
								v = board.get(x).charAt(y) - '0';
	        				}
	        				
	        				if(dp[x][y][0] < dp[i][j][0] + v){
	        					dp[x][y][0] = dp[i][j][0] + v;
	        					dp[x][y][1] = dp[i][j][1];
	        				}
	        				else if(dp[x][y][0] == dp[i][j][0] + v){
	        					dp[x][y][1] += dp[i][j][1];
	        					dp[x][y][1] = dp[x][y][1] % mod;
	        				}
	        			}
	        		}	
        		}
        	}
        }
        if(dp[n - 1][n - 1][1] == 0){
        	return new int[]{0, 0};
        }
        else{
        	return dp[n - 1][n - 1];	
        }
        
    }
}
// @lc code=end
