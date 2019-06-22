/*
 * @lc app=leetcode id=464 lang=java
 *
 * [464] Can I Win
 *
 * https://leetcode.com/problems/can-i-win/description/
 *
 * algorithms
 * Medium (27.19%)
 * Total Accepted:    36.2K
 * Total Submissions: 132.6K
 * Testcase Example:  '10\n11'
 *
 * In the "100 game," two players take turns adding, to a running total, any
 * integer from 1..10. The player who first causes the running total to reach
 * or exceed 100 wins. 
 * 
 * What if we change the game so that players cannot re-use integers? 
 * 
 * For example, two players might take turns drawing from a common pool of
 * numbers of 1..15 without replacement until they reach a total >= 100.
 * 
 * Given an integer maxChoosableInteger and another integer desiredTotal,
 * determine if the first player to move can force a win, assuming both players
 * play optimally. 
 * 
 * You can always assume that maxChoosableInteger will not be larger than 20
 * and desiredTotal will not be larger than 300.
 * 
 * 
 * Example
 * 
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 * 
 * Output:
 * false
 * 
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers
 * from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >=
 * desiredTotal.
 * Same with other integers chosen by the first player, the second player will
 * always win.
 * 
 * 
 */
class Solution {

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    	if(maxChoosableInteger*(maxChoosableInteger+1)/2<desiredTotal){
    		return false;
    	}
    	if(desiredTotal==0){
    		return true;
    	}
    	dp = new int[(1<<maxChoosableInteger)+2];
    	return canIWin(maxChoosableInteger,desiredTotal,0);
    }

	int[] dp; 
    private boolean canIWin(int maxChoosableInteger, int desiredTotal, int mask) {
    	if(desiredTotal<=0){
    		dp[mask] = -1;
    		// System.out.println(mask+"| "+dp[mask]);
    		return false;
    	}
    	else {
    		if(dp[mask]==0){
    			dp[mask] = -1;
    			for(int i = 0; i < maxChoosableInteger; i++){
    				if(((mask>>i)&1)==0){
    					if(!canIWin(maxChoosableInteger,desiredTotal - i - 1, mask|(1<<i))){
	    					dp[mask] = 1;
	    					break;
	    				}	
    				}
    			}	
    		}
    		// System.out.println(mask+", "+dp[mask]);
    		return dp[mask]==1;
    	}
    }
}
