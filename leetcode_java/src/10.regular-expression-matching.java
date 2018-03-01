/*
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (24.32%)
 * Total Accepted:    185.2K
 * Total Submissions: 760.7K
 * Testcase Example:  '"aa"\n"a"'
 *
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
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
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * 
 */
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] state = new boolean[s.length() + 1][p.length() + 1];
        state[0][0] = true;
        for (int i = 1; i <= s.length(); i++) {
        	state[i][0] = false;
        }
        for (int i = 2; i <= p.length(); i++) {
        	state[0][i] = (state[0][i - 2] && p.charAt(i - 1) == '*');
        }


        for (int i = 1; i <= s.length(); i++) {
        	for (int j = 1; j <= p.length(); j++) {
        		state[i][j] = false;
        		if(p.charAt(j - 1) == '*'){
        			state[i][j] = (state[i][j - 2] || (state[i - 1][j] && matchSimple(p.charAt(j - 2), s.charAt(i - 1))));
        		}
        		else{
        			state[i][j] = (state[i - 1][j - 1] && matchSimple(p.charAt(j - 1), s.charAt(i - 1)));	
        		}
        	}
        }
        return state[s.length()][p.length()];
    }

    private boolean matchSimple(char p, char s){
    	return s == p || p == '.';
    }
}
