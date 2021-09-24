/*
 * @lc app=leetcode id=2014 lang=java
 *
 * [2014] Longest Subsequence Repeated k Times
 *
 * https://leetcode.com/problems/longest-subsequence-repeated-k-times/description/
 *
 * algorithms
 * Hard (50.65%)
 * Likes:    70
 * Dislikes: 32
 * Total Accepted:    1.9K
 * Total Submissions: 3.8K
 * Testcase Example:  '"letsleetcode"\n2'
 *
 * You are given a string s of length n, and an integer k. You are tasked to
 * find the longest subsequence repeated k times in string s.
 * 
 * A subsequence is a string that can be derived from another string by
 * deleting some or no characters without changing the order of the remaining
 * characters.
 * 
 * A subsequence seq is repeated k times in the string s if seq * k is a
 * subsequence of s, where seq * k represents a string constructed by
 * concatenating seq k times.
 * 
 * 
 * For example, "bba" is repeated 2 times in the string "bababcba", because the
 * string "bbabba", constructed by concatenating "bba" 2 times, is a
 * subsequence of the string "bababcba".
 * 
 * 
 * Return the longest subsequence repeated k times in string s. If multiple
 * such subsequences are found, return the lexicographically largest one. If
 * there is no such subsequence, return an empty string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "letsleetcode", k = 2
 * Output: "let"
 * Explanation: There are two longest subsequences repeated 2 times: "let" and
 * "ete".
 * "let" is the lexicographically largest one.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bb", k = 2
 * Output: "b"
 * Explanation: The longest subsequence repeated 2 times is "b".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "ab", k = 2
 * Output: ""
 * Explanation: There is no subsequence repeated 2 times. Empty string is
 * returned.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "bbabbabbbbabaababab", k = 3
 * Output: "bbbb"
 * Explanation: The longest subsequence "bbbb" is repeated 3 times in
 * "bbabbabbbbabaababab".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == s.length
 * 2 <= n, k <= 2000
 * 2 <= n < k * 8
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        final int N = 26;
        String res = "";
        // q only stores valid subsequences, initialized with a empty string.
        Queue<String> q = new ArrayDeque<>();
        q.offer("");
        while (!q.isEmpty()) {
            int size = q.size();
            // BFS layer by layer, within each layer, the cur string has same length
            while (size-- > 0) {
                String cur = q.poll();
                for (int i = 0; i < N; i++) {
                    String next = cur + (char) ('a' + i);
                    if (isSub(s, next, k)) {
                        // always update res since we are looking for lexicographically largest.
                        res = next;
                        q.offer(next);
                    }
                }                
            }
        }
        return res;
    }
    
    // check if sub * k is a subsequence of string s. 
    // Time complexity - O(n)
    private boolean isSub(String s, String sub, int k) {
        int j = 0;
        int repeat = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                if (j == sub.length()) {
                    repeat++;
                    if (repeat == k) {
                        return true;
                    }
                    j = 0;
                }
            }
        }
        return false;
    }
}
// @lc code=end
