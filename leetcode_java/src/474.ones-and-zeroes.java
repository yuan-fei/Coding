/*
 * @lc app=leetcode id=474 lang=java
 *
 * [474] Ones and Zeroes
 *
 * https://leetcode.com/problems/ones-and-zeroes/description/
 *
 * algorithms
 * Medium (42.34%)
 * Likes:    903
 * Dislikes: 223
 * Total Accepted:    45.9K
 * Total Submissions: 108.3K
 * Testcase Example:  '["10","0001","111001","1","0"]\n5\n3'
 *
 * Given an array, strs, with strings consisting of only 0s and 1s. Also two
 * integers m and n.
 * 
 * Now your task is to find the maximum number of strings that you can form
 * with given m 0s and n 1s. Each 0 and 1 can be used at most once.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: This are totally 4 strings can be formed by the using of 5 0s
 * and 3 1s, which are "10","0001","1","0".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: You could form "10", but then you'd have nothing left. Better
 * form "0" and "1".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
    	int[][] dp = new int[m + 1][n + 1];
    	dp[0][0] = 1;
    	int max = 0;
    	for(String s : strs){
    		int[] cnts = getCount(s);
    		for(int i = m - cnts[0]; i >= 0 ; i--){
    			for(int j = n - cnts[1]; j >= 0; j--){
    				if(dp[i][j] > 0){
    					dp[i + cnts[0]][j + cnts[1]] = Math.max(dp[i + cnts[0]][j + cnts[1]], dp[i][j] + 1);
    					max = Math.max(max, dp[i + cnts[0]][j + cnts[1]]);
    				}
    			}
    		}
    	}
    	// System.out.println(Arrays.deepToString(dp));
        return Math.max(0, max - 1);
    }

    int[] getCount(String s){
    	int[] ans = new int[2];
    	for(int i = 0; i < s.length(); i++){
    		if(s.charAt(i) == '0'){
    			ans[0]++; 
    		}
    		else{
    			ans[1]++;
    		}
    	}
    	return ans;
    }
}
// @lc code=end
