/*
 * @lc app=leetcode id=354 lang=java
 *
 * [354] Russian Doll Envelopes
 *
 * https://leetcode.com/problems/russian-doll-envelopes/description/
 *
 * algorithms
 * Hard (34.72%)
 * Likes:    824
 * Dislikes: 32
 * Total Accepted:    56.4K
 * Total Submissions: 162.3K
 * Testcase Example:  '[[5,4],[6,4],[6,7],[2,3]]'
 *
 * You have a number of envelopes with widths and heights given as a pair of
 * integers (w, h). One envelope can fit into another if and only if both the
 * width and height of one envelope is greater than the width and height of the
 * other envelope.
 * 
 * What is the maximum number of envelopes can you Russian doll? (put one
 * inside other)
 * 
 * Note:
 * Rotation is not allowed.
 * 
 * Example:
 * 
 * 
 * 
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3 
 * Explanation: The maximum number of envelopes you can Russian doll is 3
 * ([2,3] => [5,4] => [6,7]).
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> Integer.compare(a[0], b[0]));
        int n = envelopes.length;
        int[] dp = new int[n];
        int max = 0;
        for(int i = 0; i < n; i++){
        	dp[i] = 1;
        	for(int j = 0; j < i; j++){
        		if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
        			dp[i] = Math.max(dp[i], dp[j] + 1);	
        		}
        	}
        	max = Math.max(max, dp[i]);
        }
        return max;
    }
}
// @lc code=end
