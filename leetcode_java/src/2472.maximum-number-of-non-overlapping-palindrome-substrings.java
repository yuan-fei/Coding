/*
 * @lc app=leetcode id=2472 lang=java
 *
 * [2472] Maximum Number of Non-overlapping Palindrome Substrings
 *
 * https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/description/
 *
 * algorithms
 * Hard (34.05%)
 * Likes:    161
 * Dislikes: 6
 * Total Accepted:    5.3K
 * Total Submissions: 15.4K
 * Testcase Example:  '"abaccdbbd"\n3'
 *
 * You are given a string s and a positive integer k.
 * 
 * Select a set of non-overlapping substrings from the string s that satisfy
 * the following conditions:
 * 
 * 
 * The length of each substring is at least k.
 * Each substring is a palindrome.
 * 
 * 
 * Return the maximum number of substrings in an optimal selection.
 * 
 * A substring is a contiguous sequence of characters within a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abaccdbbd", k = 3
 * Output: 2
 * Explanation: We can select the substrings underlined in s = "abaccdbbd".
 * Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
 * It can be shown that we cannot find a selection with more than two valid
 * substrings.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "adbcda", k = 2
 * Output: 0
 * Explanation: There is no palindrome substring of length at least 2 in the
 * string.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= k <= s.length <= 2000
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] start = new int[n];
        boolean[][] state = processPalindromes(s);
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++){
            dp[i] = dp[i - 1];
            for(int j = i - k + 1; j >= 1; j--){
                if(state[j - 1][i - 1]){
                    dp[i] = Math.max(dp[i], dp[j - 1] + 1);
                    break;
                }
            }
        }
        return dp[n];
    }

    boolean[][] processPalindromes(String s){
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
            if (i > 0) {
                isPalindrome[i - 1][i] = (s.charAt(i - 1) == s.charAt(i));
            }
        }

        for (int length = 2; length < s.length(); length++) {
            for (int j = 0; j < s.length() - length; j++) {
                isPalindrome[j][j + length] = (isPalindrome[j + 1][j + length - 1]
                        && (s.charAt(j) == s.charAt(j + length)));
            }
        }
        return isPalindrome;
    }
}
// @lc code=end
