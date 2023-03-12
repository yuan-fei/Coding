/*
 * @lc app=leetcode id=2586 lang=java
 *
 * [2586] Count the Number of Vowel Strings in Range
 *
 * https://leetcode.com/problems/count-the-number-of-vowel-strings-in-range/description/
 *
 * algorithms
 * Easy (78.36%)
 * Likes:    26
 * Dislikes: 0
 * Total Accepted:    14.1K
 * Total Submissions: 18K
 * Testcase Example:  '["are","amy","u"]\n0\n2'
 *
 * You are given a 0-indexed array of string words and two integers left and
 * right.
 * 
 * A string is called a vowel string if it starts with a vowel character and
 * ends with a vowel character where vowel characters are 'a', 'e', 'i', 'o',
 * and 'u'.
 * 
 * Return the number of vowel strings words[i] where i belongs to the inclusive
 * range [left, right].
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["are","amy","u"], left = 0, right = 2
 * Output: 2
 * Explanation: 
 * - "are" is a vowel string because it starts with 'a' and ends with 'e'.
 * - "amy" is not a vowel string because it does not end with a vowel.
 * - "u" is a vowel string because it starts with 'u' and ends with 'u'.
 * The number of vowel strings in the mentioned range is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["hey","aeo","mu","ooo","artro"], left = 1, right = 4
 * Output: 3
 * Explanation: 
 * - "aeo" is a vowel string because it starts with 'a' and ends with 'o'.
 * - "mu" is not a vowel string because it does not start with a vowel.
 * - "ooo" is a vowel string because it starts with 'o' and ends with 'o'.
 * - "artro" is a vowel string because it starts with 'a' and ends with 'o'.
 * The number of vowel strings in the mentioned range is 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 10
 * words[i] consists of only lowercase English letters.
 * 0 <= left <= right < words.length
 * 
 * 
 */

// @lc code=start
class Solution {
    public int vowelStrings(String[] words, int left, int right) {
        return (int)IntStream.range(left, right + 1).filter(i -> ifVowelStrng(words[i])).count();
    }

    boolean ifVowelStrng(String s){
        String vowels = "aeiou";
        return vowels.indexOf(s.charAt(0)) >= 0 && vowels.indexOf(s.charAt(s.length() - 1)) >= 0;
    }
}
// @lc code=end
