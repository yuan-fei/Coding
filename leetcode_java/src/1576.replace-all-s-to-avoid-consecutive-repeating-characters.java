/*
 * @lc app=leetcode id=1576 lang=java
 *
 * [1576] Replace All ?'s to Avoid Consecutive Repeating Characters
 *
 * https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/description/
 *
 * algorithms
 * Easy (46.49%)
 * Likes:    29
 * Dislikes: 44
 * Total Accepted:    7.3K
 * Total Submissions: 15.8K
 * Testcase Example:  '"?zs"'
 *
 * Given a string s containing only lower case English letters and the '?'
 * character, convert all the '?' characters into lower case letters such that
 * the final string does not contain any consecutive repeating characters. You
 * cannot modify the non '?' characters.
 * 
 * It is guaranteed that there are no consecutive repeating characters in the
 * given string except for '?'.
 * 
 * Return the final string after all the conversions (possibly zero) have been
 * made. If there is more than one solution, return any of them. It can be
 * shown that an answer is always possible with the given constraints.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem. From "azs" to "yzs",
 * all are valid. Only "z" is an invalid modification as the string will
 * consist of consecutive repeating characters in "zzs".
 * 
 * Example 2:
 * 
 * 
 * Input: s = "ubv?w"
 * Output: "ubvaw"
 * Explanation: There are 24 solutions for this problem. Only "v" and "w" are
 * invalid modifications as the strings will consist of consecutive repeating
 * characters in "ubvvw" and "ubvww".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "j?qg??b"
 * Output: "jaqgacb"
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "??yw?ipkj?"
 * Output: "acywaipkja"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 
 * 1 <= s.length <= 100
 * 
 * 
 * s contains only lower case English letters and '?'.
 * 
 * 
 * 
 */

// @lc code=start
class Solution {
    public String modifyString(String s) {
    	String rt = "";
        for(int i = 0; i < s.length(); i++){
        	int c = s.charAt(i) - 'a';
        	if(s.charAt(i) == '?'){
        		c = 0;
				if(i > 0){
					c = (rt.charAt(i - 1) - 'a' + 1) % 26;
				}
				if(i + 1 < s.length()){
					if(c == s.charAt(i + 1) - 'a'){
						c = (c + 1) % 26;
					}
				}
        	}
        	rt += (char)(c + 'a');
        }
        return rt;
    }
}
// @lc code=end
