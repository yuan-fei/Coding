/*
 * @lc app=leetcode id=650 lang=java
 *
 * [650] 2 Keys Keyboard
 *
 * https://leetcode.com/problems/2-keys-keyboard/description/
 *
 * algorithms
 * Medium (49.73%)
 * Likes:    1523
 * Dislikes: 113
 * Total Accepted:    67.8K
 * Total Submissions: 136.1K
 * Testcase Example:  '3'
 *
 * Initially on a notepad only one character 'A' is present. You can perform
 * two operations on this notepad for each step:
 * 
 * 
 * Copy All: You can copy all the characters present on the notepad (partial
 * copy is not allowed).
 * Paste: You can paste the characters which are copied last time.
 * 
 * 
 * 
 * 
 * Given a number n. You have to get exactly n 'A' on the notepad by performing
 * the minimum number of steps permitted. Output the minimum number of steps to
 * get n 'A'.
 * 
 * Example 1:
 * 
 * 
 * Input: 3
 * Output: 3
 * Explanation:
 * Intitally, we have one character 'A'.
 * In step 1, we use Copy All operation.
 * In step 2, we use Paste operation to get 'AA'.
 * In step 3, we use Paste operation to get 'AAA'.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The n will be in the range [1, 1000].
 * 
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int minSteps(int n) {
    	int[] dp = new int[n + 1];
    	Arrays.fill(dp, 5 * n);
    	dp[1] = 0;
    	for(int i = 2; i <= n; i++){
    		for(int f = 1; f * f <= i; f++){
    			if(i % f == 0){
    				if(i % f == f){
    					dp[i] = Math.min(dp[i], dp[f] + i / f - 1);	
    				}
    				else{
    					dp[i] = Math.min(dp[i], dp[f] + i / f);	
    					dp[i] = Math.min(dp[i], dp[i / f] + f);	
    				}
    			}
    		}
    	}
        return dp[n];
    }
}
// @lc code=end
