/*
 * @lc app=leetcode id=3120 lang=java
 *
 * [3120] Count the Number of Special Characters I
 *
 * https://leetcode.com/problems/count-the-number-of-special-characters-i/description/
 *
 * algorithms
 * Easy (65.96%)
 * Likes:    150
 * Dislikes: 5
 * Total Accepted:    54.3K
 * Total Submissions: 83.4K
 * Testcase Example:  '"aaAbcBC"'
 *
 * You are given a string word. A letter is called special if it appears both
 * in lowercase and uppercase in word.
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
 * The special characters in word are 'a', 'b', and 'c'.
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
 * No character in word appears in uppercase.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "abBCab"
 * 
 * Output: 1
 * 
 * Explanation:
 * 
 * The only special character in word is 'b'.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 50
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
                i -> firstOccurrence[i] != word.length() && lastOccurrence[i] != -1)
                .count();
    }
}
// @lc code=end
