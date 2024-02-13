/*
 * @lc app=leetcode id=2745 lang=java
 *
 * [2745] Construct the Longest New String
 *
 * https://leetcode.com/problems/construct-the-longest-new-string/description/
 *
 * algorithms
 * Medium (52.57%)
 * Likes:    288
 * Dislikes: 20
 * Total Accepted:    17.3K
 * Total Submissions: 32.7K
 * Testcase Example:  '2\n5\n1'
 *
 * You are given three integers x, y, and z.
 * 
 * You have x strings equal to "AA", y strings equal to "BB", and z strings
 * equal to "AB". You want to choose some (possibly all or none) of these
 * strings and concatenate them in some order to form a new string. This new
 * string must not contain "AAA" or "BBB" as a substring.
 * 
 * Return the maximum possible length of the new string.
 * 
 * A substring is a contiguous non-empty sequence of characters within a
 * string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 2, y = 5, z = 1
 * Output: 12
 * Explanation: We can concactenate the strings "BB", "AA", "BB", "AA", "BB",
 * and "AB" in that order. Then, our new string is "BBAABBAABBAB". 
 * That string has length 12, and we can show that it is impossible to
 * construct a string of longer length.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = 3, y = 2, z = 2
 * Output: 14
 * Explanation: We can concactenate the strings "AB", "AB", "AA", "BB", "AA",
 * "BB", and "AA" in that order. Then, our new string is "ABABAABBAABBAA". 
 * That string has length 14, and we can show that it is impossible to
 * construct a string of longer length.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= x, y, z <= 50
 * 
 * 
 */

// @lc code=start
class Solution {
    public int longestString(int x, int y, int z) {
        int minXY = Math.min(x, y);
        int maxXY = Math.max(x, y);
        return 2 * (z + minXY + Math.min(minXY + 1, maxXY));
    }
}
// @lc code=end
