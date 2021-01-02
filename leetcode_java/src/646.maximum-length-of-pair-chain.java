/*
 * @lc app=leetcode id=646 lang=java
 *
 * [646] Maximum Length of Pair Chain
 *
 * https://leetcode.com/problems/maximum-length-of-pair-chain/description/
 *
 * algorithms
 * Medium (52.53%)
 * Likes:    1176
 * Dislikes: 86
 * Total Accepted:    68.2K
 * Total Submissions: 129.6K
 * Testcase Example:  '[[1,2], [2,3], [3,4]]'
 *
 * 
 * You are given n pairs of numbers. In every pair, the first number is always
 * smaller than the second number.
 * 
 * 
 * 
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b
 * < c. Chain of pairs can be formed in this fashion. 
 * 
 * 
 * 
 * Given a set of pairs, find the length longest chain which can be formed. You
 * needn't use up all the given pairs. You can select pairs in any order.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 * 
 * 
 * 
 * Note:
 * 
 * The number of given pairs will be in the range [1, 1000].
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findLongestChain(int[][] pairs) {
    	int n = pairs.length;
    	int[] dp = new int[n];
    	Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
    	Arrays.fill(dp, 1);
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < i; j++){
        		if(pairs[i][0] > pairs[j][1]){
        			dp[i] = Math.max(dp[i], dp[j] + 1);	
        		}
        	}
        }
        return dp[n - 1];
    }
}
// @lc code=end
