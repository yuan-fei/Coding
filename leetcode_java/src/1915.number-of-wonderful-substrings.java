/*
 * @lc app=leetcode id=1915 lang=java
 *
 * [1915] Number of Wonderful Substrings
 *
 * https://leetcode.com/problems/number-of-wonderful-substrings/description/
 *
 * algorithms
 * Medium (33.84%)
 * Likes:    269
 * Dislikes: 19
 * Total Accepted:    3.2K
 * Total Submissions: 9.4K
 * Testcase Example:  '"aba"'
 *
 * A wonderful string is a string where at most one letter appears an odd
 * number of times.
 * 
 * 
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * 
 * 
 * Given a string word that consists of the first ten lowercase English letters
 * ('a' through 'j'), return the number of wonderful non-empty substrings in
 * word. If the same substring appears multiple times in word, then count each
 * occurrence separately.
 * 
 * A substring is a contiguous sequence of characters in a string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "aba"
 * Output: 4
 * Explanation: The four wonderful substrings are underlined below:
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "aabb"
 * Output: 9
 * Explanation: The nine wonderful substrings are underlined below:
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "he"
 * Output: 2
 * Explanation: The two wonderful substrings are underlined below:
 * - "he" -> "h"
 * - "he" -> "e"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 10^5
 * word consists of lowercase English letters from 'a' to 'j'.
 * 
 */

// @lc code=start
class Solution {
    public long wonderfulSubstrings(String word) {
        long[] dp = new long[1 << 10];
        dp[0] = 1;
        int cur = 0;
        long cnt = 0;
        for(char c : word.toCharArray()){
        	cur ^= 1 << (c - 'a');
        	for(int i = 0; i < 10; i++){
        		cnt += dp[cur ^ (1 << i)];
        	}
        	cnt += dp[cur];
        	dp[cur]++;
        	// System.out.println(cnt);
        }
        return cnt;
    }
}
// @lc code=end
