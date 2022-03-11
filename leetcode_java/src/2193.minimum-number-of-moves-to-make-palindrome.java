/*
 * @lc app=leetcode id=2193 lang=java
 *
 * [2193] Minimum Number of Moves to Make Palindrome
 *
 * https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/description/
 *
 * algorithms
 * Hard (36.71%)
 * Likes:    104
 * Dislikes: 43
 * Total Accepted:    3K
 * Total Submissions: 8.3K
 * Testcase Example:  '"aabb"'
 *
 * You are given a string s consisting only of lowercase English letters.
 * 
 * In one move, you can select any two adjacent characters of s and swap them.
 * 
 * Return the minimum number of moves needed to make s a palindrome.
 * 
 * Note that the input will be generated such that s can always be converted to
 * a palindrome.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "aabb"
 * Output: 2
 * Explanation:
 * We can obtain two palindromes from s, "abba" and "baab". 
 * - We can obtain "abba" from s in 2 moves: "aabb" -> "abab" -> "abba".
 * - We can obtain "baab" from s in 2 moves: "aabb" -> "abab" -> "baab".
 * Thus, the minimum number of moves needed to make s a palindrome is 2.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "letelt"
 * Output: 2
 * Explanation:
 * One of the palindromes we can obtain from s in 2 moves is "lettel".
 * One of the ways we can obtain it is "letelt" -> "letetl" -> "lettel".
 * Other palindromes such as "tleelt" can also be obtained in 2 moves.
 * It can be shown that it is not possible to obtain a palindrome in less than
 * 2 moves.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2000
 * s consists only of lowercase English letters.
 * s can be converted to a palindrome using a finite number of moves.
 * 
 * 
 */

// @lc code=start
class Solution {
public int minMovesToMakePalindrome(String s) {
        int count = 0;
        
        while(s.length() > 2) {
            char ch1 = s.charAt(0);
            int len = s.length();
            char ch2 = s.charAt(len - 1);
            
            if (ch1 == ch2) {
                s = s.substring(1, len - 1);
            } else {
                int id1 = s.lastIndexOf(ch1);
                int id2 = s.indexOf(ch2);
                
                int steps1 = len - id1 - 1;
                int steps2 = id2;
                
                StringBuilder sb = new StringBuilder();
                
                if (steps1 > steps2) {
                    count += steps2;
                    sb.append(s.substring(0, id2));
                    sb.append(s.substring(id2 + 1, len - 1));
                } else {
                    count += steps1;
                    sb.append(s.substring(1, id1));
                    sb.append(s.substring(id1 + 1));
                }
                
                s = sb.toString();
            }
        }
        
        return count;
    }
}
// @lc code=end
