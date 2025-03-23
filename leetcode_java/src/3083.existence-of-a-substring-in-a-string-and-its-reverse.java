/*
 * @lc app=leetcode id=3083 lang=java
 *
 * [3083] Existence of a Substring in a String and Its Reverse
 *
 * https://leetcode.com/problems/existence-of-a-substring-in-a-string-and-its-reverse/description/
 *
 * algorithms
 * Easy (66.29%)
 * Likes:    98
 * Dislikes: 1
 * Total Accepted:    49.5K
 * Total Submissions: 74.6K
 * Testcase Example:  '"leetcode"'
 *
 * Given a string s, find any substring of length 2 which is also present in
 * the reverse of s.
 * 
 * Return true if such a substring exists, and false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode"
 * 
 * Output: true
 * 
 * Explanation: Substring "ee" is of length 2 which is also present in
 * reverse(s) == "edocteel".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "abcba"
 * 
 * Output: true
 * 
 * Explanation: All of the substrings of length 2 "ab", "bc", "cb", "ba" are
 * also present in reverse(s) == "abcba".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "abcd"
 * 
 * Output: false
 * 
 * Explanation: There is no substring of length 2 in s, which is also present
 * in the reverse of s.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

class Solution {
    public boolean isSubstringPresent(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        String reversed = sb.reverse().toString();
        for (int i = 0; i < s.length() - 1; i++) {
            String sub = s.substring(i, i + 2);
            if (reversed.contains(sub)) {
                return true;
            }
        }
        return false;
    }
}
// @lc code=end
