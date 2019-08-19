/*
 * @lc app=leetcode id=1160 lang=java
 *
 * [1160] Find Words That Can Be Formed by Characters
 *
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/description/
 *
 * algorithms
 * Easy (71.97%)
 * Total Accepted:    4.4K
 * Total Submissions: 6.1K
 * Testcase Example:  '["cat","bt","hat","tree"]\n"atach"'
 *
 * You are given an array of strings words and a string chars.
 * 
 * A string is good if it can be formed by characters from chars (each
 * character can only be used once).
 * 
 * Return the sum of lengths of all good strings in words.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["cat","bt","hat","tree"], chars = "atach"
 * Output: 6
 * Explanation: 
 * The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 =
 * 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * Output: 10
 * Explanation: 
 * The strings that can be formed are "hello" and "world" so the answer is 5 +
 * 5 = 10.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= words.length <= 1000
 * 1 <= words[i].length, chars.length <= 100
 * All strings contain lowercase English letters only.
 * 
 */
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] target = new int[26];
        for(int c: chars.toCharArray()){
            target[c-'a']++;
        }
        int ans = 0;
        for(String w: words){
            if(exists(w, target)){
                ans+=w.length();
            }
        }
        return ans;
    }
    
    boolean exists(String w, int[] target){
        int[] source = new int[26];
        for(char c: w.toCharArray()){
            source[c-'a']++;
            if(source[c-'a']>target[c-'a']){
                return false;
            }
        }
        return true;
    }
}
