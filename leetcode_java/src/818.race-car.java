/*
 * @lc app=leetcode id=818 lang=java
 *
 * [818] Race Car
 *
 * https://leetcode.com/problems/race-car/description/
 *
 * algorithms
 * Hard (35.81%)
 * Total Accepted:    11.3K
 * Total Submissions: 31.6K
 * Testcase Example:  '3'
 *
 * Your car starts at position 0 and speed +1 on an infinite number line.
 * (Your car can go into negative positions.)
 * 
 * Your car drives automatically according to a sequence of instructions A
 * (accelerate) and R (reverse).
 * 
 * When you get an instruction "A", your car does the following: position +=
 * speed, speed *= 2.
 * 
 * When you get an instruction "R", your car does the following: if your speed
 * is positive then speed = -1 , otherwise speed = 1.  (Your position stays the
 * same.)
 * 
 * For example, after commands "AAR", your car goes to positions 0->1->3->3,
 * and your speed goes to 1->2->4->-1.
 * 
 * Now for some target position, say the length of the shortest sequence of
 * instructions to get there.
 * 
 * 
 * Example 1:
 * Input: 
 * target = 3
 * Output: 2
 * Explanation: 
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * 
 * 
 * 
 * Example 2:
 * Input: 
 * target = 6
 * Output: 5
 * Explanation: 
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= target <= 10000.
 * 
 * 
 */
class Solution {
    public int racecar(int target) {
    	int[] dp  =new int[target+1];
    	boolean[] p = new boolean[target+1];
    	boolean[] n = new boolean[target+1];
    	int b = 1;
    	for(int i = 1; i <= target; i++){
    		if(i==(1<<b)-1){
    			dp[i] = b++;
    			p[i] = true;
    		}
    		else{
    			dp[i] = b + 1 + dp[(1<<b)-1-i];
    			p[i] = n[(1<<b)-1-i];
    			n[i] = p[(1<<b)-1-i];
    			for(int j = 1; j < i; j++){
    				int minDelta = 1;
    				if(!n[j]){
    					minDelta = 2;
    				}
    				if(dp[i] > dp[j] + dp[i-j] + minDelta){
    					dp[i] = dp[j] + dp[i-j] + minDelta;
    					p[i] = false;
    					n[i] = false;
    				}
    				if(dp[i] == dp[j] + dp[i-j] + minDelta){
    					p[i] = p[i]|p[i-j];
    					n[i] = n[i]|n[i-j];
    				}
    			}
    		}
    	}
    	// System.out.println(Arrays.toString(dp));
    	// System.out.println(Arrays.toString(p));
    	// System.out.println(Arrays.toString(n));
    	return dp[target];
    }
}
