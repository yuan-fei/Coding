/*
 * @lc app=leetcode id=1745 lang=java
 *
 * [1745] Palindrome Partitioning IV
 *
 * https://leetcode.com/problems/palindrome-partitioning-iv/description/
 *
 * algorithms
 * Hard (48.89%)
 * Likes:    148
 * Dislikes: 2
 * Total Accepted:    5.3K
 * Total Submissions: 10.8K
 * Testcase Example:  '"abcbdd"'
 *
 * Given a string s, return true if it is possible to split the string s into
 * three non-empty palindromic substrings. Otherwise, return false.​​​​​
 * 
 * A string is said to be palindrome if it the same string when reversed.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcbdd"
 * Output: true
 * Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are
 * palindromes.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bcbddxy"
 * Output: false
 * Explanation: s cannot be split into 3 palindromes.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 2000
 * s​​​​​​ consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean checkPartitioning(String s) {
    	int N = s.length();
		char[] A = s.toCharArray();

		// build dp table
		boolean[][] dp = new boolean[N][N];
		for (int i = N - 1; i >= 0; --i) {
			for (int j = i; j < N; ++j) {
				if (A[i] == A[j]) dp[i][j] = ((i + 1 <= j - 1) ? dp[i + 1][j - 1] : true);
				else dp[i][j] = false;
			}
		}

		// iterate every mid and then check: left, mid and right
		for (int i = 1; i < N - 1; ++i) {
			for (int j = i; j < N - 1; ++j) {
				if (dp[0][i - 1] && dp[i][j] && dp[j + 1][N - 1]) return true;
			}
		}

		return false;
    }
}
// @lc code=end
