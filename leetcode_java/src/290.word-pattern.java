/*
 * @lc app=leetcode id=290 lang=java
 *
 * [290] Word Pattern
 *
 * https://leetcode.com/problems/word-pattern/description/
 *
 * algorithms
 * Easy (36.06%)
 * Likes:    857
 * Dislikes: 120
 * Total Accepted:    166.4K
 * Total Submissions: 461.4K
 * Testcase Example:  '"abba"\n"dog cat cat dog"'
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a
 * letter in pattern and a non-empty word in str.
 * 
 * Example 1:
 * 
 * 
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * 
 * Example 2:
 * 
 * 
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * 
 * Example 3:
 * 
 * 
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * 
 * Example 4:
 * 
 * 
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * 
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains
 * lowercase letters that may be separated by a single space.
 * 
 */

// @lc code=start
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> m1 = new HashMap<>();
        Map<String, Character> m2 = new HashMap<>();
        char[] p = pattern.toCharArray();
        String[] words = str.split("\\s");
        if(p.length != words.length){
        	return false;
        }
        for(int i = 0; i < p.length; i++){
        	if(!m1.containsKey(p[i])){
        		m1.put(p[i], words[i]);
        		if(!m2.containsKey(words[i])){
        			m2.put(words[i], p[i]);
        		}
        	}
        	if(!m1.get(p[i]).equals(words[i]) || m2.get(words[i]) != p[i]){
        		return false;
        	}
        }
        return true;
    }
}
// @lc code=end
