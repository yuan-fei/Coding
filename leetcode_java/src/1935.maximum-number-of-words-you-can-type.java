/*
 * @lc app=leetcode id=1935 lang=java
 *
 * [1935] Maximum Number of Words You Can Type
 *
 * https://leetcode.com/problems/maximum-number-of-words-you-can-type/description/
 *
 * algorithms
 * Easy (74.03%)
 * Likes:    78
 * Dislikes: 3
 * Total Accepted:    12.2K
 * Total Submissions: 16.5K
 * Testcase Example:  '"hello world"\n"ad"'
 *
 * There is a malfunctioning keyboard where some letter keys do not work. All
 * other keys on the keyboard work properly.
 * 
 * Given a string text of words separated by a single space (no leading or
 * trailing spaces) and a string brokenLetters of all distinct letter keys that
 * are broken, return the number of words in text you can fully type using this
 * keyboard.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: text = "hello world", brokenLetters = "ad"
 * Output: 1
 * Explanation: We cannot type "world" because the 'd' key is broken.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: text = "leet code", brokenLetters = "lt"
 * Output: 1
 * Explanation: We cannot type "leet" because the 'l' and 't' keys are
 * broken.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: text = "leet code", brokenLetters = "e"
 * Output: 0
 * Explanation: We cannot type either word because the 'e' key is broken.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= text.length <= 10^4
 * 0 <= brokenLetters.length <= 26
 * text consists of words separated by a single space without any leading or
 * trailing spaces.
 * Each word only consists of lowercase English letters.
 * brokenLetters consists of distinct lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        boolean[] broken = new boolean[26];
        for(char c : brokenLetters.toCharArray()){
            broken[c - 'a'] = true;
        } 
        int cnt = 0;
        for(String t : text.split(" ")){
            boolean bad = false;
            for(char c : t.toCharArray()){
                if(broken[c - 'a']){
                    bad = true;
                    break;
                }
            }
            if(!bad){
                cnt++;
            }
        }
        return cnt;
    }
}
// @lc code=end
