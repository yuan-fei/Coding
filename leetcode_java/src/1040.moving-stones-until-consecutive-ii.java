/*
 * @lc app=leetcode id=1040 lang=java
 *
 * [1040] Moving Stones Until Consecutive II
 *
 * https://leetcode.com/problems/moving-stones-until-consecutive-ii/description/
 *
 * algorithms
 * Medium (45.60%)
 * Total Accepted:    1.5K
 * Total Submissions: 3.4K
 * Testcase Example:  '[7,4,9]'
 *
 * On an infinite number line, the position of the i-th stone is given by
 * stones[i].  Call a stone an endpoint stone if it has the smallest or largest
 * position.
 * 
 * Each turn, you pick up an endpoint stone and move it to an unoccupied
 * position so that it is no longer an endpoint stone.
 * 
 * In particular, if the stones are at say, stones = [1,2,5], you cannot move
 * the endpoint stone at position 5, since moving it to any position (such as
 * 0, or 3) will still keep that stone as an endpoint stone.
 * 
 * The game ends when you cannot make any more moves, ie. the stones are in
 * consecutive positions.
 * 
 * When the game ends, what is the minimum and maximum number of moves that you
 * could have made?  Return the answer as an length 2 array: answer =
 * [minimum_moves, maximum_moves]
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [7,4,9]
 * Output: [1,2]
 * Explanation: 
 * We can move 4 -> 8 for one move to finish the game.
 * Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [6,5,4,3,10]
 * Output: [2,3]
 * We can move 3 -> 8 then 10 -> 7 to finish the game.
 * Or, we can move 3 -> 7, 4 -> 8, 5 -> 9 to finish the game.
 * Notice we cannot move 10 -> 2 to finish the game, because that would be an
 * illegal move.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [100,101,104,102,103]
 * Output: [0,0]
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 3 <= stones.length <= 10^4
 * 1 <= stones[i] <= 10^9
 * stones[i] have distinct values.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
	public int[] numMovesStonesII(int[] stones) {
    	int n = stones.length;
        Arrays.sort(stones);
        int max = Math.max(stones[n-2] - stones[0] - 1 - (n-3), stones[n-1] - stones[1] - 1 - (n-3));
        int left =0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
        	while(stones[i] - stones[left] + 1 > n){
        		left++;
        	}
        		
    		if((stones[i] - stones[left] + 1 == n-1) && i-left+1 == n-1){
    			min = Math.min(min, 2);
    		}
    		else{
    			min = Math.min(min, n-(i-left+1));	
    		}
        }
        
        return new int[]{min, max};
    }

    public int[] numMovesStonesII1(int[] stones) {
    	int n = stones.length;
        Arrays.sort(stones);
        int totalEmpty = stones[n-1] - stones[0] + 1 - n;
        int minLost = Math.min(stones[n-1] - stones[n-2] - 1, stones[1] - stones[0] - 1);
        int max = totalEmpty- minLost;
        int left =0;
        int min = Integer.MAX_VALUE;
        int step;
        for (int i = 0; i < n; i++) {
        	while(stones[i] - stones[left] + 1 > n){
        		step = n - (i - left);
        		if(step == 1 && (stones[i-1] - stones[left] + 1 == n-1) && (stones[1] - stones[0] - 1>1||stones[n-1] - stones[n-2] - 1>1)){
        			step++;
        		}
        		min = Math.min(min, step);
        		left++;
        	}
        }
        step = left;
        if(step == 1 && (stones[n-1] - stones[left] + 1 == n-1) && (stones[1] - stones[0] - 1>1||stones[n-1] - stones[n-2] - 1>1)){
			step++;
		}
        min = Math.min(min, step);
        return new int[]{min, max};
    }
}
