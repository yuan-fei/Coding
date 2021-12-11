/*
 * @lc app=leetcode id=720 lang=java
 *
 * [720] Longest Word in Dictionary
 *
 * https://leetcode.com/problems/longest-word-in-dictionary/description/
 *
 * algorithms
 * Medium (50.35%)
 * Likes:    1159
 * Dislikes: 1222
 * Total Accepted:    101.5K
 * Total Submissions: 201.5K
 * Testcase Example:  '["w","wo","wor","worl","world"]'
 *
 * Given an array of strings words representing an English Dictionary, return
 * the longest word in words that can be built one character at a time by other
 * words in words.
 * 
 * If there is more than one possible answer, return the longest word with the
 * smallest lexicographical order. If there is no answer, return the empty
 * string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w",
 * "wo", "wor", and "worl".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the
 * dictionary. However, "apple" is lexicographically smaller than "apply".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestWord(String[] words) {
        Set<String> s = new HashSet<>();
        for(String w : words){
            s.add(w);
        }
        String ret = "";
        for(String w : s){
            boolean good = true;
            for(int i = 1; i <= w.length(); i++){
                if(!s.contains(w.substring(0, i))){
                    good = false;
                    break;
                }
            }
            if(good){
                if(ret.length() < w.length()){
                    ret = w;
                }
                if(ret.length() == w.length() && ret.compareTo(w) > 0){
                    ret = w;
                }
            }
        }
        return ret;
    }
}
// @lc code=end
