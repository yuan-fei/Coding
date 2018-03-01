/*
 * [44] Wildcard Matching
 *
 * https://leetcode.com/problems/wildcard-matching/description/
 *
 * algorithms
 * Hard (20.86%)
 * Total Accepted:    117.4K
 * Total Submissions: 561.9K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch1(s, p);
    }

    public boolean isMatch1(String s, String p) {
    	if(s == null || p == null){
    		return false;
    	}
        boolean[][] matchState = new boolean[s.length() + 1][p.length() + 1];
        matchState[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
        	matchState[i][0] = false;
        }

        for (int i = 1; i <= p.length(); i++) {
        	matchState[0][i] = (matchState[0][i - 1] && p.charAt(i - 1) == '*' ) ;
        }

        for (int i = 1; i <= s.length(); i++) {
        	for (int j = 1; j <= p.length(); j++) {
        		if(p.charAt(j - 1) == '*'){
        			matchState[i][j] = (matchState[i - 1][j] || matchState[i - 1][j - 1] || matchState[i][j - 1]);	
        		}
        		else if(p.charAt(j - 1) == '?'){
        			matchState[i][j] = matchState[i - 1][j - 1];	
        		}
        		else{
        			matchState[i][j] = (matchState[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1)));
        		}
        	}
        }
        return matchState[s.length()][p.length()];
    }
}
