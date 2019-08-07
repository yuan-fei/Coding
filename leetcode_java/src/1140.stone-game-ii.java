/*
 * @lc app=leetcode id=1140 lang=java
 *
 * [1140] Stone Game II
 *
 * https://leetcode.com/problems/stone-game-ii/description/
 *
 * algorithms
 * Medium (60.73%)
 * Total Accepted:    2.8K
 * Total Submissions: 4.6K
 * Testcase Example:  '[2,7,9,4,4]'
 *
 * Alex and Lee continue their games with piles of stones.  There are a number
 * of piles arranged in a row, and each pile has a positive integer number of
 * stones piles[i].  The objective of the game is to end with the most
 * stones. 
 * 
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 * 
 * On each player's turn, that player can take all the stones in the first X
 * remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * 
 * The game continues until all the stones have been taken.
 * 
 * Assuming Alex and Lee play optimally, return the maximum number of stones
 * Alex can get.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alex takes one pile at the beginning, Lee takes two piles,
 * then Alex takes 2 piles again. Alex can get 2 + 4 + 4 = 10 piles in total.
 * If Alex takes two piles at the beginning, then Lee can take all three piles
 * left. In this case, Alex get 2 + 7 = 9 piles in total. So we return 10 since
 * it's larger. 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= piles.length <= 100
 * 1 <= piles[i] <= 10 ^ 4
 * 
 */
class Solution {
    public int stoneGameII(int[] piles) {
		int n = piles.length;
		int[][] dp = new int[n + 1][n + 1];
		int[] ss = new int[n + 1];
		for (int i = n - 1; i >= 0; i--) {
			ss[i] = ss[i + 1] + piles[i];
		}

		Deque<Integer>[] dq = new Deque[n + 1];
		for (int i = 0; i < dq.length; i++) {
			dq[i] = new LinkedList<Integer>();
		}

		for (int i = n; i >= 0; i--) {
			Deque<Integer> dqi = new LinkedList<>();
			for (int j = 1; j <= n; j++) {
				while (!dq[j].isEmpty() && dq[j].peekFirst() - i > j) {
					dq[j].pollFirst();
				}
				dp[i][j] = ss[i];
				if (!dq[j].isEmpty()) {
					dp[i][j] -= dp[dq[j].peekFirst()][j];
				}
				for (int lastJ = 2 * j - 1; lastJ <= 2 * j && i + lastJ <= n; lastJ++) {
					while (!dqi.isEmpty() && dp[i + dqi.peekLast()][dqi.peekLast()] >= dp[i + lastJ][lastJ]) {
						dqi.pollLast();
					}
					dqi.offerLast(lastJ);
				}
				while (!dqi.isEmpty() && dqi.peekFirst() < j + 1) {
					dqi.pollFirst();
				}
				if (!dqi.isEmpty()) {
					int k = dqi.peekFirst();
					dp[i][j] = Math.max(dp[i][j], ss[i] - dp[i + k][k]);
				}

				while (!dq[j].isEmpty() && dp[dq[j].peekLast()][j] >= dp[i][j]) {
					dq[j].pollLast();
				}
				dq[j].offerLast(i);
			}
			// System.out.println(Arrays.toString(dp[i]));
		}
		return dp[0][1];
	}
    
    public int stoneGameIISlow(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n+1][n+1];
        int[] ss = new int[n+1];
        for(int i = n-1; i>=0; i--){
            ss[i]=ss[i+1]+piles[i];
        }
        
        
        for(int i = n-1; i>=0; i--){
            for(int j = 1; j <= n; j++){
                for(int k = 1; k <= j; k++){
                    if(i+k<=n){
                        dp[i][j] =  Math.max(dp[i][j], ss[i] - dp[i+k][j]);    
                    }
                }
                for(int k = j+1; k <= 2*j; k++){
                    if(i+k<=n){
                        dp[i][j] =  Math.max(dp[i][j], ss[i] - dp[i+k][k]);
                    }
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][1];
    }
    
}
