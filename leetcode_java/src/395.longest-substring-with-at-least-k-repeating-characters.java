/*
 * @lc app=leetcode id=395 lang=java
 *
 * [395] Longest Substring with At Least K Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
 *
 * algorithms
 * Medium (40.60%)
 * Likes:    1245
 * Dislikes: 106
 * Total Accepted:    71.3K
 * Total Submissions: 175.5K
 * Testcase Example:  '"aaabb"\n3'
 *
 * 
 * Find the length of the longest substring T of a given string (consists of
 * lowercase letters only) such that every character in T appears no less than
 * k times.
 * 
 * 
 * Example 1:
 * 
 * Input:
 * s = "aaabb", k = 3
 * 
 * Output:
 * 3
 * 
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * s = "ababbc", k = 2
 * 
 * Output:
 * 5
 * 
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is
 * repeated 3 times.
 * 
 * 
 */

// @lc code=start
class Solution {
	
    public int longestSubstring(String s, int k) {
    	// System.out.println("start: "+s + k);
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	int result = 0;
    	int[] cnt = new int[26];
        for(char c : s.toCharArray()){
        	cnt[c - 'a']++;
        }
        boolean hasIllegalChar = false;
        for(int x : cnt){
        	if(x > 0 && x < k){
        		hasIllegalChar = true;
        		break;
        	}
        }
        if(!hasIllegalChar){
        	// System.out.println(s.length());
        	return s.length();
        }
        int start = 0;
        for(int i = 0; i < s.length(); i++){
        	if(cnt[s.charAt(i) - 'a'] < k){
        		result = Math.max(result, longestSubstring(s.substring(start, i), k));
        		start = i + 1;
        	}
        }
        result = Math.max(result, longestSubstring(s.substring(start, s.length()), k));
        return result;
    }

    


}
// @lc code=end
