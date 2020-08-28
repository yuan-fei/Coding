/*
 * @lc app=leetcode id=1563 lang=java
 *
 * [1563] Stone Game V
 *
 * https://leetcode.com/problems/stone-game-v/description/
 *
 * algorithms
 * Hard (42.03%)
 * Likes:    88
 * Dislikes: 21
 * Total Accepted:    4.8K
 * Total Submissions: 11.3K
 * Testcase Example:  '[6,2,3,4,5,5]'
 *
 * There are several stones arranged in a row, and each stone has an associated
 * value which is an integer given in the array stoneValue.
 * 
 * In each round of the game, Alice divides the row into two non-empty rows
 * (i.e. left row and right row), then Bob calculates the value of each row
 * which is the sum of the values of all the stones in this row. Bob throws
 * away the row which has the maximum value, and Alice's score increases by the
 * value of the remaining row. If the value of the two rows are equal, Bob lets
 * Alice decide which row will be thrown away. The next round starts with the
 * remaining row.
 * 
 * The game ends when there is only one stone remaining. Alice's is initially
 * zero.
 * 
 * Return the maximum score that Alice can obtain.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5].
 * The left row has the value 11 and the right row has value 14. Bob throws
 * away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob
 * throws away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2],
 * [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The
 * game ends because only one stone is remaining in the row.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: stoneValue = [4]
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= stoneValue.length <= 500
 * 1 <= stoneValue[i] <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
	int[] stoneValue;
	int[] prefSum;
	int[][] cache;
    public int stoneGameV(int[] stoneValue) {
        this.stoneValue = stoneValue;
        int n = stoneValue.length;
        prefSum = new int[n + 1];
        for(int i = 1; i <= n; i++){
        	prefSum[i] = prefSum[i - 1] + stoneValue[i - 1];
        }
        cache = new int[n + 1][n + 1];
        int ret = dfs(0, n);
        // System.out.println(Arrays.deepToString(cache));
        return ret;
    }

    int dfs(int start, int end){
    	if(start + 1 == end){
    		return 0;
    	}
    	if(cache[start][end] == 0){
    		int total = prefSum[end] - prefSum[start];
    		int left = 0;
    		int max = 0;
    		for(int i = start; i < end - 1; i++){
    			left += stoneValue[i];
    			if(left < total - left){
    				max = Math.max(max, dfs(start, i + 1) + left);
    			}
    			else if(left > total - left){
    				max = Math.max(max, dfs(i + 1, end) + total - left);	
    			}
    			else{
    				max = Math.max(max, Math.max(dfs(start, i + 1) + left, dfs(i + 1, end) + total - left));		
    			}
    		}
    		cache[start][end] = max;
    	}
    	// System.out.println(start + ", " + end + " = " + cache[start][end]);
    	return cache[start][end];
    }
}
// @lc code=end
