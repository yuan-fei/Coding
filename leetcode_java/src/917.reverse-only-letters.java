/*
 * @lc app=leetcode id=917 lang=java
 *
 * [917] Reverse Only Letters
 *
 * https://leetcode.com/problems/reverse-only-letters/description/
 *
 * algorithms
 * Easy (62.24%)
 * Likes:    1874
 * Dislikes: 64
 * Total Accepted:    165.3K
 * Total Submissions: 265.4K
 * Testcase Example:  '"ab-cd"'
 *
 * Given a string s, reverse the string according to the following rules:
 * 
 * 
 * All the characters that are not English letters remain in the same
 * position.
 * All the English letters (lowercase or uppercase) should be reversed.
 * 
 * 
 * Return s after reversing it.
 * 
 * 
 * Example 1:
 * Input: s = "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 * Input: s = "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 * Input: s = "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s consists of characters with ASCII values in the range [33, 122].
 * s does not contain '\"' or '\\'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseOnlyLetters(String s) {
        String letters = "";
        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                letters += c;
            }
        }
        String ans = "";
        int i = letters.length() - 1;
        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                ans += letters.charAt(i--);
            }
            else{
                ans += c;
            }
        }
        return ans;
    }
}
// @lc code=end
