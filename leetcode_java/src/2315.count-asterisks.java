/*
 * @lc app=leetcode id=2315 lang=java
 *
 * [2315] Count Asterisks
 *
 * https://leetcode.com/problems/count-asterisks/description/
 *
 * algorithms
 * Easy (80.03%)
 * Likes:    105
 * Dislikes: 14
 * Total Accepted:    15.4K
 * Total Submissions: 19.2K
 * Testcase Example:  '"l|*e*et|c**o|*de|"'
 *
 * You are given a string s, where every two consecutive vertical bars '|' are
 * grouped into a pair. In other words, the 1^st and 2^nd '|' make a pair, the
 * 3^rd and 4^th '|' make a pair, and so forth.
 * 
 * Return the number of '*' in s, excluding the '*' between each pair of '|'.
 * 
 * Note that each '|' will belong to exactly one pair.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "l|*e*et|c**o|*de|"
 * Output: 2
 * Explanation: The considered characters are underlined: "l|*e*et|c**o|*de|".
 * The characters between the first and second '|' are excluded from the
 * answer.
 * Also, the characters between the third and fourth '|' are excluded from the
 * answer.
 * There are 2 asterisks considered. Therefore, we return 2.
 * 
 * Example 2:
 * 
 * 
 * Input: s = "iamprogrammer"
 * Output: 0
 * Explanation: In this example, there are no asterisks in s. Therefore, we
 * return 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "yo|uar|e**|b|e***au|tifu|l"
 * Output: 5
 * Explanation: The considered characters are underlined:
 * "yo|uar|e**|b|e***au|tifu|l". There are 5 asterisks considered. Therefore,
 * we return 5.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters, vertical bars '|', and asterisks
 * '*'.
 * s contains an even number of vertical bars '|'.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int countAsterisks(String s) {
        int idx = 0;
        int ret = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '|'){
                idx++;
            }
            if(idx % 2 == 0 && s.charAt(i) == '*'){
                ret++;
            }
        }
        return ret;
    }
}
// @lc code=end
