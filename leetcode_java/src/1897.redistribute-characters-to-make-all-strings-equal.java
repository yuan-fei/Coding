/*
 * @lc app=leetcode id=1897 lang=java
 *
 * [1897] Redistribute Characters to Make All Strings Equal
 *
 * https://leetcode.com/problems/redistribute-characters-to-make-all-strings-equal/description/
 *
 * algorithms
 * Easy (57.94%)
 * Likes:    82
 * Dislikes: 17
 * Total Accepted:    9.7K
 * Total Submissions: 16.7K
 * Testcase Example:  '["abc","aabc","bc"]'
 *
 * You are given an array of strings words (0-indexed).
 * 
 * In one operation, pick two distinct indices i and j, where words[i] is a
 * non-empty string, and move any character from words[i] to any position in
 * words[j].
 * 
 * Return true if you can make every string in words equal using any number of
 * operations, and false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: words = ["abc","aabc","bc"]
 * Output: true
 * Explanation: Move the first 'a' in words[1] to the front of words[2],
 * to make words[1] = "abc" and words[2] = "abc".
 * All the strings are now equal to "abc", so return true.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: words = ["ab","a"]
 * Output: false
 * Explanation: It is impossible to make all the strings equal using the
 * operation.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean makeEqual(String[] words) {
    	int[] cnt = new int[26];
    	for(String w : words){
    		for(char c : w.toCharArray()){
    			cnt[c - 'a']++;
    		}
    	}
    	for(int c : cnt){
    		if(c % words.length != 0){
    			return false;
    		}
    	}
    	return true;
    }
}
// @lc code=end
