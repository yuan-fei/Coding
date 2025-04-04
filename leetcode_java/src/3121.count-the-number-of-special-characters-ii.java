/*
 * @lc app=leetcode id=3121 lang=java
 *
 * [3121] Count the Number of Special Characters II
 *
 * https://leetcode.com/problems/count-the-number-of-special-characters-ii/description/
 *
 * algorithms
 * Medium (41.83%)
 * Likes:    161
 * Dislikes: 14
 * Total Accepted:    35.2K
 * Total Submissions: 84.3K
 * Testcase Example:  '"aaAbcBC"'
 *
 * You are given a string word. A letter c is called special if it appears both
 * in lowercase and uppercase in word, and every lowercase occurrence of c
 * appears before the first uppercase occurrence of c.
 * 
 * Return the number of special letters in word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "aaAbcBC"
 * 
 * Output: 3
 * 
 * Explanation:
 * 
 * The special characters are 'a', 'b', and 'c'.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "abc"
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * There are no special characters in word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "AbBCab"
 * 
 * Output: 0
 * 
 * Explanation:
 * 
 * There are no special characters in word.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 2 * 10^5
 * word consists of only lowercase and uppercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lastOccurrence = new int[26];
        int[] firstOccurrence = new int[26];
        Arrays.fill(lastOccurrence, -1);
        Arrays.fill(firstOccurrence, word.length());
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) {
                lastOccurrence[c - 'a'] = Math.max(lastOccurrence[c - 'a'], i);
            } else {
                firstOccurrence[c - 'A'] = Math.min(firstOccurrence[c - 'A'], i);
            }
        }
        return (int) IntStream.range(0, 26).filter(
                i -> firstOccurrence[i] != word.length() && lastOccurrence[i] != -1
                        && firstOccurrence[i] > lastOccurrence[i])
                .count();
    }
}
// @lc code=end
