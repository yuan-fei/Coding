/*
 * @lc app=leetcode id=387 lang=java
 *
 * [387] First Unique Character in a String
 *
 * https://leetcode.com/problems/first-unique-character-in-a-string/description/
 *
 * algorithms
 * Easy (51.90%)
 * Likes:    1699
 * Dislikes: 112
 * Total Accepted:    458.1K
 * Total Submissions: 873.1K
 * Testcase Example:  '"leetcode"'
 *
 * 
 * Given a string, find the first non-repeating character in it and return it's
 * index. If it doesn't exist, return -1.
 * 
 * Examples:
 * 
 * s = "leetcode"
 * return 0.
 * 
 * s = "loveleetcode",
 * return 2.
 * 
 * 
 * 
 * 
 * Note: You may assume the string contain only lowercase letters.
 * 
 */

// @lc code=start
class Solution {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        for(char c : s.toCharArray()){
        	cnt[c - 'a']++;
        }
        for(int i = 0; i < s.length(); i++){
        	if(cnt[s.charAt(i) - 'a'] == 1){
        		return i;
        	}
        }
        return -1;
    }
}
// @lc code=end
