/*
 * @lc app=leetcode id=2062 lang=java
 *
 * [2062] Count Vowel Substrings of a String
 *
 * https://leetcode.com/problems/count-vowel-substrings-of-a-string/description/
 *
 * algorithms
 * Easy (66.06%)
 * Likes:    55
 * Dislikes: 52
 * Total Accepted:    6K
 * Total Submissions: 9.1K
 * Testcase Example:  '"aeiouu"'
 *
 * A substring is a contiguous (non-empty) sequence of characters within a
 * string.
 * 
 * A vowel substring is a substring that only consists of vowels ('a', 'e',
 * 'i', 'o', and 'u') and has all five vowels present in it.
 * 
 * Given a string word, return the number of vowel substrings in word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word = "aeiouu"
 * Output: 2
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "aeiouu"
 * - "aeiouu"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word = "unicornarihan"
 * Output: 0
 * Explanation: Not all 5 vowels are present, so there are no vowel
 * substrings.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: word = "cuaieuouac"
 * Output: 7
 * Explanation: The vowel substrings of word are as follows (underlined):
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * - "cuaieuouac"
 * 
 * Example 4:
 * 
 * 
 * Input: word = "bbaeixoubb"
 * Output: 0
 * Explanation: The only substrings that contain all five vowels also contain
 * consonants, so there are no vowel substrings.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= word.length <= 100
 * word consists of lowercase English letters only.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countVowelSubstrings(String word) {
        int cnt = 0;
        for(int i = 0; i < word.length(); i++){
            for(int j = i + 4; j < word.length(); j++){
                if(legal(word.substring(i, j + 1))){
                    // System.out.println(i + ", " + j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    boolean legal(String s){
        if(!s.matches("[aeiou]+")){
            return false;
        }
        for(char c : "aeiou".toCharArray()){
            if(s.indexOf(c) < 0){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end
