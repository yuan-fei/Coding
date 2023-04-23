/*
 * @lc app=leetcode id=859 lang=java
 *
 * [859] Buddy Strings
 *
 * https://leetcode.com/problems/buddy-strings/description/
 *
 * algorithms
 * Easy (29.14%)
 * Likes:    1748
 * Dislikes: 1104
 * Total Accepted:    142.7K
 * Total Submissions: 489.8K
 * Testcase Example:  '"ab"\n"ba"'
 *
 * Given two strings s and goal, return true if you can swap two letters in s
 * so the result is equal to goal, otherwise, return false.
 * 
 * Swapping letters is defined as taking two indices i and j (0-indexed) such
 * that i != j and swapping the characters at s[i] and s[j].
 * 
 * 
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "ab", goal = "ba"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is
 * equal to goal.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ab", goal = "ab"
 * Output: false
 * Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b',
 * which results in "ba" != goal.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "aa", goal = "aa"
 * Output: true
 * Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is
 * equal to goal.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, goal.length <= 2 * 10^4
 * s and goal consist of lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }
        if(s.equals(goal)){
            return s.chars().distinct().count() != s.length();
        }
        List<Integer> diff = IntStream.range(0, s.length())
            .filter(i -> s.charAt(i) != goal.charAt(i)).boxed().toList();
        return diff.size() == 2 
            && s.charAt(diff.get(0)) == goal.charAt(diff.get(1)) 
            && s.charAt(diff.get(1)) == goal.charAt(diff.get(0));
    }
}
// @lc code=end
