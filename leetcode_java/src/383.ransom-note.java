/*
 * @lc app=leetcode id=383 lang=java
 *
 * [383] Ransom Note
 *
 * https://leetcode.com/problems/ransom-note/description/
 *
 * algorithms
 * Easy (51.48%)
 * Likes:    439
 * Dislikes: 155
 * Total Accepted:    137.7K
 * Total Submissions: 267.5K
 * Testcase Example:  '"a"\n"b"'
 *
 * 
 * Given an arbitrary ransom note string and another string containing letters
 * from all the magazines, write a function that will return true if the
 * ransom 
 * note can be constructed from the magazines ; otherwise, it will return
 * false. 
 * 
 * 
 * Each letter in the magazine string can only be used once in your ransom
 * note.
 * 
 * 
 * Note:
 * You may assume that both strings contain only lowercase letters.
 * 
 * 
 * 
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for(char c : ransomNote.toCharArray()){
            cnt[c - 'a']++;
        }
        for(char c : magazine.toCharArray()){
            cnt[c - 'a']--;
        }
        for(int c : cnt){
            if(c > 0){
                return false;
            }
        }
        return true;
    }
}
// @lc code=end

