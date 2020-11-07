/*
 * @lc app=leetcode id=1639 lang=java
 *
 * [1639] Number of Ways to Form a Target String Given a Dictionary
 *
 * https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/description/
 *
 * algorithms
 * Hard (37.87%)
 * Likes:    85
 * Dislikes: 3
 * Total Accepted:    2.5K
 * Total Submissions: 6.6K
 * Testcase Example:  '["acca","bbbb","caca"]\n"aba"'
 *
 * You are given a list of strings of the same length words and a string
 * target.
 * 
 * Your task is to form target using the given words under the following
 * rules:
 * 
 * 
 * target should be formed from left to right.
 * To form the i^th character (0-indexed) of target, you can choose the k^th
 * character of the j^th string in words if target[i] = words[j][k].
 * Once you use the k^th character of the j^th string of words, you can no
 * longer use the x^th character of any string in words where x <= k. In other
 * words, all characters to the left of or at index k become unusuable for
 * every string.
 * Repeat the process until you form the string target.
 * 
 * 
 * Notice that you can use multiple characters from the same string in words
 * provided the conditions above are met.
 * 
 * Return the number of ways to form target from words. Since the answer may be
 * too large, return it modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["acca","bbbb","caca"], target = "aba"
 * Output: 6
 * Explanation: There are 6 ways to form target.
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
 * "aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
 * "aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
 * "aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["abba","baab"], target = "bab"
 * Output: 4
 * Explanation: There are 4 ways to form target.
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
 * "bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
 * "bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
 * "bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: words = ["abcd"], target = "abcd"
 * Output: 1
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: words = ["abab","baba","abba","baab"], target = "abba"
 * Output: 16
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * All strings in words have the same length.
 * 1 <= target.length <= 1000
 * words[i] and target contain only lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int numWays(String[] words, String target) {
        // dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] * #match[i][j];
        long mod = 1000000007;
        int m = target.length();
        int k = words[0].length();
        int[][] cnt = new int[k][26];
        for(String word : words){
        	for(int j = 0; j < k; j++){
        		cnt[j][word.charAt(j) - 'a']++;
        	}
        }
        long[][] dp = new long[k + 1][m + 1];
        for(int i = 0; i <= k; i++){
        	dp[i][0] = 1;
        }
        for(int i = 1; i <= k; i++){
        	for(int j = 1; j <= m; j++){
        		dp[i][j] = (dp[i - 1][j] + (dp[i - 1][j - 1] * cnt[i - 1][target.charAt(j - 1) - 'a']) % mod) % mod;
        	}
        }
        // System.out.println(Arrays.deepToString(dp));
        return (int)dp[k][m];
    }
}
// @lc code=end
