/*
 * @lc app=leetcode id=3110 lang=java
 *
 * [3110] Score of a String
 *
 * https://leetcode.com/problems/score-of-a-string/description/
 *
 * algorithms
 * Easy (92.25%)
 * Likes:    686
 * Dislikes: 42
 * Total Accepted:    338.6K
 * Total Submissions: 368.2K
 * Testcase Example:  '"hello"'
 *
 * You are given a string s. The score of a string is defined as the sum of the
 * absolute difference between the ASCII values of adjacent characters.
 * 
 * Return the score of s.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "hello"
 * 
 * Output: 13
 * 
 * Explanation:
 * 
 * The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' =
 * 108, 'o' = 111. So, the score of s would be |104 - 101| + |101 - 108| + |108
 * - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "zaz"
 * 
 * Output: 50
 * 
 * Explanation:
 * 
 * The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the
 * score of s would be |122 - 97| + |97 - 122| = 25 + 25 = 50.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= s.length <= 100
 * s consists only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.stream.IntStream;

class Solution {
    public int scoreOfString(String s) {
        return IntStream.range(1, s.length()).map(i -> Math.abs(s.charAt(i) - s.charAt(i - 1))).sum();
    }
}
// @lc code=end
