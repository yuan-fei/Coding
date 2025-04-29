/*
 * @lc app=leetcode id=3144 lang=java
 *
 * [3144] Minimum Substring Partition of Equal Character Frequency
 *
 * https://leetcode.com/problems/minimum-substring-partition-of-equal-character-frequency/description/
 *
 * algorithms
 * Medium (40.12%)
 * Likes:    152
 * Dislikes: 33
 * Total Accepted:    15.6K
 * Total Submissions: 40.2K
 * Testcase Example:  '"fabccddg"'
 *
 * Given a string s, you need to partition it into one or more balanced
 * substrings. For example, if s == "ababcc" then ("abab", "c", "c"), ("ab",
 * "abc", "c"), and ("ababcc") are all valid partitions, but ("a", "bab",
 * "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not. The unbalanced
 * substrings are bolded.
 * 
 * Return the minimum number of substrings that you can partition s into.
 * 
 * Note: A balanced string is a string where each character in the string
 * occurs the same number of times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "fabccddg"
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * We can partition the string s into 3 substrings in one of the following
 * ways: ("fab, "ccdd", "g"), or ("fabc", "cd", "dg").
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abababaccddb"
 * 
 * Output: 2
 * 
 * Explanation:
 * 
 * We can partition the string s into 2 substrings like so: ("abab",
 * "abaccddb").
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consists only of English lowercase letters.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int MAX = 2000;

    public int minimumSubstringsInPartition(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for (int j = i; j >= 0; j--) {
                freq[s.charAt(j) - 'a']++;
                if (isBalanced(freq)) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[j] + 1);
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private boolean isBalanced(int[] charFreq) {
        int minFreq = 1001, maxFreq = 0;
        for (int Freq : charFreq) {
            if (Freq > 0) {
                minFreq = Math.min(minFreq, Freq);
                maxFreq = Math.max(maxFreq, Freq);
            }
        }
        return minFreq == maxFreq;
    }
}
// @lc code=end
