/*
 * @lc app=leetcode id=1771 lang=java
 *
 * [1771] Maximize Palindrome Length From Subsequences
 *
 * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/description/
 *
 * algorithms
 * Hard (31.80%)
 * Likes:    120
 * Dislikes: 5
 * Total Accepted:    3.2K
 * Total Submissions: 9.8K
 * Testcase Example:  '"cacb"\n"cbba"'
 *
 * You are given two strings, word1 and word2. You want to construct a string
 * in the following manner:
 * 
 * 
 * Choose some non-empty subsequence subsequence1 from word1.
 * Choose some non-empty subsequence subsequence2 from word2.
 * Concatenate the subsequences: subsequence1 + subsequence2, to make the
 * string.
 * 
 * 
 * Return the length of the longest palindrome that can be constructed in the
 * described manner. If no palindromes can be constructed, return 0.
 * 
 * A subsequence of a string s is a string that can be made by deleting some
 * (possibly none) characters from s without changing the order of the
 * remaining characters.
 * 
 * A palindrome is a string that reads the same forward as well as backward.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word1 = "cacb", word2 = "cbba"
 * Output: 5
 * Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba",
 * which is a palindrome.
 * 
 * Example 2:
 * 
 * 
 * Input: word1 = "ab", word2 = "ab"
 * Output: 3
 * Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which
 * is a palindrome.
 * 
 * Example 3:
 * 
 * 
 * Input: word1 = "aa", word2 = "bb"
 * Output: 0
 * Explanation: You cannot construct a palindrome from the described method, so
 * return 0.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word1.length, word2.length <= 1000
 * word1 and word2 consist of lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
    public int longestPalindrome(String word1, String word2) {
    	int n1 = word1.length();
    	int n2 = word2.length();
    	int n = n1 + n2;
        String s = word1 + word2;
        int[][] dp = maxPalindromeSubSequence(s);
        // System.out.println(Arrays.deepToString(dp));
        int[] last = new int[26];
        int[] first = new int[26];
        for(int j = 0; j < 26; j++){
        	last[j] = -1;
        	first[j] = n + 1;
        }
        for(int i = 1; i <= n; i++){
        	int c = s.charAt(i - 1) - 'a';
        	last[c] = Math.max(i, last[c]);
        	first[c] = Math.min(i, first[c]);
        }
        int max = 0;
        for(int i = 0; i < 26; i++){
        	if(first[i] <= n1 && last[i] > n1){
        		max = Math.max(max, dp[first[i]][last[i]]);
        	}
        }
        return max;
    }

    int[][] maxPalindromeSubSequence(String s){
    	int n = s.length();
    	int[][] dp = new int[n + 1][n + 1];
    	for(int i = 1; i <= n; i++){
        	dp[i][i] = 1;
        	if(i < n){
        		if(s.charAt(i) == s.charAt(i - 1)){
        			dp[i][i + 1] = 2;	
        		}
        		else{
	        		dp[i][i + 1] = 1;	
	        	}	
        	}
        }
        for(int d = 2; d < n; d++){
        	for(int i = 1; i + d <= n; i++){
        		if(s.charAt(i - 1) == s.charAt(i + d - 1)){
        			dp[i][i + d] = dp[i + 1][i + d - 1] + 2;
        		}
        		else{
        			dp[i][i + d] = Math.max(dp[i + 1][i + d], dp[i][i + d - 1]);
        		}
        	}
        }
        return dp;
    }
}
// @lc code=end
