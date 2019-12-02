/*
 * @lc app=leetcode id=1278 lang=java
 *
 * [1278] Palindrome Partitioning III
 *
 * https://leetcode.com/problems/palindrome-partitioning-iii/description/
 *
 * algorithms
 * Hard (51.39%)
 * Likes:    39
 * Dislikes: 0
 * Total Accepted:    1.3K
 * Total Submissions: 2.5K
 * Testcase Example:  '"abc"\n2'
 *
 * You are given a string s containing lowercase letters and an integer k. You
 * need to :
 * 
 * 
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring
 * is palindrome.
 * 
 * 
 * Return the minimal number of characters that you need to change to divide
 * the string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1
 * character in "ab" to make it palindrome.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them
 * are palindrome.
 * 
 * Example 3:
 * 
 * 
 * Input: s = "leetcode", k = 8
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= s.length <= 100.
 * s only contains lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public int palindromePartition(String s, int k) {
        int[][] minCost = computeMinimumCost(s);
        int N = s.length();
        int MAX = 200;
        int[][] dp = new int[N + 1][k + 1];
        for(int i = 0; i <= N; i++){
        	for(int j = 0; j <= k; j++){
        		dp[i][j] = MAX;
        	}
        }
        dp[0][0] = 0;
        for(int i = 1; i <= N; i++){
        	for(int j = 1; j <= Math.min(i, k); j++){
        		for(int i1 = 1; i1 <= i; i1++){
        			dp[i][j] = Math.min(dp[i][j], dp[i1 - 1][j - 1] + minCost[i1 - 1][i - 1]);
        		}
        	}
        }
        return dp[N][k];
    }

    private int[][] computeMinimumCost(String s){
    	int N = s.length();
    	char[] c = s.toCharArray(); 
    	int[][] dp = new int[N][N];
    	for(int i = 0; i < N; i++){
    		dp[i][i] = 0;
    		if(i + 1 < N){
    			dp[i][i + 1] = (c[i] == c[i + 1])? 0 : 1;	
    		}
    	}
    	for(int l = 3; l <= N; l++){
    		for(int i = 0; i < N; i++){
    			int j = i + l - 1;
    			if(j < N){
    				if(c[i] == c[j]){
    					dp[i][j] = dp[i + 1][j - 1];
    				}
    				else{
    					dp[i][j] = dp[i + 1][j - 1] + 1;
    				}
    			}
    		}
    	}
    	return dp;
    }
}
// @lc code=end
