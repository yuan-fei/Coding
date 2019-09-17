/*
 * @lc app=leetcode id=1189 lang=java
 *
 * [1189] Maximum Number of Balloons
 *
 * https://leetcode.com/problems/maximum-number-of-balloons/description/
 *
 * algorithms
 * Easy (62.68%)
 * Total Accepted:    4.6K
 * Total Submissions: 7.2K
 * Testcase Example:  '"nlaebolko"'
 *
 * Given a string text, you want to use the characters of text to form as many
 * instances of the word "balloon" as possible.
 * 
 * You can use each character in text at most once. Return the maximum number
 * of instances that can be formed.
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: text = "nlaebolko"
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: text = "leetcode"
 * Output: 0
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= text.length <= 10^4
 * text consists of lower case English letters only.
 * 
 */
class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26];
        for(char c : text.toCharArray()){
        	cnt[c - 'a']++;
        }
        int min = cnt['b' - 'a'];
        min = Math.min(min, cnt['a' - 'a']);
        min = Math.min(min, cnt['l' - 'a']/2);
        min = Math.min(min, cnt['o' - 'a']/2);
        min = Math.min(min, cnt['n' - 'a']);
        return min;
    }
}
