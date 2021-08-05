/*
 * @lc app=leetcode id=1941 lang=java
 *
 * [1941] Check if All Characters Have Equal Number of Occurrences
 *
 * https://leetcode.com/problems/check-if-all-characters-have-equal-number-of-occurrences/description/
 *
 * algorithms
 * Easy (76.63%)
 * Likes:    67
 * Dislikes: 2
 * Total Accepted:    11.5K
 * Total Submissions: 15.1K
 * Testcase Example:  '"abacbc"'
 *
 * Given a string s, return true if s is a good string, or false otherwise.
 * 
 * A string s is good if all the characters that appear in s have the same
 * number of occurrences (i.e., the same frequency).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "abacbc"
 * Output: true
 * Explanation: The characters that appear in s are 'a', 'b', and 'c'. All
 * characters occur 2 times in s.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "aaabb"
 * Output: false
 * Explanation: The characters that appear in s are 'a' and 'b'.
 * 'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of
 * times.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean areOccurrencesEqual(String s) {
        int[] freq = new int[26];
        for(char c : s.toCharArray()){
            freq[c - 'a']++;
        }
        int cnt = 0;
        for(int x : freq){
            if(x != 0){
                if(cnt == 0){
                    cnt = x;    
                }
                if(cnt != x){
                    return false;
                }
            }
        }
        return true;
    }
}
// @lc code=end
