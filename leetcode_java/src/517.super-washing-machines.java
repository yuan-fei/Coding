/*
 * @lc app=leetcode id=517 lang=java
 *
 * [517] Super Washing Machines
 *
 * https://leetcode.com/problems/super-washing-machines/description/
 *
 * algorithms
 * Hard (38.31%)
 * Likes:    334
 * Dislikes: 148
 * Total Accepted:    16.9K
 * Total Submissions: 44.2K
 * Testcase Example:  '[1,0,5]'
 *
 * You have n super washing machines on a line. Initially, each washing machine
 * has some dresses or is empty. 
 * 
 * 
 * For each move, you could choose any m (1 ≤ m ≤ n) washing machines, and pass
 * one dress of each washing machine to one of its adjacent washing machines
 * at the same time .  
 * 
 * Given an integer array representing the number of dresses in each washing
 * machine from left to right on the line, you should find the minimum number
 * of moves to make all the washing machines have the same number of dresses.
 * If it is not possible to do it, return -1.
 * 
 * Example1
 * 
 * Input: [1,0,5]
 * 
 * Output: 3
 * 
 * Explanation: 
 * 1st move:    1     0     1     1     4
 * 2nd move:    1     2     1     3    
 * 3rd move:    2     1     2     2     2   
 * 
 * 
 * Example2
 * 
 * Input: [0,3,0]
 * 
 * Output: 2
 * 
 * Explanation: 
 * 1st move:    0     1     2     0    
 * 2nd move:    1     2 --> 0    =>    1     1     1     
 * 
 * 
 * Example3
 * 
 * Input: [0,2,0]
 * 
 * Output: -1
 * 
 * Explanation: 
 * It's impossible to make all the three washing machines have the same number
 * of dresses. 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The range of n is [1, 10000].
 * The range of dresses number in a super washing machine is [0, 1e5].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMinMoves(int[] machines) {
    	int total = 0;
    	int n = machines.length;
        for(int x : machines){
        	total += x;
        }
        if(total % n != 0){
        	return -1;
        }
        int avg = total / n;
        int moveRight = 0;
        int maxDiff = 0;
        for(int x : machines){
			moveRight = x + moveRight - avg;
        	maxDiff = Math.max(maxDiff, Math.max(Math.abs(moveRight), x - avg));
        }
        return maxDiff;
    }
}
// @lc code=end
