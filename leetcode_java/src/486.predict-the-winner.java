/*
 * @lc app=leetcode id=486 lang=java
 *
 * [486] Predict the Winner
 *
 * https://leetcode.com/problems/predict-the-winner/description/
 *
 * algorithms
 * Medium (46.78%)
 * Total Accepted:    50.4K
 * Total Submissions: 107.8K
 * Testcase Example:  '[1,5,2]'
 *
 * Given an array of scores that are non-negative integers. Player 1 picks one
 * of the numbers from either end of the array followed by the player 2 and
 * then player 1 and so on. Each time a player picks a number, that number will
 * not be available for the next player. This continues until all the scores
 * have been chosen. The player with the maximum score wins. 
 * 
 * Given an array of scores, predict whether player 1 is the winner. You can
 * assume each player plays to maximize his score. 
 * 
 * Example 1:
 * 
 * Input: [1, 5, 2]
 * Output: False
 * Explanation: Initially, player 1 can choose between 1 and 2. If he chooses 2
 * (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5,
 * then player 1 will be left with 1 (or 2). So, final score of player 1 is 1 +
 * 2 = 3, and player 2 is 5. Hence, player 1 will never be the winner and you
 * need to return False.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [1, 5, 233, 7]
 * Output: True
 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between
 * 5 and 7. No matter which number player 2 choose, player 1 can choose
 * 233.Finally, player 1 has more score (234) than player 2 (12), so you need
 * to return True representing player1 can win.
 * 
 * 
 * 
 * Note:
 * 
 * 1 
 * Any scores in the given array are non-negative integers and will not exceed
 * 10,000,000.
 * If the scores of both players are equal, then player 1 is still the winner.
 * 
 * 
 */
class Solution {
    public boolean PredictTheWinner1(int[] nums) {
    	int n = nums.length;
    	int[] dp = new int[n+1];
    	for(int l = 1; l <= n; l++){
    		int[] newDp = new int[n+1];
    		for(int j = 0; j+l-1 < n; j++){
    			if((n-l)%2==0){
    				newDp[j]=Math.max(dp[j] + nums[j+l-1], nums[j] + dp[j+1]);
    			}
    			else{
    				newDp[j]=Math.min(dp[j] - nums[j+l-1], -nums[j] + dp[j+1]);	
    			}
    		}
    		dp = newDp;
    		//System.out.println(Arrays.toString(dp));
    	}
    	return dp[0]>=0;
    }

    int[][] cache;
    public boolean PredictTheWinner(int[] nums) {
    	cache = new int[nums.length][nums.length];
    	for(int i = 0; i < cache.length; i++){
    		for(int j = 0;j<cache[i].length; j++){
    			cache[i][j] = Integer.MAX_VALUE;
    		}
    	}
    	return maxDiff(nums, 0 , nums.length-1)>=0;
    }

    private int maxDiff(int[] nums, int start, int end){
    	if(start==end){
    		cache[start][end] = nums[start];
    	}
    	else{
    		if(cache[start][end]==Integer.MAX_VALUE){
    			cache[start][end] = Math.max(nums[start] - maxDiff(nums, start+1, end), nums[end] - maxDiff(nums, start, end-1));	
    		}
    	}
    	return cache[start][end];
    }
}
