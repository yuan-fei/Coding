/*
 * @lc app=leetcode id=3174 lang=java
 *
 * [3174] Clear Digits
 *
 * https://leetcode.com/problems/clear-digits/description/
 *
 * algorithms
 * Easy (73.53%)
 * Likes:    628
 * Dislikes: 26
 * Total Accepted:    246.8K
 * Total Submissions: 298.8K
 * Testcase Example:  '"abc"'
 *
 * You are given a string s.
 * 
 * Your task is to remove all digits by doing this operation repeatedly:
 * 
 * 
 * Delete the first digit and the closest non-digit character to its left.
 * 
 * 
 * Return the resulting string after removing all digits.
 * 
 * Note that the operation cannot be performed on a digit that does not have
 * any non-digit character to its left.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abc"
 * 
 * Output: "abc"
 * 
 * Explanation:
 * 
 * There is no digit in the string.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "cb34"
 * 
 * Output: ""
 * 
 * Explanation:
 * 
 * First, we apply the operation on s[2], and s becomes "c4".
 * 
 * Then we apply the operation on s[1], and s becomes "".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s consists only of lowercase English letters and digits.
 * The input is generated such that it is possible to delete all digits.
 * 
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

class Solution {
    public String clearDigits(String s) {
        // abc1d23 -> a
        // a1bcd23 -> b
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                dq.pop();
            } else {
                dq.push(c);
            }
        }

        String reversedAnswer = dq.stream().map(String::valueOf).collect(Collectors.joining());
        return new StringBuilder(reversedAnswer).reverse().toString();

    }
}
// @lc code=end
