/*
 * @lc app=leetcode id=2278 lang=java
 *
 * [2278] Percentage of Letter in String
 *
 * https://leetcode.com/problems/percentage-of-letter-in-string/description/
 *
 * algorithms
 * Easy (70.85%)
 * Likes:    37
 * Dislikes: 7
 * Total Accepted:    13.7K
 * Total Submissions: 19.4K
 * Testcase Example:  '"foobar"\n"o"'
 *
 * Given a string s and a character letter, return the percentage of characters
 * in s that equal letter rounded down to the nearest whole percent.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "foobar", letter = "o"
 * Output: 33
 * Explanation:
 * The percentage of characters in s that equal the letter 'o' is 2 / 6 * 100%
 * = 33% when rounded down, so we return 33.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "jjjj", letter = "k"
 * Output: 0
 * Explanation:
 * The percentage of characters in s that equal the letter 'k' is 0%, so we
 * return 0.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s consists of lowercase English letters.
 * letter is a lowercase English letter.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int percentageLetter(String s, char letter) {
        int occurance = s.length() - s.replace("" + letter, "").length();
        return 100 * occurance / s.length();
    }
}
// @lc code=end
