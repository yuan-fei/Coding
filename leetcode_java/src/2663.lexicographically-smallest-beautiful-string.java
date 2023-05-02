/*
 * @lc app=leetcode id=2663 lang=java
 *
 * [2663] Lexicographically Smallest Beautiful String
 *
 * https://leetcode.com/problems/lexicographically-smallest-beautiful-string/description/
 *
 * algorithms
 * Hard (27.59%)
 * Likes:    40
 * Dislikes: 8
 * Total Accepted:    1.2K
 * Total Submissions: 3.9K
 * Testcase Example:  '"abcz"\n26'
 *
 * A string is beautiful if:
 * 
 * 
 * It consists of the first k letters of the English lowercase alphabet.
 * It does not contain any substring of length 2 or more which is a
 * palindrome.
 * 
 * 
 * You are given a beautiful string s of length n and a positive integer k.
 * 
 * Return the lexicographically smallest string of length n, which is larger
 * than s and is beautiful. If there is no such string, return an empty
 * string.
 * 
 * A string a is lexicographically larger than a string b (of the same length)
 * if in the first position where a and b differ, a has a character strictly
 * larger than the corresponding character in b.
 * 
 * 
 * For example, "abcd" is lexicographically larger than "abcc" because the
 * first position they differ is at the fourth character, and d is greater than
 * c.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abcz", k = 26
 * Output: "abda"
 * Explanation: The string "abda" is beautiful and lexicographically larger
 * than the string "abcz".
 * It can be proven that there is no string that is lexicographically larger
 * than the string "abcz", beautiful, and lexicographically smaller than the
 * string "abda".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "dc", k = 4
 * Output: ""
 * Explanation: It can be proven that there is no string that is
 * lexicographically larger than the string "dc" and is beautiful.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n == s.length <= 10^5
 * 4 <= k <= 26
 * s is a beautiful string.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String smallestBeautifulString(String s, int k) {
        int n = s.length();
        Set<Character> seen = new HashSet<>();
        for(int i = n - 1; i >= 0; i--){
            seen.remove(s.charAt(i));
            if(i - 1 >= 0){
                seen.add(s.charAt(i - 1));
            }
            if(i - 2 >= 0){
                seen.add(s.charAt(i - 2));
            }
            for(int c = s.charAt(i) + 1; c < 'a' + k; c++){
                if(!seen.contains((char)c)){
                    return buildString(s, k, i, c);
                }
            }
        }
        return "";
    }

    String buildString(String s, int k, int pos, int cPos){
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, pos));
        sb.append((char)cPos);
        int a = (pos - 1 >= 0) ? s.charAt(pos - 1) : -1;
        int b = cPos;
        for(int i = pos + 1; i < s.length(); i++){
            for(int c = 'a'; c < 'a' + k; c++){
                if(c != a && c != b){
                    sb.append((char)c);
                    a = b;
                    b = c;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
// @lc code=end
