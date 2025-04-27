/*
 * @lc app=leetcode id=3136 lang=java
 *
 * [3136] Valid Word
 *
 * https://leetcode.com/problems/valid-word/description/
 *
 * algorithms
 * Easy (38.35%)
 * Likes:    119
 * Dislikes: 111
 * Total Accepted:    50.8K
 * Total Submissions: 132K
 * Testcase Example:  '"234Adas"'
 *
 * A word is considered valid if:
 * 
 * 
 * It contains a minimum of 3 characters.
 * It contains only digits (0-9), and English letters (uppercase and
 * lowercase).
 * It includes at least one vowel.
 * It includes at least one consonant.
 * 
 * 
 * You are given a string word.
 * 
 * Return true if word is valid, otherwise, return false.
 * 
 * Notes:
 * 
 * 
 * 'a', 'e', 'i', 'o', 'u', and their uppercases are vowels.
 * A consonant is an English letter that is not a vowel.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "234Adas"
 * 
 * Output: true
 * 
 * Explanation:
 * 
 * This word satisfies the conditions.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "b3"
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * The length of this word is fewer than 3, and does not have a vowel.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "a3$e"
 * 
 * Output: false
 * 
 * Explanation:
 * 
 * This word contains a '$' character and does not have a consonant.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 20
 * word consists of English uppercase and lowercase letters, digits, '@', '#',
 * and '$'.
 * 
 * 
 */

// @lc code=start

import java.util.Set;

class Solution {
    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

    public boolean isValid(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        if (chars.length < 3) {
            return false;
        }

        boolean hasVowel = false;
        boolean hasConsonant = false;
        for (char c : chars) {
            if (!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                return false;
            }
            hasVowel |= Character.isAlphabetic(c) && vowels.contains(c);
            hasConsonant |= Character.isAlphabetic(c) && !vowels.contains(c);
        }
        return hasVowel && hasConsonant;

    }

}
// @lc code=end
