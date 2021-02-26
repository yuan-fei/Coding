/*
 * @lc app=leetcode id=1763 lang=java
 *
 * [1763] Longest Nice Substring
 *
 * https://leetcode.com/problems/longest-nice-substring/description/
 *
 * algorithms
 * Easy (61.41%)
 * Likes:    71
 * Dislikes: 88
 * Total Accepted:    5.9K
 * Total Submissions: 9.7K
 * Testcase Example:  '"YazaAay"'
 *
 * A string s is nice if, for every letter of the alphabet that s contains, it
 * appears both in uppercase and lowercase. For example, "abABB" is nice
 * because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not
 * because 'b' appears, but 'B' does not.
 * 
 * Given a string s, return the longest substring of s that is nice. If there
 * are multiple, return the substring of the earliest occurrence. If there are
 * none, return an empty string.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the
 * alphabet in s, and both 'A' and 'a' appear.
 * "aAa" is the longest nice substring.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "Bb"
 * Output: "Bb"
 * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The
 * whole string is a substring.
 * 
 * Example 3:
 * 
 * 
 * Input: s = "c"
 * Output: ""
 * Explanation: There are no nice substrings.
 * 
 * Example 4:
 * 
 * 
 * Input: s = "dDzeE"
 * Output: "dD"
 * Explanation: Both "dD" and "eE" are the longest nice substrings.
 * As there are multiple longest nice substrings, return "dD" since it occurs
 * earlier.
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 100
 * s consists of uppercase and lowercase English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestNiceSubstring(String s) {
    	int n = s.length();
    	int max = 0;
    	String ans = "";
        for(int i = 0; i < n; i++){
        	for(int j = i + 1; j < n; j++){
        		if(isNice(s.substring(i, j + 1)) && max < j - i + 1){
        			max = j - i + 1;
        			ans = s.substring(i, j + 1);
        		}
        	}
        }
        return ans;
    }

    boolean isNice(String s){
    	boolean[] lowercase = new boolean[26];
    	boolean[] uppercase = new boolean[26];
    	for(int i = 0; i < s.length(); i++){
    		char c = s.charAt(i);
    		if( 'a' <= c && c <= 'z'){
    			lowercase[c - 'a'] = true;
    		}
    		if( 'A' <= c && c <= 'Z'){
    			uppercase[c - 'A'] = true;
    		}
    	}
    	for(int i = 0; i < 26; i++){
    		if(lowercase[i] != uppercase[i]){
    			return false;
    		}
    	}
    	return true;
    }
}
// @lc code=end
