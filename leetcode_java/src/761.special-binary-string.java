/*
 * @lc app=leetcode id=761 lang=java
 *
 * [761] Special Binary String
 *
 * https://leetcode.com/problems/special-binary-string/description/
 *
 * algorithms
 * Hard (60.46%)
 * Likes:    608
 * Dislikes: 185
 * Total Accepted:    14.5K
 * Total Submissions: 24.1K
 * Testcase Example:  '"11011000"'
 *
 * Special binary strings are binary strings with the following two
 * properties:
 * 
 * 
 * The number of 0's is equal to the number of 1's.
 * Every prefix of the binary string has at least as many 1's as 0's.
 * 
 * 
 * You are given a special binary string s.
 * 
 * A move consists of choosing two consecutive, non-empty, special substrings
 * of s, and swapping them. Two strings are consecutive if the last character
 * of the first string is exactly one index before the first character of the
 * second string.
 * 
 * Return the lexicographically largest resulting string possible after
 * applying the mentioned operations on the string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "11011000"
 * Output: "11100100"
 * Explanation: The strings "10" [occuring at s[1]] and "1100" [at s[3]] are
 * swapped.
 * This is the lexicographically largest string possible after some number of
 * swaps.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "10"
 * Output: "10"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 50
 * s[i] is either '0' or '1'.
 * s is a special binary string.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String makeLargestSpecial(String S) {
        int count = 0, i = 0;
        List<String> res = new ArrayList<String>();
        for (int j = 0; j < S.length(); ++j) {
          if (S.charAt(j) == '1') count++;
          else count--;
          if (count == 0) {
            res.add('1' + makeLargestSpecial(S.substring(i + 1, j)) + '0');
            i = j + 1;
          }
        }
        Collections.sort(res, Collections.reverseOrder());
        return String.join("", res);
    }
}
// @lc code=end
