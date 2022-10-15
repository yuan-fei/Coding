/*
 * @lc app=leetcode id=2434 lang=java
 *
 * [2434] Using a Robot to Print the Lexicographically Smallest String
 *
 * https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/description/
 *
 * algorithms
 * Medium (30.42%)
 * Likes:    193
 * Dislikes: 87
 * Total Accepted:    6K
 * Total Submissions: 19.8K
 * Testcase Example:  '"zza"'
 *
 * You are given a string s and a robot that currently holds an empty string t.
 * Apply one of the following operations until s and t are both empty:
 * 
 * 
 * Remove the first character of a string s and give it to the robot. The robot
 * will append this character to the string t.
 * Remove the last character of a string t and give it to the robot. The robot
 * will write this character on paper.
 * 
 * 
 * Return the lexicographically smallest string that can be written on the
 * paper.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "zza"
 * Output: "azz"
 * Explanation: Let p denote the written string.
 * Initially p="", s="zza", t="".
 * Perform first operation three times p="", s="", t="zza".
 * Perform second operation three times p="azz", s="", t="".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "bac"
 * Output: "abc"
 * Explanation: Let p denote the written string.
 * Perform first operation twice p="", s="c", t="ba". 
 * Perform second operation twice p="ab", s="c", t="". 
 * Perform first operation p="ab", s="", t="c". 
 * Perform second operation p="abc", s="", t="".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "bdda"
 * Output: "addb"
 * Explanation: Let p denote the written string.
 * Initially p="", s="bdda", t="".
 * Perform first operation four times p="", s="", t="bdda".
 * Perform second operation four times p="addb", s="", t="".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of only English lowercase letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String robotWithString(String s) {
        if (s == null || s.length() < 1) return "";
        int[] freq = new int[26];
        for (char c: s.toCharArray()) { // first loop
            freq[c - 'a']++;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(); // the final result
        for (char c: s.toCharArray()) { // second loop
            stack.add(c);
            freq[c - 'a']--;
            while (!stack.isEmpty()) {
                char temp = stack.peek();
                if (hasSmaller(temp, freq)) break; // check if there is a smaller character in the rest of the string compared to top character of the stack
                sb.append(stack.pop());
            }
        }
        return sb.toString();
    }
    
    private boolean hasSmaller(char c, int[] freq) {
        int ind = (int)(c - 'a');
        for (int i = 0; i < ind; i++) {
            if (freq[i] > 0) return true;
        }
        return false;
    }
}
// @lc code=end
