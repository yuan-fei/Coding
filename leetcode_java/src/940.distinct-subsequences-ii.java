/*
 * @lc app=leetcode id=940 lang=java
 *
 * [940] Distinct Subsequences II
 *
 * https://leetcode.com/problems/distinct-subsequences-ii/description/
 *
 * algorithms
 * Hard (43.30%)
 * Likes:    1603
 * Dislikes: 35
 * Total Accepted:    35.8K
 * Total Submissions: 82.7K
 * Testcase Example:  '"abc"'
 *
 * Given a string s, return the number of distinct non-empty subsequences of s.
 * Since the answer may be very large, return it modulo 10^9 + 7.
 * A subsequence of a string is a new string that is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (i.e., "ace" is a
 * subsequence of "abcde" while "aec" is not.
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abc"
 * Output: 7
 * Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac",
 * "bc", and "abc".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aba"
 * Output: 6
 * Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and
 * "aba".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aaa"
 * Output: 3
 * Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2000
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int distinctSubseqII(String s) {
        long MOD = (int)1e9 + 7;
        int n = s.length();
        long[] dp = new long[26];
        long total = 0;
        for(int i = 0; i < n; i++){
            int id = s.charAt(i) - 'a';
            long diff = total + 1 - dp[id];
            diff += MOD;
            diff %= MOD;
            dp[id] += diff;
            dp[id] %= MOD;
            total += diff;
            total %= MOD;
            // System.out.println(diff + ", " +total);
        }
        return (int)total;
    }
}
// @lc code=end
