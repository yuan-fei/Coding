/*
 * @lc app=leetcode id=3084 lang=java
 *
 * [3084] Count Substrings Starting and Ending with Given Character
 *
 * https://leetcode.com/problems/count-substrings-starting-and-ending-with-given-character/description/
 *
 * algorithms
 * Medium (48.75%)
 * Likes:    127
 * Dislikes: 7
 * Total Accepted:    39.8K
 * Total Submissions: 81.4K
 * Testcase Example:  '"abada"\n"a"'
 *
 * You are given a string s and a character c. Return the total number of
 * substrings of s that start and end with c.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abada", c = "a"
 * 
 * Output: 6
 * 
 * Explanation: Substrings starting and ending with "a" are: "abada", "abada",
 * "abada", "abada", "abada", "abada".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "zzz", c = "z"
 * 
 * Output: 6
 * 
 * Explanation: There are a total of 6 substrings in s and all start and end
 * with "z".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s and c consist only of lowercase English letters.
 * 
 * 
 */

// @lc code=start

import java.util.stream.IntStream;

class Solution {
    public long countSubstrings(String s, char c) {
        int n = s.length();
        long cnt = IntStream.range(0, n).filter(i -> s.charAt(i) == c).count();
        return cnt * (cnt + 1) / 2;
    }
}
// @lc code=end
