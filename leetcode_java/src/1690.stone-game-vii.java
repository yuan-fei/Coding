/*
 * @lc app=leetcode id=1690 lang=java
 *
 * [1690] Stone Game VII
 *
 * https://leetcode.com/problems/stone-game-vii/description/
 *
 * algorithms
 * Medium (44.89%)
 * Likes:    132
 * Dislikes: 60
 * Total Accepted:    4.7K
 * Total Submissions: 10.4K
 * Testcase Example:  '[5,3,1,4,2]'
 *
 * Alice and Bob take turns playing a game, with Alice starting first.
 * 
 * There are n stones arranged in a row. On each player's turn, they can remove
 * either the leftmost stone or the rightmost stone from the row and receive
 * points equal to the sum of the remaining stones' values in the row. The
 * winner is the one with the higher score when there are no stones left to
 * remove.
 * 
 * Bob found that he will always lose this game (poor Bob, he always loses), so
 * he decided to minimize the score's difference. Alice's goal is to maximize
 * the difference in the score.
 * 
 * Given an array of integers stones where stones[i] represents the value of
 * the i^th stone from the left, return the difference in Alice and Bob's score
 * if they both play optimally.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: stones = [5,3,1,4,2]
 * Output: 6
 * Explanation: 
 * - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0,
 * stones = [5,3,1,4].
 * - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones =
 * [3,1,4].
 * - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones =
 * [1,4].
 * - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
 * - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
 * The score difference is 18 - 12 = 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: stones = [7,90,5,1,100,10,10,2]
 * Output: 122
 * 
 * 
 * Constraints:
 * 
 * 
 * n == stones.length
 * 2 <= n <= 1000
 * 1 <= stones[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        pSum = new int[n + 1];
        dp = new int[n][n];
        for(int i = 0; i < n; i++){
        	Arrays.fill(dp[i], MIN);
        }
        for(int i = 1; i <= n; i++){
        	pSum[i] += pSum[i - 1] + stones[i - 1];
        }
        return rec(0, n - 1, n);
    }

    int[][] dp;
    int[] pSum;
    int MIN = -1000005;
    int rec(int start, int end, int n){
    	// System.out.println(start + ", " + end);
    	if(start == end){
    		return 0;
    	}
    	if(dp[start][end] == MIN){
    		if((n - (end - start + 1)) % 2 == 0){
    			//Alice
    			dp[start][end] = Math.max(rec(start + 1, end, n) + pSum[end + 1] - pSum[start + 1], rec(start, end - 1, n) + pSum[end] - pSum[start]);
    		}
    		else{
    			//Bob
    			dp[start][end] = Math.min(rec(start + 1, end, n) - (pSum[end + 1] - pSum[start + 1]), rec(start, end - 1, n) - (pSum[end] - pSum[start]));
    		}
    	}
    	return dp[start][end];
    }
}
// @lc code=end
