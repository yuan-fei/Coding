/*
 * @lc app=leetcode id=1900 lang=java
 *
 * [1900] The Earliest and Latest Rounds Where Players Compete
 *
 * https://leetcode.com/problems/the-earliest-and-latest-rounds-where-players-compete/description/
 *
 * algorithms
 * Hard (45.38%)
 * Likes:    77
 * Dislikes: 9
 * Total Accepted:    1.8K
 * Total Submissions: 4K
 * Testcase Example:  '11\n2\n4'
 *
 * There is a tournament where n players are participating. The players are
 * standing in a single row and are numbered from 1 to n based on their initial
 * standing position (player 1 is the first player in the row, player 2 is the
 * second player in the row, etc.).
 * 
 * The tournament consists of multiple rounds (starting from round number 1).
 * In each round, the i^th player from the front of the row competes against
 * the i^th player from the end of the row, and the winner advances to the next
 * round. When the number of players is odd for the current round, the player
 * in the middle automatically advances to the next round.
 * 
 * 
 * For example, if the row consists of players 1, 2, 4, 6, 7
 * 
 * 
 * Player 1 competes against player 7.
 * Player 2 competes against player 6.
 * Player 4 automatically advances to the next round.
 * 
 * 
 * 
 * 
 * After each round is over, the winners are lined back up in the row based on
 * the original ordering assigned to them initially (ascending order).
 * 
 * The players numbered firstPlayer and secondPlayer are the best in the
 * tournament. They can win against any other player before they compete
 * against each other. If any two other players compete against each other,
 * either of them might win, and thus you may choose the outcome of this
 * round.
 * 
 * Given the integers n, firstPlayer, and secondPlayer, return an integer array
 * containing two values, the earliest possible round number and the latest
 * possible round number in which these two players will compete against each
 * other, respectively.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 11, firstPlayer = 2, secondPlayer = 4
 * Output: [3,4]
 * Explanation:
 * One possible scenario which leads to the earliest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 2, 3, 4, 5, 6, 11
 * Third round: 2, 3, 4
 * One possible scenario which leads to the latest round number:
 * First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
 * Second round: 1, 2, 3, 4, 5, 6
 * Third round: 1, 2, 4
 * Fourth round: 2, 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 5, firstPlayer = 1, secondPlayer = 5
 * Output: [1,1]
 * Explanation: The players numbered 1 and 5 compete in the first round.
 * There is no way to make them compete in any other round.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 28
 * 1 <= firstPlayer < secondPlayer <= n
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][][][] cache = new int[29][29][29][2];
    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
    	if(firstPlayer + secondPlayer == n + 1){
    		return new int[]{1, 1};
    	}
    	if(cache[n][firstPlayer][secondPlayer][1] == 0){
	    	int[] ret = new int[]{n, 0};
	    	int fi = firstPlayer;
	    	int se = secondPlayer;
	    	if(firstPlayer > n / 2 && secondPlayer > n / 2){
	    		fi = n + 1 - secondPlayer;
	    		se = n + 1 - firstPlayer;
	    	}
	    	else if(firstPlayer <= n / 2 && secondPlayer > n / 2){
	    		if(n + 1 - secondPlayer < firstPlayer){
	    			fi = n + 1 - secondPlayer;
	    			se = n + 1 - firstPlayer;
	    		}
	    	}
	    	for(int d1 = 1; d1 <= fi; d1++){
	    		int nextFi = d1;
	    		for(int d2 = 1; d2 <= Math.min(se, n + 1 - se) - fi; d2++){
	    			int nextSe = nextFi + d2 + (se - Math.min(se, n + 1 - se)) / 2;
	    			int[] cur = earliestAndLatest((n + 1) / 2, nextFi, nextSe);
	    			ret[0] = Math.min(ret[0], 1 + cur[0]);
	    			ret[1] = Math.max(ret[1], 1 + cur[1]);
	    		}
	    	}
	    	cache[n][firstPlayer][secondPlayer] = ret;
	    	// System.out.println(Arrays.asList(n, firstPlayer, secondPlayer, ret[0], ret[1]));	
    	}
    	return cache[n][firstPlayer][secondPlayer];
    }

}
// @lc code=end
