/*
 * @lc app=leetcode id=984 lang=java
 *
 * [984] String Without AAA or BBB
 *
 * https://leetcode.com/problems/string-without-aaa-or-bbb/description/
 *
 * algorithms
 * Medium (43.45%)
 * Likes:    759
 * Dislikes: 362
 * Total Accepted:    43.8K
 * Total Submissions: 100.5K
 * Testcase Example:  '1\n2'
 *
 * Given two integers a and b, return any string s such that:
 * 
 * 
 * s has length a + b and contains exactly a 'a' letters, and exactly b 'b'
 * letters,
 * The substring 'aaa' does not occur in s, and
 * The substring 'bbb' does not occur in s.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: a = 1, b = 2
 * Output: "abb"
 * Explanation: "abb", "bab" and "bba" are all correct answers.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: a = 4, b = 1
 * Output: "aabaa"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= a, b <= 100
 * It is guaranteed such an s exists for the given a and b.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String strWithout3a3b(int A, int B) {
        StringBuilder ans = new StringBuilder();

        while (A > 0 || B > 0) {
            boolean writeA = false;
            int L = ans.length();
            if (L >= 2 && ans.charAt(L-1) == ans.charAt(L-2)) {
                if (ans.charAt(L-1) == 'b')
                    writeA = true;
            } else {
                if (A >= B)
                    writeA = true;
            }

            if (writeA) {
                A--;
                ans.append('a');
            } else {
                B--;
                ans.append('b');
            }
        }

        return ans.toString();
    }
}
// @lc code=end
