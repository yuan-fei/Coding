/*
 * @lc app=leetcode id=1269 lang=java
 *
 * [1269] Number of Ways to Stay in the Same Place After Some Steps
 *
 * https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/description/
 *
 * algorithms
 * Hard (35.75%)
 * Likes:    38
 * Dislikes: 2
 * Total Accepted:    1.8K
 * Total Submissions: 5K
 * Testcase Example:  '3\n2'
 *
 * You have a pointer at index 0 in an array of size arrLen. At each step, you
 * can move 1 position to the left, 1 position to the right in the array or
 * stay in the same place  (The pointer should not be placed outside the array
 * at any time).
 * 
 * Given two integers steps and arrLen, return the number of ways such that
 * your pointer still at index 0 after exactly steps steps.
 * 
 * Since the answer may be too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: steps = 4, arrLen = 2
 * Output: 8
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= steps <= 500
 * 1 <= arrLen <= 10^6
 * 
 * 
 */

// @lc code=start
class Solution {
	int mod = 1000000007;
    public int numWays(int steps, int arrLen) {
    	int[] dp = new int[arrLen];
    	dp[0] = 1;
        for(int i = 1; i <= steps; i++){
        	int[] newDp = new int[arrLen];
        	for(int j = 0; j < Math.min(arrLen, i); j++){
        		// from stay
        		newDp[j] = (newDp[j] + dp[j]) % mod;
        		
        		// from left
        		if(j - 1 >= 0){
        			newDp[j - 1] = (newDp[j - 1] + dp[j]) % mod;	
        		}
        		
        		// from right
        		if(j + 1 < arrLen){
        			newDp[j + 1] = (newDp[j + 1] + dp[j]) % mod;
        		}
        	}
        	dp = newDp;
        	// System.out.println(Arrays.toString(dp));
        }
        return dp[0];
    }
}
// @lc code=end
