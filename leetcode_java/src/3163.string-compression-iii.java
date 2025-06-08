/*
 * @lc app=leetcode id=3163 lang=java
 *
 * [3163] String Compression III
 *
 * https://leetcode.com/problems/string-compression-iii/description/
 *
 * algorithms
 * Medium (68.06%)
 * Likes:    603
 * Dislikes: 53
 * Total Accepted:    191.5K
 * Total Submissions: 286.7K
 * Testcase Example:  '"abcde"'
 *
 * Given a string word, compress it using the following algorithm:
 * 
 * 
 * Begin with an empty string comp. While word is not empty, use the following
 * operation:
 * 
 * 
 * Remove a maximum length prefix of word made of a single character c
 * repeating at most 9 times.
 * Append the length of the prefix followed by c to comp.
 * 
 * 
 * 
 * 
 * Return the string comp.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "abcde"
 * 
 * Output: "1a1b1c1d1e"
 * 
 * Explanation:
 * 
 * Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c",
 * "d", and "e" as the prefix in each operation.
 * 
 * For each prefix, append "1" followed by the character to comp.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "aaaaaaaaaaaaaabb"
 * 
 * Output: "9a5a2b"
 * 
 * Explanation:
 * 
 * Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa",
 * "aaaaa", and "bb" as the prefix in each operation.
 * 
 * 
 * For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
 * For prefix "aaaaa", append "5" followed by "a" to comp.
 * For prefix "bb", append "2" followed by "b" to comp.
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 2 * 10^5
 * word consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int l = 0;
        for (int r = 0; r < word.length(); r++) {
            if (word.charAt(r) != word.charAt(l) || r - l + 1 > 9) {
                sb.append(r - l);
                sb.append(word.charAt(l));
                l = r;
            }
        }
        sb.append(word.length() - l);
        sb.append(word.charAt(l));
        return sb.toString();
    }
}
// @lc code=end
